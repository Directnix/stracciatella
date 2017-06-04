package Score;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nick van Endhoven, 2119719 on 5/15/2017.
 */
public class ScoreLog implements Serializable{
    Date date;
    String opponentUserName, username;
    int playerScore, opponentScore;

    public ScoreLog(Date date, String username, String opponentUserName, int playerScore, int opponentScore) {
        this.date = date;
        this.username = username;
        this.opponentUserName = opponentUserName;
        this.playerScore = playerScore;
        this.opponentScore = opponentScore;
    }

    @Override
    public String toString() {
        return "ScoreLog{" +
                "date=" + date +
                ", opponentUserName='" + opponentUserName + '\'' +
                ", playerScore=" + playerScore +
                ", opponentScore=" + opponentScore +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOpponentUserName() {
        return opponentUserName;
    }

    public void setOpponentUserName(String opponentUserName) {
        this.opponentUserName = opponentUserName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(int opponentScore) {
        this.opponentScore = opponentScore;
    }
}
