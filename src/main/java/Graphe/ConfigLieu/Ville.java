package Graphe.ConfigLieu;

public class Ville extends Lieu {
    public Ville() {
        super();
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomLieu() + ", Type : Ville]";
    }
}
