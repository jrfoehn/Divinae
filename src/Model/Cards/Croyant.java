package model.cards;

import model.components.Dogme;
import model.components.Origine;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class Croyant extends CarteAction {

    private int nbCroyant;
    private boolean estGuide = false;

    public Croyant (String nom, Origine origine, Dogme dogme1, Dogme dogme2, Dogme dogme3, int nbCroyant, String capaciteSpeciale, int idCapaciteSpeciale, int idCarte){
        super(nom, origine);
        this.listeDogmes.add(dogme1);
        this.listeDogmes.add(dogme2);
        this.listeDogmes.add(dogme3);
        this.nbCroyant = nbCroyant;
    }

//    @Override
//    public String toString() {
//        return super.toString() + "dogmes : " + this.listeDogmes + " nbCroyant " + this.nbCroyant + " capacite " + this.capaciteSpeciale;
//    }

    //  completer ces deux méthodes
    public static void instancierCroyant() {}
    public static void creerCroyant() {}

//    public int getIdCarte() {
//
//        return this.idCarte;
//    }

//    public int getIdCapaciteSpeciale() {
//        return this.idCapaciteSpeciale;
//    }

    public boolean getEstGuide() {
        return this.estGuide;
    }

    public boolean setEstGuide() {
        this.estGuide = estGuide;
    }


}
