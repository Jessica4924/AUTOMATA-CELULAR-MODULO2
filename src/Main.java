//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Tablero automata = new Tablero();
        int numEventos = 4; // Número de Eventos a simular
        for (int evento = 0; evento < numEventos; evento++) {
            automata.avanzarTiempo();
            automata.mostrarTablero();
            System.out.println("Evento: " + evento);

            // Pausa para observar el tablero, se puede ajustar el tiempo o eliminarlo
            try {
                Thread.sleep(500); // 500 milisegundos de pausa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    }

