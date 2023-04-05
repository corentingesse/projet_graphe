package Graphe.ConfigListe;

import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;
import Graphe.ConfigLieu.Lieu;
import Graphe.ConfigRoute.Route;

import java.util.TreeMap;

public class ListeVoisin {
        private final TreeMap<Lieu, Route> listeVoisin;

        public ListeVoisin () {
            listeVoisin = new TreeMap <Lieu, Route> ();
        }

        public void ajLieu (Lieu lieu, Route liaison) throws ExceptionAjListeGraphe {    // ajoute un voisin à la liste des voisins
            if (lieu != null)
                listeVoisin.put (lieu, liaison);
            else
                throw new ExceptionAjListeGraphe ();

        }

        public void afficher(){
            for (Lieu lieuVoisin : listeVoisin.keySet()) {
                System.out.print(lieuVoisin);
                System.out.print("-----------> ");
                System.out.print(" par : ");
                System.out.print (listeVoisin.get (lieuVoisin).getClass().getSimpleName());
                System.out.print(" " + listeVoisin.get(lieuVoisin).getNomRoute());
                System.out.print(" avec : ");
                System.out.println (listeVoisin.get(lieuVoisin).getDistanceKm());
            }
        }

        public TreeMap<Lieu, Route> getListeVoisin () {
            return listeVoisin;
        }
}
