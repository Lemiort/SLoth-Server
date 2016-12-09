package ru.etu.sapr;

import ru.etu.sapr.game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Hashtable;

/**
 * компонент отрисовывающий поведение кубов
 * Created by Nikita on 09.12.2016.
 */
public class DrawComponent extends JComponent implements IOtherCubeData{
    private  static final int DEFAULT_WIDTH = 400;
    private  static final int DEFAULT_HEIGHT = 400;

    private  static final float CUBE_SIZE = 10f;

    private Hashtable<Long, GameObject> cubes;

    private double d;

    private TestCube cube;
    private TestCube cube2;
    private Long ownCubeId;
    private Long ownCubeId2;

    private Timer timer = new Timer(30, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    public  DrawComponent() {

        // создание кубов
        cubes = new Hashtable<Long, GameObject>();

        cube = new TestCube();
        ownCubeId = cube.getInstanceID();
        cube.cubeAI = new GoByCircle(
                cube.getInstanceID(),
                cube,
                this,
                true,
                100f,
                new Vector3(0, 0, 0),
                5f
        );
        cubes.put(cube.getInstanceID(), cube);

        cube2 = new TestCube();
        ownCubeId2 = cube2.getInstanceID();
        cube2.cubeAI = new DynamicGoByCircle(
                cube2.getInstanceID(),
                cube2,
                this,
                true,
                50f,
                cube.getInstanceID(),
                10f
        );
        cubes.put(cube2.getInstanceID(), cube2);

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // нарисуем!!!

        cube.cubeAI.Move();
        g2.draw(new Rectangle2D.Float(
                cube.position.x + (DEFAULT_WIDTH + CUBE_SIZE)/2,
                cube.position.z + (DEFAULT_HEIGHT + CUBE_SIZE)/2,
                CUBE_SIZE,
                CUBE_SIZE));

        cube2.cubeAI.Move();
        g2.draw(new Rectangle2D.Float(
                cube2.position.x + (DEFAULT_WIDTH + CUBE_SIZE)/2,
                cube2.position.z + (DEFAULT_HEIGHT + CUBE_SIZE)/2,
                CUBE_SIZE,
                CUBE_SIZE));


    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Hashtable<Long, GameObject> GetAllObjectInformation() {
        return cubes;
    }

    public GameObject GetObjectInformation(Long objectID) {
        return cubes.get(objectID);
    }

    public Vector3 GetCubePosition(Long cubeID) {
        GameObject gameObject = cubes.get(cubeID);
        if(gameObject.getClass() == TestCube.class){
            TestCube cube = (TestCube)gameObject;
            return new Vector3(
                    cube.position.x,
                    cube.position.y,
                    cube.position.z
            );
        }
        else return null;
    }
}
