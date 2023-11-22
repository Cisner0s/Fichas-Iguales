package src.test;
import src.Movimiento;
import org.junit.Test;

//import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MovimientoTest {
    
    @Test
    public void testGettersMovimiento() {
        Movimiento movimiento = new Movimiento(1, 2, 3, 4, 'A', 10);

        assertEquals(1, movimiento.getNumMovimiento());
        assertEquals(2, movimiento.getCoordenadaX());
        assertEquals(3, movimiento.getCoordenadaY());
        assertEquals(4, movimiento.getNumFichasEliminadas());
        assertEquals('A', movimiento.getColor());
        assertEquals(10, movimiento.getPuntos());
    }

    @Test
    public void testSettersMovimiento() {
        Movimiento movimiento = new Movimiento(1, 2, 3, 4, 'A', 10);

        movimiento.setNumMovimiento(5);
        movimiento.setCoordenadaX(6);
        movimiento.setCoordenadaY(7);
        movimiento.setNumFichasEliminadas(8);
        movimiento.setColor('A');
        movimiento.setPuntos(20);

        assertEquals(5, movimiento.getNumMovimiento());
        assertEquals(6, movimiento.getCoordenadaX());
        assertEquals(7, movimiento.getCoordenadaY());
        assertEquals(8, movimiento.getNumFichasEliminadas());
        assertEquals('A', movimiento.getColor());
        assertEquals(20, movimiento.getPuntos());
    }

    
    @Test
    public void testImprimirMovimientoUnPunto() {
        Movimiento movimiento = new Movimiento(1, 2, 3, 3, 'A', 1);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call imprimirMovimiento
        movimiento.imprimirMovimiento(movimiento);

        // Reset System.out
        System.setOut(System.out);

        // Verify the printed output
        String expectedOutput = "Movimiento 1 en (2, 3): eliminó 3 fichas de color A y obtuvo 1 punto." + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testImprimirMovimientoMultiplesPuntos() {
        Movimiento movimiento = new Movimiento(2, 1, 1, 5, 'A', 10);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call imprimirMovimiento
        movimiento.imprimirMovimiento(movimiento);

        // Reset System.out
        System.setOut(System.out);

        // Verify the printed output
        String expectedOutput = "Movimiento 2 en (1, 1): eliminó 5 fichas de color A y obtuvo 10 puntos." + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}

