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
            int nbLien = -1;
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
            Lien lien;
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
                        lien = departementale;
                        nbLien = nbLien + 1;
                        break;
                    case 'N':
                        Lien nationale = new Nationale("nationale", 'N', 0.0);
                        listeLien.ajSommet(nationale);
                        lien = nationale;
                        nbLien = nbLien + 1;
                        break;
                    case 'A':
                        Lien autoroute = new Autoroute("autoroute", 'A', 0.0);
                        listeLien.ajSommet(autoroute);
                        lien = autoroute;
                        nbLien = nbLien + 1;
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
                listeLien.listeLire().get(nbLien).setNom(nomLien);
                if (Objects.equals(line.charAt(compteurCaractere), ',')) {
                    compteurCaractere = compteurCaractere + 1;
                    String lienValeur = "";
                    while (Objects.equals(line.charAt(compteurCaractere), ':') != true) {
                        lienValeur = lienValeur + line.charAt(compteurCaractere);
                        compteurCaractere = compteurCaractere + 1;
                    }
                    listeLien.listeLire().get(nbLien).setLienValeur(Double.parseDouble(lienValeur));
                    compteurCaractere = compteurCaractere + 1;
                    if (Objects.equals(line.charAt(compteurCaractere), ':')) {
                        String nomSommet = "";
                        compteurCaractere = compteurCaractere + 1;
                        switch (line.charAt(compteurCaractere)) {
                            case 'R':
                                compteurCaractere = compteurCaractere + 1;
                                if (Objects.equals(line.charAt(compteurCaractere), ',')) {
                                    compteurCaractere = compteurCaractere + 1;
                                    while (Objects.equals(line.charAt(compteurCaractere), ';') != true) {
                                        nomSommet = nomSommet + line.charAt(compteurCaractere);
                                        compteurCaractere = compteurCaractere + 1;
                                    }
                                    Sommet sommetVoisin = listeSommet.chercherSommetListeRestaurant(nomSommet);
                                    listeSommet.listeLire().get(nbLigne).ajVoisin(sommetVoisin, lien);
                                } else
                                    throw new ErreurFichier();
                                break;
                            case 'V':
                                compteurCaractere = compteurCaractere + 1;
                                if (Objects.equals(line.charAt(compteurCaractere), ',')) {
                                    compteurCaractere = compteurCaractere + 1;
                                    while (Objects.equals(line.charAt(compteurCaractere), ';') != true) {
                                        nomSommet = nomSommet + line.charAt(compteurCaractere);
                                        compteurCaractere = compteurCaractere + 1;
                                    }
                                    Sommet sommetVoisin = listeSommet.chercherSommetListeVille(nomSommet);
                                    listeSommet.listeLire().get(nbLigne).ajVoisin(sommetVoisin, lien);
                                } else
                                    throw new ErreurFichier();
                                break;
                            case 'L':
                                compteurCaractere = compteurCaractere + 1;
                                if (Objects.equals(line.charAt(compteurCaractere), ',')) {
                                    compteurCaractere = compteurCaractere + 1;
                                    while (Objects.equals(line.charAt(compteurCaractere), ';') != true) {
                                        nomSommet = nomSommet + line.charAt(compteurCaractere);
                                        compteurCaractere = compteurCaractere + 1;
                                    }
                                    Sommet sommetVoisin = listeSommet.chercherSommetListeLoisir(nomSommet);
                                    listeSommet.listeLire().get(nbLigne).ajVoisin(sommetVoisin, lien);
                                } else
                                    throw new ErreurFichier();
                                break;
                            default:
                                throw new ErreurFichier();
                        }

                    }
                    else
                        throw new ErreurFichier();

            }
            else
                throw new ErreurFichier();
            nbLigne = nbLigne + 1;
            }

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
            i = 0;
            while (i < listeLienLire.size()) {
                System.out.println(listeLienLire.get (i).getLienValeur ());
                i = i + 1;
            }
            System.out.println(listeSommetLire.get (i - 1).getHashMap ().get (listeSommetLire.get (i - 9)));
            fr.close();
            // System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

