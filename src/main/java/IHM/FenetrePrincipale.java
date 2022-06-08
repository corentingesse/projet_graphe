package IHM;

import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.IOException;

import static java.awt.Color.black;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class FenetrePrincipale extends JFrame {
    JFrame jFrame = new JFrame ();
    public FenetrePrincipale () throws IOException, ExceptionAjListeGraphe {
            super();
            constrFen();
        }

    public void constrFen() throws IOException, ExceptionAjListeGraphe {
        jFrame.setTitle("Graphe&Plan : trouvez le chemin de vos rêves ! ");
        jFrame.setSize(900, 300);
        jFrame.setLocationRelativeTo(null);
        ImageIcon imageFond = new ImageIcon("src/main/resources/Graph_Plan.png");
        // setResizable (false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setExtendedState(this.MAXIMIZED_BOTH);
        jFrame.setJMenuBar(jMenuBar());
        Image iconGraph = Toolkit.getDefaultToolkit().getImage ("src/main/resources/GraphIcone.png");
        jFrame.setIconImage (iconGraph);
        JLabel background = new JLabel(imageFond);
        background.setLayout (new BoxLayout(background, BoxLayout.Y_AXIS));
        jFrame.add (background);
        background.add(Box.createRigidArea(new Dimension(0, 200)));
        background.add (constrPan ());
        jFrame.setBackground (black);
        jFrame.setVisible(true);
    }

    public JPanel constrPan () throws IOException, ExceptionAjListeGraphe {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout (p,BoxLayout.Y_AXIS));
        JLabel texteEntree = new JLabel ("Bienvenue sur l'application Graphe&Plan : ");
        p.add (texteEntree);
        texteEntree.setForeground(Color.WHITE);
        texteEntree.setFont(new Font("Arial", Font.BOLD, 30));
        p.add (constrIcones ());
        p.setOpaque (false);
        return p;
    }

    public JPanel constrIcones () throws IOException, ExceptionAjListeGraphe {
        JPanel p = new JPanel ();
        Icon iconOuverture = new ImageIcon ("src/main/resources/ouverture.png");
        ImageIcon iconOuvrirRedim = new ImageIcon(((ImageIcon) iconOuverture).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        JButton boutonOuverture = new JButton ("Ouvir un fichier ", iconOuvrirRedim);
        p.add (boutonOuverture);
        boutonOuverture.setForeground(Color.WHITE);
        boutonOuverture.addActionListener (event -> {
            JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            choose.setDialogTitle("selectionnez votre graphe ");
            choose.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("graphe au format csv ou txt ", "csv", "txt");
            choose.addChoosableFileFilter(filter);
            int res = choose.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                try {
                    FenetreGraphe fenetreGraphe = new FenetreGraphe(choose.getSelectedFile().getPath(), this);
                    jFrame.setVisible (false);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(jFrame,"Aucun fichier csv ou txt choisi ", "Erreur ", ERROR_MESSAGE);
                    throw new RuntimeException(e);
                } catch (ExceptionAjListeGraphe e) {
                    JOptionPane.showMessageDialog(jFrame,"Erreur dans le fichier, il ne correspond pas à la syntaxe prévue ", "Erreur ", ERROR_MESSAGE);
                    throw new RuntimeException(e); 
                }
            }});

        boutonOuverture.setOpaque (false);
        boutonOuverture.setContentAreaFilled (false);
        boutonOuverture.setBorderPainted (false);
        Icon iconNouveau = new ImageIcon ("src/main/resources/nouveau.png");
        JButton boutonNouveau = new JButton ("Nouveau fichier ", iconNouveau);
        p.add (boutonNouveau);
        boutonNouveau.setForeground(Color.WHITE);
        boutonNouveau.addActionListener (event -> {
            FenetreCreer fenetreCreer = new FenetreCreer (this);
            jFrame.setVisible (false);
        });
        boutonNouveau.setOpaque (false);
        boutonNouveau.setContentAreaFilled (false);
        boutonNouveau.setBorderPainted (false);
        Icon iconTest = new ImageIcon ("src/main/resources/TestIcone.png");
        ImageIcon iconTestRedim = new ImageIcon(((ImageIcon) iconTest).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        JButton boutonTestGraphe = new JButton ("Test avec un graphe ", iconTestRedim);
        p.add (boutonTestGraphe);
        boutonTestGraphe.setForeground(Color.WHITE);
        boutonTestGraphe.addActionListener (event -> {
            try {
                FenetreGraphe fenetreGraphe = new FenetreGraphe("src/main/resources/graphe.csv",  this);
                jFrame.setVisible (false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ExceptionAjListeGraphe e) {
                throw new RuntimeException(e);
            }
        });
        boutonTestGraphe.setOpaque (false);
        boutonTestGraphe.setContentAreaFilled (false);
        boutonTestGraphe.setBorderPainted (false);
        Icon iconSortie = new ImageIcon ("src/main/resources/sortieIcone.png");
        ImageIcon iconSortieRedim = new ImageIcon(((ImageIcon) iconSortie).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        JButton boutonSortie = new JButton ("Sortie ", iconSortieRedim);
        p.add (boutonSortie);
        boutonSortie.setForeground(Color.WHITE);
        boutonSortie.setOpaque (false);
        boutonSortie.setContentAreaFilled (false);
        boutonSortie.setBorderPainted (false);
        boutonSortie.addActionListener (event -> {this.dispose ();});
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
        Icon enregistrerIcon = new ImageIcon ("src/main/resources/enregistrer.png");
        ImageIcon iconEnregistrerRedim = new ImageIcon(((ImageIcon) enregistrerIcon).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JMenuItem enregistrer = new JMenuItem ("enregistrer ", iconEnregistrerRedim);
        JMenuItem enregistrerSous = new JMenuItem ("enregistrer-sous ");
        Icon fermerIcon = new ImageIcon ("src/main/resources/fermer.png");
        ImageIcon iconFermerRedim = new ImageIcon(((ImageIcon) fermerIcon).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem fermer = new JMenuItem ("fermer ", iconFermerRedim);
        fichier.add (nouveau);
        nouveau.addActionListener (event -> {
            FenetreCreer fenetreCreer = new FenetreCreer (this);
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
                    FenetreGraphe fenetreGraphe = new FenetreGraphe(choose.getSelectedFile().getPath(), this);
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
            jFrame.dispose ();
        });

        fichier.setForeground(Color.WHITE);
        return fichier;
    }

    public JMenu jMenuAPropos () {
        JMenu APropos = new JMenu ("A propos ");
        APropos.setForeground(Color.WHITE);
        return APropos;
    }

    public JFrame getJFrame () {
        return jFrame;
    }
}
