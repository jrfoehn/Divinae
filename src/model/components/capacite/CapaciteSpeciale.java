package model.components.capacite;

import controller.DisplayCLIController;
import model.cards.*;
import model.components.*;
import model.components.joueur.Joueur;
import model.components.plateau.Defausse;
import model.components.plateau.Plateau;
import model.kernel.Partie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Contient l'ensemble des capacités spéciales concernant un unique joueur.
 */
public class CapaciteSpeciale {

    /*Constructeur*/

    /**
     * Constructeur Capacité Spéciale utilisées sur une unique carte.
     * @param joueur Joueur
     * @param carteAction CarteAction
     */
    public static void capaciteSpeciale(Joueur joueur, CarteAction carteAction) {
        System.out.println("SACRIFIER !");
        switch (carteAction.getIdCapaciteSpeciale()) {
            case 1:
                capaciteAjouterPoint(joueur, carteAction.getIdCarte());
                break;
            case 2:
                capaciteEmpecherSacrifice(joueur, carteAction.getIdCarte());
                break;
            case 3:
                capaciteVolerCarte(joueur, carteAction);
                break;
            case 4:
                System.out.println("4");
                capaciteImposerSacrificeCarteAction(joueur, carteAction);
                break;
            case 5:
                capaciteRecupererGuide(joueur, carteAction);
                break;
            case 6:
                capaciteRelancerDe(joueur);
                break;
            case 7:
                capaciteDefausserGuide(joueur);
                break;
            case 8:
                capaciteVolerPointActionDivinite(joueur);
                break;
            case 9:
                capaciteBeneficierCapaciteSpecialeAutreDivinite(joueur);
                break;
            case 10:
                capaciteEmpecherAttributionPointAction(joueur);
                break;
            case 11:
                capaciteGuidePoseApocalypse();
                break;
            case 12:
                capaciteGuideAjouterPointActionCroyant(joueur, carteAction);
                break;
            case 13:
                capaciteSacrifierCroyantNeant(joueur);
                break;
            case 14:
                capaciteDefausserGuideNonChaos(joueur);
                break;
            case 15:
                capaciteDefausserCroyantPlateau(joueur, carteAction);
                break;
            case 16:
                capaciteSacrifierDeuxCroyants(joueur, carteAction);
                break;
            case 17:
                capaciteVolerGuide(joueur, carteAction);
                break;
            case 18:
                capaciteChoisirOrigineTour(joueur);
                break;
            case 9999:
                Apocalypse.utiliserApocalypse();
            default:
                break;
        }
    }


    /*Ajout point d'action au joueur
    * idCapacité 1
    * */

    /**
     * Méthode permettant d'ajouter un point d'action au joueur.
     * @param joueur Joueur
     * @param idCarte int
     */
    public static void capaciteAjouterPoint(Joueur joueur, int idCarte) {
        switch (idCarte) {
            case 101:case 102:case 103:case 104:case 105:
                joueur.setPointJour(joueur.getPointJour() + 1);
                DisplayCLIController.afficherPoints(joueur);
                break;
            case 114:case 115:case 116:case 117:case 118:
                joueur.setPointNuit(joueur.getPointNuit() + 1);
                DisplayCLIController.afficherPoints(joueur);
                break;
            case 127:case 128:case 129:case 130:case 131:
                joueur.setPointNeant(joueur.getPointNeant() + 1);
                DisplayCLIController.afficherPoints(joueur);
                break;
        }
    }

    /*Empêche un divinité de sacrifier une de ses cartes croyants durant ce tour si elle possède les dogmes :
    *   106: Nature, Mystique
    *   119: Humain, Mystique
    *   132: Nature, Mystique
    */
    /*Empêche une divinité de sacrifier une des ses cartes guides spirituels durant ce tour si elle possède les dogmes :
    *   107: Nature, Mystique
    *   120: Humain, Symbole
    *   133: Mystique, Chaos
    *
    * idCapacité 2
    * */

    /**
     * Méthode permettant d'empêcher une divinité de sacrifier une de ses cartes Croyant ou Guide Spirituel durant ce tour si elle possède certains dogmes.
     * @param joueur Joueur
     * @param idCarte int
     */
    public static void capaciteEmpecherSacrifice(Joueur joueur, int idCarte) {
        Dogme dogme1;
        Dogme dogme2;
        String carte = "croyant"; // moins de code à taper
        switch (idCarte) {
            case 106:
                dogme1 = Dogme.Mystique;
                dogme2 = Dogme.Nature;
                break;
            case 107:
                dogme1 = Dogme.Nature;
                dogme2 = Dogme.Mystique;
                carte = "guide";
                break;
            case 119:
                dogme1 = Dogme.Humain;
                dogme2 = Dogme.Mystique;
                break;
            case 120:
                dogme1 = Dogme.Humain;
                dogme2 = Dogme.Symbole;
                carte = "guide";
                break;
            case 132:
                dogme1 = Dogme.Nature;
                dogme2 = Dogme.Mystique;
                break;
            default:
                dogme1 = Dogme.Mystique;
                dogme2 = Dogme.Chaos;
                carte = "guide";
                break;
        }
        Iterator<Joueur> joueurIterator = Partie.getInstance().joueursActifs.iterator();
        ArrayList<Joueur> adversaires = new ArrayList<Joueur>();
        while (joueurIterator.hasNext()) {
            Joueur joueur1 = joueurIterator.next();
            for (int i=0; i < 3; i++) {
                if ((joueur1.getDivinite().listeDogmes.get(i) == dogme1) || (joueur1.getDivinite().listeDogmes.get(i) == dogme2)) {
                    adversaires.add(joueur1);
                }
            }
        }
        System.out.println("Vous pouvez attaquer les joueurs suivants. Identifiez les avec leur indice");
        Partie.getInstance().afficherAdversaires(adversaires);
        int joueurIndex = joueur.choisirVictime();
        Joueur joueurIdentifier = adversaires.get(joueurIndex);
        if (carte == "croyant") {
            System.out.println(joueurIdentifier.getId() + "ne peut sacrifier de croyants durant ce tour");
            joueurIdentifier.setSacrifierCroyant(false);
        }
        else if (carte == "guide") {
            System.out.println(joueurIdentifier.getId() + "ne peut sacrifier de guides spirituels durant ce tour");
            joueurIdentifier.setSacrifierGuideSpirituel(false);
        }
    }

    /*Fonction pour voler une carte à une autre joueur
    *   360: prenez 3 cartes à un joueur et incluez les à votre main
    *   108: piochez 2 cartes à un joueur et incluez les à cotre main
    *
    * idCapacité 3
    * */

    /**
     * Méthode permettant de voler une carte dans la main d'un autre joueur.
     * @param joueur Joueur
     * @param carteAction CarteAction
     */
    public static void capaciteVolerCarte(Joueur joueur, CarteAction carteAction) {
        int indexVictime = joueur.choixVictime();
        Joueur joueurVictime = Partie.getInstance().joueursActifs.get(indexVictime);
        int max;
        switch (carteAction.getIdCarte()) {
            case 108:case 121:case 134:
                max = 2;
                break;
            default: //360
                max = 3;
        }
        for (int i = 0; i < max; i++) {
            int j =(int)Math.floor(Math.random()*(joueurVictime.getMainJoueur().getListeCartes().size()-1));
            CarteAction carteActionPrise = joueurVictime.getMainJoueur().getListeCartes().get(j);
            joueur.getMainJoueur().getListeCartes().add(carteActionPrise);
            joueurVictime.getMainJoueur().getListeCartes().remove(carteActionPrise);
        }
        System.out.println("Vous avez volé " + max + " cartes.");
    }

    /*Fonction pour imposer le choix du croyant à sacrifier
    *   109, 110, 122, 123 : croyant (def)
    *   111 : guide (inclu dans le if du default)
    *   136 : mutliples divinité
    *   216 : Divinité dogme Nature ou Mystique sacrifie guide
    *   idCapacité 4
    *   */

    /**
     * Méthode permettant d'imposer à une divinité le choix du croyant à sacrifier.
     * @param joueur Joueur
     * @param carteAction CarteAction
     */
    public static void capaciteImposerSacrificeCarteAction(Joueur joueur, CarteAction carteAction) {
        int NombreDivinite;
        int idVictime;
        Joueur victime;
        switch (carteAction.getIdCarte()) {
            default:
                idVictime = joueur.choixVictime();
                victime = Partie.getInstance().joueursActifs.get(idVictime);
                victime.afficherCartePlateauPerso();
                if (carteAction.getIdCarte() == 111) {
                    System.out.println("Vous devez choisir un guide à sacrifier");
                    victime.sacrifierGuide();
                }
                else {
                    System.out.println("Vous devez choisir un croyant à sacrifier");
                    victime.sacrifierCroyant();
                }
                break;
            case 136:
                ArrayList<Joueur> listeVictimes = joueur.choixJoueursMultiples();
                Iterator<Joueur> joueurIterator = listeVictimes.iterator();
                while (joueurIterator.hasNext()) {
                    Joueur next = joueurIterator.next();
                    System.out.println("Les carte du joueur " + next.getId() + " sont : ");
                    next.afficherCartePlateauPerso();
                    next.sacrifierCroyant();
                }
                break;
            case 216:
                ArrayList<Joueur> victimesPotentielles = new ArrayList<Joueur>();
                Iterator<Joueur> joueursAcrifsIterator = Partie.getInstance().joueursActifs.iterator();
                while (joueursAcrifsIterator.hasNext()) {
                    Joueur victimePossible = joueursAcrifsIterator.next();
                    if (victimePossible.getDivinite().listeDogmes.contains(Dogme.Nature) || victimePossible.getDivinite().listeDogmes.contains(Dogme.Mystique)) {
                        victimesPotentielles.add(victimePossible);
                    }
                }
                System.out.println("Vous pouvez attaquer les joueurs suivant : ");
                Partie.getInstance().afficherAdversaires(victimesPotentielles);
                int idVictime2 = joueur.choixVictime();
                Joueur victime2 = Partie.getInstance().joueursActifs.get(idVictime2);
                victime2.sacrifierGuide();
                break;
        }
    }

    /*Fonction pour récupérer un Guide Spirituel posé
    *   112: croyant reviennent au centre
    *   217: pour une Divinité d'origine nuit ou possédant les dogmes mystique et chaos, les croyans sont défaussés
    * idCapacité 5
    * */

    /**
     * Méthode permettant de récupérer une carte Guide Spirituel posée.
     * @param joueur Joueur
     * @param carteAction CarteAction
     */
    public static void capaciteRecupererGuide(Joueur joueur, CarteAction carteAction) {
        ArrayList<Croyant> croyantsRataches = new ArrayList<Croyant>();
        switch (carteAction.getIdCarte()) {
            case 112:
                int joueurID = joueur.choixVictime();
                Joueur victime = Partie.getInstance().joueursActifs.get(joueurID);
                System.out.println("Voici les cartes que ce joueur a poser : ");
                victime.afficherCartePlateauPerso();
                int indexGuideRecuperer = joueur.choisirIndexGuide(victime);
                GuideSpirituel guideSpirituel = (GuideSpirituel) victime.getCartesPlateauPerso().getListeCartePlateau().get(indexGuideRecuperer);
                Plateau.getInstance().getListeCartePlateau().addAll(guideSpirituel.croyantGuider);
                guideSpirituel.croyantGuider.clear();
                victime.getMainJoueur().getListeCartes().add(guideSpirituel);
                victime.getCartesPlateauPerso().getListeCartePlateau().remove(guideSpirituel);
                System.out.println("Le joueur visé récupère son guide. Les croyants qui y étaient rattachés reviennent au centre");
                break;
            default:
                ArrayList<Joueur> adversaires = new ArrayList<Joueur>();
                Iterator<Joueur> joueurIterator = Partie.getInstance().joueursActifs.iterator();
                while (joueurIterator.hasNext()) {
                    Joueur joueur1 = joueurIterator.next();
                    if ((joueur1.getDivinite().origine == Origine.Nuit) && ((joueur1.getDivinite().listeDogmes.contains(Dogme.Chaos)) && (joueur1.getDivinite().listeDogmes.contains(Dogme.Mystique)))) {
                        adversaires.add(joueur1);
                    }
                }
                if (!adversaires.isEmpty()) {
                    System.out.println("Divinités que vous pouvez attaquer : ");
                    Partie.getInstance().afficherAdversaires(adversaires);
                    int i = joueur.choixVictime();
                    Joueur joueurVictime = adversaires.get(i);
                    System.out.println("Voici ses cartes. Quel guide choisissez vous ?");
                    joueurVictime.afficherCartePlateauPerso();
                    int j = joueur.choisirIndexGuide(joueurVictime);
                    GuideSpirituel guideSpirituelVictime = (GuideSpirituel) joueurVictime.getCartesPlateauPerso().getListeCartePlateau().get(j);
                    Defausse.getInstance().getListeCartes().addAll(guideSpirituelVictime.croyantGuider);
                    guideSpirituelVictime.croyantGuider.clear();
                    joueurVictime.getMainJoueur().getListeCartes().add(guideSpirituelVictime);
                    joueurVictime.getCartesPlateauPerso().getListeCartePlateau().remove(guideSpirituelVictime);
                    System.out.println("Le guide retourne dans la main de la victime. Les croyants qui y étaient rattachés sont défaussés.");
                }
                break;
        }
    }

    /*Fonction pour relancer le dé de cosmogonie
    * idCapacité 6
    * */

    /**
     * Méthode permettant de relancer le dé de cosmogonie et de redéfinir l'influence du tour.
     * @param joueur Joueur
     */
    public static void capaciteRelancerDe(Joueur joueur) {
        System.out.printf("Appuyer sur entrée pour relancer le dé");
        Partie.getInstance().lancerDe();
        System.out.println("Le tour est désormais d'origine : "+Partie.getInstance().getOrgineTour());
        Partie.getInstance().ajouterPoint(joueur);
        DisplayCLIController.afficherPoints(joueur);
    }

    /*Fonction pour défausser un Guide Spirituel posé
    * idCapacité 7
    * */

    /**
     * Méthode permettant de défausser une carte Guide Spirituel posée.
     * @param joueur Joueur
     */
    public static void capaciteDefausserGuide(Joueur joueur) {
        int i = joueur.choixVictime();
        Joueur joueurVictime = Partie.getInstance().joueursActifs.get(i);
        System.out.println("Rentrez l'index du guide dont vous voulez défausser.");
        int indexGuide = joueur.choisirIndexGuide(joueur);
        GuideSpirituel guideSpirituel = (GuideSpirituel) joueurVictime.getCartesPlateauPerso().getListeCartePlateau().get(indexGuide);
        Plateau.getInstance().getListeCartePlateau().addAll(guideSpirituel.croyantGuider);
        guideSpirituel.croyantGuider.clear();
        Defausse.getInstance().getListeCartes().add(guideSpirituel);
        joueurVictime.getCartesPlateauPerso().getListeCartePlateau().remove(guideSpirituel);
    }

    /*Fonction pour voler les points d'action d'une Divinité
    * idCapacité 8
    * */

    /**
     * Méthode permettant de voler les points d'action d'un autre joueur.
     * @param joueur Joueur
     */
    public static void capaciteVolerPointActionDivinite(Joueur joueur) {
        int i = Partie.getInstance().joueursActifs.indexOf(joueur);
        Iterator<Joueur> joueurIterator = Partie.getInstance().joueursActifs.listIterator(i);
        System.out.println("Quel joueur voulez vous attaquer ?");
        while (joueurIterator.hasNext()) {
            Joueur victime = joueurIterator.next();
            int idVictime = Partie.getInstance().joueursActifs.indexOf(victime);
            DisplayCLIController.afficherJoueurs(victime, idVictime);
        }
        int j = joueur.choixVictime();
        Joueur joueurChoisi = Partie.getInstance().joueursActifs.get(j);
        joueur.setPointJour(joueur.getPointJour() + joueurChoisi.getPointJour());
        joueurChoisi.setPointJour(0);
        joueur.setPointNuit(joueur.getPointNuit() + joueurChoisi.getPointNuit());
        joueurChoisi.setPointNuit(0);
        joueur.setPointNeant(joueur.getPointNeant() + joueurChoisi.getPointNeant());
        joueurChoisi.setPointNeant(0);
        DisplayCLIController.afficherPoints(joueur);
    }

    /*Fonction permettant d'utiliser la capacité d'une carte croyant appartenant à une autre divinité :
    * idCapacité 9
    * */

    /**
     * Méthode permettant d'utiliser la capacité spéciale d'une carte Croyant appartenant à une autre divinité.
     * @param joueur Joueur
     */
    public static void capaciteBeneficierCapaciteSpecialeAutreDivinite(Joueur joueur) {
        System.out.println("Quel joueur voulez vous attaquer ?");
        Plateau.afficherCartePlateauPersoAdversaires();
        int idAdversaire = joueur.choixVictime();
        Joueur adversaire = Partie.getInstance().joueursActifs.get(idAdversaire);
        System.out.println("Croyant (0) ou Guide (1) ?");
        //TODO methode apropriée ?
        int i = joueur.choisirBinaire();
        if (i == 0) {
            System.out.println("Entrez l'indice du guide possédant le croyant : ");
            int idGuidePossedantCroyant = joueur.choisirIndexGuide(adversaire);
            GuideSpirituel guideSpirituel = (GuideSpirituel) adversaire.getCartesPlateauPerso().getListeCartePlateau().get(idGuidePossedantCroyant);
            System.out.println("Quel est l'indice du croyant à sacrifier ?");
            DisplayCLIController.showCroyant(guideSpirituel.croyantGuider);
            int idCroyant = joueur.choisirIndexCroyant(guideSpirituel);
            Croyant croyant = guideSpirituel.croyantGuider.get(idCroyant);
            System.out.println("Vous avez activé en votre faveur le sacrifice de cette carte");
            capaciteSpeciale(joueur, croyant);
        }
        else if (i == 1){
            System.out.println("Entrez l'indice du guide a sacrifier");
            int idGuide = joueur.choisirIndexGuide(adversaire);
            GuideSpirituel guideSpirituel = (GuideSpirituel) adversaire.getCartesPlateauPerso().getListeCartePlateau().get(idGuide);
            capaciteSpeciale(joueur, guideSpirituel);
        }
        else {
            System.out.println("Le choix ne peut être que 0 (croyant) ou 1 (guide) !!!!");
            capaciteBeneficierCapaciteSpecialeAutreDivinite(joueur);
        }
    }

    /*Fonction pour empêcher une divinité de recevoir des points d'action
    * idCapacité 10
    * */

    /**
     * Méthode permettant d'empêcher une divinité de recevoir des points d'action.
     * @param joueur Joueur
     */
    public static void capaciteEmpecherAttributionPointAction(Joueur joueur) {
        int indexDuJoueur = Partie.getInstance().joueursActifs.indexOf(joueur);
        Iterator<Joueur> joueurIterator = Partie.getInstance().joueursActifs.listIterator(indexDuJoueur);
        ArrayList<Joueur> joueurSansPA = new ArrayList<Joueur>();
        while (joueurIterator.hasNext()) {
            Joueur nextPlayer = joueurIterator.next();
            nextPlayer.setPointAction(false);
            joueurSansPA.add(nextPlayer);
        }
        System.out.println("Les joueurs suivants ne recevront pas de points d'action : " + joueurSansPA);
    }

    /*Fonction rendant le sacrifice du guide équivalent à la pose d'une carte apocalypse.
    * idCapacité 11
    * */

    /**
     * Méthode rendant le sacrifice d'une carte Guide Spirituel équivalent à la pose d'une carte Apocalypse.
     */
    public static void capaciteGuidePoseApocalypse() {
        Apocalypse.utiliserApocalypse();
    }

    /*Fonction pour gagner une nombre de point d'action correspondant au nombre de croyants guidés
    * idCapacité 12
    * */

    /**
     * Méthode permettant de gagner un nombre de points d'action correspondant au nombre de croyants guidés.
     * @param joueur Joueur
     * @param carteAction CarteAction
     */
    public static void capaciteGuideAjouterPointActionCroyant(Joueur joueur, CarteAction carteAction) {
        int pointSupp = 0;
        GuideSpirituel guideSpirituel = (GuideSpirituel) carteAction;
        Iterator<Croyant> croyantIterator = guideSpirituel.croyantGuider.iterator();
        while (croyantIterator.hasNext()) {
            pointSupp ++;
        }
        System.out.println("Quel type de points voulez vous ajouter : \r 0 - jour \r 1 - nuit \r 2 - neant");
        int typePointAction = joueur.choisirJourNuitNeant();
        switch (typePointAction) {
            case 0:
                joueur.setPointJour(joueur.getPointJour() + pointSupp);
                break;
            case 1:
                joueur.setPointNuit(joueur.getPointNuit() + pointSupp);
                break;
            default:
                joueur.setPointNeant(joueur.getPointNeant() + pointSupp);
                break;
        }
        DisplayCLIController.afficherPoints(joueur);
    }

    /*Fonction pour sacrifier les croyants d'origine néant
    * idCapacité 13
    * */

    /**
     * Méthode permettant de sacrifier les cartes Croyant d'origine néant.
     * @param joueur Joueur
     */
    public static void capaciteSacrifierCroyantNeant(Joueur joueur) {
        ArrayList<Croyant> croyantArrayList = new ArrayList<Croyant>();
        ArrayList<Joueur> joueurArrayList = new ArrayList<Joueur>();
        Scanner scanner = new Scanner(System.in);
        Iterator<Joueur> joueurIterator = Partie.getInstance().joueursActifs.iterator();
        while (joueurIterator.hasNext()) {
            Joueur joueurRestant = joueurIterator.next();
            if (joueurRestant.getDivinite().listeDogmes.contains(Dogme.Humain)) {
                joueurArrayList.add(joueurRestant);
            }
        }
        System.out.println("Pour quelle Divinité voulez-vous sacrifier les croyants d'origine néant ?");
        Partie.getInstance().afficherAdversaires(joueurArrayList);
        int indiceDivinite = joueur.choixVictime();
        Joueur victime = joueurArrayList.get(indiceDivinite);
        Iterator<CarteAction> carteActionIterator = victime.getCartesPlateauPerso().getListeCartePlateau().iterator();
        while (carteActionIterator.hasNext()) {
            GuideSpirituel guideSpirituel = (GuideSpirituel) carteActionIterator.next();
            Iterator<Croyant> croyantIterator = guideSpirituel.croyantGuider.iterator();
            while (croyantIterator.hasNext()) {
                Croyant croyant = croyantIterator.next();
                if (croyant.origine == Origine.Neant) {
                    croyantArrayList.add(croyant);
                    guideSpirituel.croyantGuider.remove(croyant);
                }
            }
        }
        System.out.println("Les croyants sacrifiés sont : " + croyantArrayList);
        Iterator<Croyant> croyantSacrifier = croyantArrayList.iterator();
        while (croyantSacrifier.hasNext()) {
            Croyant croyantMort = croyantSacrifier.next();
            capaciteSpeciale(victime, croyantMort);
        }
        Defausse.getInstance().getListeCartes().addAll(croyantArrayList);
    }

    /*Fonction pour défausser un guide s'il lui, ou sa divinité, ne possède pas le dogme Chaos
    * idCapacité 14
    * */

    /**
     * Méthode permettant de défausser une carte Guide Spirituel si ce dernier ou sa divinité ne possède pas le dogme chaos.
     * @param joueur Joueur
     */
    public static void capaciteDefausserGuideNonChaos (Joueur joueur) {
        ArrayList<Joueur> joueurAvecDivinitSansDogmeChaos = new ArrayList<Joueur>();
        ArrayList<GuideSpirituel> guideSansDogmeChaos = new ArrayList<GuideSpirituel>();
        ArrayList<Joueur> joueurAvecGuideSansDogmeChaos = new ArrayList<Joueur>();
        Iterator<Joueur> joueurActif = Partie.getInstance().joueursActifs.iterator();
        while (joueurActif.hasNext()) {
            Joueur joueurSuivant = joueurActif.next();
            if (joueurSuivant.getDivinite().listeDogmes.contains(Dogme.Chaos)) {
                joueurAvecDivinitSansDogmeChaos.add(joueurSuivant);
            }
            else {
                Iterator<CarteAction> carteJoueurSuivant = joueurSuivant.getCartesPlateauPerso().getListeCartePlateau().iterator();
                while (carteJoueurSuivant.hasNext()) {
                    GuideSpirituel guideJoueurSuivant = (GuideSpirituel) carteJoueurSuivant.next();
                    if (guideJoueurSuivant.listeDogmes.contains(Dogme.Chaos)) {
                        guideSansDogmeChaos.add(guideJoueurSuivant);
                        joueurAvecGuideSansDogmeChaos.add(joueurSuivant);
                    }
                }
            }
            if (joueurAvecDivinitSansDogmeChaos.isEmpty()) {
                if (joueurAvecGuideSansDogmeChaos.isEmpty()) {
                    System.out.println("Pas de cibles possible");
                }
                else if (!joueurAvecGuideSansDogmeChaos.isEmpty()) {
                    System.out.println("Joueurs qui possèdent des Guides sans dogmes Chaos : ");
                    Iterator<Joueur> listeJoueurAvecGuideSansChaos = joueurAvecGuideSansDogmeChaos.iterator();
                    while (listeJoueurAvecGuideSansChaos.hasNext()) {
                        Joueur prochainJoueur = listeJoueurAvecGuideSansChaos.next();
                        int indice = joueurAvecGuideSansDogmeChaos.indexOf(prochainJoueur);
                        System.out.println("[" + indice + "] numéro joueur : " + prochainJoueur.getId() + " guide " + guideSansDogmeChaos.get(indice));
                    }
                    System.out.println("Quel est le joueur cible ?");
                    int cible = joueur.choixVictime();
                    Joueur joueurCible = joueurAvecGuideSansDogmeChaos.get(cible);
                    GuideSpirituel zombie = guideSansDogmeChaos.get(cible);
                    Iterator<Croyant> croyantIterator = zombie.croyantGuider.iterator();
                    while (croyantIterator.hasNext()) {
                        Croyant prochainCroyant = croyantIterator.next();
                        Plateau.getInstance().getListeCartePlateau().add(prochainCroyant);
                    }
                    zombie.croyantGuider.clear();
                    Defausse.getInstance().defausser(zombie);
                    capaciteSpeciale(joueurCible, zombie);
                }
            }
            else if (!joueurAvecDivinitSansDogmeChaos.isEmpty() && joueurAvecGuideSansDogmeChaos.isEmpty()) {
                System.out.println("Voici les joueurs sans dogme Chaos. Quel joueur choisissez-vous ?");
                Partie.getInstance().afficherAdversaires(joueurAvecDivinitSansDogmeChaos);
                int idVictime = joueur.choixVictime();
                Joueur victime = joueurAvecDivinitSansDogmeChaos.get(idVictime);
                System.out.println("Joueur " + victime + ", sacrifiez un de vos guides");
                victime.sacrifierGuide();
            }
            else {
                System.out.println("Choisissez ce que vous voulez : ");
                System.out.println("\r Cas 1 - Vous laisser un des joueurs suivant choisir quel guide sacrifier : ");
                Partie.getInstance().afficherAdversaires(joueurAvecDivinitSansDogmeChaos);
                System.out.println("\r Cas 2 - Vous choisissez de sacrifier un guide de l'un des joueurs suivant. Vous choisissez quel guide précisemment.");
                Iterator<Joueur> joueurIterator = joueurAvecGuideSansDogmeChaos.iterator();
                while (joueurIterator.hasNext()) {
                    Joueur next = joueurIterator.next();
                    int id = joueurAvecGuideSansDogmeChaos.indexOf(next);
                    System.out.println("[[" + id + "]] joueur num " + next.getId() + " guide : " + guideSansDogmeChaos.indexOf(id));
                }
                System.out.println("Quel cas choisissez vous ?");
                int cas = joueur.choisirBinaire();
                switch (cas) {
                    default:
                        System.out.println("Entrez l'indice du joueur qui doit sacrifier un de ses guides");
                        int idDiv = joueur.choixVictime();
                        joueurAvecDivinitSansDogmeChaos.get(idDiv).sacrifierGuide();
                        break;
                    case 2:
                        System.out.println("Entrez l'indice correspondant au guide du joueur que vous voulez sacrifier :");
                        int idVictime = joueur.choixVictime();
                        Joueur victime = Partie.getInstance().joueursActifs.get(idVictime);
                        int idGuide = joueur.choisirIndexGuide(victime);
                        capaciteSpeciale(joueurAvecGuideSansDogmeChaos.get(idGuide), guideSansDogmeChaos.get(idGuide));
                        break;
                }
            }
        }
    }

    /*Fonction pour défausser des croyants du plateau
    * idCapacité 15
    * */

    /**
     * Méthode permettant de defausser les cartes Croyant du plateau.
     * @param joueur Joueur
     * @param carteAction CarteAction
     */
    public static void capaciteDefausserCroyantPlateau(Joueur joueur, CarteAction carteAction) {
        boolean contientDogme = false;
        Dogme dogme; // on initialise le dogme
        ArrayList<Croyant> croyantDefausser = new ArrayList<Croyant>();
        Iterator<CarteAction> carteActionIterator = Plateau.getInstance().getListeCartePlateau().iterator();
        switch (carteAction.getIdCarte()) {
            default:
                dogme = Dogme.Mystique;
                break;
            case 214 :
                dogme = Dogme.Nature;
                break;
        }
        while (carteActionIterator.hasNext()) {
            CarteAction nextCarteAction = carteActionIterator.next();
            if (nextCarteAction.listeDogmes.contains(dogme)) {
                contientDogme = true;
            }
            if (contientDogme == true) {
                croyantDefausser.add((Croyant) nextCarteAction);
                Defausse.getInstance().defausser(nextCarteAction);
            }
        }
        Plateau.getInstance().getListeCartePlateau().removeAll(croyantDefausser);
    }

    /*Fonction pour sacrifier deux Croyants
    * 215: Divinité possédant le dogme humain et symbole, capacités utilisées
    * 305: Divinité au choix, capacités non utilisées
    * idCapacité 16
    * */

    /**
     * Méthode permettant de sarcrifier deux cartes Croyant.
     * @param joueur Joueur
     * @param carteAction CarteAction
     */
    public static void capaciteSacrifierDeuxCroyants(Joueur joueur, CarteAction carteAction) {
        int diviniteVictime;
        boolean utiliserCapacite = true;
        boolean possible = true;
        Joueur victime = joueur;
        switch (carteAction.getIdCarte()) {
            case 305:
                utiliserCapacite = false;
                diviniteVictime = joueur.choixVictime();
                victime = Partie.getInstance().joueursActifs.get(diviniteVictime);
                break;
            default:
                ArrayList<Joueur> victimePotentielle = new ArrayList<Joueur>();
                Iterator<Joueur> joueurIterator = Partie.getInstance().joueursActifs.iterator();
                while (joueurIterator.hasNext()) {
                    Joueur cible = joueurIterator.next();
                    if (cible.getDivinite().listeDogmes.contains(Dogme.Humain) || cible.getDivinite().listeDogmes.contains(Dogme.Symbole)) {
                        victimePotentielle.add(cible);
                    }
                }
                if (!victimePotentielle.isEmpty()) {
                    System.out.println("Liste des victimes potentielles : ");
                    Partie.getInstance().afficherAdversaires(victimePotentielle);
                    diviniteVictime = joueur.choixVictime();
                    victime = Partie.getInstance().joueursActifs.get(diviniteVictime);
                }
                else {
                    possible = false;
                    System.out.printf("Aucune victime possible");
                }
                break;
        }
        if (possible != true) {
            System.out.println("Cartes de la victime : ");
            victime.afficherCartePlateauPerso();
            if (utiliserCapacite == true) {
                System.out.println("Entrez l'index du premier croyant : ");
                int croyant1 = joueur.choisirIndexCroyantVictime(victime);
                System.out.println("Entrez l'index du second croyant :");
                int croyant2 = joueur.choisirIndexCroyantVictime(victime);
                while (croyant1 == croyant2) {
                    System.out.println("Entrez l'index du second croyant :");
                    croyant2 = joueur.choisirIndexCroyantVictime(victime);
                }
            }
            else {
                System.out.println("Choisissez deux croyants à défausser");
                for (int i = 0; i < 2; i++) {
                    System.out.println("Quel guide ?");
                    int idGuide = joueur.choisirIndexGuide(victime);
                    GuideSpirituel guideSpirituel = (GuideSpirituel) victime.getCartesPlateauPerso().getListeCartePlateau().get(idGuide);
                    System.out.println("Quel croyant ?");
                    int idCroyant = joueur.choisirIndexCroyant(guideSpirituel);
                    Croyant croyant = guideSpirituel.croyantGuider.get(idCroyant);
                    Defausse.getInstance().defausser(croyant);
                    guideSpirituel.croyantGuider.remove(croyant);
                }
            }
        }
    }

    /*Fonction pour voler un guide
    * 218: Echange avec un guide du joueur
    * 304: Vol (def)
    * 307: Vol (def)
    * idCapacité 17
    * */

    /**
     * Méthode permettant de voler une carte Guide Spirituel
     * @param joueur Joueur
     * @param carteAction CarteAction
     */
    public static void capaciteVolerGuide(Joueur joueur, CarteAction carteAction) {
        int idVictime = joueur.choixVictime();
        Scanner scanner = new Scanner(System.in);
        Joueur victime = Partie.getInstance().joueursActifs.get(idVictime);
        System.out.println("Les cartes de ce joueur sont : ");
        victime.afficherCartePlateauPerso();
        int i = scanner.nextInt();
        GuideSpirituel guideVictime = (GuideSpirituel) victime.getCartesPlateauPerso().getListeCartePlateau().get(i);
        switch (carteAction.getIdCarte()) {
            default:
                System.out.println("Ce guide est rajouté à vos cartes.");
                joueur.getCartesPlateauPerso().getListeCartePlateau().add(guideVictime);
                break;
            case 218:
                System.out.println("Voici vos cartes, choisissez le guide à échanger");
                joueur.afficherCartePlateauPerso();
                int j = scanner.nextInt();
                GuideSpirituel guideJoueur = (GuideSpirituel) joueur.getCartesPlateauPerso().getListeCartePlateau().get(j);
                joueur.getCartesPlateauPerso().getListeCartePlateau().add(guideVictime);
                joueur.getCartesPlateauPerso().getListeCartePlateau().remove(guideJoueur);
                victime.getCartesPlateauPerso().getListeCartePlateau().add(guideJoueur);
                victime.getCartesPlateauPerso().getListeCartePlateau().remove(guideVictime);
                break;
        }
    }

    /*Fonction pour choisir l'origine d'un tour
    * idCapacité 18
    * */

    /**
     * Méthode permettant de choisir l'origine du tour.
     * @param joueur Joueur
     */
    public static void capaciteChoisirOrigineTour (Joueur joueur) {
        ArrayList<Joueur> nouvelOrdre = new ArrayList<Joueur>();
        Scanner scanner = new Scanner(System.in);
        int i = Partie.getInstance().joueursActifs.indexOf(joueur);
        Iterator<Joueur> joueurIterator = Partie.getInstance().joueursActifs.listIterator(i);
        while (joueurIterator.hasNext()) {
            Joueur joueur1 = joueurIterator.next();
            nouvelOrdre.add(joueur1);
            Partie.getInstance().joueursActifs.remove(joueur1);
        }
        Partie.getInstance().joueursActifs = nouvelOrdre;
        System.out.println("Choisissez la nouvel origine : \r 0 - jour \r 1 - nuit \r 2 - neant");
        int nouvelOrigine = scanner.nextInt();
        switch (nouvelOrigine) {
            case 0:
                Partie.getInstance().setOrgineTour(Origine.Jour);
                break;
            case 1:
                Partie.getInstance().setOrgineTour(Origine.Nuit);
                break;
            default:
                Partie.getInstance().setOrgineTour(Origine.Neant);
                break;
        }
        Partie.getInstance().tourSuivant();
    }

}