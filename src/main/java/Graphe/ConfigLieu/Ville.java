package Graphe.ConfigLieu;

import Graphe.ConfigLieu.Lieu;

public class Ville extends Lieu {
    public Ville(String nomLieu) {
        super(nomLieu);
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomLieu() + ", Type : Ville]";
    }
}
