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
        try {
            randomAccessFile = new RandomAccessFile(data, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        readData();
    }

    public void readData() {
        listPlayer = new ArrayList<>();
        String line;
        try {
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
        if (listPlayer.size() == 0) {
            listPlayer.add(player);
            try {
                System.out.println(player.toString().getBytes());
                randomAccessFile.write(player.toString().getBytes());
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else if (player.getScore() > listPlayer.get(listPlayer.size() - 1).getScore()) {
            listPlayer.remove(listPlayer.size() - 1);
            listPlayer.add(player);
            try {
                randomAccessFile.write(player.toString().getBytes());
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;

        }
        return false;
    }

}
