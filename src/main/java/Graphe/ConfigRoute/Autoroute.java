package Graphe.ConfigRoute;

public class Autoroute extends Route {
    public Autoroute() {
        super();
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomRoute() + ", Distance : " + super.getDistanceKm() + "km, Type : Autoroute]";
    }
}
