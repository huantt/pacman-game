package com.huantt.pacmangame.model;

import java.awt.*;

/**
 * Created by Huan on 7/22/2016.
 */
public abstract class GameObject {

    protected int x;
    protected int y;

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
