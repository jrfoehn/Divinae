package model.cards;

import model.components.Origine;
import model.components.plateau.Pioche;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class DeusEx extends CarteAction {

    private TypeCarte typeCarte = TypeCarte.DeusEx;

    /**
     * Constructeur de DeusEx
     * @param nom String
     * @param origine Origine
     * @param typeCarte TypeCarte
     * @param idCapaciteSpeciale int
     * @param idCarte int
     * @param capaciteSpeciale String
     */
    public DeusEx(String nom, Origine origine, TypeCarte typeCarte, int idCapaciteSpeciale, int idCarte, String capaciteSpeciale) {
        super(nom, origine, typeCarte, idCapaciteSpeciale, idCarte);
        this.capaciteSpeciale = capaciteSpeciale;
        this.typeCarte = typeCarte;
    }

    @Override
    public String toString() {
        return super.toString() + " [CAPACITE] '" + this.capaciteSpeciale + "'";
    }

    public static void creerDeusEx(String nom, Origine origine, int idCapaciteSpeciale, int idCarte, String capaciteSpeciale) {
        DeusEx deusEx = new DeusEx(nom, origine, TypeCarte.DeusEx, idCapaciteSpeciale, idCarte, capaciteSpeciale);
        Pioche.getInstance().addCarte(deusEx);
    }

    public static void initialiserDeusEx() {
        creerDeusEx("Colère Divine", Origine.Jour, 19, 301, "Détruit une carte Guide Spirituel d'Origine Nuit ou Neant, dont la capacité spéciale n'a pas effet. Les croyants attachés reviennent au centre de la table.");
        creerDeusEx("Colère Divine", Origine.Nuit, 19, 302, "Détruit une carte Guide Spirituel d'Origine Nuit ou Neant, dont la capacité spéciale n'a pas effet. Les croyants attachés reviennent au centre de la table.");
        creerDeusEx("Stase", Origine.Jour, 20, 303, "Protège un Guide Spirituel et ses Croyants jusqu'à ce que cette carte soit annulée ou jusqu'à la prochaine tentative d'Apocalypse.");
        creerDeusEx("Ordre Céleste", Origine.Jour, 17, 304, "Vous récupérez un des Guides Spirituels posés devant une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.");
        creerDeusEx("Fourberie", Origine.Nuit, 16, 305, "Sacrifiez deux listeCartes Croyants d'une autre Divinité. Les capacités spéciales ne sont pas jouées.");
        creerDeusEx("Diversion", Origine.Nuit, 3, 306, "Prenez trois listeCartes dans la Main d'un autre joueur et incluez-les à votre Main.");
        creerDeusEx("Conentration", Origine.Neant, 17, 307, "Vous récupérez un des Guides Spirituels posés devant une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.");
        creerDeusEx("Trou Noir", Origine.Neant, 10, 308, "Aucun autre joueur ne gagne de points d'Action durant ce tour.");
        creerDeusEx("Phoenix", Origine.Neant, 21, 309, "Permet de bénéficier de la capacité spéciale de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte.");
        creerDeusEx("Influence Jour", Origine.Aucune, 22, 310, "Annule la capacité spéciale d'une carte d'action d'Origine Nuit ou Neant.");
        creerDeusEx("Influence Nuit", Origine.Aucune, 22, 311, "Annule la capacité spéciale d'une carte d'action d'Origine Jour ou Neant.");
        creerDeusEx("Influence Neant", Origine.Aucune, 22, 312, "Annule la capacité spéciale d'une carte d'action d'Origine Jour ou Nuit.");
        creerDeusEx("Influence Nulle", Origine.Aucune, 22, 313, "Annule la capacité spéciale d'une carte d'action.");
        creerDeusEx("Influence Nulle", Origine.Aucune, 22, 314, "Annule la capacité spéciale d'une carte d'action.");
        creerDeusEx("Transe", Origine.Aucune, 23, 315, "Permet de récupérer les effets bénéfiques d'une carte d'Action posée par une autre Divinité. S'il s'agit d'une carte Croyants ou Guide Spirituel, vous posez la carte devant vous.");
        creerDeusEx("Miroir", Origine.Aucune, 24, 316, "Retourne les effets d'une carte d'Action sur la Divinité qui l'a posée.");
        creerDeusEx("Bouleversement", Origine.Aucune, 6, 317, "Relancez le dé de Cosmogonie. Le tour de jeu se terminera normalement, mais sous la nouvelle influence.");
        creerDeusEx("Inquisition", Origine.Aucune, 25, 318, "Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. Lancez le dé de Cosmogonie. Sur Jour, le Guide adverse est sacrifié, sur Nuit le votre est sacrifié, sur Neant rien ne se passe.");
    }

}