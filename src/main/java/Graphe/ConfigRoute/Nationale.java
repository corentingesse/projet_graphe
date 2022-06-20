package Graphe.ConfigRoute;

public class Nationale extends Route {
    public Nationale() {
        super();
    }

    public Nationale (String nomRoute, int distance) {
        super.setNomRoute(nomRoute);
        super.setDistanceKm(distance);
    }

    @Override
    public String toString() {
        return "Nom : " + super.getNomRoute() + ", Distance : " + super.getDistanceKm() + "km, Type : Nationale";
    }

    @Override
    public int compareTo(Object o) {     // compare deux nationales pour savoir ou les placer dans la listes des voisins
        Route o1 = (Route) o;
        return getNomRoute ().compareTo (o1.getNomRoute ());
    }
}
