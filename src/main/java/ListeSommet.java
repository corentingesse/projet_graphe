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
