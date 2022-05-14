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

        public void ajLieu (Lieu lieu, Route liaison) throws ExceptionAjListeGraphe {
            if (lieu != null)
                listeVoisin.put (lieu, liaison);
            else
                throw new ExceptionAjListeGraphe ();

        }

        public String toString () {
            for (Lieu lieuVoisin : listeVoisin.keySet()) {
                System.out.print(lieuVoisin);
                System.out.print("-----------> ");
                System.out.print(" par : ");
                System.out.print (listeVoisin.get (lieuVoisin).getClass().getSimpleName());
                System.out.print(" " + listeVoisin.get(lieuVoisin).getNomRoute());
                System.out.print(" avec : ");
                System.out.println (listeVoisin.get(lieuVoisin).getDistanceKm());
            }
            return " ";
        }
}
