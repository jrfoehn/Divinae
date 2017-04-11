package tools;

import model.cards.CarteAction;
import model.cards.GuideSpirituel;
import model.components.joueur.Joueur;

import java.util.ArrayList;

/**
 * Interface pour les stratégiesc
 */
public interface Strategy {
    int choisirIndexCarteAPoser();
    int choisirSacrifier();
    int choisirSacrifierGuideCroyant();
    int choisirIndexGuide(Joueur joueur);
    int choisirIndexCroyant(GuideSpirituel guideSpirituel);
    int choisirIndexCroyantVictime(Joueur joueur);
    int choisirVictime();
    int[] choisirVictimesMultiples();
    int choisirJourNuitNeant(Joueur joueur);
    int choisirCapaciteSpeciale(Joueur joueur);
    int choisirUtiliserCapaciteDivine(Joueur joueur);
    int choisirUtiliserCapaciteDivineOuCarteSansOrigine(Joueur joueur);
    int choisirGuiderCroyantSupplementaire(ArrayList<CarteAction> cartesPossibles, GuideSpirituel guideSpirituel);
    int choisirIndexCroyantGuider(ArrayList<CarteAction> cartesPossibles, GuideSpirituel guideSpirituel);
    int choisirBinaire();
    int[] choisirCartesADefausser();
    int choisirPoserCartePlateau();
}
