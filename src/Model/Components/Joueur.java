package model.components;

import model.cards.Divinite;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class Joueur {

    protected String nom;
    protected int id;
    protected Divinite divinite;
    protected int pointJour;
    protected int pointNuit;
    protected int pointNeant;
    protected int pointPriere;
    protected Deck listeCarte;

    public Joueur() {

    }

}
