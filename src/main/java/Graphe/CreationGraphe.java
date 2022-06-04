package Graphe;

import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;
import Graphe.ConfigListe.ListeGraphe;
import Graphe.ConfigLieu.Lieu;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.layout.Layout;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class CreationGraphe {
    static ListeGraphe listeGraphe;
    private static Graph graph = null;
    public CreationGraphe (String cheminFile) throws IOException, ExceptionAjListeGraphe {
        listeGraphe = new ListeGraphe();
        listeGraphe.afficher();
        CreationListe.creerListe(listeGraphe, cheminFile);
    }
    public static JPanel creerGraphe (){
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);



        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(980, 460));

        // view.getCamera().setViewPercent(1);

        int i = 0;

        for(Lieu lieuParcour : listeGraphe.getListeGraphe().keySet ()) {
            graph.addNode(lieuParcour.getNomLieu());
            Node n = graph.getNode(lieuParcour.getNomLieu());
            n.setAttribute("ui.label", n.getId());
            switch (lieuParcour.getClass().getSimpleName()) {
                case "Ville" :
                    n.setAttribute ("ui.style", "fill-color: red; ");
                    break;
                case "Restaurant":
                    n.setAttribute ("ui.style", "fill-color: #0CAF09; ");
                    break;
                case "Loisir":
                    n.setAttribute ("ui.style", "fill-color: orange; ");
                    break;
            }
        }

        for(Lieu lieuParcour : listeGraphe.getListeGraphe().keySet ()) {
            for(Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin ().keySet ()){
                if (graph.getNode (lieuParcour.getNomLieu ()).hasEdgeBetween (lieuVoisin.getNomLieu ()) == false) {
                    graph.addEdge(String.valueOf(i), lieuVoisin.getNomLieu(), lieuParcour.getNomLieu());
                    Edge edge = graph.getEdge(String.valueOf(i));
                    edge.setAttribute("ui.label", listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin ().get (lieuVoisin).getNomRoute () + ", " + listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin ().get (lieuVoisin).getDistanceKm() + "km");
                    switch (listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin ().get (lieuVoisin).getClass().getSimpleName()) {
                        case "Nationale":
                            edge.setAttribute ("ui.style", "fill-color: red; ");
                            break;
                        case "Departementale":
                            edge.setAttribute ("ui.style", "fill-color: yellow; ");
                            break;
                        case "Autoroute":
                            edge.setAttribute ("ui.style", "fill-color: blue; ");
                            break;
                    }
                }
                i = i + 1;
            }
        }

        graph.setAttribute("ui.stylesheet","node {" +
                "size: 20px;" +
                "text-color: white;" +
                "text-style: bold;" +
                "}" +
                "edge{" +
                "text-color: white;" +
                "text-style: bold;" +
                "text-alignment: along;" +
                "}" +
                "graph{" +
                "fill-color: black;" +
                "}");

        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);

        return graphPanel;

    }

    public int getNombreVille () {
        int nbVille = 0;
        for(Lieu lieuParcour : listeGraphe.getListeGraphe().keySet ()) {
            if (lieuParcour.getClass().getSimpleName().equals ("Ville"))
                nbVille = nbVille + 1;
        }
            return nbVille;
    }

    public int getNombreLoisir () {
        int nbLoisir = 0;
        for(Lieu lieuParcour : listeGraphe.getListeGraphe().keySet ()) {
            if (lieuParcour.getClass().getSimpleName().equals ("Loisir"))
                nbLoisir = nbLoisir + 1;
        }
        return nbLoisir;
    }

    public int getNombreRestaurant () {
        int nbRestaurant = 0;
        for(Lieu lieuParcour : listeGraphe.getListeGraphe().keySet ()) {
            if (lieuParcour.getClass().getSimpleName().equals ("Restaurant"))
                nbRestaurant = nbRestaurant + 1;
        }
        return nbRestaurant;
    }

    public int getNombreNationale () {
        int nbNationale = 0;
        for(Lieu lieuParcour : listeGraphe.getListeGraphe().keySet ()) {
            for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                if (listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getClass().getSimpleName().equals ("Nationale"))
                    nbNationale = nbNationale + 1;
            }
        }
        return nbNationale /2;
    }

    public int getNombreAutoroute () {
        int nbAutoroute = 0;
        for(Lieu lieuParcour : listeGraphe.getListeGraphe().keySet ()) {
            for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                if (listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getClass().getSimpleName().equals ("Autoroute"))
                    nbAutoroute = nbAutoroute + 1;
            }
        }
        return nbAutoroute /2;
    }

    public int getNombreDepartementale () {
        int nbNationale = 0;
        for(Lieu lieuParcour : listeGraphe.getListeGraphe().keySet ()) {
            for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                if (listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getClass().getSimpleName().equals ("Departementale"))
                    nbNationale = nbNationale + 1;
            }
        }
        return nbNationale /2;
    }

    public static JPanel creerGrapheVoisins (String lieu) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);



        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(980, 460));

        // view.getCamera().setViewPercent(1);

        int i = 0;

            for(Lieu lieuParcour : listeGraphe.getListeGraphe().keySet ()) {
                if (lieuParcour.getNomLieu().equals(lieu)) {
                    graph.addNode(lieuParcour.getNomLieu());
                    Node n = graph.getNode(lieuParcour.getNomLieu());
                    n.setAttribute("ui.label", n.getId());
                    switch (lieuParcour.getClass().getSimpleName()) {
                        case "Ville":
                            n.setAttribute("ui.style", "fill-color: red; ");
                            break;
                        case "Restaurant":
                            n.setAttribute("ui.style", "fill-color: #0CAF09; ");
                            break;
                        case "Loisir":
                            n.setAttribute("ui.style", "fill-color: orange; ");
                            break;
                    }
                    for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                            graph.addNode(lieuVoisin.getNomLieu ());
                            Node n1 = graph.getNode(lieuVoisin.getNomLieu ());
                            n1.setAttribute("ui.label", n1.getId());
                            switch (lieuVoisin.getClass().getSimpleName()) {
                                case "Ville":
                                    n1.setAttribute("ui.style", "fill-color: red; ");
                                    break;
                                case "Restaurant":
                                    n1.setAttribute("ui.style", "fill-color: #0CAF09; ");
                                    break;
                                case "Loisir":
                                    n1.setAttribute("ui.style", "fill-color: orange; ");
                                    break;
                            }
                        if (graph.getNode(lieuParcour.getNomLieu()).hasEdgeBetween(lieuVoisin.getNomLieu()) == false) {
                            graph.addEdge(String.valueOf(i), lieuVoisin.getNomLieu(), lieuParcour.getNomLieu());
                            Edge edge = graph.getEdge(String.valueOf(i));
                            edge.setAttribute("ui.label", listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getNomRoute() + ", " + listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getDistanceKm() + "km");
                            switch (listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getClass().getSimpleName()) {
                                case "Nationale":
                                    edge.setAttribute("ui.style", "fill-color: red; ");
                                    break;
                                case "Departementale":
                                    edge.setAttribute("ui.style", "fill-color: yellow; ");
                                    break;
                                case "Autoroute":
                                    edge.setAttribute("ui.style", "fill-color: blue; ");
                                    break;
                            }
                        }
                        i = i + 1;
                    }
                    graph.setAttribute("ui.stylesheet","node {" +
                            "size: 20px;" +
                            "text-color: white;" +
                            "text-style: bold;" +
                            "}" +
                            "edge{" +
                            "text-color: white;" +
                            "text-style: bold;" +
                            "text-alignment: along;" +
                            "}" +
                            "graph{" +
                            "fill-color: black;" +
                            "}");

                    graphLayout.compute();

                    viewer.enableAutoLayout();

                    graphPanel.add(view);
                    return graphPanel;
                }
        }
        return graphPanel;
    }

    public ArrayList <String> getNoeud () {
        int i;
        ArrayList <String> graphNode = new ArrayList ();
        int indexGraphNode = 1;
        for (Node node : graph) {
            graphNode.add (node.getId());
        }
        return graphNode;

    }

    public int getNombreVilleVoisin (String lieu) {
        int nbVille = 0;
        for(Lieu lieuParcour : listeGraphe.getListeGraphe().keySet ()) {
            if (lieuParcour.getNomLieu().equals(lieu)) {
                switch (lieuParcour.getClass().getSimpleName()) {
                    case "Ville":
                        nbVille = nbVille + 1;
                        break;
                }
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    switch (lieuVoisin.getClass().getSimpleName()) {
                        case "Ville":
                            nbVille = nbVille + 1;
                    }
                }

                return nbVille;
            }
        }

        return 0;
    }

    public int getNombreLoisirVoisin (String lieu) {
        int nbLoisir = 0;
        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) {
            if (lieuParcour.getNomLieu().equals(lieu)) {
                switch (lieuParcour.getClass().getSimpleName()) {
                    case "Loisir":
                        nbLoisir = nbLoisir + 1;
                        break;
                }
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    switch (lieuVoisin.getClass().getSimpleName()) {
                        case "Loisir":
                            nbLoisir = nbLoisir + 1;
                    }
                }

                return nbLoisir;
            }
        }

        return 0;
    }

    public int getNombreRestaurantVoisin (String lieu) {
        int nbRestaurant = 0;
        for(Lieu lieuParcour : listeGraphe.getListeGraphe().keySet ()) {
            if (lieuParcour.getNomLieu().equals(lieu)) {
                switch (lieuParcour.getClass().getSimpleName()) {
                    case "Restaurant":
                        nbRestaurant = nbRestaurant + 1;
                        break;
                }
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    switch (lieuVoisin.getClass().getSimpleName()) {
                        case "Restaurant" :
                            nbRestaurant = nbRestaurant + 1;
                    }
                }

                return nbRestaurant;
            }
        }

        return 0;
    }

    public int getNombreNationaleVoisin (String lieu) {
        int nbNationale = 0;
        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) {
            if (lieuParcour.getNomLieu().equals(lieu)) {
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    switch (listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getClass().getSimpleName()) {
                        case "Nationale":
                            nbNationale = nbNationale + 1;
                            break;
                    }
                }

                return nbNationale;
            }
        }

        return 0;
    }

    public int getNombreAutorouteVoisin (String lieu) {
        int nbAutoroute = 0;
        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) {
            if (lieuParcour.getNomLieu().equals(lieu)) {
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    switch (listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getClass().getSimpleName()) {
                        case "Autoroute":
                            nbAutoroute = nbAutoroute + 1;
                            break;
                    }
                }

                return nbAutoroute;
            }
        }

        return 0;
    }

    public int getNombreDepartementaleVoisin (String lieu) {
        int nbDepartementale = 0;
        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) {
            if (lieuParcour.getNomLieu().equals(lieu)) {
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    switch (listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getClass().getSimpleName()) {
                        case "Departementale" :
                            nbDepartementale = nbDepartementale + 1;
                            break;
                    }
                }

                return nbDepartementale;
            }
        }

        return 0;
    }

}
