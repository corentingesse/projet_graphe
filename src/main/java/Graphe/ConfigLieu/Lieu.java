package Graphe.ConfigLieu;

public class Lieu implements Comparable {
    private String nomLieu;

    public Lieu() {
        this.nomLieu = null;
    }

    public Lieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    @Override
    public int compareTo(Object o) {
        Lieu o1 = (Lieu) o;
        return getNomLieu ().compareTo (o1.getNomLieu ());
    }
}
