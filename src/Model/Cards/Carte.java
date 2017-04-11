package model.cards;

import model.components.Dogme;
import model.components.Origine;

import java.util.ArrayList;

/**
 * Classe de base
 */

public class Carte{
    public Origine origine;
    public ArrayList<Dogme> listeDogmes;
    protected String nom;
    protected String capaciteSpeciale;
    protected TypeCarte typeCarte;

    /**
     * Constructeur des cartes actions
     * @param nom String
     * @param origine Origine
     * @param typeCarte TypeCarte
     */
    public Carte(String nom, Origine origine, TypeCarte typeCarte){
        this.nom = nom;
        this.origine = origine;
        this.listeDogmes = new ArrayList<Dogme>();
        this.typeCarte = typeCarte;
    }

    /**
     * Retourne l'origine de la carte
     * @return Origine
     */
    public Origine getOrigine() {
        return origine;
    }

    /**
     * Retourne le type de carte (Apocalypse, Croyant, Deus Ex, Divinite, Guide Spirituel)
     * @return TypeCarte
     */
    public TypeCarte getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(TypeCarte typeCarte) {
        this.typeCarte = typeCarte;
    }

    public String toString(){
        return "[TYPE] '" + this.typeCarte + "' [NOM] '" + this.nom + "' [ORIGINE] '" + this.origine + "'";
    }
}
