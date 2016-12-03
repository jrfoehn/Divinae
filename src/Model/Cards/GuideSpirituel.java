package model.cards;

import model.components.Dogme;
import model.components.Origine;

import java.util.ArrayList;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class GuideSpirituel extends CarteAction {

    private int nbCroyantMax;
    private ArrayList<Croyant> nbCroyant;

    public GuideSpirituel(String nom, Origine origine, int idCapaciteSpeciale, int idCarte, int nbCroyantMax, Dogme dogme1, Dogme dogme2, String capaciteSpeciale) {
        super(nom, origine, idCapaciteSpeciale, idCarte);
        this.nbCroyantMax = nbCroyantMax;
        nbCroyant = new ArrayList<Croyant>();
        this.capaciteSpeciale = capaciteSpeciale;
        this.listeDogmes.add(dogme1);
        this.listeDogmes.add(dogme2);
    }

    @Override
    public String toString() {
        return super.toString() + " dogmes : " + this.listeDogmes + " nb croyant max" + this.nbCroyantMax + "nb croyant guidé " + this.nbCroyant + " capacité " + this.capaciteSpeciale;
    }

    public static void creerGuide(String nom, Origine origine, int idCapaciteSpeciale, int idCarte, int nbCroyantMax, Dogme dogme1, Dogme dogme2, String capaciteSpeciale) {
        GuideSpirituel guideSpirituel = new GuideSpirituel(nom, origine, idCapaciteSpeciale, idCarte, nbCroyantMax, dogme1, dogme2, capaciteSpeciale);
        // instancier avec pioche ?
    }

    public static void guiderCroyant() {
        //TODO
    }

    public static void initialiserGuideSpirituel() {
//        creerGuide();
    }

}
