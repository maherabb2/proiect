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

    private void verificareCasier() {

    }
}