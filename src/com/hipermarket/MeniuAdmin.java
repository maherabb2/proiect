package com.hipermarket;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MeniuAdmin extends MeniuAngajat {
    @Override
    public Meniu interpreteazaComanda(char c) {
        Meniu meniu = this;

        switch (c) {
            case '1':
                adauga();
                break;
            case '3':
                listare();
                break;
            case '2':
                sterge();
                break;
            case '4':
                totalVanzari();
                break;
            case'8':
                verificareCasier();
                break;
            case '0':
                meniu = new MeniuPrincipal();
            default:
                System.out.println("Optiune invalida!");
        }

        return meniu;
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean rezutat = false;
        for (Casier c : casieri) {
            System.out.println("Casier: " + c.toString());
            if (!c.equals(casier)) {
                System.out.println("Casierul nu exista");
                rezutat = true;
                break;
            }
        }

        if (rezutat == true) {
            try {
                FileWriter writer = new FileWriter(casieriFile, true);
                writer.write(casier.toString());
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
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
        File file = new File("database/casieri.txt");
        ArrayList<Casier> casiers = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String []elemente = line.split(";");

                String username = elemente[0];
                String parola = elemente[1];

                Casier casier = new Casier(username, parola);
                casiers.add(casier);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("database/output.txt");
            for (Casier c : casiers) {
                writer.write(c.toString());

            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void sterge() {
        File file = new File("database/casieri.txt");
        ArrayList<Casier> casiers = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String []elemente = line.split(";");

                String username = elemente[0];
                String parola = elemente[1];

                Casier casier = new Casier(username, parola);
                casiers.add(casier);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            File fisier=new File("database/messages.txt");
            Scanner scanner=new Scanner(fisier);
            String line= scanner.nextLine();
            boolean rezultat=false;
            for(Iterator it=casiers.iterator();it.hasNext();){
                Casier c=(Casier)it.next();
                if(c.getUser().equals(line)){
                    it.remove();
                    rezultat=true;
                    break;
                }
            }

            File fisier1=new File("database/output.txt");
            FileWriter scrie=new FileWriter(fisier1);
            scrie.write(String.valueOf(rezultat));
            scrie.close();

            File casieri=new File("database/casieri.txt");
            FileWriter scrieCasieri=new FileWriter(casieri);
            for(Casier c:casiers){
            scrieCasieri.write(c.toString());
            }
            scrieCasieri.close();
        }catch(Exception ex){
            ex.getStackTrace();
        }
    }

    private void totalVanzari() {
        File vanzari = new File("database/vanzari.txt");

        double total = 0;

        try {
            Scanner scanner = new Scanner(vanzari);
            while (scanner.hasNextLine()) {
                double value = Double.parseDouble(scanner.nextLine());
                total += value;
            }

            FileWriter writer = new FileWriter("database/output.txt");
            writer.write(String.valueOf(total));
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void verificareCasier() {
        System.out.println("Cautare si verificare casier");

        String user = null, pass = null;

        File messages = new File("database/messages.txt");
        try {
            Scanner scanner = new Scanner(messages);
            String line = scanner.nextLine();


            String[] elemente = line.split(";");
            user = elemente[0];
            pass = elemente[1];
        } catch (Exception ex) {
            ex.printStackTrace();
        }



        File fisierProduse = new File("database/casieri.txt");
        try {
            Scanner scanner = new Scanner(fisierProduse);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elemente = line.split(";");

                String u = elemente[0];
                String p = elemente[1];

                if (u.equals(user) && p.equals(pass)){
                    try {
                        FileWriter scrie = new FileWriter("database/output.txt");
                        scrie.write(String.valueOf(true));
                        scrie.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}