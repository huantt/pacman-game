package com.huantt.pacmangame.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Huan on 7/22/2016.
 */
public class GUI extends JFrame {
    public static final int WIDTH_FRAME = GamePlayPanel.WIDTH_PANEL;
    public static final int HEIGHT_FRAME = GamePlayPanel.HEIGHT_PANEL + InfoPanel.HEIGHT_FRAME;
    public GUI() {
        initializeGUI();
        initializeWindowListener();
        initializeComponets();
    }

    private void initializeGUI() {
        setTitle("PACMAN GAME");
        setLayout(new CardLayout());
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeWindowListener() {
        WindowAdapter windowAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(windowAdapter);
    }

    private void initializeComponets() {
//        GamePlayPanel myContainer = new GamePlayPanel();
//        add(myContainer);
        MyContainer myContainer = MyContainer.getInstance();
        add(myContainer);
    }
}
