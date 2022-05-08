package Graphe.ConfigRoute;

public class Route {
    private String nomRoute;
    private int distanceKm;

    public Route(String nomRoute, int distanceKm) {
        this.nomRoute = nomRoute;
        this.distanceKm = distanceKm;
    }

    public String getNomRoute() {
        return nomRoute;
    }

    public int getDistanceKm() {
        return distanceKm;
    }

    public void setNomRoute(String nomRoute) {
        this.nomRoute = nomRoute;
    }

    public void setDistanceKm(int distanceKm) {
        this.distanceKm = distanceKm;
    }
}
