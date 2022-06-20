package Graphe.ConfigRoute;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NationaleTest {

    static Route nationale = new Nationale ("N315", 20);

    @Test
    void testToString() {
        assertEquals ("Nom : N315, Distance : 20km, Type : Nationale", nationale.toString ());
    }

    @Test
    void compareTo() {
        assertEquals (0, nationale.compareTo (nationale));
    }
}