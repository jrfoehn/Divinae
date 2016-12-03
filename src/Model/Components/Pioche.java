package model.components;

import model.cards.*;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by jrfoehn on 11/29/16.
 */
public class Pioche extends TasDeCarte {

    private static Pioche instance;

    public Pioche(){
        super();
    }

    public static Pioche getInstance() {
        if (instance == null) {
            instance = new Pioche();
        }
        return instance;
    }

    public void initialiserCartes() {
        Croyant.initialiserCroyant();
        GuideSpirituel.initialiserGuideSpirituel();
        DeusEx.initialiserDeusEx();
        Apocalypse.initialiserApocalypse();
        Collections.shuffle(this.listeCartes);
    }

    public CarteAction piocher() {
        return this.listeCartes.get(0);
//        this.listeCartes.remove(0);
    }

    public ArrayList<CarteAction> getCartes() {
        return this.listeCartes;
    }

}
