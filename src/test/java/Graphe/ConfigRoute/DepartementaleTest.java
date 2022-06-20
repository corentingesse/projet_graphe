package Graphe.ConfigRoute;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartementaleTest {

    static Route autoroute = new Autoroute ("A315", 20);

    @Test
    void testToString() {
        assertEquals ("Nom : A315, Distance : 20km, Type : Autoroute", autoroute.toString ());
    }

    @Test
    void compareTo() {
        assertEquals (0, autoroute.compareTo (autoroute));
    }
}