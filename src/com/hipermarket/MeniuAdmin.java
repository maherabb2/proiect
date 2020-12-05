package com.hipermarket;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MeniuAdmin extends MeniuAngajat {
    @Override
    public Meniu interpreteazaComanda(char c) {
        Meniu meniu = this;

        switch (c) {
            case '1':
                adauga();
                break;
        }

        return this;
    }

    @Override
    public void afisare() {
        System.out.println("com.hipermarket.Meniu Admin");
    }


    @Override
    public void comutareClient() {

    }

    @Override
    public void adauga() {
        System.out.println("Am intrat in adauga casier");
        Casier casier = null;

        File messages = new File("database/messages.txt");
        try {
            Scanner scanner = new Scanner(messages);
            String line = scanner.nextLine();


            String[] elemente = line.split(";");
            String user = elemente[0];
            String pass = elemente[1];
            casier = new Casier(user, pass);
            System.out.println("Casierul din message: " + casier.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ArrayList<Casier> casieri = new ArrayList<>();
        File casieriFile = new File("database/casieri.txt");
        try {
            Scanner scanner = new Scanner(casieriFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elemente = line.split(";");
                String user = elemente[0];
                String pass = elemente[1];

                Casier c = new Casier(user, pass);
                casieri.add(c);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        boolean rezutat = false;
        for (Casier c: casieri) {
            System.out.println("Casier: " + c.toString());
            if (! c.equals(casier)) {
                System.out.println("Casierul nu exista");
                rezutat = true;

                try {
                    FileWriter writer = new FileWriter(casieriFile, true);
                    writer.write(casier.toString());
                    writer.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Rezultat este: " + rezutat);

        try {
            File output = new File("database/output.txt");
            FileWriter writer = new FileWriter(output);
            writer.write(String.valueOf(rezutat));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listare() {

    }

    @Override
    public void sterge() {

    }

    private void verificareCasier() {

    }
}