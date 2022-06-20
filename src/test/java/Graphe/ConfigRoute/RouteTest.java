package Graphe.ConfigRoute;

import Graphe.ConfigLieu.Lieu;
import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {

        static Route route = new Route ("D315", 20);

        @BeforeClass
        public static void setUpClass() throws IOException, ExceptionAjListeGraphe {
        }
        @Test
        void getNomRoute () {
            assertEquals("D315", route.getNomRoute ());
        }

        @Test
        void setNomRoute () {
            route.setNomRoute ("D315");
            assertEquals("D315" , route.getNomRoute ());
        }
    @Test
    void getDistanceKm() {
            assertEquals(30, route.getDistanceKm ());
    }

    @Test
    void setDistanceKm() {
        route.setDistanceKm (30);
        assertEquals(30 , route.getDistanceKm ());
    }

    @Test
    void compareTo() {
        assertEquals (0, route.compareTo (route));
    }
}