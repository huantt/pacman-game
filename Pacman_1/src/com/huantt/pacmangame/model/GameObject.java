package com.huantt.pacmangame.model;

import java.awt.*;

/**
 * Created by Huan on 7/22/2016.
 */
public abstract class GameObject {
    public static final int SIZE_GAME_OBJECT = 29;
    protected int x;
    protected int y;

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public abstract void draw(Graphics2D graphics2D);


}
