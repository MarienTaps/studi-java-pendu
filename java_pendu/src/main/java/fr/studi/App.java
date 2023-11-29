package fr.studi;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.studi.game.Pendu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Bienvenu dans le jeu du pendu!" );
        
        initializeGame();
        
        // un mot à trouver, nombre erreur

    }

    private static void initializeGame(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer le mot à trouver :");
        String mot = scanner.next();
        System.out.println("Entre le nombre d'erreur maximum :");
        int erreur = checkInput(scanner) ;
        //System.out.println(erreur);
        Pendu jeuPendu = new Pendu(mot.toLowerCase(), erreur);
        jeuPendu.jouer();

    }

    private static int checkInput(Scanner scanner){
        int reponse = 0;
        boolean check =false;

        while (!check) {
                   
          try {
             reponse =scanner.nextInt();
             check=true;
            
           } catch (InputMismatchException e) {
            System.out.println("La saisie incorrete merci de réessayer !");
            scanner.next();
           }
        }
        return reponse;
    }
}
