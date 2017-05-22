import Score.ScoreLog;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nick van Endhoven, 2119719 on 5/16/2017.
 */
public class ScoreManager implements Serializable {

    public static ScoreManager instance;

    ArrayList<ScoreLog> logs = new ArrayList<>();

    public static ScoreManager getInstance(){
        if(instance == null)
            instance = new ScoreManager();
        return instance;
    }

    private ScoreManager(){}

    void addLog(ScoreLog log){
        logs.add(log);
    }
}
