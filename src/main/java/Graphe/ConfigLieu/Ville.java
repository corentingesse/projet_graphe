package Graphe.ConfigLieu;

import Graphe.ConfigRoute.Route;

public class Ville extends Lieu {
    public Ville() {
        super();
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomLieu() + ", Type : Ville]";
    }

    @Override
    public int compareTo(Object o) {
        Lieu o1 = (Lieu) o;
        return getNomLieu ().compareTo (o1.getNomLieu ());
    }
}
