package model.cards;

import model.components.Origine;

/**
 * Classe modélisant les cartes d'action
 */
public abstract class CarteAction extends Carte {

    protected int idCarte;
    protected int idCapaciteSpeciale;
    protected boolean defausser = true;
    protected boolean capaciteUtilisable = true;
    protected TypeCarte typeCarte;

    /**
     * Constructeur carte action
     * @param nom String
     * @param origine Origine
     * @param typeCarte TypeCarte
     * @param idCapaciteSpeciale int
     * @param idCarte int
     */
    public CarteAction(String nom, Origine origine, TypeCarte typeCarte, int idCapaciteSpeciale, int idCarte) {
        super(nom, origine, typeCarte);

    }

    /*Liste des getters / setters*/

    public int getIdCarte() {
        return this.idCarte;
    }

    public int getIdCapaciteSpeciale() {
        return this.idCapaciteSpeciale;
    }

    public boolean getDefausser() {
        return this.defausser;
    }

    public void setDefausser(boolean defausser) {
        this.defausser = defausser;
    }

    public boolean getCapaciteUtilisable() {
        return capaciteUtilisable;
    }

    public void setCapaciteUtilisee(boolean capaciteUtilisable) {
        this.capaciteUtilisable = capaciteUtilisable;
    }

    @Override
    public String toString(){
        return super.toString();
    }

}
