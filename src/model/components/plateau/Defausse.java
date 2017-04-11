package model.components.plateau;

import model.cards.CarteAction;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Défausse commune
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

    public void defausserMultiple(ArrayList<CarteAction> carteActions) {
        this.listeCartes.addAll(carteActions);
    }

    public void transfererPioche() {
        if (Pioche.getInstance().getCartes().isEmpty()) {
            Collections.shuffle(this.listeCartes);
            Pioche.getInstance().getCartes().addAll(this.listeCartes);
        }
    }
}
