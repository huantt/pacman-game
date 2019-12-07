package com.huantt.pacmangame.model;

import manager.ImageLoader;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Created by Huan on 7/31/2016.
 */
public class Ghost extends GameObject {

    public static final int TYPE_BLINKY = 1;
    public static final int TYPE_CLYDE = 2;
    public static final int TYPE_INKY = 3;
    public static final int TYPE_PINKY = 4;
    public static final int SIZE = GameObject.SIZE_GAME_OBJECT;
    public static final String LEFT_RIGHT = "LR";
    public static final String UP_DOWN = "UD";
    public static final String FOUR_ORIENTS = "4_Orient";
    private static final int MAX_COUNTDOWN = 100;
    private int type;
    private int orient;
    private int delay;
    private int numberOfImage;
    private int countDownImage;
    private String orients;
    private Random random = new Random();
    private Rectangle reGhost;
    private boolean isDie;

    public Ghost(int x, int y) {
        this.x = x;
        this.y = y;
        reGhost = new Rectangle(x, y, SIZE - 5, SIZE - 5);
    }

    public Ghost(int x, int y, int type, int delay, String orients) {
        this.x = x;
        this.y = y;
        this.type = type;
        orient = RIGHT;
        this.delay = delay;
        this.orients = orients;
        reGhost = new Rectangle(x, y, SIZE - 5, SIZE - 5);
    }

    public Rectangle getReGhost() {
        return reGhost;
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

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public void setDie(boolean die) {
        isDie = die;
    }

    //TODO: Apply AI
    public void changeOrient(List<Item> items) {
        Item closestBean = this.getClosestItem(items);
        //So sánh khoảng cách khi di chuyển trái phải so với lên xuống để tìm đường ngắn nhất tới Bean
        //Nếu gần nhau theo chiều ngang hơn mà không trên cùng 1 dòng thì di chuyển theo chiều dọc để tới dòng đó - tương tự với chiều dọc
        if (Math.abs(x - closestBean.getX()) <= Math.abs(y - closestBean.getY())) {
            if (closestBean.getX() == this.getX()) {
                this.changeOrientByX(items, closestBean);
            } else {
                this.changeOrientByY(items, closestBean);
            }
        } else {
            if (closestBean.getY() == this.getY()) {
                this.changeOrientByY(items, closestBean);
            } else {
                this.changeOrientByX(items, closestBean);
            }

        }
    }

    public void changeOrientByX(List<Item> items, Item closestBean) {
        if (closestBean.getX() > this.getX()) {
            if (!this.isStone(items, this.getX() + 1 * Item.SIZE, this.getY())) orient = RIGHT;
            else this.randomOrient();
        } else {
            if (!this.isStone(items, this.getX() - 1 * Item.SIZE, this.getY())) orient = LEFT;
            else this.randomOrient();
        }
    }

    public void changeOrientByY(List<Item> items, Item closestBean) {
        if (closestBean.getY() > this.getY()) {
            if (!this.isStone(items, this.getX(), this.getY() - 1 * Item.SIZE)) orient = UP;
            else this.randomOrient();
        } else {
            if (!this.isStone(items, this.getX(), this.getY() + 1 * Item.SIZE)) orient = DOWN;
            else this.randomOrient();
        }
    }

    private void randomOrient() {
        int newOrient;
        switch (orients) {
            case LEFT_RIGHT:
                while ((newOrient = random.nextInt(2)) == orient) {
                }
                if (newOrient == 0) {
                    newOrient = LEFT;
                } else newOrient = RIGHT;
                break;
            case UP_DOWN:
                while ((newOrient = random.nextInt(2)) == orient) {
                }
                if (newOrient == 0) {
                    newOrient = UP;
                } else newOrient = DOWN;

                break;
            default:
                while ((newOrient = random.nextInt(4)) == orient) {
                }

        }

        orient = newOrient;
    }

    private Item getClosestItem(List<Item> items) {
        Optional<Item> closestItem = items.stream()
                .filter(it -> it.getType() == Item.TYPE_NORMAL_BEAN || it.getType() == Item.TYPE_BIG_BEAN || it.getType() == Item.TYPE_DOOR)
                .sorted(Comparator.comparingInt(it -> Math.abs(it.getX() - this.getX() + Math.abs(it.getY() - this.getY())))).findFirst();
        return closestItem.isPresent() ? closestItem.get() : null;
    }

    private boolean isStone(List<Item> items, int x, int y) {
        Optional<Item> optionalItem = items.stream().filter(it -> it.getX() == x && it.getY() == y).findFirst();
        if (optionalItem.isPresent()) return optionalItem.get().getType() == Item.TYPE_STONE;
        else return false;
    }

    public boolean isCollisionStone(Item item) {
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
        if (item.getType() == Item.TYPE_STONE && reGhost.intersects(item.getRItem())) {
            return true;
        }
        return false;
    }

    public boolean isDie() {
        return isDie;
    }

    public void tryToChangeOrient(List<Item> items) {
        if (this.orient == DOWN || this.orient == UP) {
            if (!this.isStone(items, this.getX() + 1 * Item.SIZE, this.getY())) this.orient = RIGHT;
            else if (!this.isStone(items, this.getX() - 1 * Item.SIZE, this.getY())) this.orient = LEFT;
        } else {
            if (!this.isStone(items, this.getY() + 1 * Item.SIZE, this.getX())) this.orient = DOWN;
            else if (!this.isStone(items, this.getY() - 1 * Item.SIZE, this.getX())) this.orient = UP;
        }

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (isDie) {
            graphics2D.drawImage(ImageLoader.IMG_GHOST_DIE[numberOfImage / 2], x, y, SIZE, SIZE, null);
        } else
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
}
