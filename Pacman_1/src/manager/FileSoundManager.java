package manager;

import java.io.File;
import java.net.URL;

/**
 * Created by Huan on 8/1/2016.
 */
public class FileSoundManager {
    public static final File SOUND_INTRO = new File(getURL("sound_start.mp3").getPath());
    public static final File SOUND_PACMAN_DIE = new File(getURL("sound_die.mp3").getPath());

    public static URL getURL(String fileName) {
        return ImageLoader.class.getResource("/res/sounds/" + fileName);
    }
}
