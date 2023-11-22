    
// Clase Principal:
//     Método main que maneje la entrada y salida del programa.
//     Crear instancias de las clases anteriores y llamar a los métodos correspondientes para ejecutar el juego.

package src;

import java.util.Scanner;
import java.util.ArrayList;

public class MainFichasIguales{
    public static void main(String[] args) throws Exception {
        
        ArrayList<char[][]> todosLosJuegos = new ArrayList<>();
        leerEntrada(todosLosJuegos);

        for (int i = 0; i < todosLosJuegos.size(); i++) {
            char[][] juego = todosLosJuegos.get(i);
            Tablero tablero = new Tablero(juego.length, juego[0].length, juego);
            Tablero.imprimirTablero(tablero);
            System.out.println();
            tablero.comprimirTablero(tablero);
            System.out.println("Imprimiendo tablero comprimido:");
            Tablero.imprimirTablero(tablero);
            System.out.println();
        }

        // IMPRESION DE LAS MATRICES DE ENTRADA
        for (char[][] juego : todosLosJuegos) {
            System.out.println("Matriz:");
            for (int i = 0; i < juego.length; i++) {
                for (int j = 0; j < juego[i].length; j++) {
                    System.out.print(juego[i][j] + " ");
                }
                System.out.println(); // Nueva línea para cada fila
            }
            System.out.println(); // Nueva línea entre matrices
        }
 
    }

    public static void leerEntrada(ArrayList<char[][]> todosLosJuegos) throws IllegalArgumentException{
        Scanner sc = new Scanner(System.in);
        int numeroDeJuegos = Integer.parseInt(sc.nextLine());
        if(numeroDeJuegos < 1){
            sc.close();
            throw new IllegalArgumentException("El número de juegos debe ser al menos 1.");
        }
        
        String lineaVacia = sc.nextLine(); // Sirve para saltarse la primera linea en blanco.
        if(!lineaVacia.isEmpty()){
            sc.close();
            throw new IllegalArgumentException("No existe la linea en blanco necesaria entre el numero que indica la cantidad de juegos y el primer juego.");
        }

        for (int i = 0; i < numeroDeJuegos; i++) {
            boolean matrizIncorrecta = false;
            String primeraFila = sc.nextLine();
            char[] primeraFilaFragmentada = primeraFila.toCharArray();

            int numeroColumnas = primeraFilaFragmentada.length;

            if(numeroColumnas > 20){
                System.out.println("Numero de columnas incorrecto en el juego " + (i+1) + ".");
                break;
            }

            ArrayList<Character> juegoArrayList = new ArrayList<>();

            for(char fragmento : primeraFilaFragmentada) {
                juegoArrayList.add(fragmento);
            }

            while(sc.hasNextLine()){
                String linea = sc.nextLine();

                if (linea.equals("")) break;
 
                char[] lineafragmentada = linea.toCharArray();
                if(lineafragmentada.length == numeroColumnas){
                    for(char fragmento : lineafragmentada){
                        juegoArrayList.add(fragmento);
                    }
                } else {
                    matrizIncorrecta = true;
                    break;
                }
            }

            if(matrizIncorrecta){
                break;
            }

            int numeroFilas = (juegoArrayList.size() / numeroColumnas);

            char[][] juego = new char[numeroFilas][numeroColumnas];

            for (int j = 0; j < numeroFilas; j++) {
                for (int k = 0; k < numeroColumnas; k++) {
                    int indiceArrayList = j * numeroColumnas + k;
                    juego[j][k] = juegoArrayList.get(indiceArrayList);
                }
            }

            todosLosJuegos.add(juego);
        }
        sc.close();
    }

}

