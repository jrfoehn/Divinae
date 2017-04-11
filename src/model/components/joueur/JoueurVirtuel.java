package model.components.joueur;

import model.cards.CarteAction;
import model.cards.GuideSpirituel;
import model.components.StrategyDefensive;
import model.components.StrategyOffensive;
import model.components.StrategyRandom;
import model.components.joueur.Joueur;
import model.components.plateau.Plateau;
import model.kernel.Partie;
import tools.Strategy;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class JoueurVirtuel extends Joueur implements Strategy {

    public static final int STRATEGIE_RANDOM = 0;
    public static final int STRATEGIE_OFFENSIVE = 1;
    public static final int STRATEGIE_DEFENSIVE = 2;
    private Strategy strategie;

    public JoueurVirtuel(int nom, int strat){
        super(nom);
        if (strat == STRATEGIE_OFFENSIVE) {
            this.strategie = new StrategyOffensive(this);
        }
        else if (strat == STRATEGIE_DEFENSIVE) {
            this.strategie = new StrategyDefensive(this);
        }
        else {
            this.strategie = new StrategyRandom(this);
        }
    }

    @Override
    public int choisirBinaire() {
        return strategie.choisirBinaire();
    }

    @Override
    public int[] choisirCartesADefausser() {
        return strategie.choisirCartesADefausser();
    }

    @Override
    public int choisirIndexCarteAPoser() {
        return strategie.choisirIndexCarteAPoser();
    }

    @Override
    public int choisirSacrifier() {
        return strategie.choisirSacrifier();
    }

    @Override
    public int choisirSacrifierGuideCroyant() {
        return strategie.choisirSacrifierGuideCroyant();
    }

    @Override
    public int choisirIndexGuide(Joueur joueur) {
        return strategie.choisirIndexGuide(joueur);
    }

    @Override
    public int choisirIndexCroyant(GuideSpirituel guideSpirituel) {
        return strategie.choisirIndexCroyant(guideSpirituel);
    }

    @Override
    public int choisirIndexCroyantVictime(Joueur joueur) {
        return strategie.choisirIndexCroyantVictime(joueur);
    }

    @Override
    public int choisirVictime() {
        return strategie.choisirVictime();
    }

    @Override
    public int[] choisirVictimesMultiples() {
        return strategie.choisirVictimesMultiples();
    }

    @Override
    public int choisirJourNuitNeant(Joueur joueur) {
        return strategie.choisirJourNuitNeant(joueur);
    }

    @Override
    public int choisirCapaciteSpeciale(Joueur joueur) {
        return strategie.choisirCapaciteSpeciale(joueur);
    }

    @Override
    public int choisirUtiliserCapaciteDivine(Joueur joueur) {
        return strategie.choisirUtiliserCapaciteDivine(joueur);
    }

    @Override
    public int choisirUtiliserCapaciteDivineOuCarteSansOrigine(Joueur joueur) {
        return strategie.choisirUtiliserCapaciteDivineOuCarteSansOrigine(joueur);
    }

    @Override
    public int choisirGuiderCroyantSupplementaire(ArrayList<CarteAction> cartesPossibles, GuideSpirituel guideSpirituel) {
        return strategie.choisirGuiderCroyantSupplementaire(cartesPossibles, guideSpirituel);
    }

    @Override
    public int choisirIndexCroyantGuider(ArrayList<CarteAction> cartesPossibles, GuideSpirituel guideSpirituel) {
        return strategie.choisirIndexCroyantGuider(cartesPossibles, guideSpirituel);
    }

}
