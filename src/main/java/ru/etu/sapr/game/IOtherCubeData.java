package ru.etu.sapr.game;

import java.util.Hashtable;

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

    GameObject GetObjectInformation(Long objectID);

    /**
     *
     * @return
     */
    Hashtable<Long, GameObject> GetAllObjectInformation();
}
