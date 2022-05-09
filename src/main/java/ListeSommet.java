import java.util.ArrayList;

public class ListeSommet {
    private ArrayList<Sommet> listeSommets;
    private ArrayList <Restaurant> listeRestaurants;
    private ArrayList <Ville> listeVilles;
    private ArrayList <Loisir> listeLoisirs;

    public ListeSommet () {
        listeSommets = new ArrayList <Sommet> ();
        listeRestaurants = new ArrayList <Restaurant> ();
        listeVilles = new ArrayList <Ville> ();
        listeLoisirs = new ArrayList <Loisir> ();
    }

    public boolean estDansLaListe (String newNom) {
        int i = 0;
        boolean resultat = false;
        while (i < listeSommets.size () && resultat == false) {
            if (listeSommets.get(i).getNom().equals(newNom))
                resultat = true;
        }
        return resultat;
    }

    public Sommet chercheSommetListe (String newNom) {
        int i = 0;
        while (i < listeSommets.size ()) {
            if (listeSommets.get(i).getNom().equals(newNom))
                return listeSommets.get (i);
        }
        return null;
    }

    public Sommet chercherSommetListeVille (String newNom) {
        int i = 0;
        while (i < listeVilles.size ()) {
            if (listeVilles.get(i).getNom().equals(newNom))
                return listeVilles.get (i);
            i = i + 1;
        }
        return null;
    }

    public Sommet chercherSommetListeRestaurant (String newNom) {
        int i = 0;
        while (i < listeRestaurants.size ()) {
            if (listeRestaurants.get(i).getNom().equals(newNom))
                return listeRestaurants.get (i);
        }
        return null;
    }

    public Sommet chercherSommetListeLoisir (String newNom) {
        int i = 0;
        while (i < listeLoisirs.size ()) {
            if (listeLoisirs.get(i).getNom().equals(newNom))
                return listeLoisirs.get (i);
        }
        return null;
    }

    public void ajSommet (Sommet newSommet) {
        listeSommets.add (newSommet);
        switch (newSommet.getType ()) {
            case 'V':
                listeVilles.add((Ville) newSommet);
                break;
            case 'R':
                listeRestaurants.add((Restaurant) newSommet);
                break;
            case 'L':
                listeLoisirs.add((Loisir) newSommet);
                break;
            default:
                break;
        }
    }

    public ArrayList <Sommet> listeLire () {
        return listeSommets;
    }

}
