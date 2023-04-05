package Graphe.ConfigLieu;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LieuTest {

    static Lieu lieu = new Lieu ("Lyon");

    @BeforeClass
    public static void setUpClass() {
    }
        @Test
        void getNomLieu() {
            assertEquals("Lyon",lieu.getNomLieu());
        }

        @Test
        void setNomLieu() {
            lieu.setNomLieu("Marseille");
            assertEquals("Marseille",lieu.getNomLieu());
        }

        @Test
        void compareTo() {
            assertEquals (0, lieu.compareTo (lieu));
        }
    }