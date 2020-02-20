
package rodzajSprzetu;

import base.Sprzet;

public class Plecak extends Sprzet {
    
    private int pojemnosc;
    private String material;

    public Plecak() {
    }

    
    
    public Plecak(int pojemnosc, String material, double cena, double podatek, String nazwa, int idSprzetu) {
        super(cena, podatek, nazwa, idSprzetu);
        this.pojemnosc = pojemnosc;
        this.material = material;
    }

    public int getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(int pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    
    public String toString(){
        return super.toString() + ", pojemnosc: "+pojemnosc+", material: "+material;
    }
    
}
