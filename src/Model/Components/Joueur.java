package model.components;

import model.cards.CarteAction;
import model.cards.Divinite;

import java.util.ArrayList;

/**
 * Created by jrfoehn on 11/29/16.
 */
public abstract class Joueur {

    protected String nom;
    protected Divinite divinite;
    protected Origine origine;
    protected Deck listeCarte;
    protected PlateauPerso cartesPerso;
    protected int id;
    public int pointJour;
    public int pointNuit;
    public int pointNeant;
    public int pointPriere;
    protected boolean capaciteUtilise = false;

    public Joueur(int id) {
        this.id = id;
        this.listeCarte = new Deck();
        this.cartesPerso = new PlateauPerso();
    }

//    public CarteAction choisirCarte(int i) {
//        return this.listeCarte.choisirCarte(i);
//    }

//    @Override
//    public String toString() {
//        return "Joueur " + this.id + " Divinité " + this.divinite + " Origine " + this.origine;
//    }
}
