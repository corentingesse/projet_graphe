package Graphe.ConfigListe;

import Graphe.ConfigLieu.Lieu;
import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;
import Graphe.ConfigRoute.Route;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListeVoisinTest {

    ListeVoisin listeVoisin = new ListeVoisin ();
    Lieu lieu = new Lieu ("Lyon");

    Route route = new Route ("D315", 20);

    @Test
    void ajLieu() throws ExceptionAjListeGraphe {
        listeVoisin.ajLieu (lieu, route);
        assertEquals (route, listeVoisin.getListeVoisin ().get (lieu));
    }

    @Test
    void afficher() {
    }

    @Test
    void getListeVoisin () throws ExceptionAjListeGraphe {
        ListeVoisin listeVoisin1 = new ListeVoisin ();
        listeVoisin1.ajLieu (lieu, route);
        listeVoisin.ajLieu (lieu, route);
        assertEquals (listeVoisin1.getListeVoisin ().keySet (),  listeVoisin.getListeVoisin ().keySet ());

    }
}