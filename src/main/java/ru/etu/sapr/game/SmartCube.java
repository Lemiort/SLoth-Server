package ru.etu.sapr.game;


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
        return  new Vector3(this.getTransformation().position.x,
                this.getTransformation().position.y,
                this.getTransformation().position.z);
    }

    /**
     * Через этот метот куб сообшает позицию в которую он хочет переместиться
     * @param position позиция куда куб хочет переместиться
     */
    public void SetNextSelfPosition(Vector3 position){
        this.getTransformation().position = position;
    }
}
