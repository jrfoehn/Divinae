package model.components;

import model.cards.CarteAction;

import java.util.ArrayList;
/**
 * Created by jrfoehn on 11/29/16.
 */
public abstract class TasDeCarte {

    protected ArrayList<CarteAction> listeCartes;
    protected boolean estVide;

    public TasDeCarte() {
        this.listeCartes = new ArrayList<CarteAction>();
        this.estVide = false;
    }

    public CarteAction choisirCarte(int i) {
        return this.listeCartes.get(i);
    }

}
