package Graphe.ConfigLieu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

        static Lieu lieu = new Restaurant ("Lyon");

        @Test
        void testToString() {
            assertEquals ("Nom : Lyon, Type : Restaurant", lieu.toString ());
        }

        @Test
        void compareTo() {
            assertEquals (0, lieu.compareTo (lieu));
        }
    }