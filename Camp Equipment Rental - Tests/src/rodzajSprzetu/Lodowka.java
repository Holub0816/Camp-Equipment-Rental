package rodzajSprzetu;

import base.Sprzet;

public class Lodowka extends Sprzet {

    private int pojemnosc;
    private double czasTrzymaniaTemperatury;
    private double wysokosc;
    private double szerokosc;
    private double dlugosc;

    public Lodowka() {
    }

    public Lodowka(int pojemnosc, double czasTrzymaniaTemperatury, double wysokosc,
            double szerokosc, double dlugosc, double cena, double podatek, String nazwa, int idSprzetu) {
        super(cena, podatek, nazwa, idSprzetu);
        this.pojemnosc = pojemnosc;
        this.czasTrzymaniaTemperatury = czasTrzymaniaTemperatury;
        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;
        this.dlugosc = dlugosc;
    }

    public int getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(int pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public double getCzasTrzymaniaTemperatury() {
        return czasTrzymaniaTemperatury;
    }

    public void setCzasTrzymaniaTemperatury(double czasTrzymaniaTemperatury) {
        this.czasTrzymaniaTemperatury = czasTrzymaniaTemperatury;
    }

    public double getWysokosc() {
        return wysokosc;
    }

    public void setWysokosc(double wysokosc) {
        this.wysokosc = wysokosc;
    }

    public double getSzerokosc() {
        return szerokosc;
    }

    public void setSzerokosc(double szerokosc) {
        this.szerokosc = szerokosc;
    }

    public double getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(double dlugosc) {
        this.dlugosc = dlugosc;
    }

    @Override
    public String toString() {
        return super.toString() + ", pojemnosc: " + pojemnosc + ", czas trzymania temperatury: " + czasTrzymaniaTemperatury + ", dlugosc: "
                + dlugosc + ", wysokosc: " + wysokosc + ", szerokosc: " + szerokosc;
    }


}
