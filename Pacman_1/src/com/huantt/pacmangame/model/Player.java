package com.huantt.pacmangame.model;

/**
 * Created by Huan on 7/28/2016.
 */
public class Player {
    String name;
    int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "-" + score +"\n";
    }

    @Override
    public boolean equals(Object obj) {
        return ((Player) obj).getName().equals(name);
    }
}
