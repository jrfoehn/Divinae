package model.components;

import model.cards.CarteAction;

import java.util.ArrayList;

/**
 * Created by jrfoehn on 12/1/16.
 */
public class Defausse extends TasDeCarte {

    private static Defausse instance;

    public Defausse() {
        super();
    }

    public static Defausse getInstance(){
        if (instance == null) {
            instance = new Defausse();
        }
        return instance;
    }

    public void defausser(CarteAction carte) {
        this.listeCartes.add(carte);
    }

    public void transfererPioche(Pioche carte) {
//        TODO transferer les cartes de la défausse vers la pioche quand cette dernière est vide.
    }
}
