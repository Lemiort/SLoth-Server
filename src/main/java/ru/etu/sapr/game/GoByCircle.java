package ru.etu.sapr.game;

/**
 * заставляет куб двигаться по кругу в плоскости XZ
 */
public class GoByCircle extends CubeAI {

    /**
     * погрешность
     */
    private final float EPS = 1e-2f;
    /**
     *  направление движения
     */
    private boolean isClockwise;
    /**
     *  радиус круга
     */
    private float radius;
    /**
     *  центр круга
     */
    private Vector3 centerOfCircle;
    /**
     *  скорость (возможно стоит вывести в родительский класс)
     */
    private float speed;

    public GoByCircle(Long id, ICubeData cubeData, IOtherCubeData otherCubeData, boolean isClockwise, float radius, Vector3 centerOfCircle, float speed)
    {
        super(id, cubeData, otherCubeData);

        this.isClockwise = isClockwise;
        this.radius = radius;
        this.centerOfCircle = centerOfCircle;
        this.speed = speed;
    }


    @Override
    public void Move() {
        // инициализация
        Vector3 thisPosition = GetSelfPosition();
        Vector3 nextPosition = new Vector3();
        nextPosition.x = thisPosition.x;
        nextPosition.y = thisPosition.y;
        nextPosition.z = thisPosition.z;

        // рассчёт следующей точки
        // проверка на нахождении в нужной плоскости по Y
        if ((thisPosition.y - centerOfCircle.y) > EPS){
            // перемешение в нужную плоскость
            float d = Math.min(Math.abs(centerOfCircle.y - thisPosition.y), speed);
            float e = (centerOfCircle.y > thisPosition.y)?(1.0f):(-1.0f);

            nextPosition.y = thisPosition.y + e*d;
        }
        else{
            // проверка на нахождение в окружности
            if( (Math.abs(Math.pow((thisPosition.x - centerOfCircle.x),2) + Math.pow((thisPosition.z - centerOfCircle.z),2) - radius*radius) > EPS)  ) {
                // переход в точку, принадлежащую окружности
                if((thisPosition.x == centerOfCircle.x) && (thisPosition.z == centerOfCircle.z)) {
                    // хз нужен ли этот финт но на всякий
                    nextPosition.x = thisPosition.x + speed;
                }
                else {
                    // расстояние между точкой и центром
                    float range = VecAlg.Length(VecAlg.Subtraction(thisPosition, centerOfCircle));

                    float e = (radius > range)?(1.0f):(-1.0f);

                    float d = Math.min(Math.abs(range - radius), speed);

                    Vector3 v1 = VecAlg.Subtraction(thisPosition, centerOfCircle);

                    Vector3 v2 = VecAlg.Addition(v1, VecAlg.Multiply( VecAlg.Normalize(v1), e*d ));

                    nextPosition = VecAlg.Addition(v2, centerOfCircle);
                }
            }
            else {
                // движение по окружности
                // синус и косинус угла поворота
                float dsin = (float)Math.sin(speed/radius);
                float dcos = (float)Math.cos(speed/radius);
                //float e = (isClockwise)?(1.0f):(-1.0f);

                // матрица поворота
                Matrix3 rotMatrix = new Matrix3();
                rotMatrix.value[0][0] = dcos;
                rotMatrix.value[0][2] = dsin;
                rotMatrix.value[1][1] = 1f;
                rotMatrix.value[2][0] = -dsin;
                rotMatrix.value[2][2] = dcos;

                Vector3 dV1 = VecAlg.Subtraction(thisPosition, centerOfCircle);
                Vector3 dV2 = VecAlg.Multiply(rotMatrix, dV1);
                nextPosition = VecAlg.Addition(dV2, centerOfCircle);
            }
        }

        // переход в новую точку
        SetNextSelfPosition(nextPosition);
    }

}
