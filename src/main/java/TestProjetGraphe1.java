/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.*;
import java.util.*;

/**
 *
 * @author Corentin
 */
public class TestProjetGraphe1 {
    public static void main(String[] args) throws IOException, ErreurFichier {
        ListeSommet listeSommet = new ListeSommet();
        ListeLien listeLien = new ListeLien();
        /* File file = new File("src/main/ressources/graphe.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int nbVirgules = 0;
        char c;
        int nbVille = 0;
        int nbLoisir = 0;
        int nbRestaurant = 0;
        ListeSommet listeSommet1 = new ListeSommet ();
        while ((c = (char) br.read()) != -1) {
            char ch = (char) c;
            switch (c) {
                case 'V' :
                    if (((c = (char) br.read()) != -1) && c == ',') { 
                        String nomVille = "";
                        while ((c = (char) br.read()) != -1 && c != ':')
                            nomVille = nomVille + c;
                        Ville villeSommet = new Ville(nomVille, "V");
                        listeSommet1.ajSommet (villeSommet);
                        while ((c = (char) br.read()) != -1) { 
                            switch (c) {
                                case 'D' :
                                    String nomLien = "";
                                    while (((c = (char) br.read())!= -1) && c != ':')
                                        nomLien = nomLien + c;
                                    Departementale departementaleLien = new Departementale (nomLien, "D");
                                    if (((c = (char) br.read()) != -1) && c == ':')
                                        if (((c = (char) br.read()) != -1) && c == ':')
                                            nomVille = "";
                                            while ((c = (char) br.read()) != -1 && c != ':')
                                                nomVille = nomVille + c;
                                            if (listeSommet1.estDansLaListe (nomVille) != true)
                                                Ville vi = new Ville(nomVille, "V");
                                                System.out.println(ville[nbVille].getNom ());
                                            else

                    break;
                case 'L' :
                    if (((c = (char) br.read()) != -1) && c == ',') {
                        String nomLoisir = "";
                        while ((c = (char) br.read()) != -1 && c != ':')
                            nomLoisir = nomLoisir + c;
                        Loisir loisir = new Loisir(nomLoisir, "L");
                    }
                    break;
                case 'R' :
                    if (((c = (char) br.read()) != -1) && c == ',') {
                        String nomRestaurant = "";
                        while ((c = (char) br.read()) != -1 && c != ':')
                            nomRestaurant = nomRestaurant + c;
                        Restaurant restaurant = new Restaurant(nomRestaurant, "L");
                    }
                    break;
            }


        }

         */

        try {
            // Le fichier d'entrée
            File file = new File("src/main/ressources/graphe.csv");
            // Créer l'objet File Reader
            FileReader fr = new FileReader(file);
            // Créer l'objet BufferedReader
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            int nbLigne = 0;
            while ((line = br.readLine()) != null) {
                switch (line.charAt(0)) {
                    case 'R':
                        Sommet restaurant = new Restaurant("restaurant", 'R');
                        listeSommet.ajSommet(restaurant);
                        break;
                    case 'V':
                        Sommet ville = new Ville("ville", 'V');
                        listeSommet.ajSommet(ville);
                        break;
                    case 'L':
                        Sommet loisir = new Loisir("loisir", 'L');
                        listeSommet.ajSommet(loisir);
                        break;
                    default:
                        throw new ErreurFichier();
                }
                if (Objects.equals(line.charAt(1), ',')) {
                    String nomSommet = "";
                    int i = 2;
                    while (Objects.equals(line.charAt(i), ':') != true) {
                        nomSommet = nomSommet + line.charAt(i);
                        i = i + 1;
                    }
                    listeSommet.listeLire().get(nbLigne).setNom(nomSommet);
                } else
                    throw new ErreurFichier();
                nbLigne = nbLigne + 1;

            }
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            sb = new StringBuffer();
            nbLigne = 0;
            int i = 0;
            while ((line = br.readLine()) != null) {
                int compteurCaractere = 0;
                while (Objects.equals(line.charAt(compteurCaractere), ':') != true) {
                    compteurCaractere = compteurCaractere + 1;
                }
                compteurCaractere = compteurCaractere + 1;
                switch (line.charAt(compteurCaractere)) {
                    case 'D':
                        Lien departementale = new Departementale("departementale", 'D', 0.0);
                        listeLien.ajSommet(departementale);
                        break;
                    case 'N':
                        Lien nationale = new Nationale("nationale", 'N', 0.0);
                        listeLien.ajSommet(nationale);
                        break;
                    case 'A':
                        Lien autoroute = new Autoroute("autoroute", 'A', 0.0);
                        listeLien.ajSommet(autoroute);
                        break;
                    default:
                        throw new ErreurFichier();
                }
                String nomLien = "";
                compteurCaractere = compteurCaractere + 1;
                while (Objects.equals(line.charAt(compteurCaractere), ',') != true) {
                    nomLien = nomLien + line.charAt(compteurCaractere);
                    compteurCaractere = compteurCaractere + 1;
                }
                listeLien.listeLire().get(nbLigne).setNom(nomLien);
                compteurCaractere = compteurCaractere + 1;
                if (Objects.equals(line.charAt(compteurCaractere), ',')) {
                    String lienValeur = "";
                    compteurCaractere = compteurCaractere + 1;
                    while (Objects.equals(line.charAt(compteurCaractere), ':') != true) {
                        lienValeur = lienValeur + line.charAt(compteurCaractere);
                        compteurCaractere = compteurCaractere + 1;
                    }
                    listeLien.listeLire().get(nbLigne).setLienValeur(Double.parseDouble(lienValeur));
                } else
                    //throw new ErreurFichier();
                    nbLigne = nbLigne + 1;
                compteurCaractere = 0;

            }
            /*
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            sb = new StringBuffer();
            nbLigne = 0;
            int i = 0;
            while ((line = br.readLine()) != null) {
                int compteurCaractere = 0;
                while (Objects.equals(line.charAt(compteurCaractere), ':') != true) {
                    compteurCaractere = compteurCaractere + 1;
                }
                compteurCaractere = compteurCaractere + 1;
                switch (line.charAt(compteurCaractere)) {
                    case 'D':
                        Lien departementale = new Departementale("departementale", 'D', 0.0);
                        listeLien.ajSommet(departementale);
                        break;
                    case 'N':
                        Lien nationale = new Nationale("nationale", 'N', 0.0);
                        listeLien.ajSommet(nationale);
                        break;
                    case 'A':
                        Lien autoroute = new Autoroute("autoroute", 'A', 0.0);
                        listeLien.ajSommet(autoroute);
                        break;
                    default:
                        throw new ErreurFichier();
                }
                String nomLien = "";
                compteurCaractere = compteurCaractere + 1;
                while (Objects.equals(line.charAt(compteurCaractere), ',') != true) {
                    nomLien = nomLien + line.charAt(compteurCaractere);
                    compteurCaractere = compteurCaractere + 1;
                }
                listeLien.listeLire().get(nbLigne).setNom(nomLien);
                compteurCaractere = compteurCaractere + 1;
                if (Objects.equals(line.charAt(compteurCaractere), ',')) {
                    String lienValeur = "";
                    compteurCaractere = compteurCaractere + 1;
                    while (Objects.equals(line.charAt(compteurCaractere), ':') != true) {
                        lienValeur = lienValeur + line.charAt(compteurCaractere);
                        compteurCaractere = compteurCaractere + 1;
                    }
                    listeLien.listeLire().get(nbLigne).setLienValeur(Double.parseDouble(lienValeur));
                } else
                    //throw new ErreurFichier();
                    nbLigne = nbLigne + 1;
                compteurCaractere = compteurCaractere + 1;
                if (Objects.equals(line.charAt(compteurCaractere), ';')) {
                    String nomSommet = "";
                    compteurCaractere = compteurCaractere + 1;
                    while (Objects.equals(line.charAt(compteurCaractere), ':') != true) {
                        nomSommet = nomSommet + line.charAt(compteurCaractere);
                        compteurCaractere = compteurCaractere + 1;
                    }
                    listeSommet.listeLire
                compteurCaractere = 0;

            }

             */
            ArrayList<Sommet> listeSommetLire = listeSommet.listeLire();
            i = 0;
            while (i < listeSommetLire.size()) {
                System.out.println(listeSommetLire.get(i).getNom());
                i = i + 1;
            }
            ArrayList<Lien> listeLienLire = listeLien.listeLire();
            i = 0;
            while (i < listeLienLire.size()) {
                System.out.println(listeLienLire.get(i).getNom());
                i = i + 1;
            }
            fr.close();
            // System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

