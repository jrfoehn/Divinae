package model.components.plateau;

import model.cards.CarteAction;

/**
 * Main du joueur.
 */

public class Deck  extends TasDeCarte {

    private static Deck instance;

    public Deck() {
        super();
    }

    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    public CarteAction choisirCarte(int i) {
        return this.listeCartes.get(i);
    }

}
