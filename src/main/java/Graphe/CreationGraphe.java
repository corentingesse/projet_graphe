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

    public CreationGraphe(String cheminFile) throws IOException, ExceptionAjListeGraphe {      // crée la liste du graphe
        listeGraphe = new ListeGraphe();
        listeGraphe.afficher();
        CreationListe.creerListe(listeGraphe, cheminFile);
    }

    public static JPanel creerGraphe() {    // crée un panel ou le graphe principal est présent
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

    public static Graph getGraphe() {    // retourne le graphe principal
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

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

    public static JPanel creerGrapheVoisins(String lieu) {    // crée le panel ou le graphe contenant les voisins directes d'un lieu est présent
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

    public static Graph getGraphVoisin(String lieu) {      // retourne le graphe des voisins directes d'un lieu
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

    public ArrayList<String> getNoeud(Graph graph) {     // retourne les lieux d'un graphe passé en paramètre
        int i;
        ArrayList<String> graphNode = new ArrayList();
        int indexGraphNode = 1;
        for (Node node : graph) {
            graphNode.add(node.getId());
        }
        return graphNode;

    }

    public ArrayList<String> getEdge(Graph graph) {        // retourne les liens d'un graphe entré en paramètre
        ArrayList<String> graphEdge = new ArrayList();
        for (int indexGraphEdge = 0; indexGraphEdge < graph.getEdgeCount(); indexGraphEdge++) {
            graphEdge.add(graph.getEdge(indexGraphEdge).getAttribute("ui.label").toString());
        }
        return graphEdge;

    }

    public String getLieu1Edge(Graph graph, String nomRoute) {    // retourne le premier lieu qui est relié par la route qui se nomme nomRoute situé dans le graphe passé en paramètre
        for (int indexGraphEdge = 0; indexGraphEdge < graph.getEdgeCount(); indexGraphEdge++) {
            if (graph.getEdge(indexGraphEdge).getAttribute("ui.label").toString().equals(nomRoute))
                return graph.getEdge(indexGraphEdge).getNode0().getAttribute("ui.label").toString();
        }

        return "";
    }

    public String getLieu2Edge(Graph graph, String nomRoute) {     // retourne le deuxième lieu qui est relié par la route qui se nomme nomRoute situé dans le graphe passé en paramètre
        for (int indexGraphEdge = 0; indexGraphEdge < graph.getEdgeCount(); indexGraphEdge++) {
            if (graph.getEdge(indexGraphEdge).getAttribute("ui.label").toString().equals(nomRoute))
                return graph.getEdge(indexGraphEdge).getNode1().getAttribute("ui.label").toString();
        }

        return "";
    }


    public static JPanel creerGrapheChercher(String lieu, String lieuCherche) {    // crée le panel du graphe qqui recherche un lieu entré en paramètre
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

    public static Graph getGraphChercher(String lieu, String lieuCherche) {   // crée le graphe qui cherche un lieu entré en paramètre
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

    public int calcDistance1Et2Distance(String lieuDepart, String lieuRecherche) {  // retourne 0 si le lieu départ et le lieuRecherche sont les mêmes, retourne 1 si ils sont à une distance et retourne 2 si ils sont à deux distance
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

    public JPanel creerGrapheVoisins1Et2Distance(String lieuDepart, String lieuRecherche) {        //retourne le panel du graphe qui calacul la distance entre deux lieux
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
        } else {
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

    public Graph getGraphVoisins1Et2Distance(String lieuDepart) {  // retourne le graphe qui calcule la distance entre deux lieux
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

    public int getNbVille(Graph graphCherche) {  // retourne le nombre de ville situées dans le graphe passé en paramètre
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

    public int getNbRestaurant(Graph graphCherche) {  // retourne le nombre de restaurants situées dans le graphe passé en paramètre
        int nbRestaurant = 0;
        int nbNode = graphCherche.getNodeCount();

        for (int i = 0; i < nbNode; i++) {
            for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                if (lieuParcour.getNomLieu().equals(graphCherche.getNode(i).getId())) {
                    switch (lieuParcour.getClass().getSimpleName()) {
                        case "Restaurant":
                            nbRestaurant = nbRestaurant + 1;
                            break;
                    }
                }
            }
        }

        return nbRestaurant;

    }

    public int getNbLoisir(Graph graphCherche) {    // retourne le nombre de loisirs situées dans le graphe passé en paramètre
        int nbLoisir = 0;
        int nbNode = graphCherche.getNodeCount();

        for (int i = 0; i < nbNode; i++) {
            for (Lieu lieuParcour : listeGraphe.getListeGraphe().keySet()) { // on cherche le lieu de départ
                if (lieuParcour.getNomLieu().equals(graphCherche.getNode(i).getId())) {
                    switch (lieuParcour.getClass().getSimpleName()) {
                        case "Loisir":
                            nbLoisir = nbLoisir + 1;
                            break;
                    }
                }
            }
        }

        return nbLoisir;

    }

    public int getNbNationale(Graph graphCherche) {     // retourne le nombre de nationales situées dans le graphe passé en paramètre
        int nbNationale = 0;
        int nbEdge = graphCherche.getEdgeCount();

        for (int i = 0; i < nbEdge; i++) {
            switch (graphCherche.getEdge(i).getAttribute("ui.label").toString().charAt(0)) {
                case 'N':
                    nbNationale = nbNationale + 1;
            }
        }

        return nbNationale;

    }

    public int getNbDepartementale(Graph graphCherche) {  // retourne le nombre de départementales situées dans le graphe passé en paramètre
        int nbDepartementale = 0;
        int nbEdge = graphCherche.getEdgeCount();

        for (int i = 0; i < nbEdge; i++) {
            switch (graphCherche.getEdge(i).getAttribute("ui.label").toString().charAt(0)) {
                case 'D':
                    nbDepartementale = nbDepartementale + 1;
            }
        }

        return nbDepartementale;

    }

    public int getNbAutoroute(Graph graphCherche) {   // retourne le nombre d'autoroute situées dans le graphe passé en paramètre
        int nbAutoroute = 0;
        int nbEdge = graphCherche.getEdgeCount();

        for (int i = 0; i < nbEdge; i++) {
            switch (graphCherche.getEdge(i).getAttribute("ui.label").toString().charAt(0)) {
                case 'A':
                    nbAutoroute = nbAutoroute + 1;
            }
        }

        return nbAutoroute;

    }

    public JPanel creerGraphe2Distance(String lieuDepart) {    // crée le panel contenant le graph à 2 distance
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

    public JPanel creerGraphe2DistanceTailleRéduit(String lieuDepart) {    // retourne le graphe à 2 distance en taille réduit
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);


        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(400, 300));
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

    public String getClasse(Graph graph, String lieuCherche) {   // retourne la class du lieu passé en paramètre
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

    public Graph getGrapheLien (Graph graphCherche, String lien) {   // retourne le graphe qui recherche un lien
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");

        for (int indexGraphEdge = 0; indexGraphEdge < graphCherche.getEdgeCount(); indexGraphEdge++) {
            if (graphCherche.getEdge(indexGraphEdge).getAttribute("ui.label").toString().equals(lien)) {
                Node lieuDebut = graphCherche.getEdge (indexGraphEdge).getNode0 ();
                Node lieuFin = graphCherche.getEdge (indexGraphEdge).getNode1 ();
                graph.addNode(lieuDebut.getId ());
                Node n = graph.getNode(lieuDebut.getId ());
                n.setAttribute("ui.label", n.getId());
                graph.addNode(lieuFin.getId ());
                Node n1 = graph.getNode(lieuFin.getId ());
                n1.setAttribute("ui.label", n1.getId());
                int i = 0;
                graph.addEdge(String.valueOf(i), n, n1);
                Edge edge = graph.getEdge (i);
                edge.setAttribute("ui.label", lieuDebut.getEdgeBetween (lieuFin).getAttribute("ui.label"));
            }
        }

        return graph;
    }

    public JPanel creerGrapheLien(Graph graphCherche, String lien) {    // crée le panel contenant le graphe qui recherche un lien
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Layout graphLayout = new SpringBox(false);
        graph = new SingleGraph("embedded");
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);


        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(980, 460));
        // view.getCamera().setViewPercent(1);

        for (int indexGraphEdge = 0; indexGraphEdge < graphCherche.getEdgeCount(); indexGraphEdge++) {
            if (graphCherche.getEdge(indexGraphEdge).getAttribute("ui.label").toString().equals(lien)) {
                Node lieuDebut = graphCherche.getEdge (indexGraphEdge).getNode0 ();
                Node lieuFin = graphCherche.getEdge (indexGraphEdge).getNode1 ();
                graph.addNode(lieuDebut.getId ());
                Node n = graph.getNode(lieuDebut.getId ());
                n.setAttribute("ui.label", n.getId());
                switch (getClasse (graphCherche, lieuDebut.getId ().toString ())) {
                    case "Ville" :
                        n.setAttribute("ui.style", "fill-color: red; ");
                        break;
                    case "Restaurant" :
                        n.setAttribute("ui.style", "fill-color: #0CAF09; ");
                        break;
                    case "Loisir" :
                        n.setAttribute("ui.style", "fill-color: orange; ");
                        break;
                }
                graph.addNode(lieuFin.getId ());
                Node n1 = graph.getNode(lieuFin.getId ());
                n1.setAttribute("ui.label", n1.getId());
                switch (getClasse (graphCherche, lieuFin.toString ())) {
                    case "Ville" :
                        n1.setAttribute("ui.style", "fill-color: red; ");
                        break;
                    case "Restaurant" :
                        n1.setAttribute("ui.style", "fill-color: #0CAF09; ");
                        break;
                    case "Loisir" :
                        n1.setAttribute("ui.style", "fill-color: orange; ");
                        break;
                }
                int i = 0;
                graph.addEdge(String.valueOf(i), n, n1);
                Edge edge = graph.getEdge (i);
                edge.setAttribute("ui.label", lieuDebut.getEdgeBetween (lieuFin).getAttribute("ui.label"));
                switch (edge.getAttribute("ui.label").toString ().charAt (0)) {
                    case 'N':
                        edge.setAttribute("ui.style", "fill-color: red; ");
                        break;
                    case 'D':
                        edge.setAttribute("ui.style", "fill-color: yellow; ");
                        break;
                    case 'A':
                        edge.setAttribute("ui.style", "fill-color: blue; ");
                        break;
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

    }