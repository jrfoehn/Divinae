package model.cards;

import model.components.joueur.Joueur;
import model.components.Origine;
import model.components.plateau.Pioche;
import model.kernel.Partie;

import java.util.Iterator;

/**
 * Classe contenant l'ensemble des cartes Apocalypse
 */
public class Apocalypse extends CarteAction {

    private TypeCarte typeCarte = TypeCarte.Apocalypse;

    /**
     * Constructeur carte Apocalypse
     * @param origine Origine
     */
    public Apocalypse(Origine origine) {
        super("Apocalypse", origine,TypeCarte.Apocalypse,9999,9999);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Méthode utilisée dans l'instanciation des cartes. Ajoute une carte Apocalypse à la pioche
     * @param origine Origine
     */
    public static void creerApocalypse(Origine origine) {
        Apocalypse apocalypse = new Apocalypse(origine);
        Pioche.getInstance().addCarte(apocalypse);
    }

    /**
     * Méthode permettant d'instancier l'ensemble des cartes apocalypse
     */
    public static void initialiserApocalypse() {
        creerApocalypse(Origine.Jour);
        creerApocalypse(Origine.Nuit);
        creerApocalypse(Origine.Neant);
        creerApocalypse(Origine.Aucune);
        creerApocalypse(Origine.Aucune);
    }

    /**
     * Méthode permettant d'activer l'effet d'une carte Apocalypse
     */
    public static void utiliserApocalypse() {
        Iterator<Joueur> j = Partie.getInstance().joueursActifs.iterator();
        int nbCroyant;
        while (j.hasNext()) {
            Joueur joueur = j.next();
            int ptPriere = joueur.getPointPriere();
            Iterator<CarteAction> ca = joueur.getCartesPlateauPerso().getListeCartePlateau().iterator();
            while (ca.hasNext()) {
                CarteAction carteAction = ca.next();
                if (carteAction instanceof Croyant) {
                    nbCroyant = ((Croyant) carteAction).getNbCroyant();
                    joueur.setPointPriere(ptPriere + nbCroyant);
                }
                else if (carteAction instanceof GuideSpirituel) {
                    Iterator<Croyant> croyantIterator = ((GuideSpirituel) carteAction).croyantGuider.iterator();
                    while (croyantIterator.hasNext()) {
                        Croyant croyant = croyantIterator.next();
                        nbCroyant = ((Croyant) carteAction).getNbCroyant();
                        joueur.setPointPriere(ptPriere + nbCroyant);
                    }
                }
            }
        }
        boolean exaequoFort = false;
        boolean exaequoFaible = false;
        Joueur plusFortJoueur = Partie.getInstance().joueursActifs.get(0);
        Joueur plusFaibleJoueur = plusFortJoueur;
        int maxPointPriere = Partie.getInstance().joueursActifs.get(0).getPointPriere();
        int minPointPriere = maxPointPriere;
        while (j.hasNext()) {
            Joueur joueur = j.next();
            if (joueur.getPointPriere() > maxPointPriere) {
                maxPointPriere = joueur.getPointPriere();
                plusFortJoueur = joueur;
            }
            else if (joueur.getPointPriere() == maxPointPriere) {
                exaequoFort = true;
            }
            else if (joueur.getPointPriere() < minPointPriere) {
                minPointPriere = joueur.getPointPriere();
                plusFaibleJoueur = joueur;
            }
            else if (joueur.getPointPriere() == minPointPriere) {
                exaequoFaible = true;
            }
        }
        if ((Partie.getInstance().joueursActifs.size() >= 4) && (exaequoFaible == false)) {
            Partie.getInstance().joueursElimines.add(plusFaibleJoueur);
            Partie.getInstance().joueursActifs.remove(plusFortJoueur);
        }
        else if (Partie.getInstance().joueursActifs.size() < 4) {
            Partie.getInstance().vainqueur = plusFortJoueur;
            if (exaequoFort == false) {
                System.out.println("Le vainqueur est : " + Partie.getInstance().vainqueur);
            }
            else {
                System.out.println("Le vainqueurs sont : " + Partie.getInstance().vainqueur + " et un autre joueur.");
            }
            Partie.getInstance().setFin(true);
        }
    }

}
