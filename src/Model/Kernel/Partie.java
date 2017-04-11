package model.kernel;

import java.util.*;

import controller.DisplayCLIController;
import model.cards.*;
import model.components.joueur.Joueur;
import model.components.*;
import model.components.joueur.JoueurHumain;
import model.components.joueur.JoueurVirtuel;
import model.components.plateau.Pioche;
import model.components.plateau.Plateau;

/**
 * Classe contenant l'ensemble des méthodes permettant le déroulement d'une partie.
 */
public class Partie{
    public final static int MAX_JOUEURS = 6;
    public final static int MIN_JOUEURS = 2;
    public static ArrayList<Divinite> divinite = new ArrayList<Divinite>();
    private static Partie instance;
    private boolean apocalypsePoseeTourPrecedent = false;
    private boolean apocalypsePossible = false;
    private int nbJoueurHumain;
    private int nbJoueurVirtuel;
    private int joueurActuel;
    private int nbTour;
    public Joueur vainqueur;
    private boolean fin = false;
    private TypePartie type;
    private Origine orgineTour;
    public ArrayList<Joueur> joueursActifs;
    public ArrayList<Joueur> joueursElimines;

    /**
     * Constructeur d'une partie. Demande le nombre de joueurs humains et virtuels
     * @param nbJoueurHumain int
     * @param nbJoueurVirtuel int
     */
    public Partie(int nbJoueurHumain, int nbJoueurVirtuel) {
        this.joueursActifs = new ArrayList<Joueur>();
        this.joueursElimines = new ArrayList<Joueur>();
        for (int i = 0; i < nbJoueurHumain; i++) {
            this.joueursActifs.add(new JoueurHumain(i));
        }
        for (int i = nbJoueurHumain; i < (nbJoueurHumain+nbJoueurVirtuel); i++){
            System.out.println("Voulez-vous que le joueur [" + i + "] adopte une stratégie random (0), Offensive (1), ou Défensive (2)");
            Scanner scanner = new Scanner(System.in);
            int strat = scanner.nextInt();
            this.joueursActifs.add(new JoueurVirtuel(i, strat));
        }
    }

    public static Partie getInstance(){
        return instance;
    }

    public Origine getOrgineTour(){
        return orgineTour;
    }

    public void setOrgineTour(Origine origineTour){
        this.orgineTour = origineTour;
    }

    public boolean getFin() {
        return fin;
    }

    public void setFin(boolean fin){
        this.fin = fin;
    }

    public static ArrayList<Divinite> getDivinite() {
        return divinite;
    }

    /**
     * Fonction permettant de parametrer la partie en prenant en compte le nb de joueur humain + virtuel
     * @param nbJoueurHumain int
     * @param nbJoueurVirtuel int
     * @return Partie
     */
    public static Partie parametrerPartie(int nbJoueurHumain, int nbJoueurVirtuel){
        if (instance == null) {
            instance = new Partie(nbJoueurHumain, nbJoueurVirtuel);
        }
        return instance;
    }

    /**
     * Etablie les conditions d'une partie
     */
    public void conditionsPartie() {
        Iterator<Joueur> joueurIterator = Partie.getInstance().joueursActifs.iterator();
        while (joueurIterator.hasNext()) {
            Joueur joueur = joueurIterator.next();
            joueur.setPointAction(true);
            joueur.setSacrifierCroyant(true);
            joueur.setSacrifierGuideSpirituel(true);
        }
    }

    /**
     * Fonction permettant de lancer la partie
     */
    public void commencerPartie() {
        this.attribuerDivinite();
        this.attribuerCarte();
        Scanner scan = new Scanner(System.in);
        Iterator<Joueur> j = this.joueursActifs.iterator();
        while (j.hasNext()) {
            Joueur joueur = j.next();
            int i = this.joueursActifs.indexOf(joueur);
            System.out.println("\n ================================================================================================================= \n Joueur numéro " + i);
            DisplayCLIController.afficherDivinite(this.joueursActifs.get(i).getDivinite());
            DisplayCLIController.showCarte(this.joueursActifs.get(i).getMainJoueur().getListeCartes());
        }
    }

    public void tourSuivant() {
        this.nbTour += 1;
        Scanner scanner = new Scanner(System.in);
        if ((nbTour >= 1) && !apocalypsePoseeTourPrecedent) {
            this.apocalypsePossible = true;
        }
        this.conditionsPartie();
        DisplayCLIController.lancerDe();
        for (int i = 0; i < this.joueursActifs.size(); i ++) {
            Iterator<CarteAction> carteActionIteratorPlateau = Plateau.getInstance().getListeCartePlateau().iterator();
            while (carteActionIteratorPlateau.hasNext()) {
                Croyant croyantPlateau = (Croyant) carteActionIteratorPlateau.next();
                croyantPlateau.setNouveauCroyant(false);
            }
            ArrayList<CarteAction> carteDuJoueur = this.joueursActifs.get(i).getMainJoueur().getListeCartes();
            Joueur joueur = this.joueursActifs.get(i);
            ajouterPoint(joueur);
            DisplayCLIController.afficherPoints(joueur);
            DisplayCLIController.afficherCarteJoueur(i);
            joueur.afficherMainJoueur();
            DisplayCLIController.afficherDefausse();
            scanner = new Scanner(System.in);
            joueur.defausserCartesMultiples(carteDuJoueur);
            if (carteDuJoueur.size() < 7) {
                DisplayCLIController.afficherCompleterDeck();
                joueur.completerDeckJoueur();
            }
            int pointActionTotal = joueur.getPointJour() + joueur.getPointNeant() + joueur.getPointNuit();
            if (pointActionTotal > 0) {
                joueur.poserCarteSurPlateau();
            }
            else {
                System.out.println("\n Joueur " + joueur.getId() + " vous ne possédez pas de points d'actions. Vous ne pouvez donc pas poser de cartes.");
            }
            joueur.choixSacrifier();
            joueur.guider();
        }
        this.joueursActifs.add((this.joueursActifs.get(0)));
        this.joueursActifs.remove(0);
    }

    /**
     * Fonction permettant de lancer le dé, et ainsi déterminer l'influence du tour
     */
    public void lancerDe(){
        int origine = (int)Math.floor(Math.random()*3);
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextLine()){
            // on attend l'appuie sur la touche entrée
        }
        switch (origine) {
            case 0 : this.setOrgineTour(Origine.Jour);
                break;
            case 1 : this.setOrgineTour(Origine.Nuit);
                break;
            case 2 : this.setOrgineTour(Origine.Neant);
        }
        System.out.println("\n Le tour est d'influence : " + this.getOrgineTour());
    }

    /**
     * Fonction permettant d'attribuer les points d'actions
     * @param joueur Joueur
     */
    public void ajouterPoint(Joueur joueur) {
        if (joueur.getPointAction() == true) {
            int ptJour = joueur.getPointJour();
            int ptNuit = joueur.getPointNuit();
            int ptNeant = joueur.getPointNeant();
            if (this.getOrgineTour() == Origine.Jour) {
                if (joueur.getDivinite().origine == Origine.Jour) {

                    joueur.setPointJour(ptJour + 2);
                }
                else if (joueur.getDivinite().origine == Origine.Aube) {
                    joueur.setPointJour(ptJour + 1);
                }
            }
            if (this.getOrgineTour() == Origine.Nuit) {
                if (joueur.getDivinite().origine == Origine.Nuit) {
                    joueur.setPointNuit(ptNuit + 2);
                }
                else if (joueur.getDivinite().origine == Origine.Crepuscule) {
                    joueur.setPointNuit(ptNuit + 1);
                }
            }
            if (this.getOrgineTour() == Origine.Neant) {
                if ((joueur.getDivinite().origine == Origine.Aube) || (joueur.getDivinite().origine == Origine.Crepuscule)) {
                    joueur.setPointNeant(ptNeant + 1);
                }
            }
        }
    }

    /**
     * Fonction permettant d'attribuer une divinité à chaque joueur
     */
    public void attribuerDivinite() {
        Divinite.initialiserDivinite();
        Collections.shuffle(divinite);
        /*On attribue un divinité à chaque joueur actif (= tous les joueurs instanciés en début de partie)*/
        Iterator<Joueur> j = this.joueursActifs.iterator();
        while (j.hasNext()) {
            Joueur joueurAttribuer = j.next();
            Divinite diviniteAttribuer = (divinite).get(0);
            divinite.remove(0);
            joueurAttribuer.setDivinite(diviniteAttribuer);
        }
    }

    /**
     * Fonction permettant de distribuer les cartes au début de la partie
     */
    public void attribuerCarte() {
        Pioche pioche = Pioche.getInstance();
        Iterator<Joueur> j = this.joueursActifs.iterator();
        while (j.hasNext()) {
            Joueur tmp = j.next();
            for (int i = 0; i < 7; i++) {
                CarteAction nouvelleCarte = pioche.piocher();
                tmp.getMainJoueur().getListeCartes().add(nouvelleCarte);
            }
        }
    }

    /**
     * Fonction permettant d'afficher les adversaires
     * @param listeJoueurs ArrayList<Joueur>
     */
    public void afficherAdversaires (ArrayList<Joueur> listeJoueurs) {
        Iterator<Joueur> joueurIterator = listeJoueurs.iterator();
        while (joueurIterator.hasNext()) {
            Joueur joueur = joueurIterator.next();
            System.out.println("Le joueur " + joueur.getId() + "est encore actif");
        }
    }

}
