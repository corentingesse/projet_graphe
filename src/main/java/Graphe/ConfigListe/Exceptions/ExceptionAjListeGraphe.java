package Graphe.ConfigListe.Exceptions;

public class ExceptionAjListeGraphe extends Exception {
    public ExceptionAjListeGraphe () {
        super ("la valeur transmise est null ou est déjà présente dans la liste ");
    }
}
