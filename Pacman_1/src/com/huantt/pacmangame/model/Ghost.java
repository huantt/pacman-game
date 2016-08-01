package com.huantt.pacmangame.model;

import com.huantt.pacmangame.gui.GUI;
import manager.ImageLoader;

import java.awt.*;
import java.util.Random;

/**
 * Created by Huan on 7/31/2016.
 */
public class Ghost extends GameObject {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    public static final int TYPE_BLINKY = 1;
    public static final int TYPE_CLYDE = 2;
    public static final int TYPE_INKY = 3;
    public static final int TYPE_PINKY = 4;
    public static final int SIZE = 30;
    public static final String LEFT_RIGHT = "LR";
    public static final String UP_DOWN = "UD";
    public static final String FOUR_ORIENTS = "4_Orient";
    private static final int MAX_COUNTDOWN = 100;
    private Random random = new Random();
    private int type;
    private int orient;
    int count;
    int delay;
    private int numberOfImage;
    private int countDownImage;
    private String orients;
    private Rectangle reGhost;

    public Ghost(int x, int y, int type, int delay, String orients) {
        this.x = x;
        this.y = y;
        this.type = type;
        orient = RIGHT;
        this.delay = delay;
        this.orients = orients;
        reGhost = new Rectangle(x,y,SIZE,SIZE);

    }

    public Rectangle getReGhost() {
        return reGhost;
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        switch (type) {
            case TYPE_BLINKY:
                graphics2D.drawImage(ImageLoader.IMG_BLINKY[numberOfImage], x, y, SIZE, SIZE, null);
                break;
            case TYPE_CLYDE:
                graphics2D.drawImage(ImageLoader.IMG_CLYDE[numberOfImage], x, y, SIZE, SIZE, null);
                break;
            case TYPE_INKY:
                graphics2D.drawImage(ImageLoader.IMG_INKY[numberOfImage], x, y, SIZE, SIZE, null);
                break;
            case TYPE_PINKY:
                graphics2D.drawImage(ImageLoader.IMG_PINKY[numberOfImage], x, y, SIZE, SIZE, null);
                break;
        }
    }

    public void move(int count) {
        if (countDownImage == 0) { // Thằng mắt này nó ko liên quan đến tốc độ di chuyển nên ko dùng chung count
            if (numberOfImage < 7) {
                numberOfImage++;
            } else numberOfImage = 0;
            countDownImage = MAX_COUNTDOWN;
        } else {
            countDownImage -= 10;
        }
        if (count % delay != 0) {
            return;
        }
        switch (orient) {
            case LEFT:
                x--;

                break;
            case DOWN:
                y++;

                break;
            case RIGHT:
                x++;

                break;
            case UP:
                y--;

                break;
        }
        reGhost.setLocation(x, y);

    }

    public void autoChaneOrient() {
        int orientNew;
        switch (orients) {
            case LEFT_RIGHT:
                while ((orientNew = random.nextInt(2)) == orient) {
                }
                if (orientNew == 0) {
                    orientNew = LEFT;
                } else orientNew = RIGHT;
                break;
            case UP_DOWN:
                while ((orientNew = random.nextInt(2)) == orient) {
                }
                if (orientNew == 0) {
                    orientNew = UP;
                } else orientNew = DOWN;

                break;
            default:
                while ((orientNew = random.nextInt(4)) == orient) {
                }

        }

        orient = orientNew;

    }

    public boolean collision(Item iteam) {
        int xRec = x;
        int yRec = y;
        switch (orient) {
            case UP:
                yRec--;
                break;
            case DOWN:
                yRec++;
                break;
            case LEFT:
                xRec--;
                break;
            case RIGHT:
                xRec++;
                break;
        }
        Rectangle reGhost = new Rectangle(xRec, yRec, SIZE, SIZE);
        if (iteam.getType() == Item.TYPE_STONE && reGhost.intersects(iteam.getRItem())) {
            return true;
        }
        return false;
    }
}
