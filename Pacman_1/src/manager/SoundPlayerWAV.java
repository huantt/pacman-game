package manager;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Huan on 8/5/2016.
 */
public class SoundPlayerWAV implements LineListener {

    Clip clip;

    public SoundPlayerWAV(String fileName) {
        URL url = getClass().getResource("/res/sounds/" + fileName + ".wav");
        try {
            clip = AudioSystem.getClip();
            AudioInputStream input = AudioSystem.getAudioInputStream(url);
            clip.open(input);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip.isOpen() && !clip.isRunning()) {
            clip.start();
        }
    }

    public void stop() {
        clip.stop();
    }

    public void loop(int count) {
        clip.loop(count);
    }

    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP) {
            System.out.println("bai hat da stop");
        }
    }
}
