package ru.etu.sapr.game;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Nikita on 05.12.2016.
 */
public abstract class CubeAI {
    // идинтификационный номер куба
    private Long id;
    /**
     *  скорость
     */
    private float speed;

    protected float getSpeed() {
        return speed;
    }

    protected void setSpeed(float speed) {
        this.speed = speed;
    }

    // интерфейс с помошью которого AI общается с игрой
    private ICubeData cubeData;
    private IOtherCubeData otherCubeData;

    public CubeAI(Long id, ICubeData cubeData, IOtherCubeData orerCubeData, float speed){
        this.id = id;
        this.cubeData = cubeData;
        this.otherCubeData = orerCubeData;
        this.speed = speed;
    }

    protected Long getId() {
        return id;
    }
    //
    // Реализация взаимодействия с интерфейсом
    // TODO: сделать проверку на NULL в методах ниже
    //

    protected final Vector3 GetSelfPosition(){
        return this.cubeData.GetSelfPosition();
    }

    protected final void SetNextSelfPosition(Vector3 position){
        this.cubeData.SetNextSelfPosition(position);
    }

    protected final Vector3 GetCubePosition(Long cubeID){
        return this.otherCubeData.GetCubePosition(cubeID);
    }

    protected final Hashtable<Long, GameObject> GetAllObjectInformation(){
        return this.otherCubeData.GetAllObjectInformation();
    }

    protected final GameObject GetObjectInformation(Long objectID){
        return this.otherCubeData.GetObjectInformation(objectID);
    }



    /**
     * Метот заставляет AI расчитать позицию в которую должен переместиться куб
     */
    public abstract void Move();

}
