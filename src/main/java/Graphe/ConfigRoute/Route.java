package Graphe.ConfigRoute;

public class Route implements Comparable {
    private String nomRoute;
    private int distanceKm;

    public Route() {
        this.nomRoute = null;
        this.distanceKm = 0;
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

    @Override
    public int compareTo(Object o) {
        Route o1 = (Route) o;
        return getNomRoute ().compareTo (o1.getNomRoute ());
    }
}
