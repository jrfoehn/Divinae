package view.code;

import model.cards.CarteAction;
import model.cards.Croyant;
import model.cards.Divinite;
import model.cards.GuideSpirituel;
import model.components.joueur.Joueur;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jrfoehn on 12/3/16.
 */
public class ViewCLI {

    public static void afficherCarteJoueur(int i) {
        System.out.println("Les cartes du joueur " + i + " sont : ");
    }

    public static void afficherCarteIndex(int i, CarteAction carte) {
        System.out.println("[" + i + "] " + carte);
    }

    public static void showCarte(ArrayList<CarteAction> listeCarte) {
        System.out.println("\nVos cartes : \n");
        Iterator<CarteAction> carteActionIterator = listeCarte.iterator();
        int i = 0;
        while (carteActionIterator.hasNext()) {
            CarteAction carteAction = carteActionIterator.next();
            afficherCarteIndex(i, carteAction);
            i++;
        }
    }

    public static void showGuideSpirituel(ArrayList<GuideSpirituel> listeGuide) {
        System.out.println("Guides : " + listeGuide);
    }

    public static void showCroyant(ArrayList<Croyant> listeCroyant) {
        System.out.println("Croyants : " + listeCroyant);
    }

    public static void afficherPoints(Joueur joueur) {
        System.out.println("\n ====================================================================================== \nJoueur " + joueur.getId() + " [origine] " + joueur.getDivinite().getOrigine() +" : vous avez " + joueur.getPointJour() + " points jour, " + joueur.getPointNuit() + " points nuit, " + joueur.getPointNeant() + " points néant.");
    }

    public static void afficherCroyantIndex(int i, Croyant croyant) {
        System.out.println("[[" + i + "]] " +croyant.getClass() + croyant);
    }

    public static void afficherDivinite(Divinite divinite) {
        System.out.println("\nVous êtes : " + divinite);
    }

    public static void afficherDefausse() {
        System.out.println("\nVoulez vous défausser une carte ? Oui (1) / Non (0) ?");
    }

    public static void afficherPlateau() {
        System.out.println("\n Voici les cartes posées au centre : \n");
    }

    public static void afficherCarteDefausse() {
        System.out.println("\nQuel est l'index de la carte ?");
    }

    public static void afficherCompleterDeck() {
        System.out.println("\nVoulez vous compléter votre Main ? Oui (1) / Non (0) ?");
    }

    public static void afficherJoueurs(Joueur joueur, int i) {
        System.out.println("[" + i + "] - joueur : " + joueur.getId());
    }

    public static void lancerDe() {
        System.out.println("\n\n****************************************\n* Appuyez sur entrée pour lancer le dé *\n****************************************\n\n");
    }
}