package com.huantt.pacmangame.gui;

import javax.swing.*;

/**
 * Created by Huan on 7/22/2016.
 */
public abstract class BaseContaiter extends JPanel {

    public BaseContaiter() {
        initializeComponents();
        initializePanel();
    }



    abstract void initializePanel();

    abstract void initializeComponents();
}
