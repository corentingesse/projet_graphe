package Graphe.ConfigLieu;

import Graphe.ConfigRoute.Route;

public class Ville extends Lieu {
    public Ville() {
        super();
    }

    public Ville (String nomLieu) {
        super(nomLieu);
    }

    @Override
    public String toString() {
        return "Nom : " + super.getNomLieu() + ", Type : Ville";
    }

    @Override
    public int compareTo(Object o) {   // compare deux villes pour savoir ou les placer dans la liste
        Lieu o1 = (Lieu) o;
        return getNomLieu ().compareTo (o1.getNomLieu ());
    }
}
