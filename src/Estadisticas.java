

import java.io.FileWriter;
import java.io.IOException;

public class Estadisticas {
    private int numeroPlantas;
    private int numeroAnimales;
    private int numeroNacimientos;
    private int numeroMuertes;
    private int numeroEventos;
    private final StringBuilder eventos;

    public Estadisticas(){
        this.numeroPlantas = 0;
        this.numeroAnimales = 0;
        this.numeroNacimientos = 0;
        this.numeroMuertes = 0;
        this.numeroEventos = 0;
        this.eventos = new StringBuilder();
    }
    public void incrementarPlantas(){
        numeroPlantas++;
    }
    public void decrementarPlantas(){
        numeroPlantas--;
    }
    public void incrementarAnimales(){
        numeroAnimales++;
    }
    public void decrementarAnimales(){
        numeroAnimales--;
    }
    public void incrementarNacimientos(){
        numeroNacimientos++;
    }
    public void incrementarMuertes(){
        numeroMuertes++;
    }
    public void registrarEvento(String evento){

        eventos.append(evento).append("; ");
        numeroEventos++;
    }
    void almacenarEstadisticas() {
        try (FileWriter writer = new FileWriter("estadisticas.csv", true)) {

            writer.write( "Evento " + numeroEventos + ";" + "Plantas " + numeroPlantas + ";" + "Animales " + numeroAnimales + ";" +
                    "Nacimientos animales " + numeroNacimientos + ";" + "Muertes animales " + numeroMuertes + ";" +
                    "\n" + eventos.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        eventos.setLength(0); // Reiniciar eventos después de almacenarlos
    }
}
