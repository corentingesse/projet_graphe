package Graphe.ConfigLiaison;

import java.util.ArrayList;

public class ListeLiaison {
    ArrayList<Liaison> l;

    public ListeLiaison() {
        l = new ArrayList<Liaison>();
    }

    public void afficher(){
        for(int i = 0; i < l.size(); i++){
            System.out.println(l.get(i));
        }
    }

    public void ajouter(Liaison o){
        if(o != null && !l.contains(o)){
            l.add(o);
        }
    }
}
