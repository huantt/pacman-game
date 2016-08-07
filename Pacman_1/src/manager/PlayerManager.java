package manager;

import com.huantt.pacmangame.model.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Huan on 8/5/2016.
 */
public class PlayerManager {
    ArrayList<Player> listPlayer;
    File data;
    RandomAccessFile randomAccessFile;

    public PlayerManager() {
        data = new File(getClass().getResource("/data/DataPlayer.txt").getFile());
        readData();
    }

    public void readData() {
        try {
            randomAccessFile = new RandomAccessFile(data, "rw");
            listPlayer = new ArrayList<>();
            String line;
            while ((line = randomAccessFile.readLine()) != null) {
                System.out.println(line);
                String[] dataPlayer = line.split("-");
                listPlayer.add(new Player(dataPlayer[0], Integer.parseInt(dataPlayer[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Player> sortByScore() {
        listPlayer.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getScore() - o1.getScore();
            }
        });
        return listPlayer;
    }

    public boolean addPlayer(Player player) {
        if (listPlayer.size() < 5) {
            listPlayer.add(player);
            writeData();
            return true;
        } else if (player.getScore() > listPlayer.get(listPlayer.size() - 1).getScore()) {
            listPlayer.remove(listPlayer.size() - 1);
            listPlayer.add(player);
            writeData();
            return true;

        }
        return false;
    }

    public void writeData() {
        try {
            randomAccessFile = new RandomAccessFile(data, "rw");
            for (Player player : listPlayer) {
                randomAccessFile.write(player.toString().getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
