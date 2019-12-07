package com.huantt.pacmangame.model;

import manager.ImageLoader;

import java.awt.*;

/**
 * Created by Huan on 7/24/2016.
 */
public class Item extends GameObject {
    private int type;
    private Rectangle rItem;
    public static final int TYPE_STONE = 1;
    public static final int TYPE_NORMAL_BEAN = 2;
    public static final int TYPE_BIG_BEAN = 3;
    public static final int TYPE_DOOR = 4;
    public static final int SIZE = GameObject.SIZE_GAME_OBJECT;

    public Item(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        rItem = new Rectangle(x, y, SIZE, SIZE);
    }

    public Rectangle getRItem() {
        return rItem;
    }

    public int getType() {
        return type;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        switch (type) {
            case TYPE_STONE:
                graphics2D.drawImage(ImageLoader.IMG_ITEM_STONE, x, y, SIZE, SIZE, null);
                break;
            case TYPE_NORMAL_BEAN:
                graphics2D.drawImage(ImageLoader.IMG_ITEM_BEAN_NORMAL, x, y, SIZE, SIZE, null);
                break;
            case TYPE_BIG_BEAN:
                graphics2D.drawImage(ImageLoader.IMG_BULLET, x, y, SIZE, SIZE, null);
                break;
            case TYPE_DOOR:
                graphics2D.drawImage(ImageLoader.IMG_DOOR, x, y, SIZE, SIZE, null);
                break;
        }
    }
}
