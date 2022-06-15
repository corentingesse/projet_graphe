package IHM;

import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;
import Graphe.CreationGraphe;
import org.graphstream.graph.Graph;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Color.black;

public class APropos extends JFrame {
    FenetrePrincipale fenetrePrincipale;
    JFrame jFrame = new JFrame ();
    private String cheminFile;

    CreationGraphe creationGraphe;

    JPanel graph;

    JPanel constrPartieVisuel;

    JPanel noeudsVoisins;

    public APropos (String newCheminFile, FenetrePrincipale newFenetrePrincipale) throws IOException, ExceptionAjListeGraphe {
        super ();
        cheminFile = newCheminFile;
        fenetrePrincipale = newFenetrePrincipale;
        creationGraphe = new CreationGraphe (cheminFile);
        ImageIcon imageFond = new ImageIcon("src/main/resources/Graph_Plan.png");
        jFrame.setMinimumSize(new Dimension(1650, 1080));
        jFrame.setExtendedState(this.MAXIMIZED_BOTH);
        jFrame.getContentPane().setLayout(new GridLayout(1, 1));
        Image iconGraph = Toolkit.getDefaultToolkit().getImage ("src/main/resources/GraphIcone.png");
        jFrame.setIconImage (iconGraph);
        jFrame.setJMenuBar (jMenuBar ());
        JLabel background = new JLabel(imageFond);
        background.setLayout (new FlowLayout ());
        jFrame.add (background);
        constrPartieVisuel = constrPartieVisuel ();
        noeudsVoisins = noeudsVoisins ();
        background.add (constrPartieVisuel);
        jFrame.setVisible(true);
    }

    public JPanel constrPartieVisuel () {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout (p, BoxLayout.Y_AXIS));
        JLabel aproposexplications = new JLabel ("Cette application a été crée dans le cadre d'une SAE dans la formation du BUT Informatique à l'IUT de Lyon 1. ");
        JLabel aproposexplications1 = new JLabel ("Nous sommes Corentin GESSE--ENTRESSANGLE et Nicolas DE SAINT JEAN, les programmeurs de ce logiciel. ");
        JLabel aproposexplications2 = new JLabel ("Ce logiciel vous permettra de générer le visuel d'une carte géographique préalablement écrit sous forme d'un fichier txt ou csv ");
        JLabel aproposexplications3 = new JLabel ("dans un format spécifique. Vous pourrez alors effectuer quelques manipulations comme la recherche des voisins directes ou indirectes d'un lieu, la distance en nombres ");
        JLabel aproposexplications4 = new JLabel ("de lieux d'un lieu ou encore comparer plusieurs lieux. Nous esperons que notre logiciel correspondra à vos attentes et qu'il sera facile d'utilisation ");
        p.add (aproposexplications);
        p.add (aproposexplications1);
        p.add (aproposexplications2);
        p.add (aproposexplications3);
        p.add (aproposexplications4);
        aproposexplications.setForeground(Color.WHITE);
        aproposexplications1.setForeground(Color.WHITE);
        aproposexplications2.setForeground(Color.WHITE);
        aproposexplications3.setForeground(Color.WHITE);
        aproposexplications4.setForeground(Color.WHITE);
        p.setOpaque (false);
        return p;
    }

    public JMenuBar jMenuBar () {
        JMenuBar jMenuBar = new JMenuBar ();
        jMenuBar.setBackground (black);
        jMenuBar.add (jMenuFichier ());
        jMenuBar.add (jMenuAPropos ());
        jMenuBar.add (jMenuAffichage ());
        jMenuBar.add (jMenuChercher ());
        jMenuBar.add (jMenuDistance ());
        jMenuBar.add (jMenuComparer ());
        jMenuBar.add (jMenuRoute ());
        return jMenuBar;
    }

    public JMenu jMenuFichier () {
        JMenu fichier = new JMenu ("Fichier ");
        Icon nouveauIcon = new ImageIcon ("src/main/resources/nouveau.png");
        ImageIcon iconNouveauRedim = new ImageIcon(((ImageIcon) nouveauIcon).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem nouveau = new JMenuItem ("nouveau ", iconNouveauRedim);
        Icon iconOuverture = new ImageIcon ("src/main/resources/ouverture.png");
        ImageIcon iconOuvrirRedim = new ImageIcon(((ImageIcon) iconOuverture).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem ouvrir = new JMenuItem ("ouvrir ", iconOuvrirRedim);
        Icon fermerIcon = new ImageIcon ("src/main/resources/fermer.png");
        ImageIcon iconFermerRedim = new ImageIcon(((ImageIcon) fermerIcon).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem fermer = new JMenuItem ("fermer ", iconFermerRedim);
        fichier.add (nouveau);
        nouveau.addActionListener (event -> {
            FenetreCreer fenetreCreer = new FenetreCreer (fenetrePrincipale);
            jFrame.setVisible (false);
        });
        fichier.add (ouvrir);
        ouvrir.addActionListener (event -> {
            JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            choose.setDialogTitle("selectionnez votre graphe ");
            choose.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("graphe au format csv ou txt ", "csv", "txt");
            choose.addChoosableFileFilter(filter);
            int res = choose.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                try {
                    FenetreGraphe fenetreGraphe = new FenetreGraphe(choose.getSelectedFile().getPath(), fenetrePrincipale);
                    jFrame.setVisible (false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ExceptionAjListeGraphe e) {
                    throw new RuntimeException(e);
                }
            }});
        fichier.add (new JSeparator ());
        fichier.add (fermer);
        fermer.addActionListener (event -> {
            fenetrePrincipale.getJFrame ().setVisible (true);
            jFrame.dispose ();
        });

        fichier.setForeground(Color.WHITE);
        return fichier;
    }

    public JMenu jMenuAffichage () {
        JMenu affichage = new JMenu ("Affichage ");
        Icon mapIcone = new ImageIcon ("src/main/resources/mapIcone.png");
        ImageIcon iconMapRedim = new ImageIcon(((ImageIcon) mapIcone).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem graphePrincipal = new JMenuItem ("graphe principal ", iconMapRedim);
        Icon voisinIcone = new ImageIcon ("src/main/resources/voisinIcone.jpg");
        ImageIcon iconVoisinRedim = new ImageIcon(((ImageIcon) voisinIcone).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenu grapheVoisin = new JMenu ("graphe des voisins d'un lieu ");
        grapheVoisin.setIcon (iconVoisinRedim);
        JMenuItem choixNoeudVoisin = new JMenuItem ("Allez à la fenètre dédiée ");
        affichage.add (graphePrincipal);
        graphePrincipal.addActionListener (event -> {
            JOptionPane.showMessageDialog(new JFrame(), "Vous êtes déjà sur le graphe principal", "Erreur", JOptionPane.ERROR_MESSAGE);
        });
        affichage.add (grapheVoisin);
        grapheVoisin.add (choixNoeudVoisin);
        choixNoeudVoisin.addActionListener (event -> {
            ArrayList<String> graphNode = creationGraphe.getNoeud (creationGraphe.getGraphe ());
            ArrayList<Integer> distance = new ArrayList <Integer> ();
            distance.add (1);
            distance.add (2);
            JComboBox listegraphNode = new JComboBox (graphNode.toArray ());
            JComboBox choixDistance = new JComboBox (distance.toArray ());
            JLabel distanceExplications = new JLabel ("A ... distance ");
            JButton visualiser = new JButton ("visualiser ");
            visualiser.addActionListener (event1 -> {
                try {
                    new FenetreGraphVoisin ("src/main/resources/graphe.csv", fenetrePrincipale, listegraphNode.getSelectedItem ().toString (), (Integer) choixDistance.getSelectedItem ());
                    jFrame.setVisible (false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ExceptionAjListeGraphe e) {
                    throw new RuntimeException(e);
                }

                validate();
            });

            Object[] options = new Object[]{};
            JOptionPane fenetreGraphVoisins = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les voisins directes ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreGraphVoisins.add(listegraphNode);
            fenetreGraphVoisins.add (distanceExplications);
            fenetreGraphVoisins.add (choixDistance);
            fenetreGraphVoisins.add (visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreGraphVoisins);
            diag.pack();
            diag.setVisible(true);
        });

        affichage.setForeground(Color.WHITE);
        return affichage;
    }

    public JMenu jMenuChercher () {
        JMenu affichage = new JMenu ("Chercher lieu à proximité ");
        Icon villeIcone = new ImageIcon ("src/main/resources/lieuVilleIcone.png");
        ImageIcon iconVilleRedim = new ImageIcon(((ImageIcon) villeIcone).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem chercherVille = new JMenuItem ("Ville ", iconVilleRedim);
        Icon restaurantIcone = new ImageIcon ("src/main/resources/lieuRestaurantIcone.png");
        ImageIcon iconRestaurantRedim = new ImageIcon(((ImageIcon) restaurantIcone).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem chercherRestaurant = new JMenuItem ("Restaurant ", iconRestaurantRedim);
        Icon loisirIcone = new ImageIcon ("src/main/resources/lieuLoisirIcone.png");
        ImageIcon iconLoisirRedim = new ImageIcon(((ImageIcon) loisirIcone).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem chercherLoisir = new JMenuItem ("Loisir ", iconLoisirRedim);
        affichage.add (chercherVille);
        affichage.add (chercherRestaurant);
        affichage.add (chercherLoisir);
        chercherVille.addActionListener (event -> {
            JLabel aProximiteDe = new JLabel ("a proximité de : ");
            JLabel aDistanceDe = new JLabel ("a distance de : ");
            String [] aDistance = {"1"};
            ArrayList <String> graphNode = creationGraphe.getNoeud (creationGraphe.getGraphe ());
            JComboBox listegraphNode = new JComboBox (graphNode.toArray ());
            JComboBox distance = new JComboBox (aDistance);
            JButton visualiser = new JButton ("visualiser ");
            visualiser.addActionListener (event1 -> {
                try {
                    new FenetreGrapheChercher ("src/main/resources/graphe.csv", fenetrePrincipale, listegraphNode.getSelectedItem ().toString (), "Ville");
                    jFrame.setVisible (false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ExceptionAjListeGraphe e) {
                    throw new RuntimeException(e);
                }

                validate();
            });
            Object[] options = new Object[]{};
            JOptionPane fenetreVille = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les villes voisines ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreVille.add (aProximiteDe);
            fenetreVille.add(listegraphNode);
            fenetreVille.add (aDistanceDe);
            fenetreVille.add (distance);
            fenetreVille.add (visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreVille);
            diag.pack();
            diag.setVisible(true);
        });
        chercherRestaurant.addActionListener (event -> {
            JLabel aProximiteDe = new JLabel ("a proximité de : ");
            JLabel aDistanceDe = new JLabel ("a distance de : ");
            String [] aDistance = {"1"};
            ArrayList <String> graphNode = creationGraphe.getNoeud (creationGraphe.getGraphe ());
            JComboBox listegraphNode = new JComboBox (graphNode.toArray ());
            JComboBox distance = new JComboBox (aDistance);
            JButton visualiser = new JButton ("visualiser ");
            visualiser.addActionListener (event1 -> {
                try {
                    new FenetreGrapheChercher ("src/main/resources/graphe.csv", fenetrePrincipale, listegraphNode.getSelectedItem ().toString (), "Restaurant");
                    jFrame.setVisible (false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ExceptionAjListeGraphe e) {
                    throw new RuntimeException(e);
                }

                validate();
            });
            Object[] options = new Object[]{};
            JOptionPane fenetreRestaurant = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les restaurants voisins ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreRestaurant.add (aProximiteDe);
            fenetreRestaurant.add(listegraphNode);
            fenetreRestaurant.add (aDistanceDe);
            fenetreRestaurant.add (distance);
            fenetreRestaurant.add (visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreRestaurant);
            diag.pack();
            diag.setVisible(true);
        });
        chercherLoisir.addActionListener (event -> {
            JLabel aProximiteDe = new JLabel ("a proximité de : ");
            JLabel aDistanceDe = new JLabel ("a distance de : ");
            String [] aDistance = {"1"};
            ArrayList <String> graphNode = creationGraphe.getNoeud (creationGraphe.getGraphe ());
            JComboBox listegraphNode = new JComboBox (graphNode.toArray ());
            JComboBox distance = new JComboBox (aDistance);
            JButton visualiser = new JButton ("visualiser ");
            visualiser.addActionListener (event1 -> {
                try {
                    new FenetreGrapheChercher ("src/main/resources/graphe.csv", fenetrePrincipale, listegraphNode.getSelectedItem ().toString (), "Loisir");
                    jFrame.setVisible (false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ExceptionAjListeGraphe e) {
                    throw new RuntimeException(e);
                }

                validate();
            });

            Object[] options = new Object[]{};
            JOptionPane fenetreLoisir = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les loisirs voiisns ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreLoisir.add (aProximiteDe);
            fenetreLoisir.add(listegraphNode);
            fenetreLoisir.add (aDistanceDe);
            fenetreLoisir.add (distance);
            fenetreLoisir.add (visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreLoisir);
            diag.pack();
            diag.setVisible(true);
        });

        affichage.setForeground(Color.WHITE);
        return affichage;
    }

    public JMenu jMenuComparer () {
        JMenu comparer = new JMenu ("Comparer ");
        JMenuItem comparerTousLesLieux = new JMenuItem ("Comparer tous les lieux ");
        JMenuItem comparerDeuxLieux = new JMenuItem ("Comparer deux lieux ");
        comparer.add (comparerTousLesLieux);
        comparer.add (comparerDeuxLieux);
        comparerDeuxLieux.addActionListener (event -> {
            JLabel premierLieu = new JLabel ("Premier lieu ");
            JLabel deuxiemeLieu = new JLabel ("Deuxième lieu ");
            ArrayList <String> graphNode = creationGraphe.getNoeud (creationGraphe.getGraphe ());
            JComboBox listegraphNode = new JComboBox (graphNode.toArray ());
            JComboBox listegraphNode2 = new JComboBox (graphNode.toArray ());
            listegraphNode2.remove (listegraphNode.getSelectedIndex ());
            JButton visualiser = new JButton ("visualiser ");
            visualiser.addActionListener (event1 -> {
                try {
                    new FenetreComparaisonDeuxLieux ("src/main/resources/graphe.csv", fenetrePrincipale, listegraphNode.getSelectedItem ().toString (), listegraphNode2.getSelectedItem ().toString ());
                    jFrame.setVisible (false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ExceptionAjListeGraphe e) {
                    throw new RuntimeException(e);
                }

                validate();
            });
            Object[] options = new Object[]{};
            JOptionPane fenetreComparerDeuxLieux = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les voisins directes ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreComparerDeuxLieux.add (premierLieu);
            fenetreComparerDeuxLieux.add(listegraphNode);
            fenetreComparerDeuxLieux.add (deuxiemeLieu);
            fenetreComparerDeuxLieux.add (listegraphNode2);
            fenetreComparerDeuxLieux.add (visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreComparerDeuxLieux);
            diag.pack();
            diag.setVisible(true);
        });
        comparerTousLesLieux.addActionListener (event -> {
            JLabel categorie = new JLabel ("Categorie de lieu ");
            ArrayList <String> graphNode = new ArrayList<> ();
            graphNode.add ("toutes les catégories ");
            graphNode.add ("Ville");
            graphNode.add ("Restaurant");
            graphNode.add ("Loisir");
            JComboBox listegraphNode = new JComboBox (graphNode.toArray ());
            JButton visualiser = new JButton ("visualiser ");
            visualiser.addActionListener (event1 -> {
                try {
                    if (listegraphNode.getSelectedItem ().toString ().equals ("toutes les catégories "))
                        new FenetreComparaisonGenerale ("src/main/resources/graphe.csv", fenetrePrincipale);
                    else
                        new FenetreComparaisonGeneraleCategorie ("src/main/resources/graphe.csv", fenetrePrincipale, listegraphNode.getSelectedItem ().toString ());
                    jFrame.setVisible (false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ExceptionAjListeGraphe e) {
                    throw new RuntimeException(e);
                }

                validate();
            });
            Object[] options = new Object[]{};
            JOptionPane fenetreComparaisonGenerale = new JOptionPane("Selectionnez la catégorie que vous souhaitez comparer ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreComparaisonGenerale.add (categorie);
            fenetreComparaisonGenerale.add(listegraphNode);
            fenetreComparaisonGenerale.add (visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreComparaisonGenerale);
            diag.pack();
            diag.setVisible(true);
        });

        comparer.setForeground(Color.WHITE);
        return comparer;
    }

    public JMenu jMenuRoute () {
        JMenu route = new JMenu ("Route ");
        Icon villeIcone = new ImageIcon ("src/main/resources/lieuVilleIcone.png");
        ImageIcon iconVilleRedim = new ImageIcon(((ImageIcon) villeIcone).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem SavoirLesLieuxRoute = new JMenuItem ("Savoir quels lieux relient une route ", iconVilleRedim);
        Icon restaurantIcone = new ImageIcon ("src/main/resources/lieuRestaurantIcone.png");
        route.add (SavoirLesLieuxRoute);
        SavoirLesLieuxRoute.addActionListener (event -> {
            JLabel routeExplications = new JLabel ("Route ");
            ArrayList <String> edge = creationGraphe.getEdge (creationGraphe.getGraphe ());
            JComboBox listeGraphEdge = new JComboBox (edge.toArray ());
            JButton visualiser = new JButton ("visualiser ");
            visualiser.addActionListener (event1 -> {
                try {
                    new FenetreLien (cheminFile, fenetrePrincipale, listeGraphEdge.getSelectedItem ().toString ());
                    jFrame.setVisible (false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Le fichier contenant le graphe comporte des erreurs, il doit être sous la forme : type,nomLieu:typeRouteVoisin1,routeVoisin1::typeVoisin1,voisin1[;typeRouteVoisinN,routeVoisinN::typeVoisinN,voisinN];;  ", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                validate();
            });
            Object[] options = new Object[]{};
            JOptionPane fenetreRouteLien = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les voisins directes ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreRouteLien.add (routeExplications);
            fenetreRouteLien.add (listeGraphEdge);
            fenetreRouteLien.add (visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreRouteLien);
            diag.pack();
            diag.setVisible(true);
        });
        route.setForeground(Color.WHITE);
        return route;
    }

    public JMenu jMenuDistance () {
        JMenu distance = new JMenu("Distance");
        JMenu distanceNombreLieux = new JMenu("Distance par nombre de lieux ");
        distance.add(distanceNombreLieux);
        JMenuItem voisinsDirectes = new JMenuItem("Savoir si deux lieux sont des voisins directes ou indirectes à 2 distance ");
        distanceNombreLieux.add(voisinsDirectes);
        voisinsDirectes.addActionListener(event -> {
            JLabel villeDepart = new JLabel("ville de départ : ");
            JLabel villeRecherchee = new JLabel("ville recherchée :  ");
            ArrayList<String> graphNode = creationGraphe.getNoeud (creationGraphe.getGraphe ());
            JComboBox listegraphNodeDepart = new JComboBox(graphNode.toArray());
            JComboBox listegraphNodeRecherchee = new JComboBox(graphNode.toArray());
            JButton visualiser = new JButton("visualiser ");
            visualiser.addActionListener(event1 -> {
                try {
                    new FenetreDistance1Et2Distance ("src/main/resources/graphe.csv", fenetrePrincipale, listegraphNodeDepart.getSelectedItem().toString(), listegraphNodeRecherchee.getSelectedItem().toString(), 1);
                    jFrame.setVisible(false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Le fichier contenant le graphe comporte des erreurs, il doit être sous la forme : type,nomLieu:typeRouteVoisin1,routeVoisin1::typeVoisin1,voisin1[;typeRouteVoisinN,routeVoisinN::typeVoisinN,voisinN];;  ", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                validate();
            });
            Object[] options = new Object[]{};
            JOptionPane fenetreGraphVoisins = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les voisins directes ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreGraphVoisins.add(villeDepart);
            fenetreGraphVoisins.add(listegraphNodeDepart);
            fenetreGraphVoisins.add(villeRecherchee);
            fenetreGraphVoisins.add(listegraphNodeRecherchee);
            fenetreGraphVoisins.add(visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreGraphVoisins);
            diag.pack();
            diag.setVisible(true);
        });

        distance.setForeground(Color.WHITE);
        return distance;
    }



    public JPanel noeudsVoisins () throws IOException, ExceptionAjListeGraphe {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout(p, BoxLayout.Y_AXIS));
        ArrayList <String> graphNode = creationGraphe.getNoeud (creationGraphe.getGraphe ());
        JLabel titreVisualiserNoeudsVoisins = new JLabel ("Visualiser les noeuds voisins directes d'un noeud ");
        JLabel explicationsNoeudsVoisins = new JLabel ("Commencez par choisir le noeud dont vous souhaitez afficher les voisins directes ");
        titreVisualiserNoeudsVoisins.setForeground(Color.WHITE);
        explicationsNoeudsVoisins.setForeground(Color.WHITE);
        JComboBox listegraphNode = new JComboBox (graphNode.toArray ());
        p.add (titreVisualiserNoeudsVoisins);
        p.add (explicationsNoeudsVoisins);
        p.add (listegraphNode);
        listegraphNode.setFont (new Font("Times New Roman", Font.BOLD, 15));
        listegraphNode.setBackground (black);
        listegraphNode.setForeground(Color.WHITE);
        JButton visualiser = new JButton ("visualiser ");
        p.add (visualiser);
        visualiser.addActionListener (event -> {
            try {
                new FenetreGraphVoisin ("src/main/resources/graphe.csv", fenetrePrincipale, listegraphNode.getSelectedItem ().toString (), 0);
                jFrame.setVisible (false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(new JFrame(), "Le fichier contenant le graphe comporte des erreurs, il doit être sous la forme : type,nomLieu:typeRouteVoisin1,routeVoisin1::typeVoisin1,voisin1[;typeRouteVoisinN,routeVoisinN::typeVoisinN,voisinN];;  ", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            validate();
        });
        p.setOpaque (false);
        return p;
    }

    public JMenu jMenuAPropos () {
        JMenu APropos = new JMenu ("A propos ");
        APropos.setForeground(Color.WHITE);
        return APropos;
    }
}
