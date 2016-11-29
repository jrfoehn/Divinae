package model.kernel;

import java.util.ArrayList;
import java.util.Scanner;

import com.sun.org.apache.xpath.internal.operations.Or;
import model.components.*;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class Partie{
    public final static int MAX_JOUEURS = 6;
    public static Partie instance;
    private TypePartie type;
    private int nbJoueurHumain;
    private int nbJoueurVirtuel;
    private int joueurActuel;
    private int nbTour;
    private Origine orgineTour;
    private int vainqueur;
    private ArrayList<Joueur> joueursActifs;
    private ArrayList<Joueur> joueursElimines;


    /*On initialise le constructeur.
    * Pour l'instant il lance la partie en prenant en compte le nombre de joueurs humain + virtuels*/
    public Partie(int nbJoueurHumain, int nbJoueurVirtuel){
        this.joueursActifs = new ArrayList<Joueur>();
        this.joueursElimines = new ArrayList<Joueur>();
        for (int i = 0; i < nbJoueurHumain; i++) {
            this.joueursActifs.add(new Joueur(i));
        }
        for (int i = nbJoueurHumain; i < (nbJoueurHumain+nbJoueurVirtuel); i++){
            this.joueursActifs.add(new Joueur(i));
        }
    }
    /*Fonction permettant de parametrer la partie en prenant en compte le nb de joueur humain + virtuel*/
    public static Partie parametrerPartie(int nbJoueurHumain, int nbJoueurVirtuel){
        if (instance == null) {
            instance = new Partie(nbJoueurHumain, nbJoueurVirtuel);
        }
        return instance;
    }

    /*
    Ensemble des getters / setters nécessaires pour la classe
    */
    public static Partie getInstance(){
        return instance
    }

    public Origine getOrgineTour(){
        return orgineTour;
    }
    public void setOrgineTour(Origine origineTour){
        this.orgineTour = origineTour;
    }

    /*Fonction permettant de lancer le dé, et ainsi déterminer l'influence du tour*/
    public void lanceDe(){
        int origine = (int).Math.floor(Math.random()*3);
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextLine()){
            // on attend l'appuie sur la touche entrée
        }
        switch (origine) {
            case 0 : this.setOrgineTour(Origine.Jour);
                break;
            case 1 : this.setOrgineTour(Origine.Nuit);
                break;
            case 2 : this.setOrgineTour(Origine.Néant);
        }
    }

}
