package src.test;
import src.Tablero;
import org.junit.Test;

//import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TableroTest {

    @Test
    public void testConstructorAndGettersTablero() {
        char[][] matrizInicial = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        Tablero tablero = new Tablero(3, 3, matrizInicial);

        assertEquals(3, tablero.getFilas());
        assertEquals(3, tablero.getColumnas());
        assertArrayEquals(matrizInicial, tablero.getMatriz());
    }

    @Test
    public void testSettersTablero() {
        Tablero tablero = new Tablero(3, 3, new char[3][3]);

        tablero.setFilas(4);
        tablero.setColumnas(4);
        char[][] nuevaMatriz = {
            {'X', 'O', 'X', 'O'},
            {'O', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'O'},
            {'O', 'X', 'O', 'X'}
        };
        tablero.setMatriz(nuevaMatriz);

        assertEquals(4, tablero.getFilas());
        assertEquals(4, tablero.getColumnas());
        assertArrayEquals(nuevaMatriz, tablero.getMatriz());
    }

    @Test
    public void testImprimirTablero() {
        Tablero tablero = new Tablero(3, 3, new char[][]{{'X', 'O', 'X'}, {'O', 'X', 'O'}, {'X', 'O', 'X'}});

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Tablero.imprimirTablero(tablero);

        System.setOut(System.out);

        String expectedOutput = "  1 2 3 " + System.lineSeparator() +
                "1 X O X " + System.lineSeparator() +
                "2 O X O " + System.lineSeparator() +
                "3 X O X " + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMoverFichasAbajo1() {
        char[][] matriz = {
            {'R', 'R', 'R'},
            {'R', 'A', 'X'},
            {'X', 'X', 'X'}
        };

        Tablero tablero = new Tablero(matriz.length, matriz[0].length, matriz);

        tablero.moverFichasAbajo();

        char[][] esperado = {
            {'X', 'X', 'X'},
            {'R', 'R', 'X'},
            {'R', 'A', 'R'}
        };

        assertArrayEquals(esperado, matriz);
    }

    @Test
    public void testMoverColumnasVacias1() {
        char[][] matriz = {
            {'X', 'X', 'A'},
            {'X', 'A', 'A'},
            {'X', 'A', 'A'}
        };
        Tablero tablero = new Tablero(matriz.length, matriz[0].length, matriz);
        tablero.moverColumnasVacias();

        char[][] esperado = {
            {'X', 'A', 'O'},
            {'A', 'A', 'O'},
            {'A', 'A', 'O'}
        };

        assertArrayEquals(esperado, matriz);
    }

    @Test
    public void testComprimirTablero() {
        char[][] matriz = {
            {'R', 'R', 'R', 'X', 'A', 'A'},
            {'R', 'A', 'X', 'X', 'X', 'A'},
            {'X', 'X', 'X', 'X', 'A', 'A'}
        };
        
        Tablero tablero = new Tablero(matriz.length, matriz[0].length, matriz);

        tablero.moverFichasAbajo();
        tablero.moverColumnasVacias();

        char[][] esperado = {
            {'X', 'X', 'X', 'X', 'A', 'O'},
            {'R', 'R', 'X', 'A', 'A', 'O'},
            {'R', 'A', 'R', 'A', 'A', 'O'}
        };

        assertArrayEquals(esperado, matriz);
    }

    @Test
    public void testComprimirTablero2() {
        char[][] matriz = {
            {'R', 'R', 'R', 'X', 'V', 'X', 'A', 'A', 'X'},
            {'R', 'A', 'X', 'X', 'X', 'X', 'X', 'A', 'X'},
            {'X', 'X', 'X', 'X', 'V', 'X', 'A', 'A', 'X'}
        };
        
        Tablero tablero = new Tablero(matriz.length, matriz[0].length, matriz);

        tablero.moverFichasAbajo();
        tablero.moverColumnasVacias();

        char[][] esperado = {
            {'X', 'X', 'X', 'X', 'X', 'A', 'O', 'O', 'O'},
            {'R', 'R', 'X', 'V', 'A', 'A', 'O', 'O', 'O'},
            {'R', 'A', 'R', 'V', 'A', 'A', 'O', 'O', 'O'}
        };

        assertArrayEquals(esperado, matriz);
    }
}
