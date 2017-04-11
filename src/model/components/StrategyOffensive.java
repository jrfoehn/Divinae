package model.components;

import model.cards.CarteAction;
import model.cards.Croyant;
import model.cards.GuideSpirituel;
import model.components.joueur.Joueur;
import model.components.joueur.JoueurHumain;
import model.kernel.Partie;
import tools.Strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Stratégie Offensive
 */
public class StrategyOffensive implements Strategy {

    private Joueur joueur;

    public StrategyOffensive(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public int choisirPoserCartePlateau(){
        return this.choisirBinaire();
    };

    @Override
    public int choisirIndexCarteAPoser() {
        return 0;
    }

    @Override
    public int choisirSacrifier() {
        return 1;
    }

    @Override
    public int choisirSacrifierGuideCroyant() {
        boolean possedeCroyant = false;
        boolean possedeGuide = false;
        Iterator<CarteAction> carteActionIterator = joueur.getCartesPlateauPerso().getListeCartePlateau().iterator();
        while (carteActionIterator.hasNext()) {
            CarteAction carteAction = carteActionIterator.next();
            if (carteAction instanceof GuideSpirituel) {
                possedeGuide = true;
            }
            if (carteAction instanceof Croyant) {
                possedeCroyant = true;
            }
        }
        if (possedeCroyant || possedeGuide) {
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public int choisirIndexGuide(Joueur joueur) {
        ArrayList<GuideSpirituel> guidePlateauPerso = new ArrayList<GuideSpirituel>();
        Iterator<CarteAction> carteActionIterator = joueur.getCartesPlateauPerso().getListeCartePlateau().iterator();
        while (carteActionIterator.hasNext()) {
            GuideSpirituel guideSpirituel = (GuideSpirituel) carteActionIterator.next();
            guidePlateauPerso.add(guideSpirituel);
        }
        int nombreDeGuide = guidePlateauPerso.size() - 1;
        return ((int) Math.floor(Math.random()*nombreDeGuide));
    }

    @Override
    public int choisirIndexCroyant(GuideSpirituel guideSpirituel) {
        ArrayList<Croyant> croyantsGuides = guideSpirituel.croyantGuider;
        int nombreDeCroyant = croyantsGuides.size() - 1;
        return ((int) Math.floor(Math.random()*nombreDeCroyant));
    }

    @Override
    public int choisirIndexCroyantVictime(Joueur joueur) {
        Iterator<CarteAction> carteActionIterator = joueur.getCartesPlateauPerso().getListeCartePlateau().iterator();
        ArrayList<Croyant> croyants = new ArrayList<Croyant>();
        while (carteActionIterator.hasNext()) {
            Croyant carteAction = (Croyant) carteActionIterator.next();
            if (carteAction instanceof Croyant) {
                croyants.add(carteAction);
            }
        }
        return (int) Math.floor(Math.random()*(croyants.size()-1));
    }

    @Override
    public int choisirVictime() {
        Iterator<Joueur> joueurs = Partie.getInstance().joueursActifs.iterator();
        ArrayList<Joueur> victimesPossibles = new ArrayList<Joueur>();
        while (joueurs.hasNext()) {
            JoueurHumain joueurHumain = (JoueurHumain) joueurs.next();
            victimesPossibles.add(joueurHumain);
        }
        int proba = (int) Math.floor(Math.random()*(victimesPossibles.size()-1));
        return proba;
    }

    @Override
    public int[] choisirVictimesMultiples() {
        Iterator<Joueur> joueurs = Partie.getInstance().joueursActifs.iterator();
        ArrayList<Joueur> victimesPossibles = new ArrayList<Joueur>();
        while (joueurs.hasNext()) {
            Joueur joueur = joueurs.next();
            if (joueur instanceof JoueurHumain) {
                victimesPossibles.add(joueur);
            }
            victimesPossibles.add(joueur);
        }
        int nbVictimesPossibles = (int) Math.floor(Math.random()*(victimesPossibles.size() - 1));
        int idVictimesMultiples[] = new int[nbVictimesPossibles];
        for (int i = 0; i < nbVictimesPossibles; i++) {
            int proba = (int) Math.floor(Math.random()*(victimesPossibles.size()-1));
            Joueur victime = victimesPossibles.get(proba);
            if (!Arrays.asList(idVictimesMultiples).contains(victime)) {
                idVictimesMultiples[i] = Partie.getInstance().joueursActifs.indexOf(victime);
            }
        }
        return idVictimesMultiples;

    }

    @Override
    public int choisirJourNuitNeant(Joueur joueur) {
        int pointNeant = joueur.getPointNeant();
        int pointNuit = joueur.getPointNuit();
        int pointJour = joueur.getPointJour();
        int decision = 0;
        if (pointJour > pointNeant && pointJour > pointNuit) {
            decision = 0;
        }
        else if (pointNeant > pointJour && pointNeant > pointNuit) {
            decision = 2;
        }
        else if (pointNuit > pointJour && pointNuit > pointNeant) {
            decision = 1;
        }
        return decision;
    }

    @Override
    public int choisirCapaciteSpeciale(Joueur joueur) {
        return 1;
    }

    @Override
    public int choisirUtiliserCapaciteDivine(Joueur joueur) {
        return 1;
    }

    @Override
    public int choisirUtiliserCapaciteDivineOuCarteSansOrigine(Joueur joueur) {
        return 1;
    }

    @Override
    public int choisirGuiderCroyantSupplementaire(ArrayList<CarteAction> cartesPossibles, GuideSpirituel guideSpirituel) {
        int decision = 0;
        if (!cartesPossibles.isEmpty()) {
            Iterator<CarteAction> carteActionIterator = cartesPossibles.iterator();
            while (carteActionIterator.hasNext()) {
                Croyant croyant = (Croyant) carteActionIterator.next();
                if (croyant.listeDogmes.contains(guideSpirituel.listeDogmes.get(0)) || croyant.listeDogmes.contains(guideSpirituel.listeDogmes.get(1))) {
                    if (!croyant.getNouveauCroyant()) {
                        decision = 1;
                    }
                    else {
                        decision = 0;
                    }
                }
                else {
                    decision = 0;
                }
            }
        }
        else {
            decision = 0;
        }
        return decision;
    }

    @Override
    public int choisirIndexCroyantGuider(ArrayList<CarteAction> cartesPossibles, GuideSpirituel guideSpirituel) {
        return 0;
    }

    @Override
    public int choisirBinaire() {
        int i = (int) Math.floor(Math.random()*7);
        if (i > 2) {
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public int[] choisirCartesADefausser() {
        int size = joueur.getMainJoueur().getListeCartes().size();
        int idCartesADefausser[] = new int[size];
        int nbCartesADefausser = 0;
        ArrayList<Origine> originesPossibles = new ArrayList<Origine>();
        Iterator<CarteAction> carteActionIterator = joueur.getMainJoueur().getListeCartes().iterator();
        switch (joueur.getDivinite().getOrigine()) {
            case Aube:
                originesPossibles.add(Origine.Neant);
                originesPossibles.add(Origine.Jour);
                break;
            case Jour:
                originesPossibles.add(Origine.Jour);
                break;
            case Crepuscule:
                originesPossibles.add(Origine.Neant);
                originesPossibles.add(Origine.Nuit);
                break;
            default:
                originesPossibles.add(Origine.Nuit);
                break;
        }
        while (carteActionIterator.hasNext()) {
            CarteAction carteAction = carteActionIterator.next();
            if (!originesPossibles.contains(carteAction.getOrigine())) {
                idCartesADefausser[nbCartesADefausser] = joueur.getMainJoueur().getListeCartes().indexOf(carteAction);
                nbCartesADefausser++;
            }
        }
        return idCartesADefausser;
    }
}
