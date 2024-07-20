// Esta clase representará una celda en el tablero y puede contener un animal, una planta, o estar vacía.

public class Celda {
    private Animal animal;
    private Planta planta;

    public Celda(){
        this.animal = null;
        this.planta = null;
    }
    public boolean estaVacia(){
        return animal == null && planta == null;
    }
    public Animal getAnimal(){
        return animal;
    }
    public void setAnimal(Animal animal){
        this.animal = animal;
    }
    public Planta getPlanta(){
        return planta;
    }
    public void setPlanta(Planta planta){
        this.planta = planta;
    }
    public void vaciarCelda(){
        this.animal = null;
        this.planta = null;
    }
}
