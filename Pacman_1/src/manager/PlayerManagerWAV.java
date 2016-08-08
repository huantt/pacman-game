package manager;

/**
 * Created by Huan on 8/5/2016.
 */
public class PlayerManagerWAV {
    private static PlayerManagerWAV instance;
    private SoundPlayerWAV sound_eatItem;
    private SoundPlayerWAV sBullet;
    private SoundPlayerWAV sStartgame;
    private SoundPlayerWAV sDie;
    private SoundPlayerWAV sEatBullet;
    private SoundPlayerWAV sHu;
    private SoundPlayerWAV sWin;

    public PlayerManagerWAV() {
        sStartgame = new SoundPlayerWAV("sound_start");
        sHu = new SoundPlayerWAV("sound_hu");

    }

    public SoundPlayerWAV getsWin() {
        sWin =new SoundPlayerWAV("win");
        return sWin;
    }

    public SoundPlayerWAV getsHu() {
        return sHu;
    }

    public SoundPlayerWAV getsEatBullet() {
        sEatBullet = new SoundPlayerWAV("sound_eatBullet");
        return sEatBullet;
    }

    public SoundPlayerWAV getsStartgame() {
        return sStartgame;
    }

    public SoundPlayerWAV getsDie() {
        sDie = new SoundPlayerWAV("sound_die");
        return sDie;
    }

    public SoundPlayerWAV getSEatItem() {
        sound_eatItem = new SoundPlayerWAV("sound_eatItem");
        return sound_eatItem;
    }

    public SoundPlayerWAV getsBullet() {
        sBullet = new SoundPlayerWAV("sound_bullet");
        return sBullet;
    }

    public static synchronized PlayerManagerWAV getInstance() {
        if (instance == null) {
            instance = new PlayerManagerWAV();
        }
        return instance;
    }
}
