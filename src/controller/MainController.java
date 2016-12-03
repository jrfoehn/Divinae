package controller;

import model.cards.CarteAction;
import model.cards.Croyant;
import model.cards.Divinite;
import model.components.Joueur;

import java.util.ArrayList;

/**
 * Created by jrfoehn on 12/3/16.
 */
public abstract class MainController {

    public static void afficherCarteJoueur(int i) {
        view.ViewCLI.afficherCarteJoueur(i);
    }

    public static void afficherCarteIndex(int i, CarteAction carteAction) {
        view.ViewCLI.afficherCarteIndex(i, carteAction);
    }

    public static void showCarte(ArrayList<CarteAction> listCarte) {
        view.ViewCLI.showCarte(listCarte);
    }

    public static void afficherPoints(Joueur joueur) {
        view.ViewCLI.afficherPoints(joueur);
    }

    public static void afficherCroyantIndex(int i, Croyant croyant) {
        view.ViewCLI.afficherCroyantIndex(i, croyant);
    }

    public static void afficherDivinite(Divinite divinite) {
        view.ViewCLI.afficherDivinite(divinite);
    }

    public static void afficherDefausse() {
        view.ViewCLI.afficherDefausse();
    }

    public static void afficherCarteDefausse() {
        view.ViewCLI.afficherCarteDefausse();
    }

    public static void afficherCompleterDeck() {
        view.ViewCLI.afficherCompleterDeck();
    }

}
