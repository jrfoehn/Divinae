package model.kernel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import com.sun.org.apache.xpath.internal.operations.Or;
import model.cards.Divinite;
import model.components.Joueur;
import model.components.*;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class Partie{
    public final static int MAX_JOUEURS = 6;
    public final static int MIN_JOUEURS = 2;
    public static ArrayList<Divinite> divinite = new ArrayList<Divinite>();
    private static Partie instance;
    private int nbJoueurHumain;
    private int nbJoueurVirtuel;
    private int joueurActuel;
    private int nbTour;
    private int vainqueur;
    private boolean fin = false;
    private TypePartie type;
    public Origine orgineTour;
    public ArrayList<Joueur> joueursActifs;
    public ArrayList<Joueur> joueursElimines;


    /*On initialise le constructeur.
    * Pour l'instant il lance la partie en prenant en compte le nombre de joueurs humain + virtuels*/
    public Partie(int nbJoueurHumain, int nbJoueurVirtuel) {
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
        return instance;
    }

    public Origine getOrgineTour(){
        return orgineTour;
    }

    public void setOrgineTour(Origine origineTour){
        this.orgineTour = origineTour;
    }

    public boolean getFin() {
        return fin;
    }

    public void setFin(){
        this.fin = fin;
    }


    /*Fonction permettant de lancer le dé, et ainsi déterminer l'influence du tour*/
    /*public void lanceDe(){
        int origine = int.Math.floor(Math.random()*3);
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
    }*/

    public void attribuerDivinite() {
        Divinite.initialiserDivinite();
        Collections.shuffle(this.divinite);
        /*On attribue un divinité à chaque joueur actif (= tous les joueurs instanciés en début de partie)*/
        Iterator<Joueur> j = this.joueursActifs.iterator();
        for (Iterator<Joueur> j = this.joueursActifs.iterator(); j.hasNext();) {
            j.next().divinite = ((ArrayList<Divinite>) this.divinite).get(0);
        }
    }

    public void attribuerCarte() {
        for (int i = 0; i < 7; i++) {
            Iterator<Joueur> j = this.joueursActifs.iterator();
            while (j.hasNext()) {
                Joueur tmp = j.next();
                tmp.listeCarte.add(Pioche.piocher());
            }
        }
    }

    public void commencerPartie() {
        this.attribuerDivinite();
        this.attribuerCarte();
        Scanner scan = new Scanner(System.in);
        Iterator<Joueur> j = this.joueursActifs.iterator();
        while (j.hasNext()) {
            Joueur joueur = j.next();
            int i = this.joueursActifs.indexOf(joueur);
            System.out.println("Joueur numéro " + i);

        }
    }

}
