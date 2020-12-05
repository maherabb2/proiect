package com.hipermarket;

public class MeniuCasier extends MeniuAngajat {
    @Override
    public void afisare() {
        System.out.println("com.hipermarket.Meniu Casier");
    }

    @Override
    public Meniu interpreteazaComanda(char c) {
        return null;
    }

    @Override
    public void comutareClient() {

    }

    @Override
    public void adauga() {

    }

    @Override
    public void listare() {

    }

    @Override
    public void sterge() {

    }

    private void verificareAdmin() {

    }
}