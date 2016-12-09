package ru.etu.sapr.game;

/**
 * Created by Nikita on 06.12.2016.
 */
public class DinamicGoByCircle extends GoByCircle {

    /**
     * ID куби вокруг которого кружим
     */
    private Long centerID;

    public  DinamicGoByCircle(
            Long id, ICubeData cubeData, IOtherCubeData otherCubeData,
            boolean isClockwise, float radius, Long centerID, float speed ){
        Vector3 centerOfCircle = GetCubePosition(centerID);
        super(id, cubeData, otherCubeData, isClockwise, radius, centerOfCircle, speed);
    }


}
