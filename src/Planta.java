//Esta clase representará un animal con su nivel de energía.

public class Planta {
    private int energia;


    public Planta(int energiaInicial){
        this.energia = energiaInicial;
    }
    public int getEnergia(){
        return energia;
    }
    public void setEnergia(int energia){
        this.energia = energia;
    }
    public void crecer(){
        if (energia < Configuracion.ENERGIA_MAXIMA_PLANTA){
            energia++;
        }
    }
}
