package Graphe.ConfigRoute;

public class Departementale extends Route{
    public Departementale() {
        super();
    }

    @Override
    public String toString() {
        return "Nom : " + super.getNomRoute() + ", Distance : " + super.getDistanceKm() + "km, Type : Departementale";
    }

    @Override
    public int compareTo(Object o) {
        Route o1 = (Route) o;
        return getNomRoute ().compareTo (o1.getNomRoute ());
    }
}
