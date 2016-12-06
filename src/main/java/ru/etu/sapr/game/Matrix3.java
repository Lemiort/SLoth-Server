package ru.etu.sapr.game;

/**
 * Created by Nikita on 06.12.2016.
 */
public class Matrix3 {
    public float[][] value;

    public Matrix3(){
        value = new float[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                    value[i][j] = 0;
            }
        }
    }

}
