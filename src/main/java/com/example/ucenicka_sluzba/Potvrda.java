package com.example.ucenicka_sluzba;

public class Potvrda {
    int potvrdaId;
    String potvrdaTip;
    String potvrdaDatum;

    public Potvrda(int potvrdaId, String potvrdaTip, String potvrdaDatum) {
        this.potvrdaId = potvrdaId;
        this.potvrdaTip = potvrdaTip;
        this.potvrdaDatum = potvrdaDatum;
    }

    public int getPotvrdaId() {
        return potvrdaId;
    }

    public void setPotvrdaId(int potvrdaId) {
        this.potvrdaId = potvrdaId;
    }

    public String getPotvrdaTip() {
        return potvrdaTip;
    }

    public void setPotvrdaTip(String potvrdaTip) {
        this.potvrdaTip = potvrdaTip;
    }

    public String getPotvrdaDatum() {
        return potvrdaDatum;
    }

    public void setPotvrdaDatum(String potvrdaDatum) {
        this.potvrdaDatum = potvrdaDatum;
    }
}
