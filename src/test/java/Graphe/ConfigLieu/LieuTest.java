package Graphe.ConfigLieu;

import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;
import Graphe.CreationGraphe;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LieuTest {

    static Lieu lieu = new Lieu ("Lyon");

    @BeforeClass
    public static void setUpClass() throws IOException, ExceptionAjListeGraphe {
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