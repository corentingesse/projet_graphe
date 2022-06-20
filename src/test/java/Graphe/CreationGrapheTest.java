package Graphe;
import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;
import org.graphstream.graph.*;

import javax.swing.*;
import java.io.IOException;

import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;
import org.graphstream.graph.Graph;
import org.graphstream.stream.AttributeSink;
import org.graphstream.stream.ElementSink;
import org.graphstream.stream.Sink;
import org.graphstream.ui.view.Viewer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CreationGrapheTest {

    static CreationGraphe creationGraphe;

    @BeforeClass
    public static void setUpClass() throws IOException, ExceptionAjListeGraphe {
        creationGraphe = new CreationGraphe ("src/main/resources/graphe.csv");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }


    @Test
    void creerGraphe() {
        JPanel jpanel = new JPanel ();
        assertNotEquals (creationGraphe.creerGraphe (), jpanel);
    }

    @Test
    void getGraphe() {
        Graph graph = new Graph() {
            @Override
            public void addSink(Sink sink) {

            }

            @Override
            public void removeSink(Sink sink) {

            }

            @Override
            public void addAttributeSink(AttributeSink attributeSink) {

            }

            @Override
            public void removeAttributeSink(AttributeSink attributeSink) {

            }

            @Override
            public void addElementSink(ElementSink elementSink) {

            }

            @Override
            public void removeElementSink(ElementSink elementSink) {

            }

            @Override
            public void clearElementSinks() {

            }

            @Override
            public void clearAttributeSinks() {

            }

            @Override
            public void clearSinks() {

            }

            @Override
            public void nodeAdded(String s, long l, String s1) {

            }

            @Override
            public void nodeRemoved(String s, long l, String s1) {

            }

            @Override
            public void edgeAdded(String s, long l, String s1, String s2, String s3, boolean b) {

            }

            @Override
            public void edgeRemoved(String s, long l, String s1) {

            }

            @Override
            public void graphCleared(String s, long l) {

            }

            @Override
            public void stepBegins(String s, long l, double v) {

            }

            @Override
            public void graphAttributeAdded(String s, long l, String s1, Object o) {

            }

            @Override
            public void graphAttributeChanged(String s, long l, String s1, Object o, Object o1) {

            }

            @Override
            public void graphAttributeRemoved(String s, long l, String s1) {

            }

            @Override
            public void nodeAttributeAdded(String s, long l, String s1, String s2, Object o) {

            }

            @Override
            public void nodeAttributeChanged(String s, long l, String s1, String s2, Object o, Object o1) {

            }

            @Override
            public void nodeAttributeRemoved(String s, long l, String s1, String s2) {

            }

            @Override
            public void edgeAttributeAdded(String s, long l, String s1, String s2, Object o) {

            }

            @Override
            public void edgeAttributeChanged(String s, long l, String s1, String s2, Object o, Object o1) {

            }

            @Override
            public void edgeAttributeRemoved(String s, long l, String s1, String s2) {

            }

            @Override
            public int getNodeCount() {
                return 0;
            }

            @Override
            public int getEdgeCount() {
                return 0;
            }

            @Override
            public Stream<Node> nodes() {
                return null;
            }

            @Override
            public Stream<Edge> edges() {
                return null;
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public int getIndex() {
                return 0;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Object getFirstAttributeOf(String... strings) {
                return null;
            }

            @Override
            public <T> T getAttribute(String s, Class<T> aClass) {
                return null;
            }

            @Override
            public <T> T getFirstAttributeOf(Class<T> aClass, String... strings) {
                return null;
            }

            @Override
            public boolean hasAttribute(String s) {
                return false;
            }

            @Override
            public boolean hasAttribute(String s, Class<?> aClass) {
                return false;
            }

            @Override
            public Stream<String> attributeKeys() {
                return null;
            }

            @Override
            public void clearAttributes() {

            }

            @Override
            public void setAttribute(String s, Object... objects) {

            }

            @Override
            public void removeAttribute(String s) {

            }

            @Override
            public int getAttributeCount() {
                return 0;
            }

            /**
             * Returns an iterator over elements of type {@code T}.
             *
             * @return an Iterator.
             */
            @Override
            public Iterator<Node> iterator() {
                return null;
            }

            @Override
            public Node getNode(String s) {
                return null;
            }

            @Override
            public Edge getEdge(String s) {
                return null;
            }

            @Override
            public NodeFactory<? extends Node> nodeFactory() {
                return null;
            }

            @Override
            public EdgeFactory<? extends Edge> edgeFactory() {
                return null;
            }

            @Override
            public boolean isStrict() {
                return false;
            }

            @Override
            public boolean isAutoCreationEnabled() {
                return false;
            }

            @Override
            public double getStep() {
                return 0;
            }

            @Override
            public void setNodeFactory(NodeFactory<? extends Node> nodeFactory) {

            }

            @Override
            public void setEdgeFactory(EdgeFactory<? extends Edge> edgeFactory) {

            }

            @Override
            public void setStrict(boolean b) {

            }

            @Override
            public void setAutoCreate(boolean b) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Node addNode(String s) throws IdAlreadyInUseException {
                return null;
            }

            @Override
            public void stepBegins(double v) {

            }

            @Override
            public Iterable<AttributeSink> attributeSinks() {
                return null;
            }

            @Override
            public Iterable<ElementSink> elementSinks() {
                return null;
            }

            @Override
            public Viewer display() {
                return null;
            }

            @Override
            public Viewer display(boolean b) {
                return null;
            }

            @Override
            public Node getNode(int i) throws IndexOutOfBoundsException {
                return null;
            }

            @Override
            public Edge getEdge(int i) throws IndexOutOfBoundsException {
                return null;
            }

            @Override
            public Edge addEdge(String s, Node node, Node node1, boolean b) throws IdAlreadyInUseException, EdgeRejectedException {
                return null;
            }

            @Override
            public Edge removeEdge(Node node, Node node1) throws ElementNotFoundException {
                return null;
            }

            @Override
            public Edge removeEdge(Edge edge) {
                return null;
            }

            @Override
            public Node removeNode(Node node) {
                return null;
            }
        };
        assertEquals (creationGraphe.getGraphe (), graph);
    }

    @Test
    void creerGrapheVoisins() {
    }

    @Test
    void getGraphVoisin() {
    }

    @Test
    void getNoeud() {
    }

    @Test
    void getEdge() {
    }

    @Test
    void getLieu1Edge() {
    }

    @Test
    void getLieu2Edge() {
    }

    @Test
    void creerGrapheChercher() {
    }

    @Test
    void getGraphChercher() {
    }

    @Test
    void calcDistance() {
    }

    @Test
    void calcDistance1Et2Distance() {
    }

    @Test
    void creerGrapheVoisins1Et2Distance() {
    }

    @Test
    void getGraphVoisins1Et2Distance() {
    }

    @Test
    void getNbVille() {
    }

    @Test
    void getNbRestaurant() {
    }

    @Test
    void getNbLoisir() {
    }

    @Test
    void getNbNationale() {
    }

    @Test
    void getNbDepartementale() {
    }

    @Test
    void getNbAutoroute() {
    }

    @Test
    void creerGraphe2Distance() {
    }

    @Test
    void creerGraphe2DistanceTailleRéduit() {
    }

    @Test
    void getClasse() {
    }

    @Test
    void getGrapheLien() {
    }

    @Test
    void creerGrapheLien() {
    }
}