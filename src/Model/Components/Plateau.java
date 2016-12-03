package model.components;

import model.cards.Carte;
import model.cards.CarteAction;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class Plateau {

    public ArrayList<CarteAction> listeCartePlateau;
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
        Scanner scan = new Scanner(System.in);
        int s = scan.nextInt();
        boolean peutPoser = false;
        CarteAction carteAPoser = joueur.choisirCarte(s);
        switch (carteAPoser.origine){
            case Jour:
                if (joueur.pointJour > 0) {
                    peutPoser = true;
                    joueur.pointJour -= 1;
                }
                else {
                    System.out.println("Vous n'avez pas assez de points.");
                }
                break;
            case Nuit:
                if (joueur.pointNuit > 0) {
                    peutPoser = true;
                    joueur.pointNuit -= 1;
                }
                else {
                    System.out.println("Vous n'avez pas assez de points.");
                }
                break;
            case Néant:
                if (joueur.pointNeant > 0) {
                    peutPoser = true;
                    joueur.pointNeant -= 1;
                }
                else {
                    System.out.println("Vous n'avez pas assez de points.");
                }
                break;
        }
    }
}
