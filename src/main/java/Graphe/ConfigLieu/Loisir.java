package Graphe.ConfigLieu;

public class Loisir extends Lieu {
    public Loisir() {
        super();
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomLieu() + ", Type : Loisir]";
    }
}
