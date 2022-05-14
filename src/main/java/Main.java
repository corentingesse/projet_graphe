import Graphe.ConfigLiaison.Exceptions.ExceptionAjListeGraphe;
import Graphe.ConfigLiaison.Liaison;
import Graphe.ConfigLiaison.ListeGraphe;
import Graphe.ConfigLiaison.ListeLiaison;
import Graphe.ConfigLieu.*;
import Graphe.ConfigRoute.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        ListeGraphe listeGraphe = new ListeGraphe ();

        File file = new File("src/main/resources/graphe.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();

        String ligne;

        while ((ligne = br.readLine()) != null) {
            sb.append(ligne);
            sb.append("\n");

            String[] s1 = ligne.split(";");
            ArrayList<String> s2 = new ArrayList<String>();
            ArrayList<String> s3 = new ArrayList<String>();

            for (String value : s1) {
                String[] l = value.split(":");
                s2.addAll(Arrays.asList(l));
            }

            for (String s : s2) {
                String[] l = s.split(",");
                s3.addAll(Arrays.asList(l));
            }

            /*
            for (int i = 0; i < s3.size(); i++) {
                System.out.println(i + " " + s3.get(i));
            }
            */

            int index = 0;
            int limite = 4;

            Lieu lieuOrigine = null;
            Lieu nouvLieu = null;
            Route nouvRoute = null;

            while(index < s3.size()){
                for(int i = 0; i < limite && index < s3.size(); i++, index++){
                    switch (i){
                        case 0:
                            switch (s3.get(index)) {
                                case "V" -> nouvLieu = new Ville();
                                case "L" -> nouvLieu = new Loisir();
                                case "R" -> nouvLieu = new Restaurant();
                            }
                            break;
                        case 1:
                            nouvLieu.setNomLieu(s3.get(index));
                            if(index < 4){
                                lieuOrigine = nouvLieu;
                            }
                            else{
                                try {
                                    listeGraphe.ajLieu (lieuOrigine);
                                    listeGraphe.ajListeVoisin (lieuOrigine, nouvLieu, nouvRoute);
                                } catch (ExceptionAjListeGraphe e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            break;
                        case 2:
                            switch (s3.get(index).charAt(0)) {
                                case 'D' -> nouvRoute = new Departementale();
                                case 'N' -> nouvRoute = new Nationale();
                                case 'A' -> nouvRoute = new Autoroute();
                            }
                            nouvRoute.setNomRoute(s3.get(index));
                            break;
                        case 3:
                            nouvRoute.setDistanceKm(Integer.parseInt(s3.get(index)));
                            break;
                    }
                }
                index++;
            }

            System.out.println();
        }
        System.out.println (listeGraphe);

        System.out.println (listeGraphe.getListeGraphe ().values ());
    }
}
