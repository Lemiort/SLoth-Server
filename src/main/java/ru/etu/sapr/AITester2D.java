package ru.etu.sapr;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nikita on 09.12.2016.
 */
public class AITester2D {
    public static void main(String[] args){
        EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        JFrame frame = new DrawFrame();
                        frame.setTitle("AI test");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                    }
                }
        );
    }
}
