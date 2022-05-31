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
import java.util.stream.Stream;

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
                    n.setAttribute ("ui.style", "fill-color: green; ");
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
                    edge.setAttribute("ui.label", listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin ().get (lieuVoisin).getNomRoute ());
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
                "}" +
                "");

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

    //public static JPanel creerGraphe () {

    //}

    public String [] getNoeud () {
        int i;
        String [] graphNode = new String[0];
        int indexGraphNode = 1;
        for (i = 1; graph.getNode (i) != null; i++) {
            graphNode[i] = graph.getNode(i).getId();
            indexGraphNode = indexGraphNode + 1;
        }
        return graphNode;

    }
}
