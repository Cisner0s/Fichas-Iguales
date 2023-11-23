package src.test;
import src.Grupo;
import org.junit.Test;

//import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GrupoTest {
    
    @Test
    public void testGettersGrupo() {
        Grupo grupo = new Grupo(1, 2, 3, 4, 'A', 10);

        assertEquals(1, grupo.getNumGrupo());
        assertEquals(2, grupo.getCoordenadaX());
        assertEquals(3, grupo.getCoordenadaY());
        assertEquals(4, grupo.getNumFichasEliminadas());
        assertEquals('A', grupo.getColor());
        assertEquals(10, grupo.getPuntos());
    }

    @Test
    public void testSettersGrupo() {
        Grupo grupo = new Grupo(1, 2, 3, 4, 'A', 10);

        grupo.setCoordenadaX(6);
        grupo.setCoordenadaY(7);
        grupo.setNumFichasEliminadas(8);
        grupo.setColor('A');
        grupo.setPuntos(20);

        assertEquals(5, grupo.getNumGrupo());
        assertEquals(6, grupo.getCoordenadaX());
        assertEquals(7, grupo.getCoordenadaY());
        assertEquals(8, grupo.getNumFichasEliminadas());
        assertEquals('A', grupo.getColor());
        assertEquals(20, grupo.getPuntos());
    }

    
    @Test
    public void testImprimirGrupoUnPunto() {
        Grupo grupo = new Grupo(1, 2, 3, 3, 'A', 1);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call imprimirGrupo
        grupo.imprimirGrupo(grupo);

        // Reset System.out
        System.setOut(System.out);

        // Verify the printed output
        String expectedOutput = "Grupo 1 en (2, 3): eliminó 3 fichas de color A y obtuvo 1 punto." + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testImprimirGrupoMultiplesPuntos() {
        Grupo grupo = new Grupo(2, 1, 1, 5, 'A', 10);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call imprimirGrupo
        grupo.imprimirGrupo(grupo);

        // Reset System.out
        System.setOut(System.out);

        // Verify the printed output
        String expectedOutput = "Grupo 2 en (1, 1): eliminó 5 fichas de color A y obtuvo 10 puntos." + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}
