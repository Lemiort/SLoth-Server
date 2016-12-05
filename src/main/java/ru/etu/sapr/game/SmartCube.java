package ru.etu.sapr.game;

import java.util.ArrayList;

/**
 * Created by Red on 06.12.2016.
 */
public class SmartCube extends SimpleCube implements ICubeData {
    public SmartCube(){
        super();
    }

    public SmartCube(SimpleCube src){
        this();
        this.setTransformation( src.getTransformation());
        this.setInstanceID(src.getInstanceID());
    }

    /**
     * Через этот метод куб получает информацию о своём местоположении
     * @return
     */
    public Vector3 GetSelfPosition(){
        return this.getTransformation().position;
    }

    /**
     * Через этот метот куб сообшает позицию в которую он хочет переместиться
     * @param position позиция куда куб хочет переместиться
     */
    public void SetNextSelfPosition(Vector3 position){
        this.getTransformation().position = position;
    }

    //Long GetSelfID();

    /**
     * Получение позиции другого куба по его ID
     * @param cubeID
     * @return
     */
    public Vector3 GetCubePosition(Long cubeID){
        return new Vector3();
    }

    /**
     * Через этот метод куб узнаёт положение всех кубов
     * <b>В этом массиве не должны находиться его координаты!!!</b>
     * @return
     */
    public ArrayList<Vector3> GetAllCubesPosition(){
        return null;
    }
}
