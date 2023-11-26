// Clase EstrategiaOptima:
    //     Atributos: Instancia del juego actual.
    //     Método para encontrar el conjunto de movimientos que da la mayor puntuación posible.


package src;

import java.util.ArrayList;

public class EstrategiaOptima {

    Tablero tableroInicial;
    int puntuacionOptima;
    ArrayList<Grupo> solucionOptima;
    ArrayList<Grupo> solucionPosible;

    public EstrategiaOptima(Tablero tableroInicial){
        this.tableroInicial = tableroInicial;
        this.solucionOptima = new ArrayList<>();
        this.solucionPosible = new ArrayList<>();
        this.puntuacionOptima = 0;
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
    

    public int jugar(Tablero tableroActual, int puntuacion) {
        boolean todosLosGruposRecorridos = true;
        boolean grupoRecorrido;
        Grupo grupoSeleccionado = new Grupo();
    
        tableroActual.comprimirTablero(tableroActual);
        tableroActual.calcularGrupos();
    
        if (tableroActual.gruposDelTablero.isEmpty()) {
            if(solucion(puntuacion)){
                solucionOptima = solucionPosible;
            }

            solucionPosible.remove(solucionPosible.size()-1);
        }
    
        for (int i = 0; i < tableroActual.getGruposDelTablero().size(); i++) {
            grupoRecorrido = tableroActual.getGruposDelTablero().get(i).getProbado();
            if (!grupoRecorrido) {
                todosLosGruposRecorridos = false;
                grupoSeleccionado = tableroActual.getGruposDelTablero().get(i);
                break; // Terminamos el bucle cuando encontramos un grupo no recorrido.
            }
        }
    
        if (todosLosGruposRecorridos) {
            solucionPosible.remove(solucionPosible.size()-1);
            return 0; // No hay grupos disponibles, no se suma nada.
        }
    
        puntuacion += grupoSeleccionado.getPuntos();
        Tablero tableroNuevo = new Tablero(); // Asumo que tienes un método copiar en tu clase Tablero.
        tableroNuevo.copiar(tableroActual);
        tableroNuevo.borrarGrupoSeleccionado(grupoSeleccionado);
    
        jugar(tableroNuevo, puntuacion);
    
        // Al retroceder, restamos la puntuación del movimiento actual.
        return (puntuacion - grupoSeleccionado.getPuntos());
    }
    

    public boolean solucion(int puntuacion){
        if(puntuacion > puntuacionOptima){
            puntuacionOptima = puntuacion;
            return true;
        }else{
            return false;
        }
    }
    
    public void imprimirSolucionOptima(){
        for (int i = 0; i < solucionOptima.size(); i++) {
           System.out.print(solucionOptima.get(i).generarMovimiento(null)); 
           System.out.print("\n");
        }

        System.out.println("Puntuación final: " + puntuacionOptima +", quedando 0 fichas.");
    }
}
