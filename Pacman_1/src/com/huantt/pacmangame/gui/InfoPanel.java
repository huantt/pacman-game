package com.huantt.pacmangame.gui;

import com.huantt.pacmangame.interfaces.OnChangeListener;
import manager.GameManager;
import manager.ImageLoader;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Huan on 7/28/2016.
 */
public class InfoPanel extends BaseContaiter implements OnChangeListener {
    public static final int WIDTH_FRAME = GamePlayPanel.WIDTH_PANEL;
    public static final int HEIGHT_FRAME = 100;
    private int scores;
    private int bullets;
    private GameManager gameManager;
    private ArrayList<Image> livesPacMan;

    @Override
    void initializePanel() {
        setLayout(null);
        setBackground(Color.BLACK);
        setSize(GamePlayPanel.WIDTH_PANEL, 100);

    }

    @Override
    void initializeComponents() {
        livesPacMan = new ArrayList<>();
        livesPacMan.add(ImageLoader.IMG_PACMAN_RIGHT[1]);
        livesPacMan.add(ImageLoader.IMG_PACMAN_RIGHT[1]);
        livesPacMan.add(ImageLoader.IMG_PACMAN_RIGHT[1]);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setFont(new Font("Tahoma", Font.BOLD, 15));
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("Score: " + scores, 30, 30);
        for (int i = 0; i < livesPacMan.size(); i++) {
            graphics2D.drawImage(livesPacMan.get(i), GUI.WIDTH_FRAME - 100 + i * 21, 15, 20, 20, null);
        }
        for (int i = 0; i < bullets; i++) {
            graphics2D.drawImage(ImageLoader.IMG_BULLET, (GUI.WIDTH_FRAME - 90) / 2 + i * 21, 15, 20, 20, null);
        }

    }


    @Override
    public void onChangeScore(int score) {
        this.scores = score;
        this.repaint();


    }

    @Override
    public void onPacmanDie() {
        livesPacMan.remove(0);
        this.repaint();
    }


    @Override
    public void onAddBullet(int bullets) {
        this.bullets = bullets;
        System.out.println(bullets);
        repaint();
    }

    public void addOnChangeScore(GameManager gameManager) {
        this.gameManager = gameManager;
        gameManager.addChangeScoreListenr(this);
    }
}
