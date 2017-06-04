import Score.ScoreLog;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Nick van Endhoven, 2119719 on 5/16/2017.
 */
public class ScoreManager implements Serializable {

    public static ScoreManager instance;

    ArrayList<ScoreLog> logs = new ArrayList<>();

    public static ScoreManager getInstance() {
        if (instance == null)
            instance = new ScoreManager();
        return instance;
    }

    private ScoreManager() {
    }

    void save() {
        try (
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save.strac"));
        ) {
            out.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     void load() {
        try (
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("save.strac"))
        ) {
            ScoreManager result = (ScoreManager) in.readObject();
            logs = result.logs;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void addLog(ScoreLog log) {
        logs.add(log);
    }
}
