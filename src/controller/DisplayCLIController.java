package controller;

import model.cards.*;
import model.components.joueur.Joueur;
import model.components.plateau.Plateau;
import model.kernel.Partie;
import view.code.ViewCLI;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe contenant l'ensemble des méthodes d'interface commande
 */
public abstract class DisplayCLIController {

    /**
     * Affiche la carte du joueur
     * @param i int
     */
    public static void afficherCarteJoueur(int i) {
        ViewCLI.afficherCarteJoueur(i);
    }

    /**
     * Affiche les cartes du joueur indexées
     * @param i int
     * @param carteAction CarteAction
     */
    public static void afficherCarteIndex(int i, CarteAction carteAction) {
        ViewCLI.afficherCarteIndex(i, carteAction);
    }

    /**
     * Affiche la main d'un joueur
     * @param listeCarte ArrayList<CarteAction>
     */
    public static void showCarte(ArrayList<CarteAction> listeCarte) {
        ViewCLI.showCarte(listeCarte);
    }

    /**
     * Affiche les guides présents sur le plateau
     * @param listeGuide ArrayList<GuideSpirituel>
     */
    public static void showGuide(ArrayList<GuideSpirituel> listeGuide) {
        ViewCLI.showGuideSpirituel(listeGuide);
    }

    /**
     * Affiche les croyants présents sur le plateau
     * @param listeCroyant ArrayList<Croyant>
     */
    public static void showCroyant(ArrayList<Croyant> listeCroyant) {
        ViewCLI.showCroyant(listeCroyant);
    }

    /**
     * Affiche les points du joueur
     * @param joueur Joueur
     */
    public static void afficherPoints(Joueur joueur) {
        ViewCLI.afficherPoints(joueur);
    }

    /**
     * Affiche une liste de croyants indexées
     * @param i int
     * @param croyant Croyant
     */
    public static void afficherCroyantIndex(int i, Croyant croyant) {
        ViewCLI.afficherCroyantIndex(i, croyant);
    }

    /**
     * Affiche une divinité
     * @param divinite Divinite
     */
    public static void afficherDivinite(Divinite divinite) {
        ViewCLI.afficherDivinite(divinite);
    }

    /**
     * Demande au joueur s'il veut défausser
     */
    public static void afficherDefausse() {
        ViewCLI.afficherDefausse();
    }

    /**
     * Affiche les cartes présentes sur le Plateau
     */
    public static void afficherPlateau() {
        ViewCLI.afficherPlateau();
        Iterator<CarteAction> carteActionIterator = Plateau.getInstance().getListeCartePlateau().iterator();
        int i = 0;
        while (carteActionIterator.hasNext()) {
            CarteAction carteAction = carteActionIterator.next();
            DisplayCLIController.afficherCarteIndex(i, carteAction);
            i++;
        }
    }

    /**
     * Demande au joueur s'il veut compléter son deck
     */
    public static void afficherCompleterDeck() {
        ViewCLI.afficherCompleterDeck();
    }

    /**
     * Affiche les adversaires
     * @param joueur Joueur
     * @param i int
     */
    public static void afficherJoueurs(Joueur joueur, int i) {
        ViewCLI.afficherJoueurs(joueur, i);
    }

    /**
     * Demande au joueur de lancer le dé
     */
    public static void lancerDe(){
        ViewCLI.lancerDe();
        Partie.getInstance().lancerDe();
    }

}
