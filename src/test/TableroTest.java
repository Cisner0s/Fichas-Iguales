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

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call imprimirTablero
        Tablero.imprimirTablero(tablero);

        // Reset System.out
        System.setOut(System.out);

        // Verify the printed output
        String expectedOutput = "  1 2 3 " + System.lineSeparator() +
                "1 X O X " + System.lineSeparator() +
                "2 O X O " + System.lineSeparator() +
                "3 X O X " + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}
