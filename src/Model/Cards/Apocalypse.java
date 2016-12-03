package model.cards;

import model.components.Origine;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class Apocalypse extends CarteAction {

    public Apocalypse(String nom, Origine origine, int idCapaciteSpeciale, int idCarte) {
        super(nom, origine, idCapaciteSpeciale, idCarte);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void creerApocalypse(String nom, Origine origine, int idCapaciteSpeciale, int idCarte) {
        Apocalypse apocalypse = new Apocalypse(nom, origine, idCapaciteSpeciale, idCarte);
        //TODO ajouter pioche;
    }

    public static void initialiserApocalypse() {
//        creerApocalypse();
    }
}
