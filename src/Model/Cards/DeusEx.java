package model.cards;

import model.components.Origine;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class DeusEx extends CarteAction {

    public DeusEx(String nom, Origine origine, int idCapaciteSpeciale, int idCarte, String capaciteSpeciale) {
        super(nom, origine, idCapaciteSpeciale, idCarte);
        this.capaciteSpeciale = capaciteSpeciale;
    }

    @Override
    public String toString() {
        return super.toString() + " nom " + this.nom + " capacite " + this.capaciteSpeciale;
    }

    public static void creerDeusEx(String nom, Origine origine, int idCapaciteSpeciale, int idCarte, String capaciteSpeciale) {
        DeusEx deusEx = new DeusEx(nom, origine, idCapaciteSpeciale, idCarte, capaciteSpeciale);
        // lier pioche
    }

    public static void initialiserDeusEx() {
        creerDeusEx("Colère Divine", Origine.Jour, 0, 358, "Détruit une carte Guide Spirituel d'Origine Nuit ou Néant, dont la capacité spéciale n'a pas effet. Les croyants attachés reviennent au centre de la table.");
        creerDeusEx("Colère Divine", Origine.Nuit, 0, 359, "Détruit une carte Guide Spirituel d'Origine Nuit ou Néant, dont la capacité spéciale n'a pas effet. Les croyants attachés reviennent au centre de la table.");
        creerDeusEx("Stase", Origine.Jour, 3, 360, "Protège un Guide Spirituel et ses Croyants jusqu'à ce que cette carte soit annulée ou jusqu'à la prochaine tentative d'Apocalypse.");
        creerDeusEx("Ordre Céleste", Origine.Jour, 0, 361, "Vous récupérez un des Guides Spirituels posés devant une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.");
        creerDeusEx("Fourberie", Origine.Nuit, 0, 362, "Sacrifiez deux listeCartes Croyants d'une autre Divinité. Les capacités spéciales ne sont pas jouées.");
        creerDeusEx("Diversion", Origine.Nuit, 0, 363, "Prenez trois listeCartes dans la Main d'un autre joueur et incluez-les à votre Main.");
        creerDeusEx("Conentration", Origine.Néant, 0, 364, "Vous récupérez un des Guides Spirituels posés devant une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.");
        creerDeusEx("Trou Noir", Origine.Néant, 10, 365, "Aucun autre joueur ne gagne de points d'Action durant ce tour.");
        creerDeusEx("Phoenix", Origine.Néant, 0, 366, "Permet de bénéficier de la capacité spéciale de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte.");
        creerDeusEx("Influence Jour", Origine.Aucune, 0, 367, "Annule la capacité spéciale d'une carte d'action d'Origine Nuit ou Néant.");
        creerDeusEx("Influence Nuit", Origine.Aucune, 0, 368, "Annule la capacité spéciale d'une carte d'action d'Origine Jour ou Néant.");
        creerDeusEx("Influence Néant", Origine.Aucune, 0, 369, "Annule la capacité spéciale d'une carte d'action d'Origine Jour ou Nuit.");
        creerDeusEx("Influence Nulle", Origine.Aucune, 0, 370, "Annule la capacité spéciale d'une carte d'action.");
        creerDeusEx("Influence Nulle", Origine.Aucune, 0, 371, "Annule la capacité spéciale d'une carte d'action.");
        creerDeusEx("Transe", Origine.Aucune, 0, 372, "Permet de récupérer les effets bénéfiques d'une carte d'Action posée par une autre Divinité. S'il s'agit d'une carte Croyants ou Guide Spirituel, vous posez la carte devant vous.");
        creerDeusEx("Miroir", Origine.Aucune, 0, 373, "Retourne les effets d'une carte d'Action sur la Divinité qui l'a posée.");
        creerDeusEx("Bouleversement", Origine.Aucune, 6, 374, "Relancez le dé de Cosmogonie. Le tour de jeu se terminera normalement, mais sous la nouvelle influence.");
        creerDeusEx("Inquisition", Origine.Aucune, 0, 375, "Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. Lancez le dé de Cosmogonie. Sur Jour, le Guide adverse est sacrifié, sur Nuit le votre est sacrifié, sur Néant rien ne se passe.");
    }

}