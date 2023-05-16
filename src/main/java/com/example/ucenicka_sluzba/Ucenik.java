package com.example.ucenicka_sluzba;

public class Ucenik {
    int ucenik_id;
    String ucenik_ime;
    String ucenik_prezime;
    String ucenik_adresa;
    String ucenik_datum_rodjenja;
    String ucenik_majka;
    String ucenik_otac;
    Float ucenik_prosek;
    String ucenik_pol;
    String ucenik_razred;
    String ucenik_razredni_staresina;
    Float ucenik_uspeh;

    public Ucenik(int ucenik_id, String ucenik_ime, String ucenik_prezime, String ucenik_adresa, String ucenik_datum_rodjenja, String ucenik_majka, String ucenik_otac, Float ucenik_prosek, String ucenik_pol, String ucenik_razred, String ucenik_razredni_staresina, Float ucenik_uspeh) {
        this.ucenik_id = ucenik_id;
        this.ucenik_ime = ucenik_ime;
        this.ucenik_prezime = ucenik_prezime;
        this.ucenik_adresa = ucenik_adresa;
        this.ucenik_datum_rodjenja = ucenik_datum_rodjenja;
        this.ucenik_majka = ucenik_majka;
        this.ucenik_otac = ucenik_otac;
        this.ucenik_prosek = ucenik_prosek;
        this.ucenik_pol = ucenik_pol;
        this.ucenik_razred = ucenik_razred;
        this.ucenik_razredni_staresina = ucenik_razredni_staresina;
        this.ucenik_uspeh = ucenik_uspeh;
    }

    public int getUcenik_id() {
        return ucenik_id;
    }

    public void setUcenik_id(int ucenik_id) {
        this.ucenik_id = ucenik_id;
    }

    public String getUcenik_ime() {
        return ucenik_ime;
    }

    public void setUcenik_ime(String ucenik_ime) {
        this.ucenik_ime = ucenik_ime;
    }

    public String getUcenik_prezime() {
        return ucenik_prezime;
    }

    public void setUcenik_prezime(String ucenik_prezime) {
        this.ucenik_prezime = ucenik_prezime;
    }

    public String getUcenik_adresa() {
        return ucenik_adresa;
    }

    public void setUcenik_adresa(String ucenik_adresa) {
        this.ucenik_adresa = ucenik_adresa;
    }

    public String getUcenik_datum_rodjenja() {
        return ucenik_datum_rodjenja;
    }

    public void setUcenik_datum_rodjenja(String ucenik_datum_rodjenja) {
        this.ucenik_datum_rodjenja = ucenik_datum_rodjenja;
    }

    public String getUcenik_majka() {
        return ucenik_majka;
    }

    public void setUcenik_majka(String ucenik_majka) {
        this.ucenik_majka = ucenik_majka;
    }

    public String getUcenik_otac() {
        return ucenik_otac;
    }

    public void setUcenik_otac(String ucenik_otac) {
        this.ucenik_otac = ucenik_otac;
    }

    public Float getUcenik_prosek() {
        return ucenik_prosek;
    }

    public void setUcenik_prosek(Float ucenik_prosek) {
        this.ucenik_prosek = ucenik_prosek;
    }

    public String getUcenik_pol() {
        return ucenik_pol;
    }

    public void setUcenik_pol(String ucenik_pol) {
        this.ucenik_pol = ucenik_pol;
    }

    public String getUcenik_razred() {
        return ucenik_razred;
    }

    public void setUcenik_razred(String ucenik_razred) {
        this.ucenik_razred = ucenik_razred;
    }

    public String getUcenik_razredni_staresina() {
        return ucenik_razredni_staresina;
    }

    public void setUcenik_razredni_staresina(String ucenik_razredni_staresina) {
        this.ucenik_razredni_staresina = ucenik_razredni_staresina;
    }

    public Float getUcenik_uspeh() {
        return ucenik_uspeh;
    }

    public void setUcenik_uspeh(Float ucenik_uspeh) {
        this.ucenik_uspeh = ucenik_uspeh;
    }

    @Override
    public String toString() {
        return "Ucenik{" +
                "ucenik_id=" + ucenik_id +
                ", ucenik_ime='" + ucenik_ime + '\'' +
                ", ucenik_prezime='" + ucenik_prezime + '\'' +
                ", ucenik_adresa='" + ucenik_adresa + '\'' +
                ", ucenik_datum_rodjenja='" + ucenik_datum_rodjenja + '\'' +
                ", ucenik_majka='" + ucenik_majka + '\'' +
                ", ucenik_otac='" + ucenik_otac + '\'' +
                ", ucenik_prosek=" + ucenik_prosek +
                ", ucenik_pol='" + ucenik_pol + '\'' +
                ", ucenik_razred='" + ucenik_razred + '\'' +
                ", ucenik_razredni_staresina='" + ucenik_razredni_staresina + '\'' +
                ", ucenik_uspeh=" + ucenik_uspeh +
                '}';
    }
}
