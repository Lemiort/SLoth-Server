package ru.etu.sapr;

import javax.swing.*;

/**
 * Панель наглядно показывающая поведение AI в 2D
 * Created by Nikita on 09.12.2016.
 */
public class DrawFrame extends JFrame {
    public DrawFrame(){
        add(new DrawComponent());
        pack();
    }
}
