// Clase Tablero:
//     Atributos: Número de filas, número de columnas, matriz del tablero.
//     Métodos:
//         Método para cargar la configuración inicial del tablero.
//         Método para imprimir el estado actual del tablero.

package src;

import java.util.ArrayList;
import java.util.Arrays;

public class Tablero {

    int filas;
    int columnas;
    char[][] matriz;
    ArrayList<Grupo> gruposDelTablero;
    
    public Tablero(int filas, int columnas, char[][] matriz){
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = matriz;
        this.gruposDelTablero = new ArrayList<>();
    }

    public Tablero(){
        this.gruposDelTablero = new ArrayList<>();
    }

    // Getter methods
    public int getFilas(){
        return this.filas;
    }

     public int getColumnas(){
        return this.columnas;
    }

     public char[][] getMatriz(){
        return this.matriz;
    }

    public ArrayList<Grupo> getGruposDelTablero() {
        return gruposDelTablero;
    }

    // Setter methods
    public void setFilas(int filas){
        this.filas = filas;
    }

     public void setColumnas(int columnas){
        this.columnas = columnas;
    }

     public void setMatriz(char[][] matriz){
        this.matriz = matriz;
    }

    public void comprimirTablero(Tablero tablero){
        // // ELIMINAR UNA VEZ SE FINALICE EL DESARROLLO.
        // System.out.println("TABLERO SIN COMPRIMIR:");
        // for (int i = 0; i < matriz.length; i++) {
        //     for (int j = 0; j < matriz[i].length; j++) {
        //         System.out.print(matriz[i][j] + " ");
        //     }
        //     System.out.println(); // Nueva línea para cada fila
        // }
        // System.out.println(); // Nueva línea entre matrices

        tablero.moverFichasAbajo();
        tablero.moverColumnasVacias();

        // // ELIMINAR UNA VEZ SE FINALICE EL DESARROLLO.
        // System.out.println("TABLERO COMPRIMIDO:");
        // for (int i = 0; i < matriz.length; i++) {
        //     for (int j = 0; j < matriz[i].length; j++) {
        //         System.out.print(matriz[i][j] + " ");
        //     }
        //     System.out.println(); // Nueva línea para cada fila
        // }
        // System.out.println(); // Nueva línea entre matrices
    }

    public void moverFichasAbajo() {
        for (int i = columnas - 1; i >= 0; i--) {       // Se recorre la matriz de abajo hacia arriba, columna a columna.
            for (int j = filas - 1; j >= 0; j--) {
                if(matriz[j][i] == 'X'){                // Si se encuentra un espacio se busca como rellenarlo.
                    for (int k = j - 1; k >= 0; k--){   // Si hay un vacio se recorre la columna hacia arriba.  
                        if(matriz[k][i] != 'X'){
                            matriz[j][i] = matriz[k][i];
                            matriz[k][i] = 'X';
                            break;
                        }
                    }
                }
            }
        }
    }

    public void moverColumnasVacias() {
        int filas = matriz.length;
        int columnas = matriz[0].length;
    
        for (int j = 0; j < columnas; j++) {
            boolean columnaVacia = true;
    
            // Verificar si la columna actual está vacía
            for (int i = 0; i < filas; i++) {
                if (matriz[i][j] != 'X') {
                    columnaVacia = false;
                    break;
                }
            }
    
            // Si la columna está vacía, mover las columnas restantes hacia la izquierda
            if (columnaVacia) {
                for (int k = j + 1; k < columnas; k++) {
                    for (int i = 0; i < filas; i++) {
                        matriz[i][k - 1] = matriz[i][k];
                    }
                }
    
                // Limpiar la última columna
                for (int i = 0; i < filas; i++) {
                    matriz[i][columnas - 1] = 'O';  
                }
    
                // Decrementar j para volver a verificar la nueva columna actual
                j--;
            }
        }
    }
 
   public void calcularGrupos(){
        boolean[][] visitado = new boolean[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!visitado[i][j] && matriz[i][j] != 'X' && matriz[i][j] != 'O') {
                    int numFichas = 0;
                    char color = matriz[i][j];
                    Grupo grupoPosible = new Grupo();
                    //System.out.println("Grupo de color " + color + ":");
                    numFichas = identificarGrupo(matriz, visitado, i, j, color, numFichas, grupoPosible);
                    if(numFichas > 1){
                        //System.out.println("Existe este Grupo con " + numFichas + " fichas.");

                        grupoPosible.setColor(color);
                        grupoPosible.setNumFichasEliminadas(numFichas);
                        grupoPosible.setPuntos((numFichas - 2)*(numFichas - 2)); 

                        ArrayList<int[]> fichasDelGrupoArrayList = grupoPosible.getListaFichas();
                        int[] fichaMovimiento = fichasDelGrupoArrayList.get(0);
                        double distanciaMinima = Math.sqrt(Math.pow(fichaMovimiento[0] - filas, 2) + Math.pow(fichaMovimiento[1] - 0, 2));

                        for(int k = 1; k < grupoPosible.getNumFichasEliminadas(); k++ ){
                            int[] fichaAComprobar = fichasDelGrupoArrayList.get(k);
                            double distanciaAComprobar = Math.sqrt(Math.pow(fichaAComprobar[0] - filas, 2) + Math.pow(fichaAComprobar[1] - 0, 2));
                            
                            if(distanciaAComprobar < distanciaMinima){
                                distanciaMinima = distanciaAComprobar;
                                fichaMovimiento = fichaAComprobar;
                            }
                        }

                        grupoPosible.setCoordenadaX(fichaMovimiento[0]);
                        grupoPosible.setCoordenadaY(fichaMovimiento[1]);

                        gruposDelTablero.add(grupoPosible);

                        // Trazas para ver que hace la funcion.
                        // String movimiento = grupoPosible.generarMovimiento(grupoPosible, filas);
                        // System.out.println(movimiento);

                    }else{
                        //System.out.println("No exite este Grupo");
                    }
                    //System.out.println();
                }
            }
        }
        ordenarGruposLexicograficamente();
    }

    private int identificarGrupo(char[][] matriz, boolean[][] visitado, int fila, int columna, char color, int numFichas, Grupo grupoPosible) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas || visitado[fila][columna] || matriz[fila][columna] != color) {
            return numFichas;
        }

        numFichas= 1;
        visitado[fila][columna] = true;
        //System.out.print("(" + fila + "," + columna + ") ");
        int[] ficha = {fila, columna};
        grupoPosible.añadirFicha(ficha);

        int numFichasPorArriba = (identificarGrupo(matriz, visitado, fila - 1, columna, color, 0, grupoPosible)); 
        int numFichasPorDerecha = (identificarGrupo(matriz, visitado, fila, columna + 1, color, 0, grupoPosible));
        int numFichasPorAbajo = (identificarGrupo(matriz, visitado, fila + 1, columna, color, 0, grupoPosible)); 
        int numFichasPorIzquierda = (identificarGrupo(matriz, visitado, fila, columna - 1, color, 0, grupoPosible));
        
        numFichas = numFichas + numFichasPorAbajo + numFichasPorArriba + numFichasPorDerecha + numFichasPorIzquierda;
        return numFichas;
    }

    public void borrarGrupoSeleccionado(Grupo grupo){
        for (int i = 0; i < gruposDelTablero.size(); i++) {
            if(gruposDelTablero.get(i).equals(grupo)){
                gruposDelTablero.get(i).setProbado(true);
            }
        }

        for (int i = 0; i < grupo.getListaFichas().size(); i++) {
            int[] fichaEliminada = grupo.getListaFichas().get(i);

            this.matriz[fichaEliminada[0]][fichaEliminada[1]] = 'X';
        }
    }

    public void copiar(Tablero tableroOriginal) {
        this.setColumnas(tableroOriginal.getColumnas());
        this.setFilas(tableroOriginal.getFilas());

        // Realizar una copia profunda de la matriz
        char[][] matrizOriginal = tableroOriginal.getMatriz();
        char[][] nuevaMatriz = new char[matrizOriginal.length][];
        for (int i = 0; i < matrizOriginal.length; i++) {
            nuevaMatriz[i] = Arrays.copyOf(matrizOriginal[i], matrizOriginal[i].length);
        }

        this.setMatriz(nuevaMatriz);
    }

    /* 
    public void ordenarGruposLexicograficamente() {
        for (int i = 0; i < gruposDelTablero.size() - 1; i++) {
            for (int j = i + 1; j < gruposDelTablero.size(); j++) {
                Grupo grupo1 = gruposDelTablero.get(i);
                Grupo grupo2 = gruposDelTablero.get(j);

                // Comparar por coordenada X
                if (grupo1.getCoordenadaX() > grupo2.getCoordenadaX()) {
                    intercambiarGrupos(i, j);
                } else if (grupo1.getCoordenadaX() == grupo2.getCoordenadaX()) {
                    // Si las coordenadas X son iguales, comparar por coordenada Y
                    if (grupo1.getCoordenadaY() > grupo2.getCoordenadaY()) {
                        intercambiarGrupos(i, j);
                    } else if (grupo1.getCoordenadaY() == grupo2.getCoordenadaY()) {
                        // Si las coordenadas X e Y son iguales, comparar por número de fichas eliminadas
                        if (grupo1.getNumFichasEliminadas() > grupo2.getNumFichasEliminadas()) {
                            intercambiarGrupos(i, j);
                        } else if (grupo1.getNumFichasEliminadas() == grupo2.getNumFichasEliminadas()) {
                            // Si las coordenadas X, Y y el número de fichas son iguales, comparar por color
                            if (grupo1.getColor() > grupo2.getColor()) {
                                intercambiarGrupos(i, j);
                            }
                        }
                    }
                }
            }
        }
    }

    */
    private void intercambiarGrupos(int indice1, int indice2) {
        Grupo temp = gruposDelTablero.get(indice1);
        gruposDelTablero.set(indice1, gruposDelTablero.get(indice2));
        gruposDelTablero.set(indice2, temp);
    }
    
    public void ordenarGruposLexicograficamente() {
        for (int i = 0; i < gruposDelTablero.size() - 1; i++) {
            for (int j = i + 1; j < gruposDelTablero.size(); j++) {
                Grupo grupo1 = gruposDelTablero.get(i);
                Grupo grupo2 = gruposDelTablero.get(j);
    
                // Comparar por coordenada X
                if (grupo1.getCoordenadaX() < grupo2.getCoordenadaX()) {
                    intercambiarGrupos(i, j);
                } else if (grupo1.getCoordenadaX() == grupo2.getCoordenadaX()) {
                    // Si las coordenadas X son iguales, comparar por coordenada Y
                    if (grupo1.getCoordenadaY() < grupo2.getCoordenadaY()) {
                        intercambiarGrupos(i, j);
                    } else if (grupo1.getCoordenadaY() == grupo2.getCoordenadaY()) {
                        // Si las coordenadas X e Y son iguales, comparar por número de fichas eliminadas
                        if (grupo1.getNumFichasEliminadas() < grupo2.getNumFichasEliminadas()) {
                            intercambiarGrupos(i, j);
                        } else if (grupo1.getNumFichasEliminadas() == grupo2.getNumFichasEliminadas()) {
                            // Si las coordenadas X, Y y el número de fichas son iguales, comparar por color
                            if (grupo1.getColor() < grupo2.getColor()) {
                                intercambiarGrupos(i, j);
                            }
                        }
                    }
                }
            }
        }
    
        for (int i = 0; i < gruposDelTablero.size(); i++) {
            // Después de ordenar los grupos por coordenadas, ordenar las listas de fichas de cada grupo
            ordenarListasDeFichasLexicograficamente(gruposDelTablero.get(i));
        }
    }
    
    private void ordenarListasDeFichasLexicograficamente(Grupo grupo) {
        ArrayList<int[]> listaFichas = grupo.getListaFichas();
        int n = listaFichas.size();
    
        // Implementación de un algoritmo de ordenación lexicográfica (selección)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] ficha1 = listaFichas.get(i);
                int[] ficha2 = listaFichas.get(j);
    
                // Comparar por coordenada X de la ficha o por coordenada Y en caso de empate en X
                if (ficha2[0] < ficha1[0] || (ficha2[0] == ficha1[0] && ficha2[1] < ficha1[1])) {
                    // Intercambiar fichas si están en el orden incorrecto
                    int[] temp = listaFichas.get(i);
                    listaFichas.set(i, listaFichas.get(j));
                    listaFichas.set(j, temp);
                }
            }
        }
    }
    
    
        

    public static void imprimirTablero(Tablero tablero){
        char[][] matriz = tablero.getMatriz();

        // Imprimir encabezado de columnas
        System.out.print("  ");
        for (int j = 0; j < tablero.getColumnas(); j++) {
            System.out.print((j) + " ");
        }
        System.out.println();

        // Imprimir filas y contenido de la matriz
        for (int i = 0; i < tablero.getFilas(); i++) {
            // Imprimir número de fila
            System.out.print((i) + " ");

            // Imprimir contenido de la matriz
            for (int j = 0; j < tablero.getColumnas(); j++) {
                System.out.print(matriz[i][j] + " ");
            }

            // Salto de línea después de cada fila
            System.out.println();
        }
 
    }
}
