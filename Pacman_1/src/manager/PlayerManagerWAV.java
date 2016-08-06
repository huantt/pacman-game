package manager;

import javax.sound.sampled.Clip;

/**
 * Created by Huan on 8/5/2016.
 */
public class PlayerManagerWAV {
    public static PlayerManager instance;
    private SoundPlayerWAV sBG;
    private SoundPlayerWAV sBullet;

    public PlayerManagerWAV() {
        sBG = new SoundPlayerWAV("win");
        sBG.loop(Clip.LOOP_CONTINUOUSLY);
        sBullet = new SoundPlayerWAV("win");
    }

    public static synchronized PlayerManager getInstance() {
            if (instance == null) {
                instance = new PlayerManager();
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
