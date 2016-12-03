package model.cards;

import model.components.Dogme;
import model.components.Origine;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class Divinite extends Carte {

    protected String capaciteSpeciale;

    public Divinite(String nom, Origine origine, Dogme dogme1, Dogme dogme2, Dogme dogme3, String capaciteSpeciale) {
        super(nom, origine);
        this.listeDogmes.add(dogme1);
        this.listeDogmes.add(dogme2);
        this.listeDogmes.add(dogme3);
        this.capaciteSpeciale = capaciteSpeciale;
    }

    public static void creerDivinite(String nom, Origine origine, Dogme dogme1, Dogme dogme2, Dogme dogme3, String capaciteSpeciale) {
        Divinite divinite = new Divinite(nom, origine, dogme1, dogme2, dogme3, capaciteSpeciale);
        //Ajouter à la partie TODO
    }

    @Override
    public String toString() {
        return super.toString() + " capacité " + this.capaciteSpeciale + " dogmes : " + this.listeDogmes;
    }

    public static void initialiserDivinite() {
        creerDivinite("Gwenghelen", Origine.Aube, Dogme.Humain, Dogme.Mystique, Dogme.Symbole, "Récupère autant de points d'action supplémentaires d'Origine Néant que le nombre de guides spirituels que la divinité possède.");
        creerDivinite("Shingua", Origine.Aube, Dogme.Humain, Dogme.Mystique, Dogme.Chaos, "Peut imposer le sacrifice d'un Guide Spirituel ayant le dogme Symbole ou Nature.");
        creerDivinite("Brewalen", Origine.Jour, Dogme.Nature, Dogme.Humain, Dogme.Mystique, "Peut empêcher l'utilisation d'une carte Apocalypse. La carte est défaussée.");
        creerDivinite("Drinded", Origine.Jour, Dogme.Nature, Dogme.Humain, Dogme.Symbole, "Peut empêcher le sacrifice d'un des guides spirituels de n'importe quel joueur.");
        creerDivinite("Yarstur", Origine.Jour, Dogme.Chaos, Dogme.Symbole, Dogme.Mystique, "Peut détruire toutes les listeCartes de Croyants au centre de latable d'Origine Néant.");
        creerDivinite("Gorpa", Origine.Crepuscule, Dogme.Humain, Dogme.Symbole, Dogme.Chaos,"Peut récupérer les points d'action d'une autre Divinité en plus des siens. L'autre Divinité ne reçoit aucun point d'action ce tour ci.");
        creerDivinite("Romtec", Origine.Crepuscule, Dogme.Nature, Dogme.Humain, Dogme.Chaos, "Peut empêcher un joueur de créer un Guide Spirituel. La carte est défaussée.");
        creerDivinite("Llewella", Origine.Nuit, Dogme.Nature, Dogme.Mystique, Dogme.Chaos, "Peut obliger un joueur à poser une carte Apocalypse s'il en possède une.");
        creerDivinite("Killinstred", Origine.Nuit, Dogme.Nature, Dogme.Mystique, Dogme.Chaos, "Peut obliger un joueur à poser une carte Apocalypse s'il en possède une.");
        creerDivinite("Oui-Tara", Origine.Nuit, Dogme.Nature, Dogme.Mystique, Dogme.Symbole, "Peut détruire toutes les listeCartes de Croyants au centre de la table d'Origine Jour.");
    }

}
