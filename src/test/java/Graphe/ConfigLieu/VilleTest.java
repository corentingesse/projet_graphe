package Graphe.ConfigLieu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class VilleTest {

        static Lieu lieu = new Ville ("Lyon");

        @Test
        void testToString() {
            assertEquals ("Nom : Lyon, Type : Ville", lieu.toString ());
        }

        @Test
        void compareTo() {
            assertEquals (0, lieu.compareTo (lieu));
        }
    }