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

public class FenetreComparaisonDeuxLieux extends JFrame {
    FenetrePrincipale fenetrePrincipale;
    JFrame jFrame = new JFrame ();
    private String cheminFile;

    CreationGraphe creationGraphe;

    JPanel graph1;

    JPanel graph2;

    JPanel constrPartieVisuel;

    JLabel background = new JLabel ();

    JPanel noeudsVoisins;

    String lieu1;

    String lieu2;
    Graph getGraph1;

    Graph getGraph2;

    int nbVilleLieu1;
    int nbVilleLieu2;
    int nbRestaurantLieu1;
    int nbRestaurantLieu2;
    int nbLoisirLieu1;
    int nbLoisirLieu2;

    JLabel medaille1Lieu1;
    JLabel medaille2Lieu1;
    JLabel medaille3Lieu1;

    JLabel medaille1Lieu2;

    JLabel medaille2Lieu2;

    JLabel medaille3Lieu2;

    public FenetreComparaisonDeuxLieux (String newCheminFile, FenetrePrincipale newFenetrePrincipale, String newLieu1, String newLieu2) throws IOException, ExceptionAjListeGraphe {
        super ();
        lieu1 = newLieu1;
        lieu2 = newLieu2;
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
        background.add (informationsGraphe ());
        jFrame.setVisible(true);
    }

    public JPanel constrPartieVisuel () throws IOException, ExceptionAjListeGraphe {
        JPanel p = new JPanel ();
        p.setLayout (new BorderLayout());
        graph1 = constrFenVisuel1 ();
        graph2 = constrFenVisuel2 ();
        p.add (graph1, BorderLayout.WEST);
        p.add (graph2,  BorderLayout.EAST);
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

    public JPanel informationsGraphe () throws IOException, ExceptionAjListeGraphe {
        JPanel p = new JPanel ();
        p.setLayout (new BorderLayout());

        p.add (constrInformationsSurLeGraphe1 (), BorderLayout.WEST);
        p.add (constrInformationsSurLeGraphe2 (), BorderLayout.EAST);
        p.add (constrComparaison (), BorderLayout.CENTER);
        p.setOpaque (false);

        return p;
    }

    public JPanel constrFenVisuel1 () throws IOException, ExceptionAjListeGraphe {
        System.setProperty("org.graphstream.ui", "swing");

        graph1 = creationGraphe.creerGraphe2Distance (lieu1);

        graph1.setOpaque (false);

        return graph1;
    }

    public JPanel constrFenVisuel2 () throws IOException, ExceptionAjListeGraphe {
        System.setProperty("org.graphstream.ui", "swing");

        graph2 = creationGraphe.creerGraphe2Distance (lieu2);

        graph2.setOpaque (false);

        return graph2;
    }

    public JPanel constrInformationsSurLeGraphe1 () {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel informationsGraphe = new JLabel ("Informations sur  " + lieu1);
        p.add (informationsGraphe);
        JPanel p1 = new JPanel ();
        p1.setLayout (new FlowLayout());
        medaille1Lieu1 = new JLabel (" / : ouvert ");
        medaille2Lieu1 = new JLabel ("/ : gastrnomique ");
        medaille3Lieu1 = new JLabel (" / : culturel ");
        p1.add (medaille1Lieu1);
        p1.add (medaille2Lieu1);
        p1.add (medaille3Lieu1);
        p1.setOpaque (false);
        p.add (p1);
        medaille1Lieu1.setFont (new Font ("Arial", Font.BOLD, 10));
        medaille1Lieu1.setForeground(Color.WHITE);
        medaille2Lieu1.setFont (new Font ("Arial", Font.BOLD, 10));
        medaille2Lieu1.setForeground(Color.WHITE);
        medaille3Lieu1.setFont (new Font ("Arial", Font.BOLD, 10));
        medaille3Lieu1.setForeground(Color.WHITE);
        informationsGraphe.setFont (new Font ("Arial", Font.BOLD, 20));
        informationsGraphe.setForeground(Color.WHITE);
        p.add (new JLabel (" "));
        Icon iconVille = new ImageIcon ("src/main/resources/lieuVilleIcone.png");
        getGraph1 = creationGraphe.getGraphVoisins1Et2Distance (lieu1);
        nbVilleLieu1 = creationGraphe.getNbVille (getGraph1);
        nbRestaurantLieu1 = creationGraphe.getNbRestaurant (getGraph1);
        nbLoisirLieu1 = creationGraphe.getNbVille (getGraph1);
        switch (creationGraphe.getClasse (getGraph1, lieu1)) {
            case "Ville":
                nbVilleLieu1 = nbVilleLieu1 - 1;
                break;
            case "Restaurant":
                nbRestaurantLieu1 = nbRestaurantLieu1 - 1;
                break;
            case "Loisir":
                nbLoisirLieu1 = nbLoisirLieu1 - 1;
                break;
        }
        JLabel nombreVilles = new JLabel ("Nombre de villes reliées ");
        p.add (nombreVilles);
        nombreVilles.setIcon (iconVille);
        nombreVilles.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreVilles.setForeground(Color.WHITE);
        JLabel nbVilles = new JLabel (String.valueOf(nbVilleLieu1));
        p.add (nbVilles);
        nbVilles.setForeground(Color.WHITE);
        Icon iconRestaurant = new ImageIcon ("src/main/resources/lieuRestaurantIcone.png");
        JLabel nombreRestaurants = new JLabel ("Nombre de restaurants reliés ");
        p.add (nombreRestaurants);
        nombreRestaurants.setIcon (iconRestaurant);
        nombreRestaurants.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreRestaurants.setForeground(Color.WHITE);
        JLabel nbRestaurants = new JLabel (String.valueOf(nbRestaurantLieu1));
        p.add (nbRestaurants);
        nbRestaurants.setForeground(Color.WHITE);
        Icon iconLoisir = new ImageIcon ("src/main/resources/lieuLoisirIcone.png");
        JLabel nombreLoisirs = new JLabel ("Nombre de loisirs reliés ");
        p.add (nombreLoisirs);
        nombreLoisirs.setIcon (iconLoisir);
        nombreLoisirs.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreLoisirs.setForeground(Color.WHITE);
        JLabel nbLoisirs = new JLabel (String.valueOf(nbLoisirLieu1));
        p.add (nbLoisirs);
        nbLoisirs.setForeground(Color.WHITE);
        p.setOpaque (false);
        return p;
    }

    public JPanel constrInformationsSurLeGraphe2 () {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel informationsGraphe = new JLabel ("Informations sur " + lieu2);
        p.add (informationsGraphe);
        JPanel p1 = new JPanel ();
        p1.setLayout (new FlowLayout());
        medaille1Lieu2 = new JLabel (" / : ouvert ");
        medaille2Lieu2 = new JLabel (" / : gastronomique ");
        medaille3Lieu2 = new JLabel (" / : culturel ");
        p1.add (medaille1Lieu2);
        p1.add (medaille2Lieu2);
        p1.add (medaille3Lieu2);
        p1.setOpaque (false);
        p.add (p1);
        medaille1Lieu2.setFont (new Font ("Arial", Font.BOLD, 10));
        medaille1Lieu2.setForeground(Color.WHITE);
        medaille2Lieu2.setFont (new Font ("Arial", Font.BOLD, 10));
        medaille2Lieu2.setForeground(Color.WHITE);
        medaille3Lieu2.setFont (new Font ("Arial", Font.BOLD, 10));
        medaille3Lieu2.setForeground(Color.WHITE);
        informationsGraphe.setFont (new Font ("Arial", Font.BOLD, 20));
        informationsGraphe.setForeground(Color.WHITE);
        p.add (new JLabel (" "));
        Icon iconVille = new ImageIcon ("src/main/resources/lieuVilleIcone.png");
        getGraph2 = creationGraphe.getGraphVoisins1Et2Distance (lieu2);
        nbVilleLieu2 = creationGraphe.getNbVille (getGraph2);
        nbRestaurantLieu2 = creationGraphe.getNbRestaurant (getGraph2);
        nbLoisirLieu2 = creationGraphe.getNbVille (getGraph2);
        switch (creationGraphe.getClasse (getGraph2, lieu2)) {
            case "Ville":
                nbVilleLieu2 = nbVilleLieu2 - 1;
                break;
            case "Restaurant":
                nbRestaurantLieu2 = nbRestaurantLieu2 - 1;
                break;
            case "Loisir":
                nbLoisirLieu2 = nbLoisirLieu2 - 1;
                break;
        }
        JLabel nombreVilles = new JLabel ("Nombre de villes reliées ");
        p.add (nombreVilles);
        nombreVilles.setIcon (iconVille);
        nombreVilles.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreVilles.setForeground(Color.WHITE);
        JLabel nbVilles = new JLabel (String.valueOf(nbVilleLieu2));
        p.add (nbVilles);
        nbVilles.setForeground(Color.WHITE);
        Icon iconRestaurant = new ImageIcon ("src/main/resources/lieuRestaurantIcone.png");
        JLabel nombreRestaurants = new JLabel ("Nombre de restaurants reliés ");
        p.add (nombreRestaurants);
        nombreRestaurants.setIcon (iconRestaurant);
        nombreRestaurants.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreRestaurants.setForeground(Color.WHITE);
        JLabel nbRestaurants = new JLabel (String.valueOf(nbRestaurantLieu2));
        p.add (nbRestaurants);
        nbRestaurants.setForeground(Color.WHITE);
        Icon iconLoisir = new ImageIcon ("src/main/resources/lieuLoisirIcone.png");
        JLabel nombreLoisirs = new JLabel ("Nombre de loisirs reliés ");
        p.add (nombreLoisirs);
        nombreLoisirs.setIcon (iconLoisir);
        nombreLoisirs.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreLoisirs.setForeground(Color.GREEN);
        JLabel nbLoisirs = new JLabel (String.valueOf(nbLoisirLieu2));
        p.add (nbLoisirs);
        nbLoisirs.setForeground(Color.WHITE);
        p.setOpaque (false);
        return p;
    }

    public JPanel constrComparaison () {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel informationsGraphe = new JLabel ("Comparaison des deux lieux ");
        p.add (informationsGraphe);
        informationsGraphe.setFont (new Font ("Arial", Font.BOLD, 20));
        informationsGraphe.setForeground(Color.WHITE);
        p.add (new JLabel (" "));
        Icon iconVille = new ImageIcon ("src/main/resources/lieuVilleIcone.png");
        getGraph1 = creationGraphe.getGraphVoisins1Et2Distance (lieu1);
        JLabel nombreVilles = new JLabel ("Lieu le plus ouvert ");
        p.add (nombreVilles);
        nombreVilles.setIcon (iconVille);
        nombreVilles.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreVilles.setForeground(Color.WHITE);
        Icon iconMedaille1 = new ImageIcon ("src/main/resources/medailleLieu.png");
        JLabel lieuPlusOuvert;
        if (nbVilleLieu1 > nbVilleLieu2) {
            lieuPlusOuvert = new JLabel(lieu1 + " avec " + nbVilleLieu1 + " villes ");
            medaille1Lieu1.setIcon(iconMedaille1);
            medaille1Lieu1.setText (" : ouverte ");
            medaille1Lieu1.repaint ();
        }
        else if (nbVilleLieu2 > nbVilleLieu1) {
            lieuPlusOuvert = new JLabel(lieu2 + " avec " + nbVilleLieu2 + " villes ");
            medaille1Lieu2.setIcon(iconMedaille1);
            medaille1Lieu2.setText (" : ouverte ");
            medaille1Lieu2.repaint ();
        }
        else
            lieuPlusOuvert = new JLabel (lieu1 + " et " + lieu2 + " avec " + nbRestaurantLieu1 + " restaurants ");
        p.add (lieuPlusOuvert);
        lieuPlusOuvert.setForeground(Color.WHITE);
        JLabel lieuPlusGastronomique;
        Icon iconRestaurant = new ImageIcon ("src/main/resources/lieuRestaurantIcone.png");
        JLabel nombreRestaurants = new JLabel ("Lieu le plus gastronomique ");
        p.add (nombreRestaurants);
        nombreRestaurants.setIcon (iconRestaurant);
        nombreRestaurants.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreRestaurants.setForeground(Color.WHITE);
        if (nbRestaurantLieu1 > nbRestaurantLieu2) {
            lieuPlusGastronomique = new JLabel(lieu1 + " avec " + nbRestaurantLieu1 + " restaurants ");
            medaille2Lieu1.setIcon(iconMedaille1);
            medaille2Lieu1.setText(" : gastronomique ");
            medaille2Lieu1.repaint();
        }
        else if (nbRestaurantLieu2 > nbRestaurantLieu1) {
            lieuPlusGastronomique = new JLabel(lieu2 + " avec " + nbRestaurantLieu2 + " restaurants ");
            medaille2Lieu2.setIcon(iconMedaille1);
            medaille2Lieu2.setText(" : gastronomique ");
            medaille2Lieu2.repaint();
        }
        else
            lieuPlusGastronomique = new JLabel (lieu1 + " et " + lieu2 + " avec " + nbRestaurantLieu1 + " restaurants ");
        JLabel nbRestaurants = new JLabel (String.valueOf (creationGraphe.getNbRestaurant (getGraph1)));
        p.add (lieuPlusGastronomique);
        lieuPlusGastronomique.setForeground(Color.WHITE);
        Icon iconLoisir = new ImageIcon ("src/main/resources/lieuLoisirIcone.png");
        JLabel nombreLoisirs = new JLabel ("Lieu le plus culturel ");
        p.add (nombreLoisirs);
        nombreLoisirs.setIcon (iconLoisir);
        nombreLoisirs.setFont (new Font ("Arial", Font.BOLD, 15));
        nombreLoisirs.setForeground(Color.WHITE);
        JLabel lieuPlusCulturel;
        if (nbLoisirLieu1 > nbLoisirLieu2) {
            lieuPlusCulturel = new JLabel(lieu1 + " avec " + nbLoisirLieu1 + " loisirs ");
            medaille3Lieu2.setIcon(iconMedaille1);
            medaille3Lieu2.setText(" : culturel ");
            medaille3Lieu2.repaint();
        }
        else if (nbLoisirLieu2 > nbLoisirLieu1) {
            lieuPlusCulturel = new JLabel(lieu2 + " avec " + nbLoisirLieu2 + " loisirs ");
            medaille3Lieu2.setIcon(iconMedaille1);
            medaille3Lieu2.setText(" : culturel ");
            medaille3Lieu2.repaint();
        }
        else
            lieuPlusCulturel = new JLabel (lieu1 + " et " + lieu2 + " avec " + nbLoisirLieu1 + " loisirs ");
        JLabel nbLoisirs = new JLabel (String.valueOf (creationGraphe.getNbLoisir (getGraph1)));
        p.add (lieuPlusCulturel);
        lieuPlusCulturel.setForeground(Color.WHITE);
        p.setOpaque (false);
        return p;
    }


    public JPanel legende () {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout(p, BoxLayout.Y_AXIS));
        Icon legendeRecherche = new ImageIcon("src/main/resources/mapPinSmall.png");
        JLabel recherche = new JLabel("Lieu départ : " + lieu1);
        JLabel recherche2 = new JLabel("Lieu recherché : " + lieu1);
        p.add(recherche);
        p.add (recherche2);
        recherche.setFont(new Font("Arial", Font.BOLD, 15));
        recherche.setForeground(Color.WHITE);
        recherche.setIcon(legendeRecherche);

        recherche2.setFont(new Font("Arial", Font.BOLD, 15));
        recherche2.setForeground(Color.WHITE);
        recherche2.setIcon(legendeRecherche);

        Icon legendeVille = new ImageIcon("src/main/resources/legendeVille.png");
        JLabel ville = new JLabel("Ville ");
        p.add(ville);
        ville.setFont(new Font("Arial", Font.BOLD, 15));
        ville.setForeground(Color.WHITE);
        ville.setIcon(legendeVille);

        Icon legendeRestaurant = new ImageIcon("src/main/resources/legendeRestaurant.png");
        JLabel restaurant = new JLabel("Restaurants ");
        p.add(restaurant);
        restaurant.setFont(new Font("Arial", Font.BOLD, 15));
        restaurant.setForeground(Color.WHITE);
        restaurant.setIcon(legendeRestaurant);

        Icon legendeLoisir = new ImageIcon("src/main/resources/legendeLoisir.png");
        JLabel loisir = new JLabel("Loisir ");
        p.add(loisir);
        loisir.setFont(new Font("Arial", Font.BOLD, 15));
        loisir.setForeground(Color.WHITE);
        loisir.setIcon(legendeLoisir);

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
        p.setLayout (new FlowLayout ());
        JButton revenirAuGraphePrincipal = new JButton ("Revenir au graphe principal ");
        JButton montrerLegende = new JButton ("Legende ");
        p.add (revenirAuGraphePrincipal);
        p.add (montrerLegende);
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

        montrerLegende.addActionListener (event -> {
            Object[] options = new Object[]{};
            JOptionPane fenetremontrerlegende = new JOptionPane ("Montrer la légende ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);
            fenetremontrerlegende.add (legende ());

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetremontrerlegende);
            diag.pack();
            diag.setVisible(true);
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
        jMenuBar.add (jMenuDistance ());
        jMenuBar.add (jMenuComparer ());
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
        JMenu grapheVoisin = new JMenu ("graphe des voisins d'un lieu ");
        grapheVoisin.setIcon (iconVoisinRedim);
        JMenuItem choixNoeudVoisin = new JMenuItem ("Allez à la fenètre dédiée ");
        affichage.add (graphePrincipal);
        affichage.add (graphePrincipal);
        graphePrincipal.addActionListener (event -> {
            try {
                FenetreGraphe fenetreGraphe = new FenetreGraphe (cheminFile, fenetrePrincipale);
                jFrame.setVisible (false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ExceptionAjListeGraphe e) {
                throw new RuntimeException(e);
            }
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
            JOptionPane fenetreGraphVoisins = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les villes voisines ",
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
            JOptionPane fenetreGraphVoisins = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les restaurants voisins ",
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
            JOptionPane fenetreGraphVoisins = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les loisirs voiisns ",
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

    public JMenu jMenuComparer () {
        JMenu comparer = new JMenu ("Comparer ");
        Icon villeIcone = new ImageIcon ("src/main/resources/lieuVilleIcone.png");
        ImageIcon iconVilleRedim = new ImageIcon(((ImageIcon) villeIcone).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem comparerTousLesLieux = new JMenuItem ("Comparer tous les lieux ", iconVilleRedim);
        Icon restaurantIcone = new ImageIcon ("src/main/resources/lieuRestaurantIcone.png");
        ImageIcon iconRestaurantRedim = new ImageIcon(((ImageIcon) restaurantIcone).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem comparerDeuxLieux = new JMenuItem ("Comparer deux lieux ", iconRestaurantRedim);
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
            JOptionPane fenetreGraphVoisins = new JOptionPane("Veuillez selectionner le lieu dont vous voulez connaitre les voisins directes ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreGraphVoisins.add (premierLieu);
            fenetreGraphVoisins.add(listegraphNode);
            fenetreGraphVoisins.add (deuxiemeLieu);
            fenetreGraphVoisins.add (listegraphNode2);
            fenetreGraphVoisins.add (visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreGraphVoisins);
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
            JOptionPane fenetreGraphVoisins = new JOptionPane("Selectionnez la catégorie que vous souhaitez comparer ",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.DEFAULT_OPTION,
                    null, options, null);

            fenetreGraphVoisins.add (categorie);
            fenetreGraphVoisins.add(listegraphNode);
            fenetreGraphVoisins.add (visualiser);

            JDialog diag = new JDialog();
            diag.getContentPane().add(fenetreGraphVoisins);
            diag.pack();
            diag.setVisible(true);
        });

        comparer.setForeground(Color.WHITE);
        return comparer;
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