import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
public class Tablero {
    private Celda[][] tablero;
    private Random random;
    private Estadisticas estadisticas;


    public Tablero() {
        this.tablero = new Celda[Configuracion.TAMANIO_TABLERO][Configuracion.TAMANIO_TABLERO];
        this.random = new Random();
        this.estadisticas = new Estadisticas();

        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < Configuracion.TAMANIO_TABLERO; i++) {
            for (int j = 0; j < Configuracion.TAMANIO_TABLERO; j++) {
                tablero[i][j] = new Celda();
                int estadoInicial = random.nextInt(3);
                if (estadoInicial == 0) {
                    tablero[i][j].setAnimal(new Animal(Configuracion.ENERGIA_INICIAL_ANIMAL));
                    estadisticas.incrementarAnimales();
                } else if (estadoInicial == 1) {
                    tablero[i][j].setPlanta(new Planta(Configuracion.ENERGIA_INICIAL_PLANTA));
                    estadisticas.incrementarPlantas();
                }
            }
        }
    }

    private void moverAnimales() {
        for (int i = 0; i < Configuracion.TAMANIO_TABLERO; i++) {
            for (int j = 0; j < Configuracion.TAMANIO_TABLERO; j++) {
                Celda celda = tablero[i][j];
                if (celda.getAnimal() != null) {
                    Animal animal = celda.getAnimal();
                    if (!animal.estaMuerto()) {
                        moverAnimal(i, j);
                    } else {
                        celda.setAnimal(null);
                        estadisticas.incrementarMuertes();
                        estadisticas.decrementarAnimales();
                        estadisticas.registrarEvento("Muerte animal en posición (" + i + "," + j + "); ");
                    }
                }
            }
        }
    }

    private void moverAnimal(int x, int y) {
        int[] dx = {0, 1, 0, -1}; // Movimientos en la vecindad de Von Neumann: derecha, abajo, izquierda, arriba.
        int[] dy = {1, 0, -1, 0};
        int direccion = random.nextInt(4); //Elegir una dirección aleatoria (0 a 3)
        int nuevoX = x + dx[direccion]; // Nueva posición en el eje X
        int nuevoY = y + dy[direccion]; // Nueva posición en el eje Y

        // Verificar si la nueva posición está dentro de los límites del tablero
        if (esValida(nuevoX, nuevoY)) {
            Celda celdaActual = tablero[x][y];
            Celda celdaNueva = tablero[nuevoX][nuevoY];
            Animal animal = celdaActual.getAnimal();
            animal.mover(); //Reducir la energía del animal en 1 unidad
            if (celdaNueva.estaVacia()) {
                // Caso 1: Mover a una celda vacía
                celdaNueva.setAnimal(animal);
                celdaActual.vaciarCelda();
            } else if (celdaNueva.getPlanta() != null) {
                // Caso 2: Mover a una celda que contiene una planta
                Planta planta = celdaNueva.getPlanta();
                animal.comer(planta.getEnergia());
                estadisticas.decrementarPlantas();
               estadisticas.registrarEvento("Muerte planta en posición (" + nuevoX + "," + nuevoY + "); ");
                planta.setEnergia(0); // La planta es comida (su energía se reduce a 0)
                celdaNueva.setAnimal(animal);
                celdaActual.vaciarCelda();
            } else if (celdaNueva.getAnimal() != null) {
                //Mover a una celda que contiene otro animal
                Animal otroAnimal = celdaNueva.getAnimal();
                if (animal.puedeReproducirse() && otroAnimal.puedeReproducirse()) {
                    reproducirAnimal(nuevoX, nuevoY);
                }
            }
        }
    }

    private void reproducirAnimal(int x, int y) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for (int i = 0; i < 4; i++) {
            int nuevoX = x + dx[i];
            int nuevoY = y + dy[i];
            if (esValida(nuevoX, nuevoY) && tablero[nuevoX][nuevoY].estaVacia()) {
                tablero[nuevoX][nuevoY].setAnimal(new Animal(Configuracion.ENERGIA_INICIAL_ANIMAL));
                estadisticas.incrementarAnimales();
                estadisticas.incrementarNacimientos();
                estadisticas.registrarEvento("Nacimiento animal en posición (" + nuevoX + "," + nuevoY + "); ");
                break;
            }
        }
    }

    private boolean esValida(int x, int y) {
        return x >= 0 && x < Configuracion.TAMANIO_TABLERO && y >= 0 && y < Configuracion.TAMANIO_TABLERO;
    }

    public void avanzarTiempo() {
        moverAnimales();
        crecerPlantas();
        estadisticas.almacenarEstadisticas();
    }


    private void crecerPlantas() {
        for (int i = 0; i < Configuracion.TAMANIO_TABLERO; i++) {
            for (int j = 0; j < Configuracion.TAMANIO_TABLERO; j++) {
                Celda celda = tablero[i][j];
                if (celda.getPlanta() != null) {
                    celda.getPlanta().crecer();
                }
            }
        }
    }
    

    public void mostrarTablero() {
        for (int i = 0; i < Configuracion.TAMANIO_TABLERO; i++) {
            for (int j = 0; j < Configuracion.TAMANIO_TABLERO; j++) {
                Celda celda = tablero[i][j];
                if (celda.getAnimal() != null) {
                    System.out.print("A ");
                } else if (celda.getPlanta() != null) {
                    System.out.print("P ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}



