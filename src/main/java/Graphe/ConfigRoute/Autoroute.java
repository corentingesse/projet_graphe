package Graphe.ConfigRoute;

public class Autoroute extends Route {
    public Autoroute() {
        super();
    }

    public Autoroute (String nomRoute, int distance) {
        super.setNomRoute(nomRoute);
        super.setDistanceKm(distance);
    }

    @Override
    public String toString() {
        return "Nom : " + super.getNomRoute() + ", Distance : " + super.getDistanceKm() + "km, Type : Autoroute";
    }

    @Override
    public int compareTo(Object o) {    // compare deux autoroutes pour savoir ou les placer dans la listes des voisins
        Route o1 = (Route) o;
        return getNomRoute ().compareTo (o1.getNomRoute ());
    }
}
