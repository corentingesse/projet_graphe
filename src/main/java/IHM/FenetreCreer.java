package IHM;

import Graphe.ConfigListe.Exceptions.ExceptionAjListeGraphe;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicReference;

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
        jFrame.setVisible(true);
        jFrame.setJMenuBar (jMenuBar ());
        Image iconGraph = Toolkit.getDefaultToolkit().getImage ("C:\\Users\\cocog\\sae-graphes\\src\\main\\resources\\GraphIcone.png");
        jFrame.setIconImage (iconGraph);
    }
    public JPanel constrPan () {
        JPanel p = new JPanel ();
        p.setLayout (new BoxLayout (p, BoxLayout.Y_AXIS));
        JLabel formatExplications = new JLabel ("Le format du fichier doit être comme suit : ");
        p.add (formatExplications);
        JLabel formatExplicationsSuite = new JLabel ("typeLieuOrigine,lieuOrigine:typeRouteVoisin,nomRouteVoisin::TypeLieuVoisin,nomLieuVoisin[;typeRouteVoisin,nomRouteVoisin::TypeLieuVoisin,nomLieuVoisin...];;");
        p.add (formatExplicationsSuite);
        try {

            File myFile = new File("src/main/resources/NouveauFichier.txt");

            if (myFile.createNewFile()){
                FileWriter myFileEcriture = new FileWriter ("src/main/resources/NouveauFichier.txt");
                System.out.println("Le fichier est créé.");

        AtomicReference<String> ligneFichier = new AtomicReference<>("");
        p.add (new JLabel ("Ajouter une ligne : "));
        p.add (new JLabel ("Choisissez le type de lieu que vous souhaitez ajouter : "));
        String [] listeTypeLieux = {"ville", "Loisir", "Restaurant"};
        JComboBox type = new JComboBox (listeTypeLieux);
        p.add (new JLabel ("lieu que vous souhaitez ajouter : "));
        JTextField nouveauLieuGraphe = new JTextField (30);
        p.add (type);
        p.add (nouveauLieuGraphe);
        switch (type.getSelectedItem ().toString ()) {
            case "Ville":
                ligneFichier.set(ligneFichier + "V");
                break;
            case "Restaurant":
                ligneFichier.set(ligneFichier + "R");
                break;
            case "loisir":
                ligneFichier.set(ligneFichier + "L");
                break;
        }
        ligneFichier.set(ligneFichier + "," + nouveauLieuGraphe.getText());
        p.add (new JLabel ("Choisissez le type de route qui relie votre lieu avec son premier voisin : "));
        String [] listeTypeRoute = {"Nationale", "Departementale", "Autoroute"};
        JComboBox typeRoute = new JComboBox (listeTypeRoute);
        p.add (typeRoute);
        switch (typeRoute.getSelectedItem ().toString ()) {
            case "Nationale" :
                ligneFichier.set(ligneFichier + ":N");
                break;
            case "Departementale" :
                ligneFichier.set(ligneFichier + ":D");
                break;
            case "Autoroute" :
                ligneFichier.set(ligneFichier + ":A");
                break;
        }
        p.add (new JLabel ("Nom de la route reliant le lieu avec votre premier voisin : "));
        JTextField nomRouteVoisin = new JTextField (30);
        p.add (nomRouteVoisin);
        ligneFichier.set(ligneFichier + nomRouteVoisin.getText());
        p.add (new JLabel ("Nombre de kilometre de la route reliant le lieu avec votre premier voisin : "));
        JTextField nombreKilometreRouteVoisin = new JTextField(30);
        p.add (nombreKilometreRouteVoisin);
        ligneFichier.set(ligneFichier + "," + nombreKilometreRouteVoisin.getText());
        p.add (new JLabel ("Type de votre premier voisin : "));
        JComboBox typeVoisin = new JComboBox (listeTypeLieux);
        p.add (typeVoisin);
        ligneFichier.set(ligneFichier + "::");
        switch (typeVoisin.getSelectedItem ().toString ()) {
            case "Ville":
                ligneFichier.set(ligneFichier + "V");
                break;
            case "Restaurant":
                ligneFichier.set(ligneFichier + "R");
                break;
            case "loisir":
                ligneFichier.set(ligneFichier + "L");
                break;
        }
        p.add (new JLabel ("Nom de votre premier voisin : "));
        JTextField nomVoisin = new JTextField (30);
        p.add (nomVoisin);
        ligneFichier.set(ligneFichier + "," + nomVoisin.getText());

        p.add (new JLabel ("Vous pouvez ajouter d'autres voisins : "));
        p.add (new JLabel ("Choisissez le type de route qui relie votre lieu avec son nouveau voisin : "));
        typeRoute = new JComboBox (listeTypeRoute);
        p.add (typeRoute);
        p.add (new JLabel ("Nom de la route reliant le lieu avec son nouveau voisin : "));
        nomRouteVoisin = new JTextField (30);
        p.add (nomRouteVoisin);
        p.add (new JLabel ("Nombre de kilometre de la route reliant le lieu avec son nouveau voisin : "));
        nombreKilometreRouteVoisin = new JTextField(30);
        p.add (nombreKilometreRouteVoisin);
        p.add (new JLabel ("Type de votre premier voisin : "));
        typeVoisin = new JComboBox (listeTypeLieux);
        p.add (typeVoisin);
        p.add (new JLabel ("Nom de son nouveau voisin : "));
        nomVoisin = new JTextField (30);
        p.add (nomVoisin);
        JButton ajouterLeVoisin = new JButton ("Ajouter le nouveau voisin ");
                JComboBox finalTypeRoute = typeRoute;
                JTextField finalNomRouteVoisin = nomRouteVoisin;
                JTextField finalNombreKilometreRouteVoisin = nombreKilometreRouteVoisin;
                JComboBox finalTypeVoisin = typeVoisin;
                JTextField finalNomVoisin = nomVoisin;
                ajouterLeVoisin.addActionListener (event -> {
                    ligneFichier.set(ligneFichier + ";");
                    switch (finalTypeRoute.getSelectedItem().toString()) {
                        case "Nationale":
                            ligneFichier.set(ligneFichier + ":N");
                            break;
                        case "Departementale":
                            ligneFichier.set(ligneFichier + ":D");
                            break;
                        case "Autoroute":
                            ligneFichier.set(ligneFichier + ":A");
                            break;
                    }
                    ligneFichier.set(ligneFichier + finalNomRouteVoisin.getText());
                    ligneFichier.set(ligneFichier + "," + finalNombreKilometreRouteVoisin.getText());
                    ligneFichier.set(ligneFichier + "::");
                    switch (finalTypeVoisin.getSelectedItem().toString()) {
                        case "Ville":
                            ligneFichier.set(ligneFichier + "V");
                            break;
                        case "Restaurant":
                            ligneFichier.set(ligneFichier + "R");
                            break;
                        case "loisir":
                            ligneFichier.set(ligneFichier + "L");
                            break;
                    }
                    ligneFichier.set(ligneFichier + "," + finalNomVoisin.getText());
                    ligneFichier.set(ligneFichier + ";;");
                    ligneFichier.set(ligneFichier + "/n");
                });
                JButton enregistrerFichier = new JButton ("enregistrer ");
                p.add (enregistrerFichier);
                enregistrerFichier.addActionListener (event -> {
                    try {
                        myFileEcriture.write(String.valueOf(ligneFichier));
                        myFileEcriture.close ();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    jFrame.setVisible(false);
                        });
        p.add (ajouterLeVoisin);
            }else{
                System.out.println("Le fichier existe déjà.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Le fichier contenant le graphe comporte des erreurs, il doit être sous la forme : type,nomLieu:typeRouteVoisin1,routeVoisin1::typeVoisin1,voisin1[;typeRouteVoisinN,routeVoisinN::typeVoisinN,voisinN];;  ", "Erreur", JOptionPane.ERROR_MESSAGE);
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
        JMenuItem aproposvoir = new JMenuItem ("Voir les informations ");
        APropos.add (aproposvoir);
        aproposvoir.addActionListener (event -> {
            try {
                AProposPrincipal aproposPrincipal = new AProposPrincipal (fenetrePrincipale);
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
