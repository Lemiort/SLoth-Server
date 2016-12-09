package ru.etu.sapr;

import ru.etu.sapr.game.CubeAI;
import ru.etu.sapr.game.GameObject;
import ru.etu.sapr.game.ICubeData;
import ru.etu.sapr.game.Vector3;

import java.awt.geom.Point2D;

/**
 * Created by Nikita on 06.12.2016.
 */
public class TestCube extends GameObject implements ICubeData{
    public Vector3 position;

    public CubeAI cubeAI;

    public TestCube(){
        this.position = new Vector3();
    }

    public TestCube(Vector3 position){
        this.position = position;
    }

    public Vector3 GetSelfPosition() {
        return position;
    }

    public void SetNextSelfPosition(Vector3 position) {
        this.position = position;
    }

}
