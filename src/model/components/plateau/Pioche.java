package model.components.plateau;

import model.cards.*;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Pioche commune aux joueurs
 */
public class Pioche extends TasDeCarte {

    private static Pioche instance;

    private Pioche(){
        super();
    }

    public static Pioche getInstance() {
        if (instance == null) {
            instance = new Pioche();
        }
        return instance;
    }

    /**
     * Méthode permettant d'initialiser l'ensemble des cartes
     */
    public void initialiserCartes() {
        Croyant.initialiserCroyant();
        GuideSpirituel.initialiserGuideSpirituel();
        DeusEx.initialiserDeusEx();
        Apocalypse.initialiserApocalypse();
        Collections.shuffle(listeCartes);
    }

    /**
     * Méthode permettant de piocher une carte
     * @return CarteActioni
     */
    public CarteAction piocher() {
        if (this.listeCartes.isEmpty()) {
            Defausse.getInstance().transfererPioche();
        }
        this.listeCartes.remove(0);
        return this.listeCartes.get(0);
    }

    public void addCarte(CarteAction carte) {
        listeCartes.add(carte);
    }

    public ArrayList<CarteAction> getCartes() {
        return this.listeCartes;
    }



}

