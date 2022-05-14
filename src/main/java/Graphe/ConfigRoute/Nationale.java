package Graphe.ConfigRoute;

public class Nationale extends Route {
    public Nationale() {
        super();
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomRoute() + ", Distance : " + super.getDistanceKm() + "km, Type : Nationale]";
    }

    @Override
    public int compareTo(Object o) {
        Route o1 = (Route) o;
        return getNomRoute ().compareTo (o1.getNomRoute ());
    }
}
