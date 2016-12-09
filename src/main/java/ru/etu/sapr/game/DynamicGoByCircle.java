package ru.etu.sapr.game;

/**
 * Created by Nikita on 06.12.2016.
 */
public class DynamicGoByCircle extends GoByCircle {

    /**
     * ID куба вокруг которого кружим
     */
    private Long centerID;

    public  DynamicGoByCircle(
            Long id, ICubeData cubeData, IOtherCubeData otherCubeData,
            boolean isClockwise, float radius, Long centerID, float speed ){
        super(id, cubeData, otherCubeData, isClockwise, radius, new Vector3(), speed);
        Vector3 centerOfCircle = GetCubePosition(centerID);
        this.centerID = centerID;

        setCenterOfCircle(centerOfCircle);
    }


    @Override
    public void Move() {
        Vector3 oldCenterOfCircle = getCenterOfCircle();
        Vector3 newCenterOfCircle = GetCubePosition(centerID);

        if(!newCenterOfCircle.equals(oldCenterOfCircle)){
            setCenterOfCircle(newCenterOfCircle);
            Jump(VecAlg.Subtraction(newCenterOfCircle, oldCenterOfCircle));
        }

        MoveByCircle();
    }

    private void Jump(Vector3 range){
        // инициализация
        Vector3 thisPosition = GetSelfPosition();

        Vector3 nextPosition = VecAlg.Addition(  thisPosition,   range );

        // переход в новую точку
        SetNextSelfPosition(nextPosition);
    }
}
