package Graphe.ConfigRoute;

public class Nationale extends Route {
    public Nationale(String nomRoute, int distanceKm) {
        super(nomRoute, distanceKm);
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomRoute() + ", Distance : " + super.getDistanceKm() + "km, Type : Nationale]";
    }
}
