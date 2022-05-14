package Graphe.ConfigLieu;

public class Restaurant extends Lieu {
    public Restaurant() {
        super();
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomLieu() + ", Type : Restaurant]";
    }

    @Override
    public int compareTo(Object o) {
        Lieu o1 = (Lieu) o;
        return getNomLieu ().compareTo (o1.getNomLieu ());
    }
}
