package com.huantt.pacmangame.model;

import manager.ImageLoader;

import java.awt.*;

/**
 * Created by Huan on 7/30/2016.
 */
public class Swirl extends GameObject {
    private static final int SIZE = 30;
    private int numberOfImage;
    private int countDownImage;
    private int countDownImage2;
    private static final int MAX_COUNTDOWN = 60;
    private static final int MAX_COUNTDOWN2 = 190;
    Rectangle rSwirl;

    public Swirl(int x, int y) {
        rSwirl = new Rectangle(x, y, SIZE, SIZE);
        countDownImage = MAX_COUNTDOWN;
        countDownImage2 = MAX_COUNTDOWN2;
        this.x = x;
        this.y = y;
    }

    public Rectangle getrSwirl() {
        return rSwirl;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (countDownImage2 < 100) {
            graphics2D.drawImage(ImageLoader.IMG_HU, 0, 0, null);
            if (countDownImage2 == 0) {
                countDownImage2 = MAX_COUNTDOWN2;
            }
        }
        countDownImage2--;
        if (countDownImage == 0) {
            if (numberOfImage < 2) {
                numberOfImage++;
            } else numberOfImage = 0;
            countDownImage = MAX_COUNTDOWN;
        } else {
            countDownImage -= 10;
        }
        graphics2D.drawImage(ImageLoader.IMG_SWIRL[numberOfImage], x, y, SIZE, SIZE, null);

    }


}
