package model.cards;

import model.components.Origine;

/**
 * Created by jrfoehn on 11/29/16.
 */
public abstract class CarteAction extends Carte{
    protected int idCapaciteSpeciale;
    protected int idCarte;

    public CarteAction(String nom, Origine origine){
        super(nom, origine);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    /*Liste des getters / setters*/

    public int getIdCarte() {
        return this.idCarte;
    }

    public int getIdCapaciteSpeciale() {
        return this.idCapaciteSpeciale;
    }
}
