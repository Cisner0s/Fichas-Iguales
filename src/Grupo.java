// Clase Movimiento:
//     Atributos: Número de movimiento, coordenadas (fila, columna), número de fichas eliminadas, color de las fichas, puntuación.
//     Método para imprimir la información del movimiento.

package src;

import java.util.ArrayList;

public class Grupo{

    ArrayList<int[]> listaFichas;
    int coordenadaX;
    int coordenadaY;
    int numFichasEliminadas;
    char color;
    int puntos;
    boolean probado;

    public Grupo(ArrayList<int[]> listaFichas, int coordenadaX, int coordenadaY, int numFichasEliminadas, char color, int puntos){
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.numFichasEliminadas = numFichasEliminadas;
        this.color = color;
        this.puntos = puntos;
        probado = false;
        this.listaFichas = new ArrayList<>();
    }

    public Grupo(int coordenadaX, int coordenadaY, int numFichasEliminadas, char color, int puntos){
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.numFichasEliminadas = numFichasEliminadas;
        this.color = color;
        this.puntos = puntos;
        probado = false;
        this.listaFichas = new ArrayList<>();
    }

    public Grupo(){
        this.listaFichas = new ArrayList<>();
    }

    // Getter methods.
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

    public ArrayList<int[]> getListaFichas(){
        return listaFichas;
    }

    // Setter methods.
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

    public void setListaFichas(ArrayList<int[]> listaFichas){
        this.listaFichas = listaFichas;
    }

    public void añadirFicha(int[] ficha){
        listaFichas.add(ficha);
    }

    public String generarMovimiento(Grupo grupo, int numFilas) {
        String movimiento;
        if (grupo.getNumFichasEliminadas() == 3) {
            movimiento = ("Grupo en (" + (numFilas - grupo.getCoordenadaX()) +
                    ", " + (grupo.getCoordenadaY() + 1) + "): eliminó " + grupo.getNumFichasEliminadas() +
                    " fichas de color " + grupo.getColor() + " y obtuvo " + grupo.getPuntos() + " punto.");
        } else {
            movimiento = ("Grupo en (" + (numFilas - grupo.getCoordenadaX()) +
                    ", " + (grupo.getCoordenadaY() + 1) + "): eliminó " + grupo.getNumFichasEliminadas() +
                    " fichas de color " + grupo.getColor() + " y obtuvo " + grupo.getPuntos() + " puntos.");
        }

        return movimiento;
    }
    
}
