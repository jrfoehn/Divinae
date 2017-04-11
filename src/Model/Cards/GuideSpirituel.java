package model.cards;

import model.components.*;
import model.components.plateau.Pioche;

import java.util.ArrayList;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class GuideSpirituel extends CarteAction {

    private int nbCroyantMax;
    public ArrayList<Croyant> croyantGuider;
    private TypeCarte typeCarte = TypeCarte.GuideSpirituel;

    public boolean getProteger() {
        return proteger;
    }

    public void setProteger(boolean proteger) {
        this.proteger = proteger;
    }

    public boolean getCapaciteUtilisable() {
        return capaciteUtilisable;
    }

    public void setCapaciteUtilisee(boolean capaciteUtilisable) {
        this.capaciteUtilisable = capaciteUtilisable;
    }

    public int getNbCroyantMax() {
        return nbCroyantMax;
    }

    public void setNbCroyantMax(int nbCroyantMax) {
        this.nbCroyantMax = nbCroyantMax;
    }

    private boolean proteger  = false;
    private boolean capaciteUtilisable = true;

    /**
     * Constructeur des cartes Guide Spirituel
     * @param nom String
     * @param origine Origine
     * @param idCapaciteSpeciale int
     * @param idCarte int
     * @param nbCroyantMax int
     * @param dogme1 Dogme
     * @param dogme2 Dogme
     * @param capaciteSpeciale String
     */
    public GuideSpirituel(String nom, Origine origine, TypeCarte typeCarte, int idCapaciteSpeciale, int idCarte, int nbCroyantMax, Dogme dogme1, Dogme dogme2, String capaciteSpeciale) {
        super(nom, origine, typeCarte, idCapaciteSpeciale, idCarte);
        this.nbCroyantMax = nbCroyantMax;
        croyantGuider = new ArrayList<Croyant>();
        this.capaciteSpeciale = capaciteSpeciale;
        this.listeDogmes.add(dogme1);
        this.listeDogmes.add(dogme2);
        this.typeCarte = TypeCarte.GuideSpirituel;
    }

    @Override
    public String toString() {
        return super.toString() + " [DOGMES] " + this.listeDogmes + " [NOMBRE CROYANT MAX] " + this.nbCroyantMax + " [NOMBRE CROYANTS GUIDES] " + this.croyantGuider + " [CAPACITE] '" + this.capaciteSpeciale + "'";
    }

    /**
     * Méthode permettant d'ajouter les cartes Guide Spirituel à la pioche après instanciation.
     * @param nom String
     * @param origine Origine
     * @param idCapaciteSpeciale int
     * @param idCarte int
     * @param nbCroyantMax int
     * @param dogme1 Dogme
     * @param dogme2 Dogme
     * @param capaciteSpeciale String
     */
    public static void creerGuide(String nom, Origine origine, int idCapaciteSpeciale, int idCarte, int nbCroyantMax, Dogme dogme1, Dogme dogme2, String capaciteSpeciale) {
        GuideSpirituel guideSpirituel = new GuideSpirituel(nom, origine, TypeCarte.GuideSpirituel, idCapaciteSpeciale, idCarte, nbCroyantMax, dogme1, dogme2, capaciteSpeciale);
        Pioche.getInstance().addCarte(guideSpirituel);
    }

    /**
     * Méthode permettant d'instancier les cartes Guide Spirituel
     */
    public static void initialiserGuideSpirituel() {
        creerGuide("Martyr", Origine.Jour, 11,201, 2, Dogme.Humain, Dogme.Nature, "Equivalent à la pose d'une carte Apocalypse.");
        creerGuide("Martyr", Origine.Nuit, 11,202, 2, Dogme.Humain, Dogme.Symbole, "Equivalent à la pose d'une carte Apocalypse.");
        creerGuide("Martyr", Origine.Neant, 11,203, 2, Dogme.Humain, Dogme.Chaos, "Equivalent à la pose d'une carte Apocalypse.");
        creerGuide("Clerc", Origine.Jour, 12, 204, 2, Dogme.Humain, Dogme.Chaos, "Fait gagner un nombre de points d'action égal au nombre de cartes de Croyants rattachées. L'origine des points d'action est au choix du joueur.");
        creerGuide("Clerc", Origine.Nuit, 12, 205, 2, Dogme.Nature, Dogme.Symbole, "Fait gagner un nombre de points d'action égal au nombre de cartes de Croyants rattachées. L'origine des points d'action est au choix du joueur.");
        creerGuide("Clerc", Origine.Neant, 12, 206, 2, Dogme.Mystique, Dogme.Nature, "Fait gagner un nombre de points d'action égal au nombre de cartes de Croyants rattachées. L'origine des points d'action est au choix du joueur.");
        creerGuide("Clerc", Origine.Jour, 12, 207, 2, Dogme.Nature, Dogme.Chaos, "Fait gagner un nombre de points d'action égal au nombre de cartes de Croyants rattachées. L'origine des points d'action est au choix du joueur.");
        creerGuide("Clerc", Origine.Nuit, 12, 208, 2, Dogme.Mystique, Dogme.Symbole, "Fait gagner un nombre de points d'action égal au nombre de cartes de Croyants rattachées. L'origine des points d'action est au choix du joueur.");
        creerGuide("Clerc", Origine.Neant, 12, 209, 2, Dogme.Chaos, Dogme.Symbole, "Fait gagner un nombre de points d'action égal au nombre de cartes de Croyants rattachées. L'origine des points d'action est au choix du joueur.");
        creerGuide("Clerc", Origine.Jour, 12, 210, 2, Dogme.Mystique, Dogme.Chaos, "Fait gagner un nombre de points d'action égal au nombre de cartes de Croyants rattachées. L'origine des points d'action est au choix du joueur.");
        creerGuide("Clerc", Origine.Nuit, 12, 211, 2, Dogme.Humain, Dogme.Nature, "Fait gagner un nombre de points d'action égal au nombre de cartes de Croyants rattachées. L'origine des points d'action est au choix du joueur.");
        creerGuide("Shaman", Origine.Nuit,13, 212, 3, Dogme.Nature, Dogme.Symbole, "Sacrifie tous les croyants d'Origine Néant d'une Divinité ayant le Dogme Humain. Les capacités spéciales sont jouées normalement.");
        creerGuide("Anarchyste", Origine.Neant, 14, 213, 3, Dogme.Humain, Dogme.Chaos, "Sacrifie un Guide Spirituel, si lui ou sa Divinité ne croit pas au Dogme Chaos. Les capacités sont jouées normalement.");
        creerGuide("Paladin", Origine.Jour, 15, 214, 3, Dogme.Humain, Dogme.Mystique, "Tous les croyants, d'Origine Nuit ou Néant et ayant le Dogme Nature, actuellement sur la table, sont défaussés. Les capacités spéciales ne sont pas jouées.");
        creerGuide("Ascete", Origine.Nuit,16, 215, 1, Dogme.Symbole, Dogme.Humain, "Sacrifie deux cartes Croyants d'une Divinité ayant le Dogme Humain ou Symbole. LEs capacités spéciales sont jouées normalement.");
        creerGuide("Devin", Origine.Neant, 4, 216, 1, Dogme.Mystique, Dogme.Nature,"Oblige une Divinité de Dogme Nature ou Mystique à sacrifier l'un de ses guides.");
        creerGuide("Exorciste", Origine.Jour, 5, 217, 1, Dogme.Chaos, Dogme.Mystique, "Une Divinité d'Origine Nuit ou ayant les dogmes Mystique et Chaos reprend dans sa main l'un de ses Guides Spirituels. Les croyants rattachés sont défaussés");
        creerGuide("Sorcier", Origine.Nuit, 17, 218, 3, Dogme.Symbole, Dogme.Mystique, "Echangez l'un de vos Guides avec un d'une autre Divinité. Vous choisissez les deux guides en question. Les Croyants restent attachés aux mêmes cartes");
        creerGuide("Tyran", Origine.Neant, 15, 219, 3, Dogme.Chaos, Dogme.Symbole, "Défausse tous les croyants ayant le dogme Mystique actuellement au centre de la table.");
        creerGuide("Messie", Origine.Jour, 18, 220, 3, Dogme.Mystique, Dogme.Humain, "Le joueur pose le dé de Cosmogonie sur la face qu'il désire et commence un nouveau tour de jeu.");
    }

}
