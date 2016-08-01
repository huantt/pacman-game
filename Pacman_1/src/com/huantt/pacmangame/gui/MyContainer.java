package com.huantt.pacmangame.gui;

import com.huantt.pacmangame.interfaces.OnPlayListener;
import com.huantt.pacmangame.model.GetScore;

import java.awt.*;

/**
 * Created by Huan on 7/28/2016.
 */
public class MyContainer extends BaseContaiter implements OnPlayListener {
    private InfoPanel infoPanel;
    private MenuPanel menuPanel;
    private GamePlayPanel gamePlayPanel;

    @Override
    void initializePanel() {
        setLayout(null);
        setBackground(Color.BLACK);
        menuPanel.addPlayListener(this);
    }

    @Override
    void initializeComponents() {
        menuPanel = new MenuPanel();
        menuPanel.setLocation(0, 0);
        add(menuPanel);
    }

    @Override
    public void onClickPlay() {
        remove(menuPanel);
        gamePlayPanel = new GamePlayPanel();
        add(gamePlayPanel);
        gamePlayPanel.requestFocusInWindow();

    }
}
