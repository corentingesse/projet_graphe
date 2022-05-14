package Graphe.ConfigLieu;

public class Restaurant extends Lieu {
    public Restaurant() {
        super();
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomLieu() + ", Type : Restaurant]";
    }
}
