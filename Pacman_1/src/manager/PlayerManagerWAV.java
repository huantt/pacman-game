package manager;

import javax.sound.sampled.Clip;

/**
 * Created by Huan on 8/5/2016.
 */
public class PlayerManagerWAV {
    public static PlayerManagerWAV instance;
    private SoundPlayerWAV sBG;
    private SoundPlayerWAV sBullet;

    private PlayerManagerWAV() {
        sBG = new SoundPlayerWAV("Background");
        sBullet = new SoundPlayerWAV("win");
        sBG.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static synchronized PlayerManagerWAV getInstance() {
            if (instance == null) {
                instance = new PlayerManagerWAV();
            }
            return instance;
        }

    public SoundPlayerWAV getsBG() {
        return sBG;
    }

    public SoundPlayerWAV getsBullet() {
        return sBullet;
    }
}
