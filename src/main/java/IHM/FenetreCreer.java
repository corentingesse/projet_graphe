package IHM;

import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static java.awt.Color.black;

public class FenetreCreer extends JFrame {
    FenetrePrincipale fenetrePrincipale;
    JTextField fichierGraphe;
    JFrame jFrame = new JFrame ();
    public FenetreCreer (FenetrePrincipale newFenetrePrincipale) {
        super ();
        fenetrePrincipale = newFenetrePrincipale;
        constrFen ();
    }

    public void constrFen () {
        jFrame.setTitle("Graphe&Plan : trouvez le chemin de vos rêves ! ");
        jFrame.setSize(500, 300);
        jFrame.setLocationRelativeTo(null);
        // setResizable (false);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(constrPan());
        jFrame.setJMenuBar (jMenuBar ());
        Image iconGraph = Toolkit.getDefaultToolkit().getImage ("C:\\Users\\cocog\\sae-graphes\\src\\main\\resources\\GraphIcone.png");
        jFrame.setIconImage (iconGraph);
        jFrame.setVisible(true);
    }

    public JPanel constrPan () {
        JPanel p = new JPanel ();
        JLabel formatExplications = new JLabel ("Le format du fichier doit être comme suit : ");
        p.add (formatExplications);
        JLabel formatExplicationsSuite = new JLabel ("typeLieuOrigine,lieuOrigine:typeRouteVoisin,nomRouteVoisin::TypeLieuVoisin,nomLieuVoisin[;typeRouteVoisin,nomRouteVoisin::TypeLieuVoisin,nomLieuVoisin...];;");
        p.add (formatExplicationsSuite);
        JTextField fichierGraphe = new JTextField (120);
        p.add (fichierGraphe);
        JButton boutonEnregistrer = new JButton ("enregistrerTest");
        p.add (boutonEnregistrer);
        boutonEnregistrer.addActionListener (event -> {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showSaveDialog(getParent());
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    FileWriter fw = new FileWriter(new File("RapportTest.txt"));
                    fw.write(fichierGraphe.getText ());
                    fw.flush();
                    fw.close();
                } catch (FileNotFoundException fnfe)
                {
                    fnfe.printStackTrace();
                } catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        });
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
        Icon nouveauIcon = new ImageIcon ("C:\\Users\\cocog\\sae-graphes\\src\\main\\resources\\nouveau.png");
        ImageIcon iconNouveauRedim = new ImageIcon(((ImageIcon) nouveauIcon).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem nouveau = new JMenuItem ("nouveau ", iconNouveauRedim);
        Icon iconOuverture = new ImageIcon ("C:\\Users\\cocog\\sae-graphes\\src\\main\\resources\\ouverture.png");
        ImageIcon iconOuvrirRedim = new ImageIcon(((ImageIcon) iconOuverture).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JMenuItem ouvrir = new JMenuItem ("ouvrir ", iconOuvrirRedim);
        Icon enregistrerIcon = new ImageIcon ("C:\\Users\\cocog\\sae-graphes\\src\\main\\resources\\enregistrer.png");
        ImageIcon iconEnregistrerRedim = new ImageIcon(((ImageIcon) enregistrerIcon).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JMenuItem enregistrer = new JMenuItem ("enregistrer ", iconEnregistrerRedim);
        JMenuItem enregistrerSous = new JMenuItem ("enregistrer-sous ");
        Icon fermerIcon = new ImageIcon ("C:\\Users\\cocog\\sae-graphes\\src\\main\\resources\\fermer.png");
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
        enregistrer.addActionListener (event -> {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showSaveDialog(getParent());
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    FileWriter fw = new FileWriter(new File("RapportTest.txt"));
                    fw.write(fichierGraphe.getText ());
                    fw.flush();
                    fw.close();
                } catch (FileNotFoundException fnfe)
                {
                    fnfe.printStackTrace();
                } catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        });
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

    public JMenu jMenuAPropos () {
        JMenu APropos = new JMenu ("A propos ");
        APropos.setForeground(Color.WHITE);
        return APropos;
    }
}
