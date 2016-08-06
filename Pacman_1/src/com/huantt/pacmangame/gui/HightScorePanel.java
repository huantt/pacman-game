package com.huantt.pacmangame.gui;

import com.huantt.pacmangame.interfaces.OnClickGameListener;
import com.huantt.pacmangame.model.Player;
import manager.ImageLoader;
import manager.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Huan on 8/6/2016.
 */
public class HightScorePanel extends BaseContaiter {
    private JLabel lbBackMenu;
    private PlayerManager playerManager;
    private MouseAdapter mouseAdapter;
    OnClickGameListener onClickGameListener;

    public HightScorePanel() {
        playerManager = new PlayerManager();
        initializeMouseListener();
    }

    @Override
    void initializePanel() {
        setLayout(null);
        setSize(GUI.WIDTH_FRAME, GUI.HEIGHT_FRAME);
        setBackground(Color.BLACK);
        setLocation(0, 0);
    }

    @Override
    void initializeComponents() {
        lbBackMenu = new JLabel("Back");
        lbBackMenu.setBackground(null);
        lbBackMenu.setSize(100, 40);
        lbBackMenu.setLocation((GUI.WIDTH_FRAME - 100) / 2, GUI.HEIGHT_FRAME - 150);
        lbBackMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbBackMenu.setForeground(Color.WHITE);
        add(lbBackMenu);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setFont(new Font("Arial Rounded MT", Font.BOLD, 20));
        graphics2D.setColor(Color.WHITE);
        int x = (GUI.WIDTH_FRAME - 195) / 2;
        int y = 250;

        graphics2D.drawImage(ImageLoader.IMG_BACKGROUND[1], 0, 0, GUI.WIDTH_FRAME, GUI.HEIGHT_FRAME, null);
        graphics2D.drawString("Player", x, y);
        graphics2D.drawString("Score", x + 120, y);
        for (Player player : playerManager.sortByScore()) {
            y += 30;
            graphics2D.drawString(player.getName(), x, y);

        }
        y = 250;
        for (Player player : playerManager.sortByScore()) {
            y += 30;
            graphics2D.drawString(String.valueOf(player.getScore()), x + 120, y);

        }
    }

    public void addOnClick(OnClickGameListener onClickGameListener) {
        this.onClickGameListener = onClickGameListener;

    }

    public void initializeMouseListener() {
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (e.getComponent().equals(lbBackMenu)) {
                    lbBackMenu.setForeground(Color.YELLOW);
                    lbBackMenu.setSize(100, 35);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (e.getComponent().equals(lbBackMenu)) {
                    lbBackMenu.setForeground(Color.WHITE);
                    lbBackMenu.setSize(90, 40);
                }
            }

            @Override

            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getComponent().equals(lbBackMenu)) {
                    onClickGameListener.onClickBackFromHightScore();
                }
            }
        };
        lbBackMenu.addMouseListener(mouseAdapter);
    }
}
