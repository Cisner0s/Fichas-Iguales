// Clase Movimiento:
//     Atributos: Número de movimiento, coordenadas (fila, columna), número de fichas eliminadas, color de las fichas, puntuación.
//     Método para imprimir la información del movimiento.

package src;

public class Movimiento {

    int numMovimiento;
    int coordenadaX;
    int coordenadaY;
    int numFichasEliminadas;
    char color;
    int puntos;
    boolean probado;

    public Movimiento(int numMovimiento, int coordenadaX, int coordenadaY, int numFichasEliminadas, char color, int puntos){
        this.numMovimiento = numMovimiento;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.numFichasEliminadas = numFichasEliminadas;
        this.color = color;
        this.puntos = puntos;
        probado = false;
    }

    // Getter methods
    public int getNumMovimiento() {
        return numMovimiento;
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public int getNumFichasEliminadas() {
        return numFichasEliminadas;
    }

    public char getColor() {
        return color;
    }

    public int getPuntos() {
        return puntos;
    }

    public boolean getProbado(){
        return probado;
    }

    // Setter methods
    public void setNumMovimiento(int numMovimiento) {
        this.numMovimiento = numMovimiento;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public void setNumFichasEliminadas(int numFichasEliminadas) {
        this.numFichasEliminadas = numFichasEliminadas;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setProbado(boolean probado){
        this.probado = probado;
    }

    public void imprimirMovimiento(Movimiento movimiento){
        if(movimiento.getNumFichasEliminadas() == 3){ // Caso en el que solo se consigue un punto.
            System.out.println("Movimiento " + movimiento.getNumMovimiento() + " en (" + movimiento.getCoordenadaX() +
            ", " + movimiento.getCoordenadaY() + "): eliminó " + movimiento.getNumFichasEliminadas() +
            " fichas de color " + movimiento.getColor() + " y obtuvo " + movimiento.getPuntos() + " punto.");

        } else{
            System.out.println("Movimiento " + movimiento.getNumMovimiento() + " en (" + movimiento.getCoordenadaX() +
            ", " + movimiento.getCoordenadaY() + "): eliminó " + movimiento.getNumFichasEliminadas() +
            " fichas de color " + movimiento.getColor() + " y obtuvo " + movimiento.getPuntos() + " puntos.");
        }
    }
}
