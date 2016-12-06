package ru.etu.sapr.game;

/**
 * Created by Nikita on 06.12.2016.
 */
public class VecAlg {
    private static VecAlg ourInstance = new VecAlg();

    public static VecAlg getInstance() {
        return ourInstance;
    }

    private VecAlg() {
    }

    public static float Length(Vector3 a){
        return (float) Math.sqrt(a.x*a.x + a.y*a.y + a.z*a.z);
    }

    public static Vector3 Addition(Vector3 a, Vector3 b){
        Vector3 result = new Vector3();
        result.x = a.x + b.x;
        result.y = a.y + b.y;
        result.z = a.z + b.z;

        return result;
    }

    public static Vector3 Inverse(Vector3 a){
        Vector3 result = new Vector3();
        result.x = -a.x;
        result.y = -a.y;
        result.z = -a.z;

        return result;
    }

    public static Vector3 Subtraction(Vector3 a, Vector3 b){
        return Addition(a, Inverse(b));
    }

    public static Vector3 Multiply(Vector3 a, float b){
        Vector3 result = new Vector3();

        result.x = a.x*b;
        result.y = a.y*b;
        result.z = a.z*b;

        return result;
    }

    public static Vector3 Multiply(float a, Vector3 b){
        return Multiply(b, a);
    }

    public static Vector3 Multiply(Matrix3 a, Vector3 b){

        Vector3 result = new Vector3();

        result.x = a.value[0][0]*b.x + a.value[0][1]*b.y + a.value[0][2]*b.z;
        result.y = a.value[1][0]*b.x + a.value[1][1]*b.y + a.value[1][2]*b.z;
        result.z = a.value[2][0]*b.x + a.value[2][1]*b.y + a.value[2][2]*b.z;

        return result;
    }

    public static Vector3 Normalize(Vector3 a){
        return Multiply(a, 1.0f/Length(a));
    }
}
