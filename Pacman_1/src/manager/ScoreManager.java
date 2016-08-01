package manager;

import com.huantt.pacmangame.model.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by Huan on 7/28/2016.
 */
public class ScoreManager {
    private ArrayList<Player> listPlayer = new ArrayList<>();
    private RandomAccessFile randomAccessFile;
    private File fileData;

    public ScoreManager() {
        fileData = new File(getClass().getResource("/Data/data.txt").getPath());
    }

    public ArrayList<Player> readData() {
        String lineData;
        Player player;

        try {
            randomAccessFile = new RandomAccessFile(fileData,"rw");
            randomAccessFile.seek(0);
            while ((lineData = randomAccessFile.readLine()) != null) {
                String[] data = lineData.split(" - ");
                System.out.println(lineData);
                player = new Player( data[0],Integer.parseInt(data[1]));
                listPlayer.add(player);
            }
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPlayer;
    }

    public void writeData(Player player) {
        try {
            randomAccessFile = new RandomAccessFile(fileData,"rw");
            randomAccessFile.seek(fileData.length());
            randomAccessFile.write(player.toString().getBytes());
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ScoreManager{" +
                "listPlayer=" + listPlayer +
                '}';
    }
}
