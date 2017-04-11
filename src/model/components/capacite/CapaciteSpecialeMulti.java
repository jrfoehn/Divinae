package model.components.capacite;

import controller.DisplayCLIController;
import model.cards.*;
import model.components.plateau.Defausse;
import model.components.Dogme;
import model.components.Origine;
import model.components.plateau.Plateau;
import model.components.joueur.Joueur;
import model.kernel.Partie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Contient l'ensemble des capacité spéciales utilisées sur plusieurs joeurs.
 */
public class CapaciteSpecialeMulti extends CapaciteSpeciale {

    /**
     * Constructeur Capacité Spéciale Multi utilisées sur plusieurs joueurs ou cartes.
     * @param joueurAttaque Joueur
     * @param joueurVictime Joueur
     * @param carteAttaque CarteAction
     * @param carteVictime CarteAction
     */
    public static void capaciteSpecialeMulti(Joueur joueurAttaque, Joueur joueurVictime, CarteAction carteAttaque, CarteAction carteVictime) {
        switch (carteAttaque.getIdCapaciteSpeciale()) {
            case 19:
                capaciteDefausserGuideNeantNuitJour(joueurAttaque, carteAttaque);
                break;
            case 20:
                capaciteProtegerGuide(joueurAttaque);
                break;
            case 21:
                capaciteUtiliserCapaciteSansDefausse(joueurAttaque, carteAttaque);
                break;
            case 22:
                capaciteDeusExAnnulerCapaciteSpeciale(joueurAttaque, carteAttaque, carteVictime);
                break;
            case 23:
                capaciteDeusExBeneficerCapaciteSpeciale(joueurAttaque, joueurVictime, carteAttaque);
                break;
            case 24:
                //TODO
                break;
            case 25:
                capaciteDeusExSacrificeGuideEnFonctionDuDe(joueurAttaque);
                break;
            case 26:
                capaciteDiviniteEmpecherApocalypse(joueurAttaque, joueurVictime, carteAttaque);
                break;
            case 27:
                capaciteDiviniteEmpecherSacrificeGuide(joueurAttaque, carteAttaque);
                break;
            case 28:
                capaciteDiviniteDetruireCroyants(joueurAttaque);
                break;
            case 29:
                capaciteDiviniteForcerPoseApocalypse(joueurAttaque);
                break;
            case 30:
                capaciteDiviniteAjouterPointNeantEgalNombreGuide(joueurAttaque);
                break;
            case 31:
                capaciteDiviniteImposerSacrificeGuideDogmeSymboleNature(joueurAttaque);
                break;
            case 32:
                capaciteDiviniteVolerPointAction(joueurAttaque);
                break;
            default:
                break;
        }
    }

    /*Fonction pour défausser un guide d'origine Néant ou Nuit / Jour
*   358: Nuit (def)
*   359: Jour
* idCapacité 19
* */

    /**
     * Méthode permettant de défausser une carte Guide Spirituel d'orginie Néant, Nuit ou Jour.
     * @param joueur Joueur
     * @param carteAction CarteAction
     */
    public static void capaciteDefausserGuideNeantNuitJour(Joueur joueur, CarteAction carteAction) {
        Scanner scanner = new Scanner(System.in);
        Origine origine;
        System.out.println("Cartes des joueurs : ");
        Plateau.afficherCartePlateauPersoAdversaires();
        int idJoueur = joueur.choixVictime();
        Joueur victime = Partie.getInstance().joueursActifs.get(idJoueur);
        switch (carteAction.getIdCarte()) {
            default:
                origine = Origine.Nuit;
                break;
            case 359:
                origine = Origine.Jour;
                break;
        }
        Iterator<CarteAction> carteActionIterator = victime.getCartesPlateauPerso().getListeCartePlateau().iterator();
        ArrayList<GuideSpirituel> guidesPossibles = new ArrayList<GuideSpirituel>();
        while (carteActionIterator.hasNext()) {
            GuideSpirituel guideSpirituel = (GuideSpirituel) carteActionIterator.next();
            if (guideSpirituel.listeDogmes.contains(Origine.Neant) || guideSpirituel.listeDogmes.contains(origine)) {
                guidesPossibles.add(guideSpirituel);
            }
        }
        System.out.println("Choisissez l'indice du guide en question : ");
        DisplayCLIController.showGuide(guidesPossibles);
        int idGuide = scanner.nextInt();
        GuideSpirituel guideVictime = (GuideSpirituel) victime.getCartesPlateauPerso().getListeCartePlateau().get(idGuide);
        ArrayList<Croyant> croyantAuCentre  = guideVictime.croyantGuider;
        Plateau.getInstance().getListeCartePlateau().addAll(croyantAuCentre);
        victime.getCartesPlateauPerso().getListeCartePlateau().removeAll(croyantAuCentre);
        victime.getCartesPlateauPerso().getListeCartePlateau().remove(guideVictime);
        Defausse.getInstance().defausser(guideVictime);
    }

    /*Fonction permettant de proteger un guide spirituel et les croyants qui lui sont rattachés
    * idCapacité 20
    * */

    /**
     * Méthode permettant de proteger une carte Guide Spirituel ainsi que les cartes Croyant qui lui sont rattachées.
     * @param joueur Joueur
     */
    public static void capaciteProtegerGuide(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Joueur " + joueur.getId() + " quel est le guide que vous voulez protéger ?");
        DisplayCLIController.showCarte(joueur.getCartesPlateauPerso().getListeCartePlateau());
        int idGuide = scanner.nextInt();
        if (joueur.getCartesPlateauPerso().getListeCartePlateau().get(idGuide).getTypeCarte() != TypeCarte.GuideSpirituel) {
            System.out.println("Joueur " + joueur.getId() + " quel est le GUIDE que vous voulez protéger ?");
            DisplayCLIController.showCarte(joueur.getCartesPlateauPerso().getListeCartePlateau());
        }
        else {
            GuideSpirituel guideSpirituel = (GuideSpirituel) joueur.getCartesPlateauPerso().getListeCartePlateau().get(idGuide);
            Iterator<Croyant> croyantIterator = guideSpirituel.croyantGuider.iterator();
            while (croyantIterator.hasNext()) {
                Croyant croyant = croyantIterator.next();
                croyant.setProteger(true);
            }
            guideSpirituel.setProteger(true);
        }
    }

    /*Fonction permettant d'utiliser la capacité spéciale d'un guide ou d'un croyant sans avoir à le défausser
    * idCapacité 21
    * */

    /**
     * Méthode permettant d'utiliser la capacité spéciale d'une carte Guide Spirituel ou Croyant sans avoir à la défausser.
     * @param joueur Joueur
     * @param carteAction CarteAction
     */
    public static void capaciteUtiliserCapaciteSansDefausse(Joueur joueur, CarteAction carteAction) {
        carteAction.setDefausser(false);
        joueur.sacrifier();
        carteAction.setDefausser(true);
    }

    /*Fonction permettant d'annuler une capacité spéciale
    *   310, 311, 312,
    *   313, 314 (def)
    * idCapacité 22
    * */

    /**
     * Méthode permettant d'annuler l'effet d'une Capacité Spéciale.
     * @param joueur Joueur
     * @param carteAttaque CarteAction
     * @param carteVictime CarteAction
     */
    public static void capaciteDeusExAnnulerCapaciteSpeciale(Joueur joueur,CarteAction carteAttaque, CarteAction carteVictime) {
        Origine origine1;
        Origine origine2;
        boolean valide = false;
        switch (carteAttaque.getIdCarte()) {
            default:
                valide = true;
                break;
            case 310:
                origine1 = Origine.Neant;
                origine2 = Origine.Nuit;
                if (carteVictime.listeDogmes.contains(origine1) || carteVictime.listeDogmes.contains(origine2)) {
                    valide = true;
                }
                break;
            case 311:
                origine1 = Origine.Neant;
                origine2 = Origine.Jour;
                if (carteVictime.listeDogmes.contains(origine1) || carteVictime.listeDogmes.contains(origine2)) {
                    valide = true;
                }
                break;
            case 312:
                origine1 = Origine.Jour;
                origine2 = Origine.Nuit;
                if (carteVictime.listeDogmes.contains(origine1) || carteVictime.listeDogmes.contains(origine2)) {
                    valide = true;
                }
                break;
        }
        if (valide) {
            carteVictime.setCapaciteUtilisee(false);
            System.out.println("La capacité spéciale de la carte " + carteVictime + " est annulée. La carte est défaussée.");
        }
        else {
            System.out.println("La carte du joueur " + joueur.getId() + " n'est pas valide. La capacité spéciale de la carte " + carteVictime + " prend effet. L'autre carte est défaussée.");
        }
    }
    /*Fonction permettant de récupérer les effets bénéfiques d'une carte action posée par un autre joueur. Pour un guide ou un croyant,
    * la carte est ajoutée dans la main de l'attaquant
    * idCapacité 23
    * */

    /**
     * Méthode permettant d'utiliser la capacité spéciale d'une carte action posée par un autre joueur. S'il s'agit d'une carte Guide Spirituel ou Croyant, la carte est ajoutée dans la main de l'attaquant.
     * @param attaquant Joueur
     * @param victime Joueur
     * @param carteAction CarteAction
     */
    public static void capaciteDeusExBeneficerCapaciteSpeciale(Joueur attaquant, Joueur victime, CarteAction carteAction) {
        System.out.println("La capacité spéciale de la carte qui vient d'être posée est utilisée à votre avantage.");
        capaciteSpeciale(attaquant, carteAction);
        carteAction.setCapaciteUtilisee(false);
        carteAction.setDefausser(false);
        if (carteAction instanceof Croyant || carteAction instanceof GuideSpirituel) {
            attaquant.getMainJoueur().getListeCartes().add(carteAction);
        }
    }

    /*Fonction permettant de sacrifier un guide adversaire ou un des siens en fonciton du dé de cosmogonie.
    * idCapacité 25
    * */

    /**
     * Méthode permettant de sacrifier une carte Guide Spirituel (de l'attaquant ou des adversaires), en fonction du dé de cosmogonie.
     * @param joueur Joueur
     */
    public static void capaciteDeusExSacrificeGuideEnFonctionDuDe(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        int idVictime = joueur.choixVictime();
        Joueur victime = Partie.getInstance().joueursActifs.get(idVictime);
        System.out.println("\n Voici les cartes du joueur " + victime.getId() + ". Choisissez un de ses guides.");
        victime.afficherCartePlateauPerso();
        int idGuideVictime = scanner.nextInt();
        GuideSpirituel guideVictime = (GuideSpirituel) victime.getCartesPlateauPerso().getListeCartePlateau().get(idGuideVictime);
        System.out.println("\nVoici vos cartes. Choisissez votre guide.");
        joueur.afficherCartePlateauPerso();
        int idGuide = scanner.nextInt();
        GuideSpirituel guideSpirituel  = (GuideSpirituel) joueur.getCartesPlateauPerso().getListeCartePlateau().get(idGuide);
        System.out.println("\nSi le dé de cosmogonie tombe sur jour, le guide adverse est défaussé. S'il tombre sur nuit, le votre est défaussé. Sur néant, rien ne se passe.");
        int faceDe = (int)Math.floor(Math.random()*3);
        while (!scanner.hasNextLine()){
            // on attend l'appuie sur la touche entrée
        }
        GuideSpirituel guideChoisi;
        Joueur joueurChoisi;
        switch (faceDe) {
            default:
                System.out.println("Face néant. Rien ne se passe");
                break;
            case 1:
                System.out.println("Face nuit. Le guide du joueur " + joueur.getId() + "est défaussé. La capacité spéciale est activée.");
                guideChoisi = guideSpirituel;
                joueurChoisi = joueur;
                Plateau.getInstance().getListeCartePlateau().addAll(guideChoisi.croyantGuider);
                capaciteSpeciale(joueur, guideChoisi);
                Defausse.getInstance().getListeCartes().add(guideChoisi);
                guideChoisi.croyantGuider.clear();
                joueurChoisi.getCartesPlateauPerso().getListeCartePlateau().remove(guideChoisi);
                break;
            case 2:
                System.out.println("Face nuit. Le guide du joueur " + victime.getId() + "est défaussé. La capacité spéciale est activée.");
                guideChoisi = guideVictime;
                joueurChoisi = victime;
                Plateau.getInstance().getListeCartePlateau().addAll(guideChoisi.croyantGuider);
                capaciteSpeciale(joueur, guideChoisi);
                Defausse.getInstance().getListeCartes().add(guideChoisi);
                guideChoisi.croyantGuider.clear();
                joueurChoisi.getCartesPlateauPerso().getListeCartePlateau().remove(guideChoisi);
                break;
        }
    }

    /*Fonction permettant d'empecher la pose d'une carte apocalypse
    * idCapacité 26
    * */

    /**
     * Méthode permettant d'empêcher la pose d'une carte Apocalypse.
     * @param joueur Joueur
     * @param victime Joueur
     * @param apocalypse CarteAction
     */
    public static void capaciteDiviniteEmpecherApocalypse(Joueur joueur, Joueur victime, CarteAction apocalypse) {
        if (joueur.getDivinite().getCapaciteUtilisable()) {
            System.out.println("\n Joueur " + joueur.getId() + " vous ne pouvez pas utilisé la capacité spéciale de votre Divinité.");
        }
        else if (!(apocalypse instanceof Apocalypse)) {
            System.out.println("Joueur " + joueur.getId() + ", la capacité de votre Divinité ne peut s'exercer que sur une carte apocalypse, ce qui n'est pas le cas ici.");
        }
        else {
            System.out.println("L'apocalypse a été déjouée. La carte est défaussée.");
            apocalypse.setCapaciteUtilisee(false);
            Defausse.getInstance().defausser(apocalypse);
            joueur.getDivinite().setCapaciteUtilisee(true);
            victime.getMainJoueur().getListeCartes().remove(apocalypse);
        }
    }

    /*Fonction permettant d'empecher le sacrifice d'un guide
    * idCapacité 27
    * */

    /**
     * Méthode permettant d'empêcher le sacrifice d'une carte Guide Spirituel.
     * @param joueur Joueur
     * @param carteAction CarteAction
     */
    public static void capaciteDiviniteEmpecherSacrificeGuide(Joueur joueur, CarteAction carteAction) {
        joueur.getDivinite().setCapaciteUtilisee(false);
        if (!(carteAction instanceof GuideSpirituel)) {
            System.out.println("Joueur " + joueur.getId() + ", la capacité spéciale de votre divinité ne peut s'appliquer qu'aux guides spirituels.");
        }
        else {
            System.out.println("Le guide n'est pas sacrifié.");
            carteAction.setDefausser(false);
        }
    }

    /*Fonction permettant de détruire les croyants d'une divinité
    *   403 (def) Neant
    *   406 Jour
    * idCapacité 28
    * */

    /**
     * Méthode permettant de défausser les cartes Croyants rattachés à une divinité.
     * @param joueur Joueur
     */
    public static void capaciteDiviniteDetruireCroyants(Joueur joueur) {
        joueur.getDivinite().setCapaciteUtilisee(true);
        ArrayList<Croyant> croyants = new ArrayList<Croyant>();
        Iterator<CarteAction> croyantIterator = Plateau.getInstance().getListeCartePlateau().iterator();
        while (croyantIterator.hasNext()) {
            Croyant croyant = (Croyant) croyantIterator.next();
            croyants.add(croyant);
        }
        if (!croyants.isEmpty()) {
            Plateau.getInstance().getListeCartePlateau().remove(croyants);
            Iterator<Croyant> croyantADefausser = croyants.iterator();
            while (croyantADefausser.hasNext()) {
                Croyant croyant = croyantADefausser.next();
                Defausse.getInstance().defausser(croyant);
            }
            System.out.println("Les croyants ont été supprimés");
        }
        else {
            System.out.println("Il n'y a pas de croyants de cette origine. Vous ne pouvez plus utiliser de capacité spéciale.");
        }
        joueur.afficherCartes(Plateau.getInstance().getListeCartePlateau());
    }

    /*Fonction obligeant un joueur a posé une carte apocalypse s'il en a une.
    * idCapcaité 29
    * */

    /**
     * Méthode qui force la pose d'une carte Apocalypse si le joueur en possède une.
     * @param joueur Joueur
     */
    public static void capaciteDiviniteForcerPoseApocalypse(Joueur joueur) {
        joueur.getDivinite().setCapaciteUtilisee(true);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nVoici les joueurs actifs. Choisissez en un.");
        Partie.getInstance().afficherAdversaires(Partie.getInstance().joueursActifs);
        int idVictime = scanner.nextInt();
        Joueur victime = Partie.getInstance().joueursActifs.get(idVictime);
        boolean hasApocalypse = false;
        CarteAction apocalypse = victime.getMainJoueur().getListeCartes().get(0);
        Iterator<CarteAction> carteActionIterator = victime.getMainJoueur().getListeCartes().iterator();
        while (carteActionIterator.hasNext()) {
            CarteAction carteAction = carteActionIterator.next();
            if (carteAction instanceof Apocalypse) {
                apocalypse = carteAction;
                hasApocalypse = true;
            }
        }
        if (hasApocalypse) {
            Apocalypse.utiliserApocalypse();
            victime.getMainJoueur().getListeCartes().remove(apocalypse);
            Defausse.getInstance().defausser(apocalypse);
        }
        else {
            System.out.println("Le joueur ne possède pas de carte apocalypse.");
        }
    }

    /*Fonction permettant a une divinité de récupérer autant de points néant qu'elle n'a de guides
    * idCapacité 30
    * */

    /**
     * Méthode permettant à un joueur de récupérer autant de points néant qu'il n'a de cartes Guide Spirituel posées.
     * @param joueur Joueur
     */
    public static void capaciteDiviniteAjouterPointNeantEgalNombreGuide (Joueur joueur) {
        joueur.getDivinite().setCapaciteUtilisee(true);
        Iterator <CarteAction > carteActionIterator = joueur.getCartesPlateauPerso().getListeCartePlateau().iterator();
        int nbGuide = 0;
        while (carteActionIterator.hasNext()) {
            CarteAction carteAction = carteActionIterator.next();
            if (carteAction instanceof GuideSpirituel) {
                nbGuide++;
            }
        }
        System.out.println("Joueur " + joueur.getId() + ", vous avez " + nbGuide + " Guides Spirituels posés. Vous gagnez donc " +nbGuide+ "points d'actions de type Néant.");
        joueur.setPointNeant(joueur.getPointNeant() + nbGuide);
    }

    /*Fonction permettant d'imposer le sacrifice et la défausse d'un guide possédant les dogmes symbole ou nature d'un adversaire
    * idCapacité 31
    * */

    /**
     * Méthode permettant d'imposer le sacrifice et la défausse d'une carte Guide Spirituel ne possédant pas les dogmes symbole ou nature
     * @param joueur Joueur
     */
    public static void capaciteDiviniteImposerSacrificeGuideDogmeSymboleNature (Joueur joueur) {
        joueur.getDivinite().setCapaciteUtilisee(true);
        System.out.println("\nLes cartes de vos adversaires : ");
        Plateau.afficherCartePlateauPersoAdversaires();
        int idVictime = joueur.choixVictime();
        Joueur victime = Partie.getInstance().joueursActifs.get(idVictime);
        boolean hasGuideVictime = false;
        GuideSpirituel guideVictime = (GuideSpirituel) victime.getCartesPlateauPerso().getListeCartePlateau().get(0);
        Iterator<CarteAction> carteActionIterator = victime.getCartesPlateauPerso().getListeCartePlateau().iterator();
        while (carteActionIterator.hasNext()) {
            GuideSpirituel guideSpirituel = (GuideSpirituel) carteActionIterator.next();
            if (guideSpirituel.listeDogmes.contains(Dogme.Nature) || guideSpirituel.listeDogmes.contains(Dogme.Symbole)) {
                hasGuideVictime = true;
                guideVictime = guideSpirituel;
            }
        }
        if (hasGuideVictime) {
            System.out.println("\nLe guide " + guideVictime + " est sacrifié par le joueur " + victime.getId() + ". Sa capacité spéciale est executée.");
            capaciteSpeciale(victime, guideVictime);
            victime.getCartesPlateauPerso().getListeCartePlateau().remove(guideVictime);
            Defausse.getInstance().defausser(guideVictime);
        }
        else {
            System.out.println("Ce joueur ne possède pas de guides de dogmes Nature ou Symbole.");
        }
    }

    /*Fonction permettant de récupérer les points d'actions d'une autre divinité
    * idCapacité 32
    * */

    /**
     * Méthode permettant de voler l'ensemble des points d'action d'une autre divinité.
     * @param joueur Joueur
     */
    public static void capaciteDiviniteVolerPointAction(Joueur joueur) {
        joueur.getDivinite().setCapaciteUtilisee(true);
        int i = Partie.getInstance().joueursActifs.indexOf(joueur);
        ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
        Iterator<Joueur> joueurIterator = Partie.getInstance().joueursActifs.listIterator(i);
        System.out.println("\nJoueurs n'ayant pas encore joué pendant ce tour : ");
        while (joueurIterator.hasNext()){
            Joueur next = joueurIterator.next();
            listeJoueur.add(next);
            int iNext = Partie.getInstance().joueursActifs.indexOf(next);
            DisplayCLIController.afficherJoueurs(next, iNext);
        }
        System.out.println("Choisissez l'index du joueur a voler : ");
        int index = joueur.choixVictime();
        Joueur victime = Partie.getInstance().joueursActifs.get(index);
        Partie.getInstance().ajouterPoint(victime);
        victime.setPointAction(false);
        int pointNeant = joueur.getPointNeant() + victime.getPointNeant();
        int pointJour = joueur.getPointJour() + victime.getPointJour();
        int pointNuit = joueur.getPointNuit() + victime.getPointNuit();
        victime.setPointNuit(0);
        victime.setPointJour(0);
        victime.setPointNeant(0);
        DisplayCLIController.afficherPoints(victime);
        joueur.setPointNeant(pointNeant);
        joueur.setPointJour(pointJour);
        joueur.setPointNuit(pointNuit);
        DisplayCLIController.afficherPoints(joueur);
    }
}
