package IHM;

import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;
import Graphe.ConfigListe.ListeGraphe;
import Graphe.CreationGraphe;
import Graphe.CreationListe;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Color.black;

public class FenetreGraphVoisin extends JFrame {
    FenetrePrincipale fenetrePrincipale;
    JFrame jFrame = new JFrame ();
    private String cheminFile;

    CreationGraphe creationGraphe;

    JPanel graph;

    JPanel constrPartieVisuel;

    JLabel background = new JLabel ();

    JPanel noeudsVoisins;

    String noeudVoisin;
    public FenetreGraphVoisin (String newCheminFile, FenetrePrincipale newFenetrePrincipale, String newNoeudVoisin) throws IOException, ExceptionAjListeGraphe {
        super ();
        noeudVoisin = newNoeudVoisin;
        cheminFile = newCheminFile;
        fenetrePrincipale = newFenetrePrincipale;
        creationGraphe = new CreationGraphe (cheminFile);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
        background.add (basPanel ());
        jFrame.setVisible(true);
    }

    public JPanel constrPartieVisuel () throws IOException, ExceptionAjListeGraphe {
        JPanel p = new JPanel ();
        p.setLayout (new BorderLayout());
        graph = constrFenVisuel ();
        p.add (graph, BorderLayout.CENTER);
        p.add (constrInformationsSurLeGraphe (), BorderLayout.WEST);
        p.add (legende (), BorderLayout.EAST);
        p.setOpaque (false);
        return p;
    }

    public JPanel basPanel () {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add (boutonsDeVisualisation ());
        p.add (Box.createRigidArea(new Dimension(0, 60)));
        p.add (noeudsVoisins);
        p.setOpaque (false);
        return p;
    }

    public JPanel constrFenVisuel () throws IOException, ExceptionAjListeGraphe {
        System.setProperty("org.graphstream.ui", "swing");

        graph = creationGraphe.creerGrapheVoisins (noeudVoisin);

        graph.setOpaque (false);

        return graph;
    }

    public JPanel constrInformationsSurLeGraphe () {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel informationsGraphe = new JLabel ("Informations sur la carte ");
        p.add (informationsGraphe);
        informationsGraphe.setFont (new Font ("Arial", Font.BOLD, 20));
        informationsGraphe.setForeground(Color.WHITE);
        p.add (new JLabel (" "));
        Icon iconVille = new ImageIcon ("src/main/resources/lieuVilleIcone.png");
        JLabel nombreVilles = new JLabel ("Nombre de villes ");
        p.add (nombreVilles);
        nombreVilles.setIcon (iconVille);
        nombreVilles.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreVilles.setForeground(Color.WHITE);
        JLabel nbVilles = new JLabel (String.valueOf(creationGraphe.getNombreVilleVoisin (noeudVoisin)));
        p.add (nbVilles);
        nbVilles.setForeground(Color.WHITE);
        Icon iconRestaurant = new ImageIcon ("src/main/resources/lieuRestaurantIcone.png");
        JLabel nombreRestaurants = new JLabel ("Nombre de restaurants ");
        p.add (nombreRestaurants);
        nombreRestaurants.setIcon (iconRestaurant);
        nombreRestaurants.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreRestaurants.setForeground(Color.WHITE);
        JLabel nbRestaurants = new JLabel (String.valueOf(creationGraphe.getNombreRestaurantVoisin (noeudVoisin)));
        p.add (nbRestaurants);
        nbRestaurants.setForeground(Color.WHITE);
        Icon iconLoisir = new ImageIcon ("src/main/resources/lieuLoisirIcone.png");
        JLabel nombreLoisirs = new JLabel ("Nombre de loisirs ");
        p.add (nombreLoisirs);
        nombreLoisirs.setIcon (iconLoisir);
        nombreLoisirs.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreLoisirs.setForeground(Color.WHITE);
        JLabel nbLoisirs = new JLabel (String.valueOf(creationGraphe.getNombreLoisirVoisin (noeudVoisin)));
        p.add (nbLoisirs);
        nbLoisirs.setForeground(Color.WHITE);
        p.add (new JLabel (" "));
        p.add (new JLabel (""));
        Icon iconNationale = new ImageIcon ("src/main/resources/640px-Route_nationale_française_7.svg.png");
        JLabel nombreDeNationales = new JLabel ("Nombre de nationales ");
        p.add (nombreDeNationales);
        nombreDeNationales.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreDeNationales.setForeground(Color.WHITE);
        JLabel nbNationales = new JLabel (String.valueOf(creationGraphe.getNombreNationaleVoisin (noeudVoisin)));
        p.add (nbNationales);
        nbNationales.setForeground(Color.WHITE);
        nombreDeNationales.setIcon (iconNationale);
        Icon iconDepertementale = new ImageIcon ("src/main/resources/routeDepartementaleIcone.png");
        JLabel nombreDeDepartementales = new JLabel ("Nombre de departementales ");
        p.add (nombreDeDepartementales);
        nombreDeDepartementales.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreDeDepartementales.setForeground(Color.WHITE);
        JLabel nbDepartementales = new JLabel (String.valueOf(creationGraphe.getNombreDepartementaleVoisin (noeudVoisin)));
        p.add (nbDepartementales);
        nbDepartementales.setForeground(Color.WHITE);
        nbDepartementales.setIcon (iconDepertementale);
        Icon iconAutoroute = new ImageIcon ("src/main/resources/autorouteIcone.png");
        JLabel nombreDeAutoroutes = new JLabel ("Nombre d'autoroutes ");
        p.add (nombreDeAutoroutes);
        nombreDeAutoroutes.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreDeAutoroutes.setForeground(Color.WHITE);
        JLabel nbAutoroutes = new JLabel (String.valueOf (creationGraphe.getNombreAutorouteVoisin (noeudVoisin)));
        p.add (nbAutoroutes);
        nbAutoroutes.setForeground(Color.WHITE);
        nombreDeAutoroutes.setIcon (iconAutoroute);
        p.setOpaque (false);
        return p;
    }

    public JPanel legende () {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout(p, BoxLayout.Y_AXIS));
        Icon legendeVille = new ImageIcon ("src/main/resources/legendeVille.png");
        JLabel ville = new JLabel ("Ville ");
        p.add (ville);
        ville.setFont (new Font ("Arial", Font.BOLD, 15));
        ville.setForeground(Color.WHITE);
        ville.setIcon (legendeVille);
        Icon legendeRestaurant = new ImageIcon ("src/main/resources/legendeRestaurant.png");
        JLabel restaurant = new JLabel ("Restaurants ");
        p.add (restaurant);
        restaurant.setFont (new Font ("Arial", Font.BOLD, 15));
        restaurant.setForeground(Color.WHITE);
        restaurant.setIcon (legendeRestaurant);
        Icon legendeLoisir = new ImageIcon ("src/main/resources/legendeLoisir.png");
        JLabel loisir = new JLabel ("Loisir ");
        p.add (loisir);
        loisir.setFont (new Font ("Arial", Font.BOLD, 15));
        loisir.setForeground(Color.WHITE);
        loisir.setIcon (legendeLoisir);
        Icon lgendeNationale = new ImageIcon ("src/main/resources/legendeNationale.png");
        JLabel nationale = new JLabel ("Nationale ");
        p.add (nationale);
        nationale.setFont (new Font ("Arial", Font.BOLD, 15));
        nationale.setForeground(Color.WHITE);
        nationale.setIcon (lgendeNationale);
        Icon legendeDepartementale = new ImageIcon ("src/main/resources/legendeDepartementale.png");
        JLabel departementale = new JLabel ("Departementale ");
        p.add (departementale);
        departementale.setFont (new Font ("Arial", Font.BOLD, 15));
        departementale.setForeground(Color.WHITE);
        departementale.setIcon (legendeDepartementale);
        Icon legendeAutoroute = new ImageIcon ("src/main/resources/legendeAutoroute.png");
        JLabel autoroute = new JLabel ("Autoroute ");
        p.add (autoroute);
        autoroute.setFont (new Font ("Arial", Font.BOLD, 15));
        autoroute.setForeground(Color.WHITE);
        autoroute.setIcon (legendeAutoroute);
        p.setOpaque (false);
        return p;
    }

    public JPanel boutonsDeVisualisation () {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout(p, BoxLayout.Y_AXIS));
        JButton revenirAuGraphePrincipal = new JButton ("Revenir au graphe principal ");
        p.add (revenirAuGraphePrincipal);
        revenirAuGraphePrincipal.addActionListener (event -> {
            try {
                FenetreGraphe fenetreGraphe = new FenetreGraphe (cheminFile, fenetrePrincipale);
                jFrame.setVisible (false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ExceptionAjListeGraphe e) {
                throw new RuntimeException(e);
            }
        });
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
        Icon enregistrerIcon = new ImageIcon ("src/main/resources/enregistrer.png");
        ImageIcon iconEnregistrerRedim = new ImageIcon(((ImageIcon) enregistrerIcon).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JMenuItem enregistrer = new JMenuItem ("enregistrer ", iconEnregistrerRedim);
        JMenuItem enregistrerSous = new JMenuItem ("enregistrer-sous ");
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
        fichier.add (enregistrer);
        fichier.add (enregistrerSous);
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
        JMenu grapheVoisin = new JMenu ("graphe des voisins directes d'un lieu ");
        grapheVoisin.setIcon (iconVoisinRedim);
        JMenuItem choixNoeudVoisin = new JMenuItem ("Allez à la fenètre dédiée ");
        affichage.add (graphePrincipal);
        affichage.add (grapheVoisin);
        grapheVoisin.add (choixNoeudVoisin);
        choixNoeudVoisin.addActionListener (event -> {
            ArrayList <String> graphNode = creationGraphe.getNoeud ();
            JComboBox listegraphNode = new JComboBox (graphNode.toArray ());
            JButton visualiser = new JButton ("visualiser ");
            visualiser.addActionListener (event1 -> {
                try {
                    new FenetreGraphVoisin ("src/main/resources/graphe.csv", fenetrePrincipale, listegraphNode.getSelectedItem ().toString ());
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
        JMenuItem chercherLoisir = new JMenuItem ("Restaurant ", iconLoisirRedim);
        affichage.add (chercherVille);
        affichage.add (chercherRestaurant);
        affichage.add (chercherLoisir);
        chercherVille.addActionListener (event -> {
            JLabel aProximiteDe = new JLabel ("a proximité de : ");
            JLabel aDistanceDe = new JLabel ("a distance de : ");
            String [] aDistance = {"1"};
            ArrayList <String> graphNode = creationGraphe.getNoeud ();
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
            JOptionPane fenetreGraphVoisins = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les voisins directes ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreGraphVoisins.add (aProximiteDe);
            fenetreGraphVoisins.add(listegraphNode);
            fenetreGraphVoisins.add (aDistanceDe);
            fenetreGraphVoisins.add (distance);
            fenetreGraphVoisins.add (visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreGraphVoisins);
            diag.pack();
            diag.setVisible(true);
        });
        chercherRestaurant.addActionListener (event -> {
            JLabel aProximiteDe = new JLabel ("a proximité de : ");
            JLabel aDistanceDe = new JLabel ("a distance de : ");
            String [] aDistance = {"1"};
            ArrayList <String> graphNode = creationGraphe.getNoeud ();
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
            JOptionPane fenetreGraphVoisins = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les voisins directes ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreGraphVoisins.add (aProximiteDe);
            fenetreGraphVoisins.add(listegraphNode);
            fenetreGraphVoisins.add (aDistanceDe);
            fenetreGraphVoisins.add (distance);
            fenetreGraphVoisins.add (visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreGraphVoisins);
            diag.pack();
            diag.setVisible(true);
        });
        chercherLoisir.addActionListener (event -> {
            JLabel aProximiteDe = new JLabel ("a proximité de : ");
            JLabel aDistanceDe = new JLabel ("a distance de : ");
            String [] aDistance = {"1"};
            ArrayList <String> graphNode = creationGraphe.getNoeud ();
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
            JOptionPane fenetreGraphVoisins = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les voisins directes ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreGraphVoisins.add (aProximiteDe);
            fenetreGraphVoisins.add(listegraphNode);
            fenetreGraphVoisins.add (aDistanceDe);
            fenetreGraphVoisins.add (distance);
            fenetreGraphVoisins.add (visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreGraphVoisins);
            diag.pack();
            diag.setVisible(true);
        });

        affichage.setForeground(Color.WHITE);
        return affichage;
    }

    public JPanel noeudsVoisins () throws IOException, ExceptionAjListeGraphe {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout(p, BoxLayout.Y_AXIS));
        ArrayList <String> graphNode = creationGraphe.getNoeud ();
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
                new FenetreGraphVoisin ("src/main/resources/graphe.csv", fenetrePrincipale, listegraphNode.getSelectedItem ().toString ());
                jFrame.setVisible (false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ExceptionAjListeGraphe e) {
                throw new RuntimeException(e);
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
