package Graphe.ConfigRoute;

public class Departementale extends Route{
    public Departementale(String nomRoute, int distanceKm) {
        super(nomRoute, distanceKm);
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomRoute() + ", Distance : " + super.getDistanceKm() + "km, Type : Departementale]";
    }
}
