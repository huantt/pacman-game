package com.huantt.pacmangame.gui;

import com.huantt.pacmangame.interfaces.OnClickGameListener;
import com.huantt.pacmangame.model.Ghost;
import com.huantt.pacmangame.model.Pacman;
import manager.ImageLoader;
import manager.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Huan on 7/31/2016.
 */
public class StartPanel extends BaseContaiter implements Runnable {
    private OnClickGameListener onPlayListener;
    private JLabel lbPlay;
    private JLabel lbHighScores;
    private JLabel lbAbout;
    private SoundPlayer soundPlayer;
    private Image background = ImageLoader.IMG_BACKGROUND[0];
    private Pacman pacman;
    private int countSpeed;
    private int x;
    private int y;
    private ArrayList<Ghost> ghosts;

    public StartPanel() {
        super();
        x = GUI.WIDTH_FRAME / 2 - Pacman.SIZE - 2 * Ghost.SIZE;
        y = GUI.HEIGHT_FRAME / 2 + 200;
        pacman = new Pacman(x, y, Pacman.RIGHT, 1);
        ghosts = new ArrayList<>();
        initializeGhost();
    }

    @Override
    void initializePanel() {
        soundPlayer = new SoundPlayer();
        setLayout(null);
        setBackground(Color.BLUE);
        setSize(GUI.WIDTH_FRAME, GUI.HEIGHT_FRAME);
        initializeMouseListener();
        new Thread(this).start();
    }

    public void initializeMouseListener() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                if (e.getComponent().equals(lbPlay)) {
                    lbPlay.setLocation(200, GUI.HEIGHT_FRAME / 3 - 5);
                    lbPlay.setIcon(ImageLoader.IC_PLAY[1]);
                }
                if (e.getComponent().equals(lbHighScores)) {
                    lbHighScores.setLocation(200, GUI.HEIGHT_FRAME / 3 + 100 - 5);
                    lbHighScores.setIcon(ImageLoader.IC_HIGH_SCORES[1]);
                }
                if (e.getComponent().equals(lbAbout)) {
                    lbAbout.setLocation(200, GUI.HEIGHT_FRAME / 3 + 200 - 5);
                    lbAbout.setIcon(ImageLoader.IC_ABOUT[1]);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

                if (e.getComponent().equals(lbPlay)) {
                    lbPlay.setLocation(200, GUI.HEIGHT_FRAME / 3);
                    lbPlay.setIcon(ImageLoader.IC_PLAY[0]);
                }
                if (e.getComponent().equals(lbHighScores)) {
                    lbHighScores.setLocation(200, GUI.HEIGHT_FRAME / 3 + 100);
                    lbHighScores.setIcon(ImageLoader.IC_HIGH_SCORES[0]);
                }
                if (e.getComponent().equals(lbAbout)) {
                    lbAbout.setLocation(200, GUI.HEIGHT_FRAME / 3 + 200);
                    lbAbout.setIcon(ImageLoader.IC_ABOUT[0]);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getComponent().equals(lbPlay)) {
                    onPlayListener.onClickPlay();
                }
                if (e.getComponent().equals(lbHighScores)) {
                    onPlayListener.onClickHightScore();
                }
                if (e.getComponent().equals(lbAbout)){
                    onPlayListener.onClickAbout();
                }
            }

        };
        lbPlay.addMouseListener(mouseAdapter);
        lbHighScores.addMouseListener(mouseAdapter);
        lbAbout.addMouseListener(mouseAdapter);
    }

    @Override
    void initializeComponents() {
        lbPlay = new JLabel("PLAY");
        lbPlay.setBounds(200, GUI.HEIGHT_FRAME / 3, 200, 55);
        lbPlay.setIcon(ImageLoader.IC_PLAY[0]);
        lbHighScores = new JLabel("PLAY");
        lbHighScores.setBounds(200, GUI.HEIGHT_FRAME / 3 + 100, 200, 55);
        lbHighScores.setIcon(ImageLoader.IC_HIGH_SCORES[0]);
        lbAbout = new JLabel("PLAY");
        lbAbout.setBounds(200, GUI.HEIGHT_FRAME / 3 + 200, 200, 55);
        lbAbout.setIcon(ImageLoader.IC_ABOUT[0]);


    }

    public void addPlayListener(OnClickGameListener onPlayListener) {
        this.onPlayListener = onPlayListener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(background, 0, 0, GUI.WIDTH_FRAME, GUI.HEIGHT_FRAME, null);
        pacman.draw(graphics2D);
        drawGhost(graphics2D);
    }

    @Override
    public void run() {
//        soundPlayer.playSound(FileSoundManager.SOUND_INTRO);
        background = ImageLoader.IMG_BACKGROUND[1];
        repaint();
        add(lbPlay);
        add(lbHighScores);
        add(lbAbout);
        while (true) {
            repaint();
            if (pacman.getX() == GUI.WIDTH_FRAME - Pacman.SIZE) {
                pacman.setOrient(Pacman.LEFT);
                for (Ghost ghost : ghosts) {
                    ghost.setOrient(Ghost.LEFT);
                }
            }
            if (pacman.getX() == 0) {
                pacman.setOrient(Pacman.RIGHT);
                for (Ghost ghost : ghosts) {
                    ghost.setOrient(Ghost.RIGHT);
                }
            }

            pacman.move(countSpeed);
            if (countSpeed == 100) {
                countSpeed = 0;
            } else
                countSpeed++;
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Ghost ghost : ghosts) {
                ghost.move(countSpeed);
            }
        }

    }

    public void drawGhost(Graphics2D graphics2D) {
        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).draw(graphics2D);
        }
    }

    private void initializeGhost() {
        ghosts = new ArrayList<>();
        ghosts.add(new Ghost(x + Ghost.SIZE, y, Ghost.TYPE_BLINKY, 1, Ghost.LEFT_RIGHT));
        ghosts.add(new Ghost(x + 2 * Ghost.SIZE, y, Ghost.TYPE_BLINKY, 1, Ghost.LEFT_RIGHT));
        ghosts.add(new Ghost(x + 3 * Ghost.SIZE, y, Ghost.TYPE_PINKY, 1, Ghost.LEFT_RIGHT));
        ghosts.add(new Ghost(x + 4 * Ghost.SIZE, y, Ghost.TYPE_CLYDE, 1, Ghost.LEFT_RIGHT));
        ghosts.add(new Ghost(x + 5 * Ghost.SIZE, y, Ghost.TYPE_INKY, 1, Ghost.LEFT_RIGHT));
    }

}
