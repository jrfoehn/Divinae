package model.components.joueur;

import controller.DisplayCLIController;
import model.cards.CarteAction;
import model.cards.Croyant;
import model.cards.GuideSpirituel;
import model.components.capacite.CapaciteSpeciale;
import model.components.joueur.Joueur;
import model.components.plateau.Defausse;
import model.components.plateau.Plateau;
import model.kernel.Partie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class JoueurHumain extends Joueur {

    public JoueurHumain(int nom) {
        super(nom);
    }

    @Override
    public int choisirBinaire() {
        int decision;
        Scanner scanner = new Scanner(System.in);
        boolean exact = false;
        do {
            System.out.println("Veuillez saisir 0 ou 1.");
            try{
                decision = scanner.nextInt();
                if(decision == 0 || decision == 1) exact = true;
            }catch (Exception e){
                System.out.println("Veuillez saisir 0 ou 1.");
                decision = 2;
            }
        } while(!exact);
        return decision;
    }

    @Override
    public int choisirIndexCroyantGuider(ArrayList<CarteAction> cartesPossibles, GuideSpirituel guideSpirituel) {
        Scanner scanner = new Scanner(System.in);
        DisplayCLIController.showCarte(cartesPossibles);
        System.out.println("Quelle carte voulez-vous ajouter ?");
        return scanner.nextInt();
    }

    @Override
    public int[] choisirCartesADefausser() {
        Scanner scanner = new Scanner(System.in);
        int tableauCartesADefausser[] = new int[7];
        int nbCarteADefausser = 0;
        while (scanner.hasNextInt() && !scanner.hasNext("T")) {
            tableauCartesADefausser[nbCarteADefausser] = scanner.nextInt();
            nbCarteADefausser++;
        }
        return tableauCartesADefausser;
    }

    @Override
    public int choisirIndexCarteAPoser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    @Override
    public int choisirSacrifier() {
        return this.choisirBinaire();
    }

    @Override
    public int choisirJourNuitNeant() {
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();
        return choix;
    }

    @Override
    public int choisirIndexGuide(Joueur joueur) {
        return this.choisirIndexCarteAPoser();
    }

    public int choisirIndexCroyant(GuideSpirituel guideSpirituel) {

        return this.choisirIndexCarteAPoser();
    }

    @Override
    public void guider() {
        boolean hasGuide = false;
        Scanner scanner = new Scanner(System.in);
        Iterator<CarteAction> carteActionIterator = this.getCartesPlateauPerso().getListeCartePlateau().iterator();
        while (carteActionIterator.hasNext()) {
            CarteAction carteAction = carteActionIterator.next();
            if (carteAction instanceof GuideSpirituel) {
                hasGuide = true;
            }
        }
        if (hasGuide) {
            System.out.println("Voulez-vous guider un croyant avec l'un de vos guide posé ?");
            this.getCartesPlateauPerso().getListeCartePlateau();
            int veutGuider = scanner.nextInt();
            if (veutGuider == 1) {
                System.out.println("Entrez l'indice du guide : \r");
                DisplayCLIController.showCarte(this.getCartesPlateauPerso().getListeCartePlateau());
                int idGuide = scanner.nextInt();
                GuideSpirituel guideSpirituel = (GuideSpirituel) this.getCartesPlateauPerso().getListeCartePlateau().get(idGuide);
                System.out.println(guideSpirituel);
                this.guiderCroyant(guideSpirituel);
            }
        }
    }


    @Override
    public void sacrifierGuide() {
        this.choisirIndexCarteAPoser();
    }

    @Override
    public int choisirVictime() {
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        while (index == this.getId()) {
            System.out.println("Ne vous choisissez pas vous même");
            index = scanner.nextInt();
        }
        return index;
    }

    @Override
    public int choisirPoserCartePlateau() {
        return this.choisirBinaire();
    }

    @Override
    public int[] choisirVictimesMultiples() {
        Partie.getInstance().afficherAdversaires(Partie.getInstance().joueursActifs);
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        int joueursActifs[] = new int[Partie.getInstance().joueursActifs.size()];
        while (scanner.hasNextInt()) {
            joueursActifs[i] = scanner.nextInt();
            i++;
        }
        return joueursActifs;
    }
}
