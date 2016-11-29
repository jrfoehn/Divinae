package Tools;

import Model.Cards.CarteAction;

/**
 * Created by jrfoehn on 11/29/16.
 */
public interface Strategy {
    public int choisirCarteAJouer();
    public int choixAction(CarteAction carteAction);
    public int choisirAdversaire();
    public int choixDebutManche();
}
