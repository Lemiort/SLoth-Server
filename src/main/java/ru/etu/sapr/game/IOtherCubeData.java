package ru.etu.sapr.game;

import java.util.ArrayList;

/**
 * Created by Nikita on 06.12.2016.
 */
public interface IOtherCubeData {
    /**
     * Получение позиции другого куба по его ID
     * @param cubeID
     * @return
     */
    Vector3 GetCubePosition(Long cubeID);

    /**
     * Через этот метод куб узнаёт положение всех кубов
     * <b>В этом массиве не должны находиться его координаты!!!</b>
     * @return
     */
    ArrayList<Vector3> GetAllCubesPosition();
}
