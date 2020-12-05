package com.hipermarket;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MeniuClient implements Meniu {
     private ArrayList<Produs> cos;

     MeniuClient() {
         cos = new ArrayList<>();
     }

    @Override
    public void afisare() {
        System.out.println("com.hipermarket.Meniu Client");
    }

    @Override
    public Meniu interpreteazaComanda(char c) {
        Meniu meniu = this;

        switch (c) {
            case '1':
                scaneazaProdus();
                break;
            case '2':
                finalizarePlata();
            case '4':
                totalPlata();
                break;
            case '5':
                stergeProdus();
            case '6':
                anulareCumparaturi();
            case '8':
                verificareCasier();
                break;
            case '9':
                verificareAdmin();
                break;
            case '0':
                // Aici menu ar trebui sa aiba valloarea com.hipermarket.MeniuPrincipal
                break;
            default:
                System.out.println(this.getClass().getName() + " - Optiune invalida");
        }

        return meniu;
    }

    private void scaneazaProdus() {
        System.out.println("Clientul a scanat un produs");

        int produsId = 0;
        float produsCantitate = 0;

        File messages = new File("database/messages.txt");
        try {
            Scanner scanner = new Scanner(messages);
            String line = scanner.nextLine();


            String[] elemente = line.split(";");
            produsId = Integer.parseInt(elemente[0]);
            produsCantitate = Float.parseFloat(elemente[1]);
            System.out.println("Id-ul meu este: " + produsId);
            System.out.println("Cantintatea mea este: " + produsCantitate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ArrayList<Produs> produse = new ArrayList<>(); // array-ul va fi gol

        File fisierProduse = new File("database/produse.txt");
        try {
            Scanner scanner = new Scanner(fisierProduse);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elemente = line.split(";");

                int id = Integer.parseInt(elemente[0]);
                String nume = elemente[1];
                float pret = Float.parseFloat(elemente[2]);
                float cantitate = Float.parseFloat(elemente[3]);
                TipCantitate tipCantitate = TipCantitate.fromInt(Integer.parseInt(elemente[4]));
                CategorieProdus categorieProdus = CategorieProdus.fromInt(Integer.parseInt(elemente[5]));

                Produs produs = new Produs(id, nume, pret, cantitate, tipCantitate, categorieProdus);
                produse.add(produs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (Produs produs: produse) {
            if (produs.getId() == produsId) {
                System.out.println("Am gasit un produs: " + produs.toString());
                produs.setCantitate(produsCantitate);
                cos.add(produs);

                try {
                    FileWriter scrie = new FileWriter("database/output.txt");
                    scrie.write(produs.toString());
                    scrie.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void totalPlata() {
        System.out.println("Clientul a cerut totalul de plata");
        // Aici facem suma tututror produselor din cos
        double suma=0;
        for (Produs p:cos){
           suma+=p.getPret()*p.getCantitate();
        }try{FileWriter scrie=new FileWriter("database/output.txt");
        scrie.write(String.valueOf(suma));
        scrie.close();}catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void stergeProdus() {
        System.out.println("Clientul a cerut stergerea unui produs");

        
    }

    private void anulareCumparaturi() {
            System.out.println("Clientul a cerut anularea cumparaturilor");
    }

    private void finalizarePlata() {
        System.out.println("Clientul a cerut finalizare plata");
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

    private void verificareAdmin() {
        System.out.println("Cautare si verificare admin");
    }
}