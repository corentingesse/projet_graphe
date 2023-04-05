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

public class AProposPrincipal extends JFrame {
    FenetrePrincipale fenetrePrincipale;
    JFrame jFrame = new JFrame ();
    private String cheminFile;

    CreationGraphe creationGraphe;

    JPanel graph;

    JPanel constrPartieVisuel;

    JPanel noeudsVoisins;

    public AProposPrincipal (FenetrePrincipale newFenetrePrincipale) throws IOException, ExceptionAjListeGraphe {
        super ();
        fenetrePrincipale = newFenetrePrincipale;
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
                    jFrame.setVisible(false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Le fichier contenant le graphe comporte des erreurs, il doit être sous la forme : type,nomLieu:typeRouteVoisin1,routeVoisin1::typeVoisin1,voisin1[;typeRouteVoisinN,routeVoisinN::typeVoisinN,voisinN];;  ", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }});
        fichier.add (new JSeparator ());
        fichier.add (fermer);
        fermer.addActionListener (event -> {
            jFrame.dispose ();
        });

        fichier.setForeground(Color.WHITE);
        return fichier;
    }

    public JMenu jMenuAPropos () {
        JMenu APropos = new JMenu ("A propos ");
        APropos.setForeground(Color.WHITE);
        JMenuItem aproposvoir = new JMenuItem ("Voir les informations ");
        APropos.add (aproposvoir);
        aproposvoir.addActionListener (event -> {
            try {
                AProposPrincipal aproposprincipalgraphe = new AProposPrincipal(fenetrePrincipale);
                jFrame.setVisible (false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ExceptionAjListeGraphe e) {
                throw new RuntimeException(e);
            }
        });
        return APropos;
    }
}
