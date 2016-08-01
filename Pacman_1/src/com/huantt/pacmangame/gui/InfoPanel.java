package com.huantt.pacmangame.gui;

import com.huantt.pacmangame.model.GetScore;

import java.awt.*;

/**
 * Created by Huan on 7/28/2016.
 */
public class InfoPanel extends BaseContaiter {
    int score;
    @Override
    void initializePanel() {
        setLayout(null);
        setBackground(Color.BLACK);
        setSize(GUI.WIDTH_FRAME,100);


    }

    @Override
    void initializeComponents() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setFont(new Font("Tahoma", Font.BOLD, 15));
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("HAHAHAH", 10, 30);
    }


}
