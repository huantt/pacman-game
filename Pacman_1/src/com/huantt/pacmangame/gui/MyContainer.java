package com.huantt.pacmangame.gui;

import com.huantt.pacmangame.interfaces.OnClickGameListener;

import java.awt.*;

/**
 * Created by Huan on 7/28/2016.
 */
public class MyContainer extends BaseContaiter implements OnClickGameListener {

    private StartPanel startPanel;
    private GamePlayPanel gamePlayPanel;
    private InfoPanel infoPanel;

    @Override
    void initializePanel() {
        setLayout(null);
        setBackground(Color.BLACK);
        startPanel.addPlayListener(this);
    }

    @Override
    void initializeComponents() {
        startPanel = new StartPanel();
        startPanel.setLocation(0, 0);
        add(startPanel);
    }

    @Override
    public void onClickPlay() {
        remove(startPanel);
        infoPanel = new InfoPanel();
        gamePlayPanel = new GamePlayPanel();
        gamePlayPanel.setLocation(0, 10);
        infoPanel.setLocation(0, GamePlayPanel.HEIGHT_PANEL+10);
        infoPanel.addOnChangeScore(gamePlayPanel.getGameManager());
        add(infoPanel);
        add(gamePlayPanel);
        infoPanel.setRequestFocusEnabled(true);
        gamePlayPanel.requestFocusInWindow();
        repaint();

    }

    @Override
    public void onRePlay() {

    }
}
