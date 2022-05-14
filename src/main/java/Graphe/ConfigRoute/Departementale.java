package Graphe.ConfigRoute;

public class Departementale extends Route{
    public Departementale() {
        super();
    }

    @Override
    public String toString() {
        return "[Nom : " + super.getNomRoute() + ", Distance : " + super.getDistanceKm() + "km, Type : Departementale]";
    }
}
