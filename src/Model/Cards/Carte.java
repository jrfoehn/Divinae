package model.cards;

import model.components.Dogme;
import model.components.Origine;

import java.util.ArrayList;

/**
 * Created by jrfoehn on 11/29/16.
 */
public class Carte{
    public Origine origine;
    protected ArrayList<Dogme> listeDogmes;
    protected String nom;
    protected String capaciteSpeciale;

    public Carte(String nom, Origine origine){
        this.nom = nom;
        this.origine = origine;
        this.listeDogmes = new ArrayList<Dogme>();
    }

    public String toString(){
        return "Nom : " + this.nom + " Origine : " + this.origine;
    }
}
