package model.components;

import com.sun.tools.javac.util.ArrayUtils;
import model.cards.*;
import model.components.joueur.Joueur;
import model.components.plateau.Plateau;
import model.kernel.Partie;
import tools.Strategy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Stratégie Aléatoire
 */
public class StrategyRandom implements Strategy {

    private Joueur joueur;

    public StrategyRandom(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public int choisirPoserCartePlateau() {
        return this.choisirBinaire();
    }

    @Override
    public int choisirBinaire() {
//        return (int) Math.floor(Math.random()*2);
    return 1;
    }

    @Override
    public int[] choisirCartesADefausser() {
//        Iterator<CarteAction> carteActionIterator = joueur.getMainJoueur().getListeCartes().iterator();
//        ArrayList<Origine> listeOriginePossible = new ArrayList<Origine>();
//        switch (joueur.getOrigine()) {
//            case Jour:
//                listeOriginePossible.add(Origine.Jour);
//                break;
//            case Nuit:
//                listeOriginePossible.add(Origine.Nuit);
//                break;
//            case Aube:
//                listeOriginePossible.add(Origine.Jour);
//                listeOriginePossible.add(Origine.Neant);
//                break;
//            case Crepuscule:
//                listeOriginePossible.add(Origine.Nuit);
//                listeOriginePossible.add(Origine.Neant);
//                break;
//            default:
//                break;
//        }
        int tableauCartesADefausser[] = new int[7];
        int nbCarteADefausser = 0;
//        while (carteActionIterator.hasNext()) {
//            CarteAction carteAction = carteActionIterator.next();
//            if (!listeOriginePossible.contains(carteAction.getOrigine())) {
//                tableauCartesADefausser[nbCarteADefausser] = joueur.getMainJoueur().getListeCartes().indexOf(carteAction);
//                nbCarteADefausser++;
//            }
//            else {
                int random = (int) Math.floor(Math.random()*7);
//                tableauCartesADefausser[0] = random;
//            }
//        }
        while (tableauCartesADefausser.length < random) {
            int carte = this.choisirIndexCarteAPoser();
            if (!Arrays.asList(tableauCartesADefausser).contains(carte)) {
                tableauCartesADefausser[nbCarteADefausser] = carte;
            }
        }
        return tableauCartesADefausser;
    }

    @Override
    public int choisirIndexCarteAPoser() {
        int i = (int) Math.floor(Math.random()*(joueur.getMainJoueur().getListeCartes().size()-1));
        CarteAction carteAction = joueur.getMainJoueur().choisirCarte(i);
        boolean peutPoser = false;
        boolean nouveauChoix = false;
        int nbJoueurSansCartes = 0;
        Iterator<Joueur> joueurIterator = Partie.getInstance().joueursActifs.iterator();
        while (joueurIterator.hasNext()) {
            Joueur joueurSuivant = joueurIterator.next();
            if (joueurSuivant.getCartesPlateauPerso().getListeCartePlateau().isEmpty()) {
                nbJoueurSansCartes ++;
            }
        }
        while (!peutPoser) {
            if (Plateau.getInstance().getListeCartePlateau().isEmpty() && (carteAction instanceof GuideSpirituel)) {
                nouveauChoix = true;
            }
            else if (!(Plateau.getInstance().getListeCartePlateau().isEmpty() && (carteAction instanceof GuideSpirituel))){
                peutPoser = true;
            }
            if ((nbJoueurSansCartes == Partie.getInstance().joueursActifs.size() - 1) && (carteAction instanceof Apocalypse)) {
                nouveauChoix = true;
            }
            else if (!((nbJoueurSansCartes == Partie.getInstance().joueursActifs.size() - 1) && (carteAction instanceof Apocalypse))) {
                peutPoser = true;
            }
            if (nouveauChoix) {
                i = (int) Math.floor(Math.random()*(joueur.getMainJoueur().getListeCartes().size() - 1));
                carteAction = joueur.getMainJoueur().choisirCarte(i);
            }
        }
        return i;
    }

    @Override
    public int choisirSacrifier() {
        int decision = 0;
        if (!joueur.getCartesPlateauPerso().getListeCartePlateau().isEmpty()) {
            int i = (int) Math.floor(Math.random()*2);
            if (i == 0) {
                decision = 0;
            }
            if (i == 1) {
                decision = 1;
            }
        }
        return decision;
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
        if (possedeCroyant) {
            return 1;
        }
        else if (possedeGuide || possedeCroyant) {
            return ((int) Math.floor(Math.random()*2));
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
        int nombreDeCroyant = croyantsGuides.size();
        return ((int) Math.floor(Math.random()*nombreDeCroyant));
    }

    @Override
    public int choisirVictime() {
        ArrayList<Joueur> joueursActifs = Partie.getInstance().joueursActifs;
        int idVictime = joueursActifs.indexOf(joueur);
        while (idVictime == joueursActifs.indexOf(joueur)) {
            idVictime = (int) Math.floor(Math.random()*(joueursActifs.size()-1));
        }
        return idVictime;
    }

    @Override
    public int[] choisirVictimesMultiples() {
        int size = (int) Math.floor(Math.random()*(Partie.getInstance().joueursActifs.size()-1));
        int joueursActifs[] = new int[size];
        for (int i = 0; i < size; i++) {
            int random = (int) Math.floor(Math.random()*(Partie.getInstance().joueursActifs.size()-1));
            int id = Partie.getInstance().joueursActifs.get(random).getId();
            if (Arrays.asList(joueursActifs).contains(id))
            joueursActifs[i] = Partie.getInstance().joueursActifs.get(random).getId();
        }
        return joueursActifs;
    }

    @Override
    public int choisirIndexCroyantVictime(Joueur joueurVictime) {
        Iterator<CarteAction> carteActionIterator = joueurVictime.getCartesPlateauPerso().getListeCartePlateau().iterator();
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
    public int choisirJourNuitNeant(Joueur joueur) {
        int decision = 0;
        switch (joueur.getDivinite().getOrigine()) {
            default:
                decision = 2;
                break;
            case Jour:
            decision = 0;
            break;
            case Nuit:
            decision = 1;
            break;
        }
        return decision;
    }

    @Override
    public int choisirUtiliserCapaciteDivine(Joueur joueur) {
        return this.choisirBinaire();
    }

    @Override
    public int choisirUtiliserCapaciteDivineOuCarteSansOrigine(Joueur joueur) {
        return (int) Math.floor(Math.random()*2);
    }

    @Override
    public int choisirGuiderCroyantSupplementaire(ArrayList<CarteAction> cartesPlateau, GuideSpirituel guideSpirituel) {
        int decision = 0;
        if (!cartesPlateau.isEmpty()) {
            Iterator<CarteAction> carteActionIterator = cartesPlateau.iterator();
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
    public int choisirIndexCroyantGuider(ArrayList<CarteAction> cartesPlateau, GuideSpirituel guideSpirituel) {
        int decision = 0;
        ArrayList<Integer> indexCroyantsPossibles = new ArrayList<Integer>();
        if (!cartesPlateau.isEmpty()) {
            Iterator<CarteAction> carteActionIterator = cartesPlateau.iterator();
            while (carteActionIterator.hasNext()) {
                Croyant croyant = (Croyant) carteActionIterator.next();
                if ((croyant.listeDogmes.contains(guideSpirituel.listeDogmes.get(0)) || croyant.listeDogmes.contains(guideSpirituel.listeDogmes.get(1))) && !croyant.getNouveauCroyant()) {
                        indexCroyantsPossibles.add(cartesPlateau.indexOf(croyant));
                }
            }
            int i = indexCroyantsPossibles.size() - 1;
            int idCroyant = (int) Math.floor(Math.random()*i);
            decision = indexCroyantsPossibles.get(idCroyant);
        }
        return decision;
    }

    @Override
    public int choisirCapaciteSpeciale(Joueur joueur) {
        return this.choisirBinaire();
    }

}
