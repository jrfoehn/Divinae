package model.cards;

import model.components.Dogme;
import model.components.Origine;

import java.util.ArrayList;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class GuideSpirituel extends CarteAction {

    private int nbCroyantMax;
    private ArrayList<Croyant> nbCroyant;

    public GuideSpirituel(String nom, Origine origine, Dogme dogme1, Dogme dogme2, int nbCroyantMax, String capaciteSpeciale, int idCarte, int idCapaciteSpeciale) {
        super(nom, origine, capaciteSpeciale, idCapaciteSpeciale);

    }

}
