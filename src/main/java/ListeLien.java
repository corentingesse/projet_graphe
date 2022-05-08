import java.util.ArrayList;

public class ListeLien {
    private ArrayList <Lien> listeLien;
    private ArrayList <Departementale> listeDepartementale;
    private ArrayList <Nationale> listeNationale;
    private ArrayList <Autoroute> listeAutoroute; 

    public ListeLien () {
        listeLien = new ArrayList <Lien> ();
        listeDepartementale = new ArrayList <Departementale> ();
        listeNationale = new ArrayList <Nationale> ();
        listeAutoroute = new ArrayList <Autoroute> ();
    }

    public boolean estDansLaListe (String newNom) {
        int i = 0;
        boolean resultat = false;
        while (i < listeLien.size () && resultat == false) {
            if (listeLien.get(i).getNom().equals(newNom))
                resultat = true;
        }
        return resultat;
    }

    public void ajSommet (Lien newLien) {
        listeLien.add (newLien);
        switch (newLien.getType ()) {
            case 'D' :
                listeDepartementale.add((Departementale) newLien);
                break;
            case 'N':
                listeNationale.add((Nationale) newLien);
                break;
            case 'A':
                listeAutoroute.add((Autoroute) newLien);
                break;
            default:
                break;
        }
    }

    public ArrayList <Lien> listeLire () {
        return listeLien;
    }
}
