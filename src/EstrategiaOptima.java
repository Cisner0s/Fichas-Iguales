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
    

    public int jugar(Tablero tableroActual, int puntuacion) {

        boolean todosLosGruposRecorridos = true;
        boolean grupoRecorrido;
        Grupo grupoSeleccionado = new Grupo();
    
        tableroActual.comprimirTablero(tableroActual);
        tableroActual.calcularGrupos();
    
        if (tableroActual.gruposDelTablero.isEmpty()) { // Cuando la matriz esta vacia o queda solo un elemento.
            if(solucion(puntuacion, tableroActual.getMatriz())){                         //solucionPosible.remove(solucionPosible.size()-1); 
                // Si la puntuacion de la solucion actual es mayor que la puntuacionOptima
                solucionOptima.clear();
                solucionOptima.addAll(solucionPosible);       // La solucion actual se convierte en la solucion mas optima
            }
            if (!solucionPosible.isEmpty()) {
                solucionPosible.remove(solucionPosible.size()-1);
                return 0;
            }
        }
    
        for (int i = 0; i < tableroActual.getGruposDelTablero().size(); i++) {
            grupoRecorrido = tableroActual.getGruposDelTablero().get(i).getProbado();
            if (!grupoRecorrido) {
                todosLosGruposRecorridos = false;
                grupoSeleccionado = tableroActual.getGruposDelTablero().get(i);
                tableroActual.getGruposDelTablero().get(i).setProbado(true);
                break; // Terminamos el bucle cuando encontramos un grupo no recorrido.
            }
        }
    
        if (todosLosGruposRecorridos) {
            if (!solucionPosible.isEmpty()) {
                solucionPosible.remove(solucionPosible.size()-1);
            }          
            return 0; // No hay grupos disponibles, no se suma nada.

        } else{
    
            puntuacion += grupoSeleccionado.getPuntos();
            solucionPosible.add(grupoSeleccionado);

            Tablero tableroNuevo = new Tablero(); // Asumo que tienes un método copiar en tu clase Tablero.
            tableroNuevo.copiar(tableroActual);
            tableroNuevo.borrarGrupoSeleccionado(grupoSeleccionado);
        
            jugar(tableroNuevo, puntuacion);
        
            // Al retroceder, restamos la puntuación del movimiento actual.
            return (puntuacion - grupoSeleccionado.getPuntos());
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
        }else{
            return false;
        }
    }
    
    public void imprimirSolucionOptima(){
        for (int i = 0; i < solucionOptima.size(); i++) {
           Grupo grupoSolucion = solucionOptima.get(i);
           System.out.print(grupoSolucion.generarMovimiento(grupoSolucion, tableroInicial.getFilas())); 
           System.out.print("\n");
        }

        if(fichasRestantes == 1){
            System.out.println("Puntuación final: " + puntuacionOptima +", quedando " + fichasRestantes + " ficha.");

        } else {
            System.out.println("Puntuación final: " + puntuacionOptima +", quedando " + fichasRestantes + " fichas.");
        }
    }
}
