package Model.Kernel;

import java.util.ArrayList;
import Model.Components.*;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class Partie {

    private TypePartie type;
    private int nbJoueurHumain;
    private int nbJoueurVirtuel;
    private int joueurActuel;
    private int nbTour;
    private Origine orgineTour;
    private int vainqueur;
    private ArrayList<Joueur> joueurs;


    public Partie() {

    }

}
