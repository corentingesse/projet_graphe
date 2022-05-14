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

    public Lieu getLieu1() {
        return lieu1;
    }

    public Route getRoute() {
        return route;
    }

    public Lieu getLieu2() {
        return lieu2;
    }

    public void setLieu1(Lieu lieu1) {
        this.lieu1 = lieu1;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setLieu2(Lieu lieu2) {
        this.lieu2 = lieu2;
    }

    @Override
    public String toString() {
        return lieu1 + " -- " + route + " -- " + lieu2;
    }
}
