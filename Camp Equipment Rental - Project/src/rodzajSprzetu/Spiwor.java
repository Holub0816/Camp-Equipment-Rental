
package rodzajSprzetu;

import base.Sprzet;

public class Spiwor extends Sprzet {
    
    private String rozmiar;
    private double gruboscPodszewki;

    public Spiwor() {
    }

    
    
    public Spiwor(String rozmiar, double gruboscPodszewki, double cena, double podatek, String nazwa, int idSprzetu) {
        super(cena, podatek, nazwa, idSprzetu);
        this.rozmiar = rozmiar;
        this.gruboscPodszewki = gruboscPodszewki;
    }

    public String getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(String rozmiar) {
        this.rozmiar = rozmiar;
    }

    public double getGruboscPodszewki() {
        return gruboscPodszewki;
    }

    public void setGruboscPodszewki(double gruboscPodszewki) {
        this.gruboscPodszewki = gruboscPodszewki;
    }

    public String toString(){
        return super.toString() + ", rozmiar: "+rozmiar+", grubosc podszewki: "+gruboscPodszewki;
    }  
    
}
