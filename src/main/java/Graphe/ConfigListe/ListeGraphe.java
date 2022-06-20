package Graphe.ConfigListe;

import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;
import Graphe.ConfigLieu.Lieu;
import Graphe.ConfigRoute.Route;

import java.util.TreeMap;

public class ListeGraphe {
    private final TreeMap <Lieu, ListeVoisin> listeGraphe;

    public ListeGraphe () {
        listeGraphe = new TreeMap <Lieu, ListeVoisin> ();
    }

    public void ajLieu (Lieu lieu) throws ExceptionAjListeGraphe {
        if (lieu != null) {
            ListeVoisin listeVoisin = new ListeVoisin ();
            listeGraphe.put (lieu, listeVoisin);
        }
        else
            throw new ExceptionAjListeGraphe ();

    }

    public void ajListeVoisin (Lieu lieuOrigine, Lieu lieu, Route route) throws ExceptionAjListeGraphe {
        if (lieuOrigine != null && lieu != null) {
            ListeVoisin listeVoisin = listeGraphe.get(lieuOrigine);
            listeVoisin.ajLieu (lieu, route);
        }

        else
            throw new ExceptionAjListeGraphe ();

    }

    public void afficher(){
        for (Lieu lieuParcour : listeGraphe.keySet ()) {
            System.out.println("----------------------------------------------------------");
            System.out.println ("Origine : ");
            System.out.println(lieuParcour);
            System.out.println("liste des voisins : ");
            listeGraphe.get(lieuParcour).afficher();
        }
    }

    public TreeMap <Lieu, ListeVoisin> getListeGraphe () {
        return listeGraphe;
    }

}
