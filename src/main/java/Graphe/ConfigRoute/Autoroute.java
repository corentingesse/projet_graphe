package Graphe.ConfigRoute;

public class Autoroute extends Route {
    public Autoroute(String nomRoute, int distanceKm) {
        super(nomRoute, distanceKm);
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomRoute() + ", Distance : " + super.getDistanceKm() + "km, Type : Autoroute]";
    }
}
