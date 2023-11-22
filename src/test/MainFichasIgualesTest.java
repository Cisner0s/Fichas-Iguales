package src.test;
import src.MainFichasIguales;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;

public class MainFichasIgualesTest {

    @Test
    public void testLeerEntradaEjemplo() {
        ArrayList<char[][]> todosLosJuegos = new ArrayList<>();
        String input = "2\r\n" + //
                        "\r\n" + //
                        "AVR\r\n" + //
                        "AAR\r\n" + //
                        "ARR\r\n" + //
                        "VVR\r\n" + //
                        "\r\n" + //
                        "VRA\r\n" + //
                        "RAR\r\n" + //
                        "AAR\r\n" + //
                        "RVV";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainFichasIguales.leerEntrada(todosLosJuegos);

        assertEquals(2, todosLosJuegos.size());

        char[][] juego1 = todosLosJuegos.get(0);
        assertEquals('A', juego1[0][0]);
        assertEquals('V', juego1[0][1]);
        assertEquals('R', juego1[0][2]);
        assertEquals('A', juego1[1][0]);
        assertEquals('A', juego1[1][1]);
        assertEquals('R', juego1[1][2]);
        assertEquals('A', juego1[2][0]);
        assertEquals('R', juego1[2][1]);
        assertEquals('R', juego1[2][2]);
        assertEquals('V', juego1[3][0]);
        assertEquals('V', juego1[3][1]);
        assertEquals('R', juego1[3][2]);

        char[][] juego2 = todosLosJuegos.get(1);
        assertEquals('V', juego2[0][0]);
        assertEquals('R', juego2[0][1]);
        assertEquals('A', juego2[0][2]);
        assertEquals('R', juego2[1][0]);
        assertEquals('A', juego2[1][1]);
        assertEquals('R', juego2[1][2]);
        assertEquals('A', juego2[2][0]);
        assertEquals('A', juego2[2][1]);
        assertEquals('R', juego2[2][2]);
        assertEquals('R', juego2[3][0]);
        assertEquals('V', juego2[3][1]);
        assertEquals('V', juego2[3][2]);
    }

    @Test
    public void testLeerEntradaUnSoloJuego() {
        ArrayList<char[][]> todosLosJuegos = new ArrayList<>();
        String input = "1\r\n" + //
                        "\r\n" + //
                        "AVR\r\n" + //
                        "AAR\r\n" + //
                        "ARR\r\n" + //
                        "VVR\r\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainFichasIguales.leerEntrada(todosLosJuegos);

        assertEquals(1, todosLosJuegos.size());

        char[][] juego1 = todosLosJuegos.get(0);
        assertEquals('A', juego1[0][0]);
        assertEquals('V', juego1[0][1]);
        assertEquals('R', juego1[0][2]);
        assertEquals('A', juego1[1][0]);
        assertEquals('A', juego1[1][1]);
        assertEquals('R', juego1[1][2]);
        assertEquals('A', juego1[2][0]);
        assertEquals('R', juego1[2][1]);
        assertEquals('R', juego1[2][2]);
        assertEquals('V', juego1[3][0]);
        assertEquals('V', juego1[3][1]);
        assertEquals('R', juego1[3][2]);
    }

      @Test
    public void testMaximoColumnas() {
        ArrayList<char[][]> todosLosJuegos = new ArrayList<>();
        String input = "1\r\n" + //
                        "\r\n" + //
                        "AAAAAAAAAAAAAAAAAAAA\r\n" + //
                        "AAAAAAAAAAAAAAAAAAAA\r\n" + //
                        "AAAAAAAAAAAAAAAAAAAA\r\n" + //
                        "AAAAAAAAAAAAAAAAAAAA\r\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainFichasIguales.leerEntrada(todosLosJuegos);

        assertEquals(1, todosLosJuegos.size());

        char[][] juego1 = todosLosJuegos.get(0);
        assertEquals('A', juego1[0][0]);
        assertEquals('A', juego1[0][1]);
        assertEquals('A', juego1[0][2]);
        assertEquals('A', juego1[0][3]);
        assertEquals('A', juego1[0][4]);
        assertEquals('A', juego1[0][5]);
        assertEquals('A', juego1[0][6]);
        assertEquals('A', juego1[0][7]);
        assertEquals('A', juego1[0][8]);
        assertEquals('A', juego1[0][9]);
        assertEquals('A', juego1[0][10]);
        assertEquals('A', juego1[0][11]);
        assertEquals('A', juego1[0][12]);
        assertEquals('A', juego1[0][13]);
        assertEquals('A', juego1[0][14]);
        assertEquals('A', juego1[0][15]);
        assertEquals('A', juego1[0][16]);
        assertEquals('A', juego1[0][17]);
        assertEquals('A', juego1[0][18]);
        assertEquals('A', juego1[0][19]);
    }

    @Test
    public void testMinimaMatriz() {
        ArrayList<char[][]> todosLosJuegos = new ArrayList<>();
        String input = "1\r\n" + //
                        "\r\n" + //
                        "A\r\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainFichasIguales.leerEntrada(todosLosJuegos);

        assertEquals(1, todosLosJuegos.size());

        char[][] juego1 = todosLosJuegos.get(0);
        assertEquals('A', juego1[0][0]);
    }

    @Test   
    public void testColumnasNoCuadranSoloValidoElPrimerJuego() {
        ArrayList<char[][]> todosLosJuegos = new ArrayList<>();
        String input = "2\r\n" + //
                        "\r\n" + //
                        "AVR\r\n" + //
                        "AAR\r\n" + //
                        "ARR\r\n" + //
                        "VVR\r\n" + //
                        "\r\n" + //
                        "AAAAAAAAAAAAAAAAAAAA\r\n" + //
                        "A\r\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainFichasIguales.leerEntrada(todosLosJuegos);

        assertEquals(1, todosLosJuegos.size());

        char[][] juego1 = todosLosJuegos.get(0);
        assertEquals('A', juego1[0][0]);
        assertEquals('V', juego1[0][1]);
        assertEquals('R', juego1[0][2]);
        assertEquals('A', juego1[1][0]);
        assertEquals('A', juego1[1][1]);
        assertEquals('R', juego1[1][2]);
        assertEquals('A', juego1[2][0]);
        assertEquals('R', juego1[2][1]);
        assertEquals('R', juego1[2][2]);
        assertEquals('V', juego1[3][0]);
        assertEquals('V', juego1[3][1]);
        assertEquals('R', juego1[3][2]);
    }

    @Test
    public void testColumnasNoCuadranSeCortaLaEjecucion() {
        ArrayList<char[][]> todosLosJuegos = new ArrayList<>();
        String input = "2\r\n" + //
                        "\r\n" + //
                        "AAAAAAAAAAAAAAAAAAAA\r\n" + //
                        "A\r\n" + //
                        "\r\n" + //
                        "AVR\r\n" + //
                        "AAR\r\n" + //
                        "ARR\r\n" + //
                        "VVR\r\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainFichasIguales.leerEntrada(todosLosJuegos);

        assertEquals(0, todosLosJuegos.size());
    }

    @Test
    public void testSuperaElLimiteDeColumnas() {
        ArrayList<char[][]> todosLosJuegos = new ArrayList<>();
        String input = "2\r\n" + //
                        "\r\n" + //
                        "AAAAAAAAAAAAAAAAAAAAA\r\n" + //
                        "A\r\n" + //
                        "\r\n" + //
                        "AVR\r\n" + //
                        "AAR\r\n" + //
                        "ARR\r\n" + //
                        "VVR\r\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainFichasIguales.leerEntrada(todosLosJuegos);

        assertEquals(0, todosLosJuegos.size());
    }

    @Test
    public void testNumeroDeJuegosInvalido() {
        String input = "0\n";  // Simulamos un número de juegos inválido (menos de 1)
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ArrayList<char[][]> todosLosJuegos = new ArrayList<>();
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            MainFichasIguales.leerEntrada(todosLosJuegos);
        });

        assertEquals("El número de juegos debe ser al menos 1.", exception.getMessage());
    }

    @Test
    public void testLineaEnBlancoFaltante() {
        ArrayList<char[][]> todosLosJuegos = new ArrayList<>();
        String input = "2\r\n" + //
                        // No hay línea en blanco después del número de juegos
                        "AVR\r\n" + //
                        "AAR\r\n" + //
                        "ARR\r\n" + //
                        "VVR\r\n" + //
                        "\r\n" + //
                        "VRA\r\n" + //
                        "RAR\r\n" + //
                        "AAR\r\n" + //
                        "RVV";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            MainFichasIguales.leerEntrada(todosLosJuegos);
        });

        assertEquals("No existe la linea en blanco necesaria entre el numero que indica la cantidad de juegos y el primer juego.", exception.getMessage());
    }
}

