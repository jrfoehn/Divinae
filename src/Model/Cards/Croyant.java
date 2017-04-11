package model.cards;

import model.components.Dogme;
import model.components.Origine;
import model.components.plateau.Pioche;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class Croyant extends CarteAction {

    private int nbCroyant;
    private boolean estGuide;
    private boolean nouveauCroyant; // Permet de savoir si la carte vient d'être posée au centre ou non
    private TypeCarte typeCarte = TypeCarte.Croyant;
    private boolean proteger = false;
    private boolean capaciteUtilisable = true;

    /**
     * Constructeur carte Croyant
     * @param nom String
     * @param origine Origine
     * @param idCapaciteSpeciale int
     * @param idCarte int
     * @param dogme1 Dogme
     * @param dogme2 Dogme
     * @param dogme3 Dogme
     * @param nbCroyant int
     * @param capaciteSpeciale String
     */
    public Croyant(String nom, Origine origine, TypeCarte typeCarte, int idCapaciteSpeciale, int idCarte, Dogme dogme1, Dogme dogme2, Dogme dogme3, int nbCroyant, String capaciteSpeciale) {
        super(nom, origine, typeCarte, idCapaciteSpeciale, idCarte);
        this.estGuide = false;
        this.nouveauCroyant = true;
        this.listeDogmes.add(dogme1);
        this.listeDogmes.add(dogme2);
        this.listeDogmes.add(dogme3);
        this.nbCroyant = nbCroyant;
        this.capaciteSpeciale = capaciteSpeciale;
        this.typeCarte = typeCarte;
    }

    @Override
    public String toString() {
        return super.toString() + " [NOMBRE DE CROYANTS] [" + this.nbCroyant + "] [CAPACITE] '" + this.capaciteSpeciale + "'";
    }

    @Override
    public int getIdCarte() {
        return this.idCarte;
    }

    @Override
    public int getIdCapaciteSpeciale() {
        return this.idCapaciteSpeciale;
    }

    public int getNbCroyant() {
        return this.nbCroyant;
    }

    public boolean getNouveauCroyant() {
        return nouveauCroyant;
    }

    public void setNouveauCroyant(boolean nouveauCroyant) {
        this.nouveauCroyant = nouveauCroyant;
    }

    public boolean isProteger() {
        return proteger;
    }

    public void setProteger(boolean proteger) {
        this.proteger = proteger;
    }

    public boolean isCapaciteUtilisable() {
        return capaciteUtilisable;
    }

    public void setCapaciteUtilisee(boolean capaciteUtilisable) {
        this.capaciteUtilisable = capaciteUtilisable;
    }

    public boolean getEstGuide() {
        return this.estGuide;
    }

    public void setEstGuide(boolean estGuide) {
        this.estGuide = estGuide;
    }

    /**
     * Méthode permettant d'ajouter une carte Croyant à la pioche après qu'elles aient été instanciée.
     * @param nom String
     * @param origine Origine
     * @param idCapaciteSpeciale int
     * @param idCarte int
     * @param dogme1 Dogme
     * @param dogme2 Dogme
     * @param dogme3 Dogme
     * @param nbCroyant int
     * @param capaciteSpeciale String
     */
    public static void creerCroyant(String nom, Origine origine, int idCapaciteSpeciale, int idCarte, Dogme dogme1, Dogme dogme2, Dogme dogme3, int nbCroyant, String capaciteSpeciale) {
        Croyant croyant = new Croyant(nom, origine, TypeCarte.Croyant, idCapaciteSpeciale, idCarte, dogme1, dogme2, dogme3, nbCroyant, capaciteSpeciale);
        Pioche.getInstance().addCarte(croyant);
    }

    /**
     * Méthode permettant d'instancer les cartes Croyant.
     */
    public static void initialiserCroyant() {
        creerCroyant("Moines", Origine.Jour, 1, 101, Dogme.Humain, Dogme.Nature, Dogme.Mystique, 2, "Donne un point d'action d'origine JOUR.");
        creerCroyant("Moines", Origine.Jour, 1, 102, Dogme.Humain, Dogme.Nature, Dogme.Mystique, 2, "Donne un point d'action d'origine JOUR.");
        creerCroyant("Moines", Origine.Jour, 1, 103, Dogme.Humain, Dogme.Nature, Dogme.Mystique, 2, "Donne un point d'action d'origine JOUR.");
        creerCroyant("Moines", Origine.Jour, 1, 104, Dogme.Humain, Dogme.Nature, Dogme.Mystique, 2, "Donne un point d'action d'origine JOUR.");
        creerCroyant("Moines", Origine.Jour, 1, 105, Dogme.Humain, Dogme.Nature, Dogme.Mystique, 2, "Donne un point d'action d'origine JOUR.");
        creerCroyant("Travailleurs", Origine.Jour, 2, 106, Dogme.Symbole, Dogme.Humain, Dogme.Chaos, 2, "Empêche une Divinité de dogme NATURE ou MYSTIQUE de sacrifier un croyant pendant ce tour.");
        creerCroyant("Travailleurs", Origine.Jour, 2, 107, Dogme.Humain, Dogme.Nature, Dogme.Symbole, 2, "Empêche une Divinité de dogme CHAOS ou MYSTIQUE de sacrifier un de ses guides spirituels pendant ce tour.");
        creerCroyant("Travailleurs", Origine.Jour, 3, 108, Dogme.Mystique, Dogme.Humain, Dogme.Chaos, 2, "Piochez deux listeCartes au hasard dans la Main d'une autre Divinité.");
        creerCroyant("Ermite", Origine.Jour, 4, 109, Dogme.Mystique, Dogme.Nature, Dogme.Chaos, 1, "Impose le sacrifice d'un croyant d'une autre Divinité, qui choisit la carte. La capacité spéciale du sacrifice est jouée.");
        creerCroyant("Ermite", Origine.Jour, 4, 110, Dogme.Mystique, Dogme.Nature, Dogme.Symbole, 1, "Impose le sacrifice d'un croyant d'une autre Divinité, qui choisit la carte. La capacité spéciale du sacrifice est jouée.");
        creerCroyant("Intégristes", Origine.Jour, 4, 111, Dogme.Humain, Dogme.Nature, Dogme.Chaos, 1,"Impose le sacrifice d'un guide spirituel d'une autre Divinité, qui choisit la carte. La capacité spéciale du sacrifice est jouée.");
        creerCroyant("Guerriers Saints", Origine.Jour, 5, 112, Dogme.Mystique, Dogme.Nature, Dogme.Symbole, 4, "Un guide spirituel revient dans la Main de sa Divinité. Ses croyants reviennent au centre de la table.");
        creerCroyant("Diplomates", Origine.Jour, 6, 113, Dogme.Symbole, Dogme.Humain, Dogme.Chaos, 4, "Relancez le dé de cosmogonie. Le tour se termine normalement sous la nouvelle influence.");
        creerCroyant("Démons", Origine.Nuit, 1, 114, Dogme.Humain, Dogme.Nature, Dogme.Mystique, 2, "Donne un point d'action d'origine NUIT.");
        creerCroyant("Démons", Origine.Nuit, 1, 115, Dogme.Humain, Dogme.Nature, Dogme.Mystique, 2, "Donne un point d'action d'origine NUIT.");
        creerCroyant("Démons", Origine.Nuit, 1, 116, Dogme.Humain, Dogme.Nature, Dogme.Mystique, 2, "Donne un point d'action d'origine NUIT.");
        creerCroyant("Démons", Origine.Nuit, 1, 117, Dogme.Humain, Dogme.Nature, Dogme.Mystique, 2, "Donne un point d'action d'origine NUIT.");
        creerCroyant("Démons", Origine.Nuit, 1, 118, Dogme.Humain, Dogme.Nature, Dogme.Mystique, 2, "Donne un point d'action d'origine NUIT.");
        creerCroyant("Alchimistes", Origine.Nuit, 2, 119, Dogme.Symbole, Dogme.Nature, Dogme.Chaos, 2, "Empêche une Divinité de dogme HUMAIN ou MYSTIQUE de sacrifier un croyant pendant ce tour.");
        creerCroyant("Alchimistes", Origine.Nuit, 2, 120, Dogme.Mystique, Dogme.Nature, Dogme.Chaos, 2, "Empêche une Divinité de dogme HUMAIN ou SYMBOLE de sacrifier un de ses guides spirituels pendant ce tour.");
        creerCroyant("Alchimistes", Origine.Nuit, 3, 121, Dogme.Symbole, Dogme.Nature, Dogme.Chaos, 2, "Piochez deux listeCartes au hasard dans la Main d'une autre Divinité.");
        creerCroyant("Vampire", Origine.Nuit, 4, 122, Dogme.Humain, Dogme.Nature, Dogme.Symbole, 1, "Impose le sacrifice d'un croyant d'une autre Divinité, qui choisit la carte. La capacité spéciale du sacrifice est jouée.");
        creerCroyant("Vampire", Origine.Nuit, 4, 123, Dogme.Humain, Dogme.Nature, Dogme.Symbole, 1, "Impose le sacrifice d'un croyant d'une autre Divinité, qui choisit la carte. La capacité spéciale du sacrifice est jouée.");
        creerCroyant("Lycanthropes", Origine.Nuit, 7, 124, Dogme.Humain, Dogme.Nature, Dogme.Chaos, 4, "Retirez tous les croyants rattachés à un guide spirituel d'une autre Divinité. Le guide est défaussé et les croyants reviennent au centre de la table.");
        creerCroyant("Pillards", Origine.Nuit, 8, 125, Dogme.Mystique, Dogme.Nature, Dogme.Symbole, 4, "Récupérez les points d'action d'une Divinité n'ayant pas encore joué pendant ce tour. Les points d'actions gardent leur origine. La Divinité perd ses points.");
        creerCroyant("Illusionnistes", Origine.Nuit, 9, 126, Dogme.Symbole, Dogme.Humain, Dogme.Chaos, 4, "Vous bénéficiez de la capacité spéciale d'une carte croyant appartenant à une autre Divinité. La carte en question reste en jeu.");
        creerCroyant("Esprits", Origine.Neant, 1, 127, Dogme.Mystique, Dogme.Nature, Dogme.Chaos, 2, "Donne un point d'action d'origine NEANT.");
        creerCroyant("Esprits", Origine.Neant, 1, 128, Dogme.Mystique, Dogme.Nature, Dogme.Chaos, 2, "Donne un point d'action d'origine NEANT.");
        creerCroyant("Esprits", Origine.Neant, 1, 129, Dogme.Mystique, Dogme.Nature, Dogme.Chaos, 2, "Donne un point d'action d'origine NEANT.");
        creerCroyant("Esprits", Origine.Neant, 1, 130, Dogme.Mystique, Dogme.Nature, Dogme.Chaos, 2, "Donne un point d'action d'origine NEANT.");
        creerCroyant("Esprits", Origine.Neant, 1, 131, Dogme.Mystique, Dogme.Nature, Dogme.Chaos, 2, "Donne un point d'action d'origine NEANT.");
        creerCroyant("Aliénés", Origine.Neant, 2, 132, Dogme.Symbole, Dogme.Humain, Dogme.Chaos, 2, "Empêche une Divinité de dogme NATURE ou MYSTIQUE de sacrifier un croyant pendant ce tour.");
        creerCroyant("Aliénés", Origine.Neant, 2, 133, Dogme.Humain, Dogme.Nature, Dogme.Symbole, 2, "Empêche une Divinité de dogme MYSTIQUE ou CHAOS de sacrifier un de ses guides spirituels pendant ce tour.");
        creerCroyant("Aliénés", Origine.Neant, 3, 134, Dogme.Mystique, Dogme.Humain, Dogme.Chaos, 2, "Piochez deux listeCartes au hasard dans la Main d'une autre Divinité.");
        creerCroyant("Revenant", Origine.Neant, 6, 135, Dogme.Symbole, Dogme.Humain, Dogme.Chaos,2, "Relancez le dé de cosmogonie. Le tour se termine normalement sous la nouvelle influence.");
        creerCroyant("Révolutionnaires", Origine.Neant,4, 136, Dogme.Symbole, Dogme.Humain, Dogme.Chaos, 2, "Imposez le sacrifice d'un croyant à autant de Divinités que vous le voulez. Chaque Divinité choisit la carte à sacrifier. Les capacités spéciales sont jouées.");
    }

}
