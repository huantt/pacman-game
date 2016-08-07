package com.huantt.pacmangame.gui;

import com.huantt.pacmangame.interfaces.OnClickGameListener;
import manager.PlayerManagerWAV;

import java.awt.*;

/**
 * Created by Huan on 7/28/2016.
 */
public class MyContainer extends BaseContaiter implements OnClickGameListener {

    private StartPanel startPanel;
    private GamePlayPanel gamePlayPanel;
    private InfoPanel infoPanel;
    private HightScorePanel hightScorePanel;
    private AboutPanel aboutPanel;
    private PlayerManagerWAV playerManagerWAV;

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
        infoPanel.setLocation(0, GamePlayPanel.HEIGHT_PANEL + 10);
        infoPanel.addOnChangeScore(gamePlayPanel.getGameManager());
        add(infoPanel);
        add(gamePlayPanel);
        infoPanel.setRequestFocusEnabled(true);
        gamePlayPanel.requestFocusInWindow();
        repaint();

    }

    @Override
    public void onClickHightScore() {
        remove(startPanel);
        hightScorePanel = new HightScorePanel();
        hightScorePanel.addOnClick(this);
        add(hightScorePanel);
        repaint();
    }

    @Override
    public void onClickBackFromHightScore() {
        remove(hightScorePanel);
        add(startPanel);
    }

    @Override
    public void onClickBackFromAbout() {
        remove(aboutPanel);
        add(startPanel);
    }

    @Override
    public void onClickAbout() {
        remove(startPanel);
        aboutPanel = new AboutPanel();
        aboutPanel.addOnClick(this);
        add(aboutPanel);
        repaint();
    }
}
