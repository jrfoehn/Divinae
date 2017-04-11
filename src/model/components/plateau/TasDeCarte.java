package model.components.plateau;

import model.cards.CarteAction;

import java.util.ArrayList;
/**
 * Classe abstraite permettant d'avoir des méthodes communes.
 */
public abstract class TasDeCarte {

    protected ArrayList<CarteAction> listeCartes;
    protected boolean estVide;

    public TasDeCarte() {
        this.listeCartes = new ArrayList<CarteAction>();
        this.estVide = true;
    }

    public ArrayList<CarteAction> getListeCartes() {
        return listeCartes;
    }
}
