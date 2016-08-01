package manager;

import com.huantt.pacmangame.model.Pacman;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Huan on 7/24/2016.
 */
public class ImageLoader {
    public static final Image IMG_ITEM_STONE = new ImageIcon(getURL("brick.png")).getImage();
    public static final Image IMG_ITEM_BEAN_NORMAL = new ImageIcon(getURL("bean_normal.png")).getImage();
    public static final Image IMG_ITEM_BEAN_POWER = new ImageIcon(getURL("bean_power.png")).getImage();
    public static final Image IMG_BULLET = new ImageIcon(getURL("bullet.png")).getImage();
    public static final Image[] IMG_SWIRL = {
            new ImageIcon(getURL("swirl_1.png")).getImage(),
            new ImageIcon(getURL("swirl_2.png")).getImage(),
            new ImageIcon(getURL("swirl_3.png")).getImage()};
    public static final Image[] IMG_PACMAN_LEFT = {
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_0.png")).getImage(),
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_left_1.png")).getImage(),
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_left_2.png")).getImage(),
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_left_3.png")).getImage()};

    public static final Image[] IMG_PACMAN_RIGHT = {
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_0.png")).getImage(),
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_right_1.png")).getImage(),
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_right_2.png")).getImage(),
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_right_3.png")).getImage()};

    public static final Image[] IMG_PACMAN_UP = {
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_0.png")).getImage(),
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_top_1.png")).getImage(),
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_top_2.png")).getImage(),
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_top_3.png")).getImage()};
    public static final Image[] IMG_PACMAN_DOWN = {
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_0.png")).getImage(),
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_down_1.png")).getImage(),
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_down_2.png")).getImage(),
            new ImageIcon(Pacman.class.getResource("/res/images/pacman_down_3.png")).getImage(),
    };
    public static final Image[] IMG_BLINKY = {
            new ImageIcon(getURL("blinky_1.png")).getImage(),
            new ImageIcon(getURL("blinky_2.png")).getImage(),
            new ImageIcon(getURL("blinky_3.png")).getImage(),
            new ImageIcon(getURL("blinky_4.png")).getImage(),
            new ImageIcon(getURL("blinky_5.png")).getImage(),
            new ImageIcon(getURL("blinky_6.png")).getImage(),
            new ImageIcon(getURL("blinky_7.png")).getImage(),
            new ImageIcon(getURL("blinky_8.png")).getImage(),
    };
    public static final Image[] IMG_INKY = {
            new ImageIcon(getURL("inky_1.png")).getImage(),
            new ImageIcon(getURL("inky_2.png")).getImage(),
            new ImageIcon(getURL("inky_3.png")).getImage(),
            new ImageIcon(getURL("inky_4.png")).getImage(),
            new ImageIcon(getURL("inky_5.png")).getImage(),
            new ImageIcon(getURL("inky_6.png")).getImage(),
            new ImageIcon(getURL("inky_7.png")).getImage(),
            new ImageIcon(getURL("inky_8.png")).getImage(),
    };
    public static final Image[] IMG_CLYDE = {
            new ImageIcon(getURL("clyde_1.png")).getImage(),
            new ImageIcon(getURL("clyde_2.png")).getImage(),
            new ImageIcon(getURL("clyde_3.png")).getImage(),
            new ImageIcon(getURL("clyde_4.png")).getImage(),
            new ImageIcon(getURL("clyde_5.png")).getImage(),
            new ImageIcon(getURL("clyde_6.png")).getImage(),
            new ImageIcon(getURL("clyde_7.png")).getImage(),
            new ImageIcon(getURL("clyde_8.png")).getImage(),
    };
    public static final Image[] IMG_PINKY = {
            new ImageIcon(getURL("pinky_1.png")).getImage(),
            new ImageIcon(getURL("pinky_2.png")).getImage(),
            new ImageIcon(getURL("pinky_3.png")).getImage(),
            new ImageIcon(getURL("pinky_4.png")).getImage(),
            new ImageIcon(getURL("pinky_5.png")).getImage(),
            new ImageIcon(getURL("pinky_6.png")).getImage(),
            new ImageIcon(getURL("pinky_7.png")).getImage(),
            new ImageIcon(getURL("pinky_8.png")).getImage(),
    };
    public static final ImageIcon[] IC_PLAY = {
            new ImageIcon(getURL("play_1.png")),
            new ImageIcon(getURL("play_2.png"))
    };
    public static final ImageIcon[] IC_HIGH_SCORES = {
            new ImageIcon(getURL("highscores_1.png")),
            new ImageIcon(getURL("highscores_2.png"))
    };
    public static final ImageIcon[] IC_ABOUT = {
            new ImageIcon(getURL("about_1.png")),
            new ImageIcon(getURL("about_2.png"))
    };
    public static final Image[] IMG_BACKGROUND = {
            new ImageIcon(getURL("background_1.png")).getImage(),
            new ImageIcon(getURL("background_2.png")).getImage()
    };


    public static URL getURL(String fileName) {
        return ImageLoader.class.getResource("/res/images/" + fileName);
    }
}
