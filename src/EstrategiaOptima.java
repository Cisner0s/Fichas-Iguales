// Clase EstrategiaOptima:
    //     Atributos: Instancia del juego actual.
    //     Método para encontrar el conjunto de movimientos que da la mayor puntuación posible.


package src;

import java.util.ArrayList;

public class EstrategiaOptima {

    Tablero tableroInicial;
    int puntuacionOptima;
    int fichasRestantes;
    ArrayList<Grupo> solucionOptima;
    ArrayList<Grupo> solucionPosible;

    public EstrategiaOptima(Tablero tableroInicial){
        this.tableroInicial = tableroInicial;
        this.solucionOptima = new ArrayList<>();
        this.solucionPosible = new ArrayList<>();
        this.puntuacionOptima = 0;
        this.fichasRestantes = 0;

    }

    public Tablero getTableroInicial() {
        return tableroInicial;
    }

    public void setTableroInicial(Tablero tableroInicial) {
        this.tableroInicial = tableroInicial;
    }

    public int getPuntuacionOptima() {
        return puntuacionOptima;
    }

    public void setPuntuacionOptima(int puntuacionOptima) {
        this.puntuacionOptima = puntuacionOptima;
    }

    public ArrayList<Grupo> getSolucionOptima() {
        return solucionOptima;
    }

    public void setSolucionOptima(ArrayList<Grupo> solucionOptima) {
        this.solucionOptima = solucionOptima;
    }

    public boolean tabletoLimpio(){
        return true;
    }
    

    public void jugar(Tablero tableroActual, int puntuacion) {
        tableroActual.comprimirTablero(tableroActual);
        tableroActual.calcularGrupos();
    
        if (tableroActual.getGruposDelTablero().isEmpty()) {
            if (solucion(puntuacion, tableroActual.getMatriz())) {
                solucionOptima.clear();
                solucionOptima.addAll(new ArrayList<>(solucionPosible));
            }
            fichasRestantes = 0;
            return;
        }
    
        for (int i = 0; i < tableroActual.getGruposDelTablero().size(); i++) {
            Grupo grupoSeleccionado = tableroActual.getGruposDelTablero().get(i);
            if (!grupoSeleccionado.getProbado()) {
                grupoSeleccionado.setProbado(true);
    
                puntuacion += grupoSeleccionado.getPuntos();
                solucionPosible.add(grupoSeleccionado);
    
                Tablero tableroNuevo = new Tablero();
                tableroNuevo.copiar(tableroActual);
                tableroNuevo.borrarGrupoSeleccionado(grupoSeleccionado);
    
                jugar(tableroNuevo, puntuacion);
    
                // Deshacer la selección del grupo antes de probar otro
                grupoSeleccionado.setProbado(false);
                puntuacion -= grupoSeleccionado.getPuntos();
                solucionPosible.remove(solucionPosible.size() - 1);
            }
        }
    }
    
    
    

    public boolean solucion(int puntuacion, char[][] matrizFinal){
        for (int i = 0; i < matrizFinal.length; i++) {
            for (int j = 0; j < matrizFinal[0].length; j++) {
                if(matrizFinal[i][j] != 'O' & matrizFinal[i][j] != 'X'){
                    fichasRestantes ++;
                }
            }
        }

        if(fichasRestantes == 0){
            puntuacion += 1000;
        }

        if(puntuacion > puntuacionOptima){
            puntuacionOptima = puntuacion;
            return true;

        //}else if(puntuacion == puntuacionOptima){

        
        }else{
            return false;
        }
    }
    
    public void imprimirSolucionOptima(){
        for (int i = 0; i < solucionOptima.size(); i++) {
           Grupo grupoSolucion = solucionOptima.get(i);
           System.out.print("Movimiento " + (++i) + grupoSolucion.generarMovimiento(grupoSolucion, tableroInicial.getFilas())); 
           System.out.print("\n");
           i--;
        }

        if(fichasRestantes == 1){
            System.out.println("Puntuación final: " + puntuacionOptima +", quedando " + fichasRestantes + " ficha.");

        } else {
            System.out.println("Puntuación final: " + puntuacionOptima +", quedando " + fichasRestantes + " fichas.");
        }
    }
}
