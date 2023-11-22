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
}
