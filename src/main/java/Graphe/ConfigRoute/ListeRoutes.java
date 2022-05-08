package Graphe.ConfigRoute;

import Graphe.ConfigLiaison.Liaison;

import java.util.ArrayList;

public class ListeRoutes {
    ArrayList<Route> l;

    public ListeRoutes() {
        l = new ArrayList<Route>();
    }

    public void afficher(){
        for(int i = 0; i < l.size(); i++){
            System.out.println(l.get(i));
        }
    }

    public void ajouter(Route o){
        if(o != null && l.contains(o)){
            l.add(o);
        }
    }
}
