package rodzajSprzetu;

import base.Sprzet;

public class KijkiTrekkingowe extends Sprzet {

    private double dlugosc;
    private String material;

    public KijkiTrekkingowe() {
    }

    public KijkiTrekkingowe(double dlugosc, String material, double cena, double podatek, String nazwa, int idSprzetu) {
        super(cena, podatek, nazwa, idSprzetu);
        this.dlugosc = dlugosc;
        this.material = material;
    }

    public double getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(double dlugosc) {
        this.dlugosc = dlugosc;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return super.toString() + ", dlugosc: " + dlugosc + ", material: " + material;
    }


}
