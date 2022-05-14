package Graphe.ConfigLiaison;

import Graphe.ConfigLiaison.Exceptions.ExceptionAjListeGraphe;
import Graphe.ConfigLieu.Lieu;
import Graphe.ConfigRoute.Route;

import java.util.TreeMap;

public class ListeVoisin {
        private TreeMap<Lieu, Route> listeVoisin;

        public ListeVoisin () {
            listeVoisin = new TreeMap <Lieu, Route> ();
        }

        public void ajLieu (Lieu lieu) throws ExceptionAjListeGraphe {
            if (lieu != null && listeVoisin.containsKey (lieu) != true)
                listeVoisin.put (lieu, null);
            else
                throw new ExceptionAjListeGraphe ();

        }

        public void ajRouteVoisin (Lieu lieu, Route liaison) throws ExceptionAjListeGraphe {
            if (lieu != null && liaison != null && listeVoisin.get (lieu) == null)
                listeVoisin.replace(lieu, liaison);
            else
                throw new ExceptionAjListeGraphe ();

        }

        public String toString () {
            for (Lieu lieuVoisin : listeVoisin.keySet()) {
                System.out.println(lieuVoisin);
                System.out.println("-----------> ");
                System.out.print(" par : ");
                System.out.print(listeVoisin.get(lieuVoisin).getNomRoute());
                System.out.print(" avec : ");
                System.out.print(listeVoisin.get(lieuVoisin).getDistanceKm());
            }
            return " ";
        }
}
