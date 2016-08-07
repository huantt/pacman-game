package com.huantt.pacmangame.gui;


import com.huantt.pacmangame.interfaces.OnChangeListener;
import com.huantt.pacmangame.model.Item;
import com.huantt.pacmangame.model.Pacman;
import manager.GameManager;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

/**
 * Created by Huan on 7/12/2016.
 */

public class GamePlayPanel extends BaseContaiter implements Runnable, OnChangeListener {
    public static final int WIDTH_PANEL = GameManager.NUM_OF_COLUMNS_MAP * Item.SIZE;
    public static final int HEIGHT_PANEL = GameManager.NUM_OF_ROWS_MAP * Item.SIZE;

    private GameManager gameManager;
    private boolean isRunning;
    private KeyAdapter keyAdapter;
    private BitSet bitSet;

    public GamePlayPanel() {
        super();
        startGame();
        initializeKeyListener();
        setSize(WIDTH_PANEL, HEIGHT_PANEL);

    }

    public GameManager getGameManager() {
        return gameManager;
    }

    void initializeKeyListener() {
        bitSet = new BitSet(265);
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                bitSet.set(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                bitSet.clear(e.getKeyCode());

            }
        };
        addKeyListener(keyAdapter);

    }

    @Override
    void initializePanel() {
        setLayout(null);
        setBackground(Color.BLACK);
    }

    @Override
    void initializeComponents() {
        gameManager = new GameManager();
    }

    public void startGame() {
        gameManager.chanePacmanOrient(Pacman.LEFT); // Ban dau no quay dau ben trai
        isRunning = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stopGame() {
        isRunning = false;
    }

    private void handleKeyAction() {
        if (bitSet.get(KeyEvent.VK_LEFT)) {
            gameManager.setPacmanNextOrient(Pacman.LEFT);
        }
        if (bitSet.get(KeyEvent.VK_RIGHT)) {
            gameManager.setPacmanNextOrient(Pacman.RIGHT);
        }
        if (bitSet.get(KeyEvent.VK_UP)) {
            gameManager.setPacmanNextOrient(Pacman.UP);
        }
        if (bitSet.get(KeyEvent.VK_DOWN)) {
            gameManager.setPacmanNextOrient(Pacman.DOWN);
        }
        if (bitSet.get(KeyEvent.VK_SPACE)) {
            gameManager.fireByPacman();
        }

    }

    @Override
    public void onChangeScore(int score) {

    }

    @Override
    public void onPacmanDie() {

    }

    @Override
    protected void paintComponent(Graphics g) { // Gọi mỗi lần repaint trong run !
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        gameManager.drawBullet(graphics2D);
        gameManager.drawSwirl(graphics2D);
        gameManager.drawIteam(graphics2D);
        gameManager.drawGhost(graphics2D);
        gameManager.drawPacMan(graphics2D);
    }

    @Override
    public void run() { // Thread nay chuyen xu ly cac cong viec tinh toan de nhan vat di chuyen cac thu
        int countSpeed = 0;
        while (isRunning) {
            handleKeyAction();
            gameManager.handleMovePacMan(countSpeed);
            if (gameManager.isPacmanChangeOrient() && gameManager.canChangeOrientPacman()) {
                gameManager.chanePacmanOrient(gameManager.getPacmanNextOrient());
            }
            gameManager.handleBulletMove();
            gameManager.handleGhostMove(countSpeed);
            if (countSpeed == 100) {
                countSpeed = 0;
            } else
                countSpeed++;
            repaint();
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}