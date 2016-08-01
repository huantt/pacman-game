package manager;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Huan on 8/1/2016.
 */
public class SoundPlayer {
    Player player;

    public void playSound(File fileSound){
        try {
            FileInputStream sound = new FileInputStream(fileSound);
            player = new Player(sound);
            player.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
