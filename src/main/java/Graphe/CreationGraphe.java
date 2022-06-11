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

    public CreationGraphe(String cheminFile) throws IOException, ExceptionAjListeGraphe {
        listeGraphe = new ListeGraphe();
        listeGraphe.afficher();
        CreationListe.creerListe(listeGraphe, cheminFile);
    }

    public static JPanel creerGraphe() {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);


        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(980, 460));

        // view.getCamera().setViewPercent(1);

        int i = 0;

        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) {
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
        }

        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) {
            for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
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
        }

        graph.setAttribute("ui.stylesheet", "node {" +
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

    public static Graph getGraphe () {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");

        // view.getCamera().setViewPercent(1);

        int i = 0;

        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) {
            graph.addNode(lieuParcour.getNomLieu());
            Node n = graph.getNode(lieuParcour.getNomLieu());
            n.setAttribute("ui.label", n.getId());
        }

        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) {
            for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                if (graph.getNode(lieuParcour.getNomLieu()).hasEdgeBetween(lieuVoisin.getNomLieu()) == false) {
                    graph.addEdge(String.valueOf(i), lieuVoisin.getNomLieu(), lieuParcour.getNomLieu());
                    Edge edge = graph.getEdge(String.valueOf(i));
                    edge.setAttribute("ui.label", listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getNomRoute() + ", " + listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getDistanceKm() + "km");
                }
                i = i + 1;
            }
        }

        return graph;

    }

    public static JPanel creerGrapheVoisins(String lieu) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);


        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(980, 460));

        // view.getCamera().setViewPercent(1);

        int i = 0;

        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) {
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
                    graph.addNode(lieuVoisin.getNomLieu());
                    Node n1 = graph.getNode(lieuVoisin.getNomLieu());
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
                graph.setAttribute("ui.stylesheet", "node {" +
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

    public static Graph getGraphVoisin (String lieu) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");
        int i = 0;

        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) {
            if (lieuParcour.getNomLieu().equals(lieu)) {
                graph.addNode(lieuParcour.getNomLieu());
                Node n = graph.getNode(lieuParcour.getNomLieu());
                n.setAttribute("ui.label", n.getId());
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    graph.addNode(lieuVoisin.getNomLieu());
                    Node n1 = graph.getNode(lieuVoisin.getNomLieu());
                    n1.setAttribute("ui.label", n1.getId());
                    if (graph.getNode(lieuParcour.getNomLieu()).hasEdgeBetween(lieuVoisin.getNomLieu()) == false) {
                        graph.addEdge(String.valueOf(i), lieuVoisin.getNomLieu(), lieuParcour.getNomLieu());
                        Edge edge = graph.getEdge(String.valueOf(i));
                        edge.setAttribute("ui.label", listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getNomRoute() + ", " + listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getDistanceKm() + "km");
                    }
                    i = i + 1;
                }
                return graph;
            }
        }
        return graph;
    }

    public ArrayList<String> getNoeud() {
        int i;
        ArrayList<String> graphNode = new ArrayList();
        int indexGraphNode = 1;
        for (Node node : graph) {
            graphNode.add(node.getId());
        }
        return graphNode;

    }

    public static JPanel creerGrapheChercher(String lieu, String lieuCherche) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);


        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(980, 460));

        // view.getCamera().setViewPercent(1);

        int i = 0;

        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) {
            if (lieuParcour.getNomLieu().equals(lieu)) {
                graph.addNode(lieuParcour.getNomLieu());
                Node n = graph.getNode(lieuParcour.getNomLieu());
                n.setAttribute("ui.label", n.getId());
                switch (lieuParcour.getClass().getSimpleName()) {
                    case "Ville":
                        if ("Ville".equals(lieuCherche))
                            n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                        else
                            n.setAttribute("ui.style", "fill-color: red; ");
                        break;
                    case "Restaurant":
                        if ("Restaurant".equals(lieuCherche))
                            n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                        else
                            n.setAttribute("ui.style", "fill-color: #0CAF09; ");
                        break;
                    case "Loisir":
                        if ("Loisir".equals(lieuCherche))
                            n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                        else
                            n.setAttribute("ui.style", "fill-color: orange; ");
                        break;
                }
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    graph.addNode(lieuVoisin.getNomLieu());
                    Node n1 = graph.getNode(lieuVoisin.getNomLieu());
                    n1.setAttribute("ui.label", n1.getId());
                    switch (lieuVoisin.getClass().getSimpleName()) {
                        case "Ville":
                            if ("Ville".equals(lieuCherche))
                                n1.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                            else
                                n1.setAttribute("ui.style", "fill-color: red; ");
                            break;
                        case "Restaurant":
                            if ("Restaurant".equals(lieuCherche))
                                n1.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                            else
                                n1.setAttribute("ui.style", "fill-color: #0CAF09; ");
                            break;
                        case "Loisir":
                            if ("Ville".equals(lieuCherche))
                                n1.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                            else
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
                graph.setAttribute("ui.stylesheet", "node {" +
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

    public static Graph getGraphChercher (String lieu, String lieuCherche) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");

        int i = 0;

        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) {
            if (lieuParcour.getNomLieu().equals(lieu)) {
                graph.addNode(lieuParcour.getNomLieu());
                Node n = graph.getNode(lieuParcour.getNomLieu());
                n.setAttribute("ui.label", n.getId());
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    graph.addNode(lieuVoisin.getNomLieu());
                    Node n1 = graph.getNode(lieuVoisin.getNomLieu());
                    n1.setAttribute("ui.label", n1.getId());
                    if (graph.getNode(lieuParcour.getNomLieu()).hasEdgeBetween(lieuVoisin.getNomLieu()) == false) {
                        graph.addEdge(String.valueOf(i), lieuVoisin.getNomLieu(), lieuParcour.getNomLieu());
                        Edge edge = graph.getEdge(String.valueOf(i));
                        edge.setAttribute("ui.label", listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getNomRoute() + ", " + listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getDistanceKm() + "km");
                    }
                    i = i + 1;
                }
                return graph;
            }
        }
        return graph;
    }

    public int calcDistance(String lieuDepart, String lieuRecherche, ArrayList<String> listeLieuxParcourus) {
        int minimum = 0;
        String lieuMinimum = null;
        int resultat;
        boolean premierVoisin = true;

        if (lieuDepart == null)
            return 100000;
        if (lieuDepart.equals(lieuRecherche))
            return minimum;
        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
            if (lieuParcour.getNomLieu().equals(lieuDepart)) {
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    if (listeLieuxParcourus.contains(lieuVoisin.getNomLieu()) != true) {
                        listeLieuxParcourus.add(lieuVoisin.getNomLieu());
                        resultat = calcDistance(lieuVoisin.getNomLieu(), lieuRecherche, listeLieuxParcourus) + 1;
                        listeLieuxParcourus.remove(lieuVoisin.getNomLieu());
                        if (resultat < minimum || premierVoisin == true) {
                            minimum = resultat + 1;
                            lieuMinimum = lieuVoisin.getNomLieu();
                            premierVoisin = false;
                        }
                    }
                }

                listeLieuxParcourus.add(lieuMinimum);
                return minimum + calcDistance(lieuMinimum, lieuRecherche, listeLieuxParcourus);
            }
        }

        return -1;
    }

    public int calcDistance1Et2Distance(String lieuDepart, String lieuRecherche) {
        int distance = 0;

        if (lieuDepart.equals(lieuRecherche))
            return distance;
        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
            if (lieuParcour.getNomLieu().equals(lieuDepart)) {
                distance = distance + 1;
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    if (lieuVoisin.getNomLieu().equals(lieuRecherche))
                        return distance;
                }
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    for (Lieu lieuParcourP1Graphe : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                        if (lieuParcourP1Graphe.getNomLieu().equals(lieuVoisin.getNomLieu())) {
                            for (Lieu lieuVoisinP1Graphe : listeGraphe.getListeGraphe().get(lieuParcourP1Graphe).getListeVoisin().keySet()) {
                                if (lieuVoisinP1Graphe.getNomLieu().equals(lieuRecherche)) {
                                    distance = distance + 1;
                                    return distance;
                                }
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }

    public JPanel creerGrapheVoisins1Et2Distance(String lieuDepart, String lieuRecherche) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);


        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(980, 460));

        // view.getCamera().setViewPercent(1);

        int i = 0;
        int minimum = 0;

        if (lieuDepart.equals(lieuRecherche)) {
            for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                if (lieuParcour.getNomLieu().equals(lieuDepart)) {
                    graph.addNode(lieuParcour.getNomLieu());
                    Node n = graph.getNode(lieuParcour.getNomLieu());
                    n.setAttribute("ui.label", n.getId());
                    switch (lieuParcour.getClass().getSimpleName()) {
                        case "Ville":
                            n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                            break;
                        case "Restaurant":
                            n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                            break;
                        case "Loisir":
                            n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                            break;
                    }
                }
            }
        }
        else {
            for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                if (lieuParcour.getNomLieu().equals(lieuDepart)) {
                    graph.addNode(lieuParcour.getNomLieu());
                    Node n = graph.getNode(lieuParcour.getNomLieu());
                    n.setAttribute("ui.label", n.getId());
                    switch (lieuParcour.getClass().getSimpleName()) {
                        case "Ville":
                            n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                            break;
                        case "Restaurant":
                            n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                            break;
                        case "Loisir":
                            n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                            break;
                    }
                    for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                        graph.addNode(lieuVoisin.getNomLieu());
                        n = graph.getNode(lieuVoisin.getNomLieu());
                        n.setAttribute("ui.label", n.getId());
                        switch (lieuVoisin.getClass().getSimpleName()) {
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
                        if (lieuVoisin.getNomLieu().equals(lieuRecherche)) {
                            switch (lieuVoisin.getClass().getSimpleName()) {
                                case "Ville":
                                    n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                                    break;
                                case "Restaurant":
                                    n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                                    break;
                                case "Loisir":
                                    n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                                    break;
                            }
                        }
                    }
                    for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                        for (Lieu lieuParcourP1Graphe : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                            if (lieuParcourP1Graphe.getNomLieu().equals(lieuVoisin.getNomLieu())) {
                                for (Lieu lieuVoisinP1Graphe : listeGraphe.getListeGraphe().get(lieuParcourP1Graphe).getListeVoisin().keySet()) {
                                    if (graph.getNode(lieuVoisinP1Graphe.getNomLieu()) == null) {
                                        graph.addNode(lieuVoisinP1Graphe.getNomLieu());
                                        n = graph.getNode(lieuVoisinP1Graphe.getNomLieu());
                                        n.setAttribute("ui.label", n.getId());
                                        switch (lieuVoisinP1Graphe.getClass().getSimpleName()) {
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
                                        if (graph.getNode(lieuParcourP1Graphe.getNomLieu()).hasEdgeBetween(lieuVoisinP1Graphe.getNomLieu()) == false) {
                                            graph.addEdge(String.valueOf(i), lieuVoisinP1Graphe.getNomLieu(), lieuParcourP1Graphe.getNomLieu());
                                            Edge edge = graph.getEdge(String.valueOf(i));
                                            edge.setAttribute("ui.label", listeGraphe.getListeGraphe().get(lieuParcourP1Graphe).getListeVoisin().get(lieuVoisinP1Graphe).getNomRoute() + ", " + listeGraphe.getListeGraphe().get(lieuParcourP1Graphe).getListeVoisin().get(lieuVoisinP1Graphe).getDistanceKm() + "km");
                                            switch (listeGraphe.getListeGraphe().get(lieuParcourP1Graphe).getListeVoisin().get(lieuVoisinP1Graphe).getClass().getSimpleName()) {
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
                                        if (lieuVoisinP1Graphe.getNomLieu().equals(lieuRecherche)) {
                                            switch (lieuVoisinP1Graphe.getClass().getSimpleName()) {
                                                case "Ville":
                                                    n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                                                    break;
                                                case "Restaurant":
                                                    n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                                                    break;
                                                case "Loisir":
                                                    n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                                                    break;
                                            }

                                        }

                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

            graph.setAttribute("ui.stylesheet", "node {" +
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

    public Graph getGraphVoisins1Et2Distance (String lieuDepart) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");

        int i = 0;
        int minimum = 0;

        for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
            if (lieuParcour.getNomLieu().equals(lieuDepart)) {
                graph.addNode(lieuParcour.getNomLieu());
                Node n = graph.getNode(lieuParcour.getNomLieu());
                n.setAttribute("ui.label", n.getId());
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    graph.addNode(lieuVoisin.getNomLieu());
                    Node n1 = graph.getNode(lieuVoisin.getNomLieu());
                    n1.setAttribute("ui.label", n1.getId());
                    if (graph.getNode(lieuParcour.getNomLieu()).hasEdgeBetween(lieuVoisin.getNomLieu()) == false) {
                        graph.addEdge(String.valueOf(i), lieuVoisin.getNomLieu(), lieuParcour.getNomLieu());
                        Edge edge = graph.getEdge(String.valueOf(i));
                        edge.setAttribute("ui.label", listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getNomRoute() + ", " + listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().get(lieuVoisin).getDistanceKm() + "km");
                    }
                    i = i + 1;
                }
                for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                    for (Lieu lieuParcourP1Graphe : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                        if (lieuParcourP1Graphe.getNomLieu().equals(lieuVoisin.getNomLieu())) {
                            for (Lieu lieuVoisinP1Graphe : listeGraphe.getListeGraphe().get(lieuParcourP1Graphe).getListeVoisin().keySet()) {
                                if (graph.getNode(lieuVoisinP1Graphe.getNomLieu()) == null) {
                                    graph.addNode(lieuVoisinP1Graphe.getNomLieu());
                                    n = graph.getNode(lieuVoisinP1Graphe.getNomLieu());
                                    n.setAttribute("ui.label", n.getId());
                                    if (graph.getNode(lieuParcourP1Graphe.getNomLieu()).hasEdgeBetween(lieuVoisinP1Graphe.getNomLieu()) == false) {
                                        graph.addEdge(String.valueOf(i), lieuVoisinP1Graphe.getNomLieu(), lieuParcourP1Graphe.getNomLieu());
                                        Edge edge = graph.getEdge(String.valueOf(i));
                                        edge.setAttribute("ui.label", listeGraphe.getListeGraphe().get(lieuParcourP1Graphe).getListeVoisin().get(lieuVoisinP1Graphe).getNomRoute() + ", " + listeGraphe.getListeGraphe().get(lieuParcourP1Graphe).getListeVoisin().get(lieuVoisinP1Graphe).getDistanceKm() + "km");
                                    }
                                    i = i + 1;
                                    }

                                }

                            }
                        }
                    }
                }
            }
            return graph;
    }

    public int getNbVille (Graph graphCherche) {
        int nbVille = 0;
        int nbNode = graphCherche.getNodeCount();

        for (int i = 0; i < nbNode; i++) {
            for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                if (lieuParcour.getNomLieu().equals(graphCherche.getNode(i).getId())) {
                    switch (lieuParcour.getClass().getSimpleName()) {
                        case "Ville":
                            nbVille = nbVille + 1;
                            break;
                    }
                }
            }
        }

        return nbVille;

    }

    public int getNbRestaurant (Graph graphCherche) {
        int nbRestaurant = 0;
        int nbNode = graphCherche.getNodeCount();

        for (int i = 0; i < nbNode; i++) {
            for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                if (lieuParcour.getNomLieu().equals(graphCherche.getNode(i).getId())) {
                    switch (lieuParcour.getClass().getSimpleName()) {
                        case "Restaurant" :
                            nbRestaurant = nbRestaurant + 1;
                            break;
                    }
                }
            }
        }

        return nbRestaurant;

    }

    public int getNbLoisir (Graph graphCherche) {
        int nbLoisir = 0;
        int nbNode = graphCherche.getNodeCount();

        for (int i = 0; i < nbNode; i++) {
            for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                if (lieuParcour.getNomLieu().equals(graphCherche.getNode(i).getId())) {
                    switch (lieuParcour.getClass().getSimpleName()) {
                        case "Loisir" :
                            nbLoisir = nbLoisir + 1;
                            break;
                    }
                }
            }
        }

        return nbLoisir;

    }

    public int getNbNationale (Graph graphCherche) {
        int nbNationale = 0;
        int nbEdge = graphCherche.getEdgeCount ();

        for (int i = 0; i < nbEdge; i++) {
            switch (graphCherche.getEdge(i).getAttribute("ui.label").toString().charAt (0)) {
                case 'N':
                    nbNationale = nbNationale + 1;
            }
        }

        return nbNationale;

    }

    public int getNbDepartementale (Graph graphCherche) {
        int nbDepartementale = 0;
        int nbEdge = graphCherche.getEdgeCount ();

        for (int i = 0; i < nbEdge; i++) {
            switch (graphCherche.getEdge(i).getAttribute("ui.label").toString().charAt (0)) {
                case 'D' :
                    nbDepartementale = nbDepartementale + 1;
            }
        }

        return nbDepartementale;

    }

    public int getNbAutoroute (Graph graphCherche) {
        int nbAutoroute = 0;
        int nbEdge = graphCherche.getEdgeCount ();

        for (int i = 0; i < nbEdge; i++) {
            switch (graphCherche.getEdge(i).getAttribute("ui.label").toString().charAt (0)) {
                case 'A' :
                    nbAutoroute = nbAutoroute + 1;
            }
        }

        return nbAutoroute;

    }

    public JPanel creerGrapheComparaison (String lieuDepart) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);


        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(980, 460));
        // view.getCamera().setViewPercent(1);

        int i = 0;
        int minimum = 0;

            for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                if (lieuParcour.getNomLieu().equals(lieuDepart)) {
                    graph.addNode(lieuParcour.getNomLieu());
                    Node n = graph.getNode(lieuParcour.getNomLieu());
                    n.setAttribute("ui.label", n.getId());
                    switch (lieuParcour.getClass().getSimpleName()) {
                        case "Ville":
                            n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                            break;
                        case "Restaurant":
                            n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                            break;
                        case "Loisir":
                            n.setAttribute("ui.style", "fill-mode: image-scaled; " + "fill-image : url ('file:///C:/Users/cocog/sae-graphes/src/main/resources/mapPinSmall.png');");
                            break;
                    }
                    for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                        graph.addNode(lieuVoisin.getNomLieu());
                        Node n1 = graph.getNode(lieuVoisin.getNomLieu());
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
                    for (Lieu lieuVoisin : listeGraphe.getListeGraphe().get(lieuParcour).getListeVoisin().keySet()) {
                        for (Lieu lieuParcourP1Graphe : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                            if (lieuParcourP1Graphe.getNomLieu().equals(lieuVoisin.getNomLieu())) {
                                for (Lieu lieuVoisinP1Graphe : listeGraphe.getListeGraphe().get(lieuParcourP1Graphe).getListeVoisin().keySet()) {
                                    if (graph.getNode(lieuVoisinP1Graphe.getNomLieu()) == null) {
                                        graph.addNode(lieuVoisinP1Graphe.getNomLieu());
                                        n = graph.getNode(lieuVoisinP1Graphe.getNomLieu());
                                        n.setAttribute("ui.label", n.getId());
                                        switch (lieuVoisinP1Graphe.getClass().getSimpleName()) {
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
                                        if (graph.getNode(lieuParcourP1Graphe.getNomLieu()).hasEdgeBetween(lieuVoisinP1Graphe.getNomLieu()) == false) {
                                            graph.addEdge(String.valueOf(i), lieuVoisinP1Graphe.getNomLieu(), lieuParcourP1Graphe.getNomLieu());
                                            Edge edge = graph.getEdge(String.valueOf(i));
                                            edge.setAttribute("ui.label", listeGraphe.getListeGraphe().get(lieuParcourP1Graphe).getListeVoisin().get(lieuVoisinP1Graphe).getNomRoute() + ", " + listeGraphe.getListeGraphe().get(lieuParcourP1Graphe).getListeVoisin().get(lieuVoisinP1Graphe).getDistanceKm() + "km");
                                            switch (listeGraphe.getListeGraphe().get(lieuParcourP1Graphe).getListeVoisin().get(lieuVoisinP1Graphe).getClass().getSimpleName()) {
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

                                }
                            }
                        }
                    }
                }
            }

        graph.setAttribute("ui.stylesheet", "node {" +
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

    public String getClasse (Graph graph, String lieuCherche) {
            for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                if (lieuParcour.getNomLieu().equals(lieuCherche)) {
                    switch (lieuParcour.getClass().getSimpleName()) {
                        case "Ville":
                            return "Ville";
                        case "Restaurant":
                            return "Restaurant";
                        case "Loisir":
                            return "Loisir";

                    }
                }
            }

            return "";
    }

}