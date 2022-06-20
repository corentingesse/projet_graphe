package Graphe.ConfigListe;

import Graphe.ConfigLieu.Lieu;
import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;
import Graphe.ConfigRoute.Route;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ListeGrapheTest {

    ListeGraphe listeGraphe = new ListeGraphe ();
    Lieu lieu = new Lieu ("Lyon");
    ListeVoisin listeVoisin = new ListeVoisin ();

    Route route = new Route ("D315", 20);

    @Test
    void ajLieu() throws ExceptionAjListeGraphe {
        listeGraphe.ajLieu (lieu);
        assertEquals (listeVoisin.getListeVoisin(), listeGraphe.getListeGraphe().get (lieu).getListeVoisin());
    }

    @Test
    void ajListeVoisin() throws ExceptionAjListeGraphe {
        Lieu lieuVoisin = new Lieu ("Marseille");
        ListeVoisin listeVoisin1 = new ListeVoisin ();
        listeVoisin1.ajLieu (lieuVoisin, route);
        listeGraphe.ajListeVoisin (lieu, lieuVoisin, route);
        assertEquals (listeVoisin1.getListeVoisin (), listeGraphe.getListeGraphe ().get (lieu).getListeVoisin ());
    }

    @Test
    void afficher() {
    }

    @Test
    void getListeGraphe() {
    }
}