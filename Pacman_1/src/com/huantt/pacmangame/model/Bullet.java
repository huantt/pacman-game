package com.huantt.pacmangame.model;

import manager.ImageLoader;

import java.awt.*;

/**
 * Created by Huan on 7/29/2016.
 */
public class Bullet extends GameObject {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    public static final int SIZE = 8;
    private int orient;

    public Bullet(int orient, int x, int y) {
        this.x = x;
        this.y = y;
        this.orient = orient;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int xx = x;
        int yy = y;
        switch (orient) {
            case LEFT:
                yy += Item.SIZE / 2 - SIZE / 2;

                break;
            case DOWN:
                xx += Item.SIZE / 2 - SIZE / 2;

                break;
            case RIGHT:
                yy += Item.SIZE / 2 - SIZE / 2;

                break;
            case UP:
                xx += Item.SIZE / 2 - SIZE / 2;
                break;
        }
        graphics2D.drawImage(ImageLoader.IMG_BULLET, xx, yy, SIZE, SIZE, null);
    }

    public int getOrient() {
        return orient;
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public void move() {
        switch (orient) {
            case LEFT:
                x -= 2;

                break;
            case DOWN:
                y += 2;

                break;
            case RIGHT:
                x += 2;

                break;
            case UP:
                y -= 2;
                break;
        }

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
        Rectangle recPacman = new Rectangle(xRec, yRec, SIZE, SIZE);
        switch (iteam.getType()) {
            case Item.TYPE_STONE:
                return recPacman.intersects(iteam.getRItem());

        }
        return false;
    }

}
