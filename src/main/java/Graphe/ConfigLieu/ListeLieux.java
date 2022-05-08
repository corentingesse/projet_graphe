package Graphe.ConfigLieu;

import Graphe.ConfigLiaison.Liaison;

import java.util.ArrayList;

public class ListeLieux {
    ArrayList<Lieu> l;

    public ListeLieux(){
        l = new ArrayList<Lieu>();
    }

    public void afficher(){
        for(int i = 0; i < l.size(); i++){
            System.out.println(l.get(i));
        }
    }

    public void ajouter(Lieu o){
        if(o != null && l.contains(o)){
            l.add(o);
        }
    }
}
