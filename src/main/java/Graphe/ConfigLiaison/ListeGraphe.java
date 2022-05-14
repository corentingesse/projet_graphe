package Graphe.ConfigLiaison;

import Graphe.ConfigLiaison.Exceptions.ExceptionAjListeGraphe;
import Graphe.ConfigLieu.Lieu;
import Graphe.ConfigRoute.Route;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

public class ListeGraphe {
    private TreeMap <Lieu, ListeVoisin> listeGraphe;

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
            listeVoisin.ajLieu (lieu);
            listeVoisin.ajRouteVoisin (lieu, route);
        }

        else
            throw new ExceptionAjListeGraphe ();

    }

    public TreeMap <Lieu, ListeVoisin> getListeGraphe () {
        return listeGraphe;
    }

    public String toString () {
        Lieu lieu = null;
        for (Lieu lieuParcour : listeGraphe.keySet ()) {
            System.out.println("----------------------------------------------------------");
            System.out.println(lieuParcour);
            System.out.println("liste des voisins : ");
            System.out.println (listeGraphe.get (lieuParcour));
        }
        return " ";
    }
}
