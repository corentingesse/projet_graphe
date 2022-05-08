package Graphe.ConfigLieu;

public class Loisir extends Lieu {
    public Loisir(String nomLieu) {
        super(nomLieu);
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomLieu() + ", Type : Loisir]";
    }
}
