package com.example.ucenicka_sluzba;

public class Predmet {
    int predmet_id;
    String predmet_ime;
    String predmet_razred;

    public Predmet(int predmet_id, String predmet_ime, String predmet_razred) {
        this.predmet_id = predmet_id;
        this.predmet_ime = predmet_ime;
        this.predmet_razred = predmet_razred;
    }

    public int getPredmet_id() {
        return predmet_id;
    }

    public void setPredmet_id(int predmet_id) {
        this.predmet_id = predmet_id;
    }

    public String getPredmet_ime() {
        return predmet_ime;
    }

    public void setPredmet_ime(String predmet_ime) {
        this.predmet_ime = predmet_ime;
    }

    public String getPredmet_razred() {
        return predmet_razred;
    }

    public void setPredmet_razred(String predmet_razred) {
        this.predmet_razred = predmet_razred;
    }
}
