
import java.util.ArrayList;
import java.util.HashMap;

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
    private HashMap <Sommet, Lien> listeVoisin;
    public Sommet (String newNom, Character newType) {
        nom = newNom;
        type = newType;
        listeVoisin = new HashMap <Sommet, Lien> ();
    }

    public void ajVoisin (Sommet sommetVoisin, Lien lienVoisin) {
        if (sommetVoisin != null && lienVoisin != null)
            listeVoisin.put (sommetVoisin, lienVoisin);
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

