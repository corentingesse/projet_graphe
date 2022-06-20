package Graphe.ConfigLieu;

public class Restaurant extends Lieu {
    public Restaurant() {
        super();
    }

    public Restaurant (String nomLieu) {
        super(nomLieu);
    }

    @Override
    public String toString() {
        return "Nom : " + super.getNomLieu() + ", Type : Restaurant";
    }

    @Override
    public int compareTo(Object o) {    // compare deux restaurants pour savoir ou les placer dans la liste
        Lieu o1 = (Lieu) o;
        return getNomLieu ().compareTo (o1.getNomLieu ());
    }
}
