
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Corentin
 */
public class Sommet {
    private String nom;
    private Character type;
    private ArrayList <Sommet> listeSommetsVoisins;
    private ArrayList <Lien> listeLiensVoisins;
    public Sommet (String newNom, Character newType) {
        nom = newNom;
        type = newType;
    }

    private boolean ajSommetVoisin (Sommet sommetVoisin) {
        if (sommetVoisin != null) {
            listeSommetsVoisins.add(sommetVoisin);
            return true;
        }
        else
            return false;
    }

    private boolean ajLienVoisin (Lien lienVoisin) {
        if (lienVoisin != null) {
            listeLiensVoisins.add(lienVoisin);
            return true;
        }
        else
            return false;
    }

    public boolean ajVoisin (Sommet sommetVoisin, Lien lienVoisin) {
        if (ajSommetVoisin (sommetVoisin) && ajLienVoisin (lienVoisin))
            return true;
        else
            return false;
    }

    public String getNom () {
        return nom;
    }

    public Character getType () {
        return type;
    }

    public void setNom (String newNom) {
        nom = newNom;
    }

    public void setType (Character newType) {
        type = newType;
    }
}

