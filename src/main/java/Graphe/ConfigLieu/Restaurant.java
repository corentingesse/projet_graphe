package Graphe.ConfigLieu;

public class Restaurant extends Lieu {
    public Restaurant(String nomLieu) {
        super(nomLieu);
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomLieu() + ", Type : Restaurant]";
    }
}
