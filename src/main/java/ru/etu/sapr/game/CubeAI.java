package ru.etu.sapr.game;

import java.util.ArrayList;

/**
 * Created by Nikita on 05.12.2016.
 */
public abstract class CubeAI {
    // идинтификационный номер куба
    private Long id;

    // интерфейс с помошью которого AI общается с игрой
    private ICubeData cubeData;

    public CubeAI(Long id, ICubeData cubeData){
        this.id = id;
        this.cubeData = cubeData;
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
        return this.cubeData.GetCubePosition(cubeID);
    }

    protected final ArrayList<Vector3> GetAllCubesPosition(){
        return this.cubeData.GetAllCubesPosition();
    }



    /**
     * Метот заставляет AI расчитать позицию в которую должен переместиться куб
     */
    public abstract void Move();
}