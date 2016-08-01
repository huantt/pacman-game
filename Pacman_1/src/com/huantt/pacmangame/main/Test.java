package com.huantt.pacmangame.main;

import manager.GameManager;

import java.io.IOException;

/**
 * Created by Huan on 7/24/2016.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        GameManager g = new GameManager();
        g.loadMap("map_1.txt");
    }
}
