package model.components.joueur;

import controller.DisplayCLIController;
import model.cards.*;
import model.components.capacite.CapaciteSpeciale;
import model.components.plateau.*;
import model.components.Origine;
import model.kernel.Partie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Classe contenant l'ensemble des fonctions des joueurs qui seront redéfinies par héritage dans les classes JoueurHumain et JoueurVirtuel.
 */
public abstract class Joueur {

    protected String nom;
    protected Divinite divinite;
    protected Origine origine;
    protected Deck mainJoueur;
    protected PlateauPerso cartesPlateauPerso;
    protected int id;
    protected int pointJour;
    protected int pointNuit;
    protected int pointNeant;
    protected int pointPriere;
    protected boolean capaciteUtilise = false;
    protected boolean pointAction = true;
    protected boolean sacrifierCroyant = true;
    protected boolean sacrifierGuideSpirituel = true;

    /**
     * Constructeur Joueur
     * @param i int
     */
    public Joueur(int i) {
//        this.nom = nom;
//        pour l'instant les thiss n'ont que des indices, et pas de nom
        this.id = i;
        this.mainJoueur = new Deck();
        this.cartesPlateauPerso = new PlateauPerso();
        this.pointJour = 0;
        this.pointNuit = 0;
        this.pointNeant = 0;
    }

    public boolean getSacrifierCroyant() {
        return this.sacrifierCroyant;
    }

    public void setSacrifierCroyant(boolean sacrifierCroyant) {
        this.sacrifierCroyant = sacrifierCroyant;
    }

    public PlateauPerso getCartesPlateauPerso() {
        return cartesPlateauPerso;
    }

    public void setCartesPlateauPerso(PlateauPerso cartesPlateauPerso) {
        this.cartesPlateauPerso = cartesPlateauPerso;
    }

    public Origine getOrigine() {
        return origine;
    }

    public void setOrigine(Origine origine) {
        this.origine = origine;
    }

    public int getPointPriere() {
        return pointPriere;
    }

    public void setPointPriere(int pointPriere) {
        this.pointPriere = pointPriere;
    }

    public boolean getSacrifierGuideSpirituel() {
        return this.sacrifierGuideSpirituel;
    }

    public void setSacrifierGuideSpirituel(boolean sacrifierGuideSpirituel) {
        this.sacrifierGuideSpirituel = sacrifierGuideSpirituel;
    }

    public boolean getPointAction() {
        return this.pointAction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPointAction(boolean pointAction) {
        this.pointAction = pointAction;
    }

    public Deck getMainJoueur() {
        return mainJoueur;
    }

    public void setMainJoueur(Deck mainJoueur) {
        this.mainJoueur = mainJoueur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCapaciteUtilise(boolean capaciteUtilise) {
        this.capaciteUtilise = capaciteUtilise;
    }

    public int getPointNeant() {
        return pointNeant;
    }

    public void setPointNeant(int pointNeant) {
        this.pointNeant = pointNeant;
    }

    public int getPointJour() {
        return pointJour;
    }

    public void setPointJour(int pointJour) {
        this.pointJour = pointJour;
    }

    public int getPointNuit() {
        return pointNuit;
    }

    public void setPointNuit(int pointNuit) {
        this.pointNuit = pointNuit;
    }

    public Divinite getDivinite() {
        return divinite;
    }

    public void setDivinite(Divinite divinite) {
        this.divinite = divinite;
    }

    /**
     * Méthode permettant de piocher des cartes
     */
    public void completerDeckJoueur() {
        int decision = this.choisirBinaire();
        if (decision == 1) {
            while (this.getMainJoueur().getListeCartes().size() < 7) {
                CarteAction cartePioche = Pioche.getInstance().piocher();
                this.getMainJoueur().getListeCartes().add(cartePioche);
            }
            this.afficherMainJoueur();
        }
    }


    /**
     * Méthode permettant d'afficher la main du joueur
     */
    public void afficherMainJoueur() {
        Iterator<CarteAction> indexCarteAction = this.getMainJoueur().getListeCartes().iterator();
        int i = 0;
        while (indexCarteAction.hasNext()) {
            CarteAction carteAction = indexCarteAction.next();
            DisplayCLIController.afficherCarteIndex(i, carteAction);
            i++;
        }
    }

    /**
     * Méthode permettant d'afficher des cartes indexées
     * @param listeCartes ArrayList<CarteAction>
     */
    public void afficherCartes(ArrayList<CarteAction> listeCartes) {
        Iterator<CarteAction> carteActionIterator = listeCartes.iterator();
        while (carteActionIterator.hasNext()) {
            CarteAction carteAction = carteActionIterator.next();
            int index = listeCartes.indexOf(carteAction);
            DisplayCLIController.afficherCarteIndex(index, carteAction);
        }
    }


    /**
     * Méthode permettant d'afficher les cartes du plateau perso du joeuur
     */
    public void afficherCartePlateauPerso() {
        Iterator<CarteAction> carteActionIterator = this.getCartesPlateauPerso().getListeCartePlateau().iterator();
        while (carteActionIterator.hasNext()) {
            CarteAction carteAction = carteActionIterator.next();
            DisplayCLIController.afficherCarteIndex(this.getCartesPlateauPerso().getListeCartePlateau().indexOf(carteAction), carteAction);
            if (carteAction instanceof GuideSpirituel) {
                Iterator<Croyant> croyantIterator = ((GuideSpirituel) carteAction).croyantGuider.iterator();
                while (croyantIterator.hasNext()) {
                    Croyant croyant = croyantIterator.next();
                    DisplayCLIController.afficherCroyantIndex(((GuideSpirituel) carteAction).croyantGuider.indexOf(croyant), croyant);
                }
            }
        }
    }

    /**
     * Méthode permettant de poser une carte (généralement une carte Croyant) sur le plateau
     */
    public void poserCarteSurPlateau() {
        boolean veutPoser = true;
        while (((this.getPointJour() != 0) || (this.getPointNuit() != 0) || (this.getPointNeant() != 0)) && veutPoser) {
            boolean carteSup = true;
            while (carteSup) {
                System.out.println("Souhaitez vous poser une carte ? Oui (1) / Non (0)");
                int decisionCarteSup = this.choisirBinaire();
//                int decisionCarteSup = this.choisirPoserCartePlateau();
                switch (decisionCarteSup) {
                    default:
                        System.out.println("Entrez 1 pour oui, 0 pour non, c'est pas compliqué !");
                        break;
                    case 0:
                        carteSup = false;
                        veutPoser = false;
                        break;
                    case 1:
                        Plateau.getInstance().poserCarte(this);
                        carteSup = true;
                        break;
                }
            }
        }
    }

    /**
     * Méthode permettant de défausser une liste de carte
     * @param carteActionArrayList ArrayList<CarteAction>
     */
    public void defausserCartesMultiples(ArrayList<CarteAction> carteActionArrayList) {
        int decision = this.choisirBinaire();
        if (decision == 1) {
            System.out.println("Quelles sont les cartes dont vous voulez vous séparer ? Appuyer sur T pour terminer");
            int[] tableauCartesADefausser = this.choisirCartesADefausser();
            int nbCarteADefausser = tableauCartesADefausser.length;
            ArrayList<CarteAction> carteADefausser = new ArrayList<CarteAction>();
            for (int i = 0; i < nbCarteADefausser; i++) {
                carteADefausser.add(carteActionArrayList.get(tableauCartesADefausser[i]));
            }
            carteActionArrayList.removeAll(carteADefausser);
            Defausse.getInstance().defausserMultiple(carteADefausser);
            DisplayCLIController.showCarte(carteActionArrayList);
        }
    }

    /**
     * Méthode permettant de choisir la carte à sacrifier
     */
    public void choixSacrifier() {
        if (!this.getCartesPlateauPerso().getListeCartePlateau().isEmpty()) {
            System.out.println("Voici vos cartes. Souhaitez vous en sacrifier une ? Oui (1) Non (0)");
            this.afficherCartePlateauPerso();
            int i = this.choisirSacrifier();
            if (i == 1) {
                this.sacrifier();
            }
        } else {
            System.out.println("Vous n'avez pas de cartes disponibles.");
        }
    }

    /**
     * Méthode permettant de choisir la carte croyant à guider
     * @param cartesPossibles ArrayList<CarteAction>
     * @param guideSpirituel ArrayList<CarteAction>
     * @return
     */
    public int choisirIndexCroyantGuider(ArrayList<CarteAction> cartesPossibles, GuideSpirituel guideSpirituel) {
        return 0;
    }

    /**
     * Méthode permettant de guider un croyant lorsque l'on pose un guide spirituel.
     * @param guideSpirituel GuideSpirituel
     */
    public void guiderCroyant(GuideSpirituel guideSpirituel) {
        int carteSup = 1;
        boolean peutEtreGuider = false;
        while (carteSup == 1) {
            System.out.println("\nCartes présentes sur le plateau de jeu : ");
            this.afficherCartes(Plateau.getInstance().getListeCartePlateau());
            System.out.println("\nQuel est l'index de la carte Croyant à guider ?");
            boolean success = false;
            int idCarte = 0;
            do {
                try {
                    idCarte = this.choisirIndexCroyantGuider(Plateau.getInstance().getListeCartePlateau(), guideSpirituel);
//                    if (Plateau.getInstance().getListeCartePlateau().get(id) instanceof Croyant) {
                    success = true;
//                    }
                } catch (Exception e) {
                    System.out.println("Mauvaise Carte");
                }
            } while (!success);
            Croyant croyant = ((Croyant) Plateau.getInstance().getListeCartePlateau().get(idCarte));
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    if (guideSpirituel.listeDogmes.get(i) == croyant.listeDogmes.get(j)) {
                        peutEtreGuider = true;
                    }
                }
            }
            if ((!croyant.getNouveauCroyant()) && (guideSpirituel.croyantGuider.size() <= guideSpirituel.getNbCroyantMax()) && (peutEtreGuider)) {
                guideSpirituel.croyantGuider.add(croyant);
                croyant.setEstGuide(true);
                Plateau.getInstance().getListeCartePlateau().remove(croyant);
                System.out.println("Vous guidez ce croyant.");
            } else {
                System.out.println("Vous ne pouvez pas guider ce croyant.");
            }
            if (guideSpirituel.croyantGuider.size() >= guideSpirituel.getNbCroyantMax()) {
                carteSup = 0;
            }
            System.out.println("\n Voulez vous guider un croyant supplémentaire ? Oui (1) Non (0)");
            carteSup = this.choisirBinaire();
        }

//        joueur.getCartesPlateauPerso().getListeCartePlateau().add(guideSpirituel);
//        joueur.getMainJoueur().getListeCartes().add(guideSpirituel);
    }


    /**
     * Fonction permettant de choisir un joueur à attaquer
     * @return int
     */
    public int choixVictime() {
        System.out.println("\nListe des joueurs actifs : ");
        Partie.getInstance().afficherAdversaires(Partie.getInstance().joueursActifs);
        int index = this.choisirVictime();
        return index;
    }

    /**
     * Fonction permettant de choisir plusieurs joueurs à attquer
     * @return ArrayList<Joueur>
     */
    public ArrayList<Joueur> choixJoueursMultiples() {
        ArrayList<Joueur> joueursMultiples = new ArrayList<Joueur>();
        System.out.println("\nChoisissez plusieurs joueurs grâce à leur index. Appuyez sur T pour terminer.");
        int joueursActifsArray[] = this.choisirVictimesMultiples();
        int i = joueursActifsArray.length;
        for (int j = 0; j < i; j++) {
            if (joueursActifsArray[j] != this.getId()) {
                joueursMultiples.add(Partie.getInstance().joueursActifs.get(joueursActifsArray[j]));
            }
        }
        return joueursMultiples;
    }

    /**
     * Fonction proposant de rajouter des croyants à un guide déjà posé.
     */
    public void guider() {
        System.out.println("Voulez-vous ajouter des croyants à un guide déjà posé ?");
        int decision = this.choisirBinaire();
        boolean hasGuide = false;
        if (decision == 1) {
            ArrayList<CarteAction> cartesDejaPosees = this.getCartesPlateauPerso().getListeCartePlateau();
            Iterator<CarteAction> carteActionIterator = cartesDejaPosees.iterator();
            while (carteActionIterator.hasNext()) {
                CarteAction carteAction = carteActionIterator.next();
                if (carteAction instanceof GuideSpirituel) {
                    hasGuide = true;
                }
            }
            if (hasGuide) {
                afficherCartes(cartesDejaPosees);
                int idGuide = this.choisirIndexGuide(this);
                GuideSpirituel guideSpirituel = (GuideSpirituel) this.getCartesPlateauPerso().getListeCartePlateau().get(idGuide);
                this.guiderCroyant(guideSpirituel);
            } else {
                System.out.println("\n Vous ne possedez pas de guide.");
            }
        }
    }

    /**
     * Fonction permettant de choisir une carte à sacrifier
     */
    public void sacrifier() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sacrifier un guide (0) ou un croyant (1)");
        int i = this.choisirBinaire();
        switch (i) {
            default: //0
                this.sacrifierGuide();
                break;
            case 1:
                this.sacrifierCroyant();
                break;
        }
    }

    /**
     * Fonction permettant de choisir un guide a sacrifier
     */
    public void sacrifierGuide() {
        System.out.println("Quel est l'index du guide que vous voulez sacrifier ?");
        ArrayList<CarteAction> guidesDuJoueur = new ArrayList<CarteAction>();
        Iterator<CarteAction> carteActionIterator = this.getMainJoueur().getListeCartes().iterator();
        while (carteActionIterator.hasNext()) {
            GuideSpirituel guideSpirituel = (GuideSpirituel) carteActionIterator.next();
            guidesDuJoueur.add(guideSpirituel);
        }
        this.afficherCartes(guidesDuJoueur);
        int indexGuideSacrifier = this.choisirIndexGuide(this);
        GuideSpirituel guideSacrifier = (GuideSpirituel) this.getCartesPlateauPerso().getListeCartePlateau().get(indexGuideSacrifier);
        System.out.println("Vous sacrifier la carte numéro : " + indexGuideSacrifier + "\r ses croyants seront remis au centre.");
        CapaciteSpeciale.capaciteSpeciale(this, guideSacrifier);
        if (guideSacrifier.getDefausser() == true) {
            Iterator<Croyant> croyantIterator = guideSacrifier.croyantGuider.iterator();
            while (croyantIterator.hasNext()) {
                Croyant croyant = croyantIterator.next();
                guideSacrifier.croyantGuider.remove(croyant);
                Plateau.getInstance().getListeCartePlateau().add(croyant);
            }
            Defausse.getInstance().defausser(guideSacrifier);
            this.getCartesPlateauPerso().getListeCartePlateau().remove(guideSacrifier);
        }
    }

    /**
     * Fonction permettant de choisir un croyant à sacrifier
     */
    public void sacrifierCroyant() {
        System.out.println("Quel est l'index du guide qui possède le croyant ?");
        ArrayList<CarteAction> guidesDuJoueur = new ArrayList<CarteAction>();
        Iterator<CarteAction> carteActionIterator = this.getMainJoueur().getListeCartes().iterator();
        while (carteActionIterator.hasNext()) {
            GuideSpirituel guideSpirituel = (GuideSpirituel) carteActionIterator.next();
            guidesDuJoueur.add(guideSpirituel);
        }
        int indexGuide = this.choisirIndexGuide(this);
        GuideSpirituel guideSpirituel = (GuideSpirituel) this.getCartesPlateauPerso().getListeCartePlateau().get(indexGuide);
        ArrayList<CarteAction> croyantsDuGuide = new ArrayList<CarteAction>();
        Iterator<Croyant> croyantIterator = guideSpirituel.croyantGuider.iterator();
        while (croyantIterator.hasNext()) {
            Croyant croyant = (Croyant) carteActionIterator.next();
            croyantsDuGuide.add(croyant);
        }
        this.afficherCartes(croyantsDuGuide);
        System.out.println("Quel est l'index du croyant que vous voulez sacrifier ?");
        int indexCroyant = choisirIndexCroyant(guideSpirituel);
        Croyant croyantVictime = guideSpirituel.croyantGuider.get(indexCroyant);
        if (!croyantVictime.getNouveauCroyant()) {
            CapaciteSpeciale.capaciteSpeciale(this, croyantVictime);
            if (croyantVictime.getDefausser()) {
                Defausse.getInstance().defausser(croyantVictime);
                guideSpirituel.croyantGuider.remove(croyantVictime);
            }
        } else {
            System.out.println("La carte croyant vient d'être posée.");
        }
    }

    /**
     * Méthode demandant au joueur s'il veut poser une carte. Fait appel à une méthode qui peut être redéfinie lorsque c'est un joueur virtuel.
     * @return int
     */
    public int choisirPoserCartePlateau() {
        return this.choisirBinaire();
    }

    /**
     * Méthode retournant un choix binaire. Elle est redéfinie lorsque qu'on fait appel à un joueur virtuel.
     * @return int
     */
    public int choisirBinaire() {
        return 3;
    }

    /**
     * Méthode retournant le choix du joueur concernant le sacrifice d'une carte. Elle est redéfinie lorsque qu'on fait appel à un joueur virtuel.
     * @return int
     */
    public int choisirSacrifier() {
        return 3;
    }

    /**
     * Méthode renvoyant l'indice de la carte à poser. Elle est redéfinie lorsque qu'on fait appel à un joueur virtuel.
     * @return int
     */
    public int choisirIndexCarteAPoser() {
        return 3;
    }

    /**
     * Méthode renvoyant la liste des cartes à défausser. Elle est redéfinie lorsque qu'on fait appel à un joueur virtuel.
     * @return int
     */
    public int[] choisirCartesADefausser() {
        int[] A = {1, 2, 3};
        return A;
    }

    /**
     * Méthode renvoyant l'indice du guide sujet de l'action. Elle est redéfinie lorsque qu'on fait appel à un joueur virtuel.
     * @param joueur Joueur
     * @return int
     */
    public int choisirIndexGuide(Joueur joueur) {
        return 3;
    }

    /**
     * Méthode permettant de choisir l'indice du croyant victime d'une attaque. Elle est redéfinie lorsque qu'on fait appel à un joueur virtuel.
     * @param joueur Joueur
     * @return int
     */
    public int choisirIndexCroyantVictime(Joueur joueur) {
        return 3;
    }

    /**
     * Méthode permettant de choisir l'indice du croyant a guider. Elle est redéfinie lorsque qu'on fait appel à un joueur virtuel.
     * @param guideSpirituel GuideSpirituel
     * @return int
     */
    public int choisirIndexCroyant(GuideSpirituel guideSpirituel) {
        return 3;
    }

    /**
     * Méthode permettant de choisir une victime. Elle est redéfinie lorsque qu'on fait appel à un joueur virtuel.
     * @return int
     */
    public int choisirVictime() {
        return 0;
    }

    /**
     * Méthode permettant de choisir plusieurs victimes. Elle renvoie un tableau avec leur id. Elle est redéfinie lorsque qu'on fait appel à un joueur virtuel.
     * @return int[]
     */
    public int[] choisirVictimesMultiples() {
        int A[] = {1, 2, 3};
        return A;
    }

    /**
     * Méthode permettant de choisir l'influence du jour.
     * @return int
     */
    public int choisirJourNuitNeant() {
        return 0;
    }

}
