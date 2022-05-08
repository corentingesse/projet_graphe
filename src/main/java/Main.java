import Graphe.ConfigLiaison.ListeLiaison;
import Graphe.ConfigLieu.ListeLieux;
import Graphe.ConfigRoute.ListeRoutes;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ListeLiaison listeLiaison = new ListeLiaison();
        ListeRoutes listeRoutes = new ListeRoutes();
        ListeLieux listeLieux = new ListeLieux();

        int nbVirgules = 0;
        int nbDeuxPoints = 0;
        int nbPointVirgules = 0;
        char typeLieu;
        String chaine = "";

        File file = new File("src/main/resources/graphe.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int c;

        while ((c = br.read()) != -1){
            char ch = (char) c;

            switch (ch){
                case ',':
                    nbVirgules++;
                    break;
                case ':':
                    break;
                case ';':
                    break;
                case '\n':
                    break;
            }
        }
    }
}
