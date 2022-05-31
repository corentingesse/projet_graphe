package Graphe;

import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;
import Graphe.ConfigListe.ListeGraphe;
import Graphe.ConfigLieu.Lieu;
import Graphe.ConfigLieu.Loisir;
import Graphe.ConfigLieu.Restaurant;
import Graphe.ConfigLieu.Ville;
import Graphe.ConfigRoute.Autoroute;
import Graphe.ConfigRoute.Departementale;
import Graphe.ConfigRoute.Nationale;
import Graphe.ConfigRoute.Route;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CreationListe {
    public static void creerListe(ListeGraphe listeGraphe, String cheminFile) throws IOException, ExceptionAjListeGraphe {
        File file = new File(cheminFile);
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
                                listeGraphe.ajLieu (lieuOrigine);
                            }
                            else{
                                try {
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
    }
}
