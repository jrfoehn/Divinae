package model.cards;

import model.components.Dogme;
import model.components.Origine;
import model.kernel.Partie;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class Divinite extends CarteAction {

    private String capaciteSpeciale;
    private TypeCarte typeCarte = TypeCarte.Divinite;

    /**
     * Constructeur des divinités
     * @param nom String
     * @param origine Origine
     * @param dogme1 Dogme
     * @param dogme2 Dogme
     * @param dogme3 Dogme
     * @param idCarte int
     * @param idCapacite int
     * @param capaciteSpeciale String
     */
    public Divinite(String nom, Origine origine, TypeCarte typeCarte, Dogme dogme1, Dogme dogme2, Dogme dogme3, int idCarte, int idCapacite, String capaciteSpeciale) {
        super(nom, origine, typeCarte, idCarte, idCapacite);
        this.listeDogmes.add(dogme1);
        this.listeDogmes.add(dogme2);
        this.listeDogmes.add(dogme3);
        this.capaciteSpeciale = capaciteSpeciale;
        this.typeCarte = TypeCarte.Divinite;
    }

    /**
     * Méthode permettant d'ajouter les cartes Divinité à la Partie.
     * @param nom String
     * @param origine Origine
     * @param dogme1 Dogme
     * @param dogme2 Dogme
     * @param dogme3 Dogme
     * @param idCarte int
     * @param idCapacite int
     * @param capaciteSpeciale String
     */
    public static void creerDivinite(String nom, Origine origine, Dogme dogme1, Dogme dogme2, Dogme dogme3, int idCarte, int idCapacite, String capaciteSpeciale) {
        Divinite divinite = new Divinite(nom, origine, TypeCarte.Divinite, dogme1, dogme2, dogme3, idCarte, idCapacite, capaciteSpeciale);
        Partie.divinite.add(divinite);
    }

    /**
     * Méthode permettant d'instancier les cartes Divinté
     */
    public static void initialiserDivinite() {
        creerDivinite("Gwenghelen", Origine.Aube, Dogme.Humain, Dogme.Mystique, Dogme.Symbole, 407, 30, "Récupère autant de points d'action supplémentaires d'Origine Neant que le nombre de guides spirituels que la divinité possède.");
        creerDivinite("Shingua", Origine.Aube, Dogme.Humain, Dogme.Mystique, Dogme.Chaos, 408, 31, "Peut imposer le sacrifice d'un Guide Spirituel ayant le dogme Symbole ou Nature.");
        creerDivinite("Brewalen", Origine.Jour, Dogme.Nature, Dogme.Humain, Dogme.Mystique, 401, 26, "Peut empêcher l'utilisation d'une carte Apocalypse. La carte est défaussée.");
        creerDivinite("Drinded", Origine.Jour, Dogme.Nature, Dogme.Humain, Dogme.Symbole, 402, 27, "Peut empêcher le sacrifice d'un des guides spirituels de n'importe quel joueur.");
        creerDivinite("Yarstur", Origine.Jour, Dogme.Chaos, Dogme.Symbole, Dogme.Mystique, 403, 28, "Peut détruire toutes les cartes de Croyants au centre de latable d'Origine Neant.");
        creerDivinite("Gorpa", Origine.Crepuscule, Dogme.Humain, Dogme.Symbole, Dogme.Chaos, 409, 32, "Peut récupérer les points d'action d'une autre Divinité en plus des siens. L'autre Divinité ne reçoit aucun point d'action ce tour ci.");
        creerDivinite("Romtec", Origine.Crepuscule, Dogme.Nature, Dogme.Humain, Dogme.Chaos, 410, 33, "Peut empêcher un joueur de créer un Guide Spirituel. La carte est défaussée.");
        creerDivinite("Llewella", Origine.Nuit, Dogme.Nature, Dogme.Mystique, Dogme.Chaos, 405, 29, "Peut obliger un joueur à poser une carte Apocalypse s'il en possède une.");
        creerDivinite("Killinstred", Origine.Nuit, Dogme.Nature, Dogme.Mystique, Dogme.Chaos, 404, 29, "Peut obliger un joueur à poser une carte Apocalypse s'il en possède une.");
        creerDivinite("Pui-Tara", Origine.Nuit, Dogme.Nature, Dogme.Mystique, Dogme.Symbole,406, 28, "Peut détruire toutes les cartes de Croyants au centre de la table d'Origine Jour.");
    }


    @Override
    public String toString() {
        return super.toString() + " [DOGMES] " + this.listeDogmes + " [CAPACITE] '" + this.capaciteSpeciale + "'";
    }

}
