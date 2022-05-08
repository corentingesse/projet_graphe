public class Lien {
    private String nom;
    private Character type;
    private double lienValeur;
    public Lien (String newNom, Character newType, double newLienValeur) {
        nom = newNom;
        type = newType;
        lienValeur = newLienValeur;
    }
    public String getNom () {
        return nom;
    }

    public Character getType () {
        return type;
    }

    public double getLienValeur () {
        return lienValeur;
    }
    public void setNom (String newNom) {
        nom = newNom;
    }

    public void setType (Character newType) {
        type = newType;
    }

    public void setLienValeur (double newLienValeur) {
        lienValeur = newLienValeur;
    }
}
