package ru.etu.sapr.game;

/**
 * заставляет куб двигаться по кругу в плоскости XZ
 */
public class GoByCircle extends CubeAI {

    /**
     * погрешность
     */
    private final float EPS = 1e-5f;
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
            if( (Math.pow((thisPosition.x - centerOfCircle.x),2) + Math.pow((thisPosition.z - centerOfCircle.z),2) - radius*radius) > EPS ) {
                // переход в точку, принадлежащую окружности
                if((thisPosition.x == centerOfCircle.x) && (thisPosition.z == centerOfCircle.z)) {
                    // хз нужен ли этот финт но на всякий
                    nextPosition.x = thisPosition.x + speed;
                }
                else {
                    // расстояние между точкой и центром
                    float a = Math.abs(thisPosition.x - centerOfCircle.x);
                    float b = Math.abs(thisPosition.z - centerOfCircle.z);
                    float range = (float)Math.sqrt(a*a + b*b);

                    // синус и косинус угла между OX и лучём из центра к точке
                    float sin = (thisPosition.z - centerOfCircle.z)/range;
                    float cos = (thisPosition.x - centerOfCircle.x)/range;

                    float d = Math.min(Math.abs(range - radius), speed);
                    float e = (radius > range)?(1.0f):(-1.0f);

                    nextPosition.x = thisPosition.x + e*d*cos;
                    nextPosition.z = thisPosition.z + e*d*sin;
                }
            }
            else {
                // движение по окружности

                // синус и косинус угла между OX и лучём из центра к точке
                float sin = (thisPosition.z - centerOfCircle.z)/radius;
                float cos = (thisPosition.x - centerOfCircle.x)/radius;

                // синус и косинус угла поворота
                float dsin = (float)Math.sin(speed/radius);
                float dcos = (float)Math.cos(speed/radius);

                float e = (isClockwise)?(-1.0f):(1.0f);

                nextPosition.x = centerOfCircle.x + radius*(cos + e*dcos);
                nextPosition.z = centerOfCircle.z + radius*(sin + e*dsin);
            }
        }

        // переход в новую точку
        SetNextSelfPosition(nextPosition);
    }

}
