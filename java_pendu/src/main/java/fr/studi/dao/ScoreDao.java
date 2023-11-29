package fr.studi.dao;
import fr.studi.pojo.Score;
import java.util.List;
public interface ScoreDao {

  // Sauvegarder les scores 
  void sauvegarderScore(Score score);

  Score obtenirScore(String username);
 
  List<Score> obtenirTousLesScores();

}
