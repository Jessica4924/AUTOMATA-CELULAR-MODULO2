//Esta clase representará un animal con su nivel de energía y edad.

public class Animal {
    private int energia;
    private int edad;

    public Animal(int energiaInicial) {
        this.energia = energiaInicial;
        this.edad = 0;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getEdad() {
        return edad;
    }

    public void envejecer() {
        this.edad++;
    }

    public boolean estaMuerto() {
        return energia <= 0 || edad >= Configuracion.EDAD_MAXIMA_ANIMAL;
    }
    public void mover(){

        this.energia--;
    }
    public void comer(int energiaPlanta){

        this.energia += energiaPlanta;
    }
    public boolean puedeReproducirse(){
        return this.energia >= Configuracion.ENERGIA_REPRODUCCION;
    }
}
