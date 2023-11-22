import java.util.Scanner;

public class TicTac {
    public static void main(String[] args) throws Exception{

        // ENTRADA
        // Declaracion scanner para uso en la entrada de datos.
        Scanner sc = new Scanner(System.in);

        String primeraLinea = sc.nextLine();
        char[] primeraLineaEnCaracteres = primeraLinea.toCharArray();

        int n = primeraLineaEnCaracteres.length;

        // Condicion para el caso de una matriz 1x1
        if (n == 1) {                   
            if(primeraLinea == "x" || primeraLinea == "o")
                System.out.println(primeraLinea);      
            else
                System.out.println('x');   
                
            System.exit(0);
        }

        // Creacion del arreglo bidimensional que contendra la matriz.
        char[][] matriz = new char[n][n];

        // Se introduce la primera linea en la matriz.
        for (int i = 0; i < matriz.length; i++) {
            matriz[0][i] = primeraLineaEnCaracteres[i];
        }

        // Se añaden el resto de lineas.
        for (int i = 1; i < n; i++) {
            String lineaNueva = sc.nextLine();
            for (int j = 0; j < n; j++) {
                matriz[i][j] = lineaNueva.toCharArray()[j];
            }
            
        }

        sc.close();

        // Llamada a la funcion recursiva que realiza el backtracking para encontrar la solucion de la matriz.
        if(backtrack(matriz, n)){
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    System.out.print(matriz[i][j]);
                }
                System.out.println();
            }
        } else{
            System.out.println("No hay solución.");
        }

    }

    public static boolean backtrack(char[][] matriz, int n){
        // Buscamos una celda vacía (con valor '_')
        int fil = -1;
        int col = -1;
        boolean completa = true;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matriz[i][j] == '_') {
                    fil = i;
                    col = j;
                    completa = false;
                    break;
                }
            }
            if (!completa) {
                break;
            }
        }
        
        // Si no hay celdas vacías, hemos resuelto la matriz
        if (completa) {
            return true;
        }

        if(esValido(matriz, fil, col, 'x')){
            matriz[fil][col] = 'x';

            if(backtrack(matriz, n)){
                return true;
            }

            matriz[fil][col] = '_';

        }
        
        if (esValido(matriz, fil, col, 'o')){
            matriz[fil][col] = 'o';

            if(backtrack(matriz, n)){
                return true;
            }

            matriz[fil][col] = '_';
        }

        return false;
    }


    public static boolean esValido(char[][] matriz, int fil, int col, char letraPrueba){
        int contadorRepeticionesHorizontal = 0;
        int contadorRepeticionesVertical = 0;
        int OFila = -1;
        int OColumna = -1;
        boolean setCantidadOFila = false;
        boolean setCantidadOColumna = false;

        // Comprobacion no mas de dos repeticiones de la misma letra consecutivas.
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[fil][i] == letraPrueba){ // Se recorren todas las columnas.
                contadorRepeticionesHorizontal++;
                
                if(contadorRepeticionesHorizontal > 1){
                    return false;
                }
            
            } else if(!(i == col)){ // Obliga a que las repeticiones sean consecutivas.
                contadorRepeticionesHorizontal = 0;
            }
            
            if( matriz[i][col] == letraPrueba){// Se recorren todas las filas.
                contadorRepeticionesVertical++;

                if(contadorRepeticionesVertical > 1){
                    return false;
                }

            } else if(!(i == fil)){
                contadorRepeticionesVertical = 0;
            }


            // Comprobacion misma cantidad de o's en cada fila.
            int numOEnEstaFila = 0;
            int posicionAunDudosa = 0;
            boolean contieneBarraBaja = false;

            for (int j = 0; j < matriz.length; j++) {
                if(matriz[i][j] == '_') {
                    contieneBarraBaja = true;
                    posicionAunDudosa++;
                
                } else if(matriz[i][j] == 'o'){
                    numOEnEstaFila++;
                }
            }

            if(!contieneBarraBaja && !setCantidadOFila){
                OFila = numOEnEstaFila; 
                setCantidadOFila = true;
            
            } else if(setCantidadOFila){
                if(numOEnEstaFila > OFila || (numOEnEstaFila + posicionAunDudosa) < OFila)
                    return false;
            }


            // Comprobacion misma cantidad de o's en cada columna.
            int numOEnEstaColumna = 0;
            posicionAunDudosa = 0;
            contieneBarraBaja = false;  

            for (int j = 0; j < matriz.length; j++) {
                    if(matriz[j][i] == '_') {
                        contieneBarraBaja = true;
                        posicionAunDudosa++;
                    
                    } else if(matriz[j][i] == 'o'){
                        numOEnEstaColumna++;
                    }
            }

            if(!contieneBarraBaja && !setCantidadOColumna){
                OColumna = numOEnEstaColumna; 
                setCantidadOColumna = true;
            
            } else if(setCantidadOColumna){
                if(numOEnEstaColumna > OColumna || (numOEnEstaColumna + posicionAunDudosa) < OColumna){
                    return false;
                }
            }
        }

        return true;

    }
    
}
