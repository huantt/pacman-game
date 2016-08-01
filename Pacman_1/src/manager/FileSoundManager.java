package manager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

/**
 * Created by Huan on 8/1/2016.
 */
public class FileSoundManager {
    public static final File SOUND_INTRO = new File(getURL("sound_start.mp3").getPath());

    public static URL getURL(String fileName) {
        return ImageLoader.class.getResource("/res/sounds/" + fileName);
    }
}
