package com.huantt.pacmangame.interfaces;

/**
 * Created by Huan on 8/4/2016.
 */
public interface OnChangeListener {
    void onChangeScore(int scores);
    void onPacmanDie();
    void onAddBullet(int bullets);
}
