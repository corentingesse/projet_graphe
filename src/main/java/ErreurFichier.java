public class ErreurFichier extends Exception {
    public ErreurFichier (int nombreLigne, String message) {
        super ("le fichier comprend des erreurs à la ligne n°" + nombreLigne + message );
    }
}
