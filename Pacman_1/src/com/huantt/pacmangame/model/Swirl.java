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
    private static final int MAX_COUNTDOWN = 60;
    Rectangle rSwirl;

    public Swirl(int x, int y) {
        rSwirl = new Rectangle(x, y, SIZE, SIZE);
        countDownImage = MAX_COUNTDOWN;
        this.x = x;
        this.y = y;
    }

    public Rectangle getrSwirl() {
        return rSwirl;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
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
