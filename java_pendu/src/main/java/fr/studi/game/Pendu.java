package fr.studi.game;

import java.util.Scanner;
import java.util.stream.IntStream;

import fr.studi.dao.ScoreDao;
import fr.studi.dao.impl.ScoreDAOImpl;
import fr.studi.pojo.Score;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pendu {

  // mot à trouver
  private String mot;

  // mot actuel
  private StringBuilder motCourant;
  // nombre  Max erreur 
  private int maxErreur;
  
  
  // nombre erreur courante
  private int erreur;
 
  // Lettres qui ont été utilisés
  private StringBuilder lettresUtilisees;



  public Pendu(String mot, int erreur) {
    this.mot = mot;
    this.erreur =0;
    this.lettresUtilisees =new StringBuilder();
    this.maxErreur = erreur;
    this.motCourant= new StringBuilder("_".repeat(mot.length()));

  }
  
  public void jouer(){
    Scanner scanner = new Scanner(System.in);
    //afficherMotCourant();

    // Si mon nombre d'erreur actuel est inférieur au nombre d'erreur max
    // je peux jouer
    while (erreur<maxErreur) {

      // rappel des précédenten information
      afficherMotCourant();
      afficherLettresUtilisees();
      afficheErreur();

      System.out.println("Entrer une lettre :");
      char lettreSaisie = scanner.next().toLowerCase().charAt(0) ;

      // Vérification de la légitimité de la lettre
      if(estLettreValide(lettreSaisie)&& !lettresUtilisees.toString().contains(String.valueOf(lettreSaisie))){
        lettresUtilisees.append(lettreSaisie);
        if(mot.contains(String.valueOf(lettreSaisie))){
          mettreAjourMotCourant(lettreSaisie);
          
        } else{
           erreur++; 
        }
      } else{
         System.out.println("Lettre invalide ou déjà utilisée. Réessayez !");
      }
          // affiche d'une phrase si j'ai trouvé le mot
          if(motCourant.toString().equals(mot)){
             afficherMotCourant();
             System.out.println("Bravo, vous avez gagné ! ");
             // Sauvegarde scrore
             ScoreDao scoreDao = new ScoreDAOImpl();
             System.out.print("entrer votre pseudo :");
             String pseudo = scanner.next();
             scoreDao.sauvegarderScore(new Score(erreur, pseudo));

             System.exit(0);
          }
    }
       //affichage d'une phase indiquant que j'ai perdu
       System.out.println("Désolé, vous avez perdu, le mot était : "+ mot);
       scanner.close();
  }


  private void afficheErreur(){
    System.out.println("Vous avez " + erreur + " Erreurs sur " + maxErreur + "." );
  }

  private void mettreAjourMotCourant(char lettreSaisie){
    for(int i=0; i< mot.length(); i++){
      if(mot.charAt(i)== lettreSaisie){
        motCourant.setCharAt(i, lettreSaisie);
      }
    }
  }
  private void afficherMotCourant(){
    //System.out.println("le mot actuel est : "+ this.motCourant.toString());
    /*System.out.println("le mot actuel est : "+ IntStream.range(0, motCourant.length())
              IntStream.mapToObj(index -> motCourant.charAt(index)+ (index < mot.length() -1 ? ",":"" ))
              .IntStream.collect); */

            StringBuffer motAvecVirgure = new StringBuffer();
            for( int i=0; i<motCourant.length(); i++ ){
              char lettre = this.motCourant.charAt(i);
              motAvecVirgure.append(lettre);

              if (i <motCourant.length()-1) {
                motAvecVirgure.append(",");
                
              }
            }
            System.out.println("Le mot actuel est : "+ motAvecVirgure);
  }
  
  private void afficherLettresUtilisees(){
   System.out.println("les lettres utilisées sont : "+ this.lettresUtilisees.toString()); 
  }

  private boolean estLettreValide(char lettre){
    return Character.isLetter(lettre);
  }
}
