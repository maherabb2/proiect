package com.hipermarket;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MeniuCasier extends MeniuAngajat {
    @Override
    public void afisare() {
        System.out.println("com.hipermarket.Meniu Casier");
    }

    @Override
    public Meniu interpreteazaComanda(char c) {
        Meniu menu = this;
        switch (c){
            case '1':
                adauga();
                break;
        }
        return menu;
    }

    @Override
    public void comutareClient() {

    }

    @Override
    public void adauga() {
        File messages = new File("database/messages.txt");
        Produs produs = null;
        try{
            Scanner scanner = new Scanner(messages);
            String line = scanner.nextLine();

            String []elemente = line.split(";");
            int id = Integer.parseInt(elemente[0])  ;
            String nume = String.valueOf(elemente[1]);
            float pret = Float.parseFloat(elemente[2]);
            float cantitate = Float.parseFloat(elemente[3]);
            TipCantitate tipCantitate = TipCantitate.fromInt(Integer.parseInt(elemente[4]));
            CategorieProdus categorieProdus = CategorieProdus.fromInt(Integer.parseInt(elemente[5]));

            produs = new Produs(id,nume,pret,cantitate,tipCantitate,categorieProdus);
        }catch (Exception e){
            e.printStackTrace();
        }

        File produsFile = new File("database/produse.txt");

        ArrayList<Produs> produse = new ArrayList<>();
        try{
            Scanner s = new Scanner(produsFile);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String []elemente = line.split(";");
                int id = Integer.parseInt(elemente[0])  ;
                String nume = String.valueOf(elemente[1]);
                float pret = Float.parseFloat(elemente[2]);
                float cantitate = Float.parseFloat(elemente[3]);
                TipCantitate tipCantitate = TipCantitate.fromInt(Integer.parseInt(elemente[4]));
                CategorieProdus categorieProdus = CategorieProdus.fromInt(Integer.parseInt(elemente[5]));

                Produs produs1 = new Produs(id,nume,pret,cantitate,tipCantitate,categorieProdus);
                produse.add(produs1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean rezultat = true;
        for(Produs p : produse){
            if(p.equals(produs) || p.getId() == produs.getId()){
                rezultat = false;
                break;
            }
        }

        try {
            FileWriter writer = new FileWriter("database/output.txt");
            writer.write(String.valueOf(rezultat));
            writer.close();
            if(rezultat == true) {
                FileWriter produsW = new FileWriter("database/produse.txt", true);
                produsW.write(produs.toString());
                produsW.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

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