package Graphe.ConfigLieu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoisirTest {

    static Lieu lieu = new Loisir ("Lyon");

    @Test
    void testToString() {
        assertEquals ("Nom : Lyon, Type : Loisir", lieu.toString ());
    }

    @Test
    void compareTo() {
        assertEquals (0, lieu.compareTo (lieu));
    }
}