package Graphe.ConfigLiaison;

import Graphe.ConfigLieu.Lieu;
import Graphe.ConfigRoute.Route;

public class Liaison {
    private Lieu lieu1;
    private Route route;
    private Lieu lieu2;

    public Liaison(Lieu lieu1, Route route, Lieu lieu2) {
        this.lieu1 = lieu1;
        this.route = route;
        this.lieu2 = lieu2;
    }

    @Override
    public String toString() {
        return lieu1 + " -- " + route + " --> " + lieu2;
    }
}
