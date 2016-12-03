package model.components;

import model.cards.CarteAction;

import java.util.ArrayList;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class Deck  extends TasDeCarte {

    private static Deck instance;

    public Deck() {
        super();
    }

//    public static

    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

}
