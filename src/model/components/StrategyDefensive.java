package model.components;

import model.cards.CarteAction;
import model.cards.GuideSpirituel;
import model.components.joueur.Joueur;
import tools.Strategy;

import java.util.ArrayList;

/**
 * Stratégie défensive
 */
public class StrategyDefensive implements Strategy {

    private Joueur joueur;

    public StrategyDefensive(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public int choisirPoserCartePlateau() {
        return this.choisirBinaire();
    }

    @Override
    public int choisirIndexCarteAPoser() {
        return 0;
    }

    @Override
    public int choisirSacrifier() {
        return 0;
    }

    @Override
    public int choisirSacrifierGuideCroyant() {
        return 0;
    }

    @Override
    public int choisirIndexGuide(Joueur joueur) {
        return 0;
    }

    @Override
    public int choisirIndexCroyant(GuideSpirituel guideSpirituel) {
        return 0;
    }

    @Override
    public int choisirIndexCroyantVictime(Joueur joueur) {
        return 0;
    }

    @Override
    public int choisirVictime() {
        return 0;
    }

    @Override
    public int[] choisirVictimesMultiples() {
        return new int[0];
    }

    @Override
    public int choisirJourNuitNeant(Joueur joueur) {
        return 0;
    }

    @Override
    public int choisirCapaciteSpeciale(Joueur joueur) {
        return 0;
    }

    @Override
    public int choisirUtiliserCapaciteDivine(Joueur joueur) {
        return 0;
    }

    @Override
    public int choisirUtiliserCapaciteDivineOuCarteSansOrigine(Joueur joueur) {
        return 0;
    }

    @Override
    public int choisirGuiderCroyantSupplementaire(ArrayList<CarteAction> cartesPossibles, GuideSpirituel guideSpirituel) {
        return 0;
    }

    @Override
    public int choisirIndexCroyantGuider(ArrayList<CarteAction> cartesPossibles, GuideSpirituel guideSpirituel) {
        return 0;
    }

    @Override
    public int choisirBinaire() {
        return 0;
    }

    @Override
    public int[] choisirCartesADefausser() {
        return new int[0];
    }
}
