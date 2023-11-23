// Clase Tablero:
//     Atributos: Número de filas, número de columnas, matriz del tablero.
//     Métodos:
//         Método para cargar la configuración inicial del tablero.
//         Método para imprimir el estado actual del tablero.

package src;

public class Tablero {

    int filas;
    int columnas;
    char[][] matriz;
    
    public Tablero(int filas, int columnas, char[][] matriz){
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = matriz;
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

    public static void imprimirTablero(Tablero tablero){
        char[][] matriz = tablero.getMatriz();

        // Imprimir encabezado de columnas
        System.out.print("  ");
        for (int j = 0; j < tablero.getColumnas(); j++) {
            System.out.print((j + 1) + " ");
        }
        System.out.println();

        // Imprimir filas y contenido de la matriz
        for (int i = 0; i < tablero.getFilas(); i++) {
            // Imprimir número de fila
            System.out.print((i + 1) + " ");

            // Imprimir contenido de la matriz
            for (int j = 0; j < tablero.getColumnas(); j++) {
                System.out.print(matriz[i][j] + " ");
            }

            // Salto de línea después de cada fila
            System.out.println();
        }
 
    }

    // Método para mover las fichas hacia abajo en cada columna
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

    // Método para comprimir las columnas hacia la izquierda
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
}
