package view;

import model.cards.CarteAction;
import model.cards.Croyant;
import model.cards.Divinite;
import model.components.Joueur;

import java.util.ArrayList;

/**
 * Created by jrfoehn on 12/3/16.
 */
public class ViewCLI {

    public static void afficherCarteJoueur(int i) {
        System.out.println("Les cartes du joueur " + i + " sont : ");
    }

    public static void afficherCarteIndex(int i, CarteAction carte) {
        System.out.println("[" + i + "] " +carte.getClass() + carte);
    }

    public static void showCarte(ArrayList<CarteAction> listCarte) {
        System.out.println("Vos cartes : " + listCarte);
    }

    public static void afficherPoints(Joueur joueur) {
        System.out.println("Vous avez " + joueur.pointJour + " points jour, " + joueur.pointNuit + " points nuit, " + joueur.pointNeant + " points néant.");
    }

    public static void afficherCroyantIndex(int i, Croyant croyant) {
        System.out.println("[[" + i + "]] " +croyant.getClass() + croyant);
    }

    public static void afficherDivinite(Divinite divinite) {
        System.out.println("Vous êtes : " + divinite);
    }

    public static void afficherDefausse() {
        System.out.println("Voulez vous défausser une carte ? O / N ?");
    }

    public static void afficherCarteDefausse() {
        System.out.println("Quel est l'index de la carte ?");
    }

    public static void afficherCompleterDeck() {
        System.out.println("Voulez vous compléter votre Main ? O / N ?");
    }

}
