package fr.studi.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Score {
   
    private int score;

    private String username;

    public Score(int score, String username) {
      this.score = score;
      this.username = username;
    }
    
}
