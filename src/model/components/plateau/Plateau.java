package model.components.plateau;

import controller.DisplayCLIController;
import model.cards.CarteAction;
import model.cards.Croyant;
import model.cards.DeusEx;
import model.cards.GuideSpirituel;
import model.components.joueur.Joueur;
import model.kernel.Partie;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Plateau central. C'est ici que les Croyants sont posés
 */
public class Plateau {

    protected ArrayList<CarteAction> listeCartePlateau;
    private static Plateau instance;

    public static Plateau getInstance() {
        if (instance == null) {
            instance = new Plateau();
        }
        return instance;
    }

    public Plateau() {
        this.listeCartePlateau = new ArrayList<CarteAction>();
    }

    public static void poserCarte(Joueur joueur) {
        System.out.println("Quel est l'index de la carte que vous voulez poser ?");
        ArrayList<CarteAction> carteDuJoueur = joueur.getMainJoueur().getListeCartes();
        DisplayCLIController.showCarte(carteDuJoueur);
        int s = joueur.choisirIndexCarteAPoser();
        boolean peutPoser = false;
        CarteAction carteAPoser = joueur.getMainJoueur().choisirCarte(s);
        switch (carteAPoser.origine){
            case Jour:
                if (joueur.getPointJour() > 0) {
                    peutPoser = true;
                    joueur.setPointJour(joueur.getPointJour() - 1);
                }
                else {
                    System.out.println("Vous n'avez pas assez de points.");
                }
                break;
            case Nuit:
                if (joueur.getPointNuit() > 0) {
                    peutPoser = true;
                    joueur.setPointNuit(joueur.getPointNuit() - 1);
                }
                else {
                    System.out.println("Vous n'avez pas assez de points.");
                }
                break;
            case Neant:
                System.out.println("Pour poser la carte en échange de : [1 point néant] taper 0, [2 points jour] taper 1, [2 points nuit] taper 2");
                int type = joueur.choisirJourNuitNeant();
                switch (type){
                    case 0 :
                        if (joueur.getPointNeant() > 0) {
                            peutPoser = true;
                            joueur.setPointNeant(joueur.getPointNeant() - 1);
                        }
                        else {
                            System.out.println("Vous n'avez pas assez de points.");
                        }
                        break;
                    case 1 :
                        if (joueur.getPointJour() > 1) {
                            peutPoser = true;
                            joueur.setPointJour(joueur.getPointJour() - 2);
                        }
                        else {
                            System.out.println("Vous n'avez pas assez de points.");
                        }
                        break;
                    case 2 :
                        if (joueur.getPointNuit() > 1) {
                            peutPoser = true;
                            joueur.setPointNuit(joueur.getPointNuit() - 2);
                        }
                        else {
                            System.out.println("Vous n'avez pas assez de points.");
                        }
                        break;
                }
        }
        DisplayCLIController.afficherPoints(joueur);
        joueur.getMainJoueur().getListeCartes();
        if (peutPoser == true) {
            if (carteAPoser instanceof Croyant) {
                Plateau.getInstance().listeCartePlateau.add(carteAPoser);
            }
            else if (carteAPoser instanceof GuideSpirituel) {
                System.out.println("Voulez-vous guider des croyants avec ce guide ? Oui (1) Non (0)");
                //TODO : refaire avec véritable méthode de choix de guidage ?
                int guiderCroyant = joueur.choisirBinaire();
                if (guiderCroyant == 1) {
                    joueur.guiderCroyant((GuideSpirituel) carteAPoser);
                }
            }
            else if (carteAPoser instanceof DeusEx) {
                Defausse.getInstance().defausser(carteAPoser);
            }
            joueur.getMainJoueur().listeCartes.remove(carteAPoser);
        }
    }

    public ArrayList<CarteAction> getListeCartePlateau() {
        return listeCartePlateau;
    }

    public void setListeCartePlateau(ArrayList<CarteAction> listeCartePlateau) {
        this.listeCartePlateau = listeCartePlateau;
    }

    /*Fonction permettant d'afficher les cartes des autres joueurs*/
    public static void afficherCartePlateauPersoAdversaires() {
        Iterator<Joueur> joueurIterator = Partie.getInstance().joueursActifs.iterator();
        while (joueurIterator.hasNext()) {
            Joueur joueur = joueurIterator.next();
            System.out.println("Joueur " + joueur.getId());
            joueur.afficherCartePlateauPerso();
        }
    }

}
