// Clase Tablero:
//     Atributos: Número de filas, número de columnas, matriz del tablero.
//     Métodos:
//         Método para cargar la configuración inicial del tablero.
//         Método para imprimir el estado actual del tablero.

package src;

import java.util.ArrayList;

public class Tablero {

    int filas;
    int columnas;
    char[][] matriz;
    ArrayList<Grupo> gruposDelTablero;
    
    public Tablero(int filas, int columnas, char[][] matriz){
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = matriz;
        calcularGrupos();
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
        tablero.moverFichasAbajo();
        tablero.moverColumnasVacias();
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
                    System.out.println("Grupo de color " + color + ":");
                    numFichas = identificarGrupo(matriz, visitado, i, j, color, numFichas, grupoPosible);
                    if(numFichas > 1){
                        System.out.println("Existe este Grupo con " + numFichas + " fichas.");

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

                        String movimiento = grupoPosible.generarMovimiento(grupoPosible);
                        System.out.println(movimiento);

                    }else{
                        System.out.println("No exite este Grupo");
                    }
                    System.out.println();
                }
            }
        }
    }

    private int identificarGrupo(char[][] matriz, boolean[][] visitado, int fila, int columna, char color, int numFichas, Grupo grupoPosible) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas || visitado[fila][columna] || matriz[fila][columna] != color) {
            return numFichas;
        }

        numFichas= 1;
        visitado[fila][columna] = true;
        System.out.print("(" + fila + "," + columna + ") ");
        int[] ficha = {fila, columna};
        grupoPosible.añadirFicha(ficha);

        int numFichasPorArriba = (identificarGrupo(matriz, visitado, fila - 1, columna, color, 0, grupoPosible)); 
        int numFichasPorDerecha = (identificarGrupo(matriz, visitado, fila, columna + 1, color, 0, grupoPosible));
        int numFichasPorAbajo = (identificarGrupo(matriz, visitado, fila + 1, columna, color, 0, grupoPosible)); 
        int numFichasPorIzquierda = (identificarGrupo(matriz, visitado, fila, columna - 1, color, 0, grupoPosible));
        
        numFichas = numFichas + numFichasPorAbajo + numFichasPorArriba + numFichasPorDerecha + numFichasPorIzquierda;
        return numFichas;
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
