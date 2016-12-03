import model.kernel.Partie;

import java.util.Scanner;

/**
 * Created by jrfoehn on 12/1/16.
 */
public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("Combien de joueurs humains ?");
        int humain = sc.nextInt();
        System.out.println("Combien de joueurs virtuels ?");
        int virtuel = sc.nextInt();

        while (humain + virtuel < Partie.MIN_JOUEURS) {
            System.out.println("Il n'y a qu'un seul joueur d'enregistré !");
            System.out.println("Combien de joueurs humains ?");
            humain = sc.nextInt();
            System.out.println("Combien de joueurs virtuels ?");
            virtuel = sc.nextInt();
        }
        while (humain + virtuel > Partie.MAX_JOUEURS) {
            System.out.println("Il y'a trop de joueurs d'enregistrés ! Divinae se joue à 6 au maximum.");
            System.out.println("Combien de joueurs humains ?");
            humain = sc.nextInt();
            System.out.println("Combien de joueurs virtuels ?");
            virtuel = sc.nextInt();
        }

        Partie partie = Partie.parametrerPartie(humain, virtuel);


    }
}
