package ru.etu.sapr.game;

import java.util.ArrayList;

/**
 * Created by Nikita on 05.12.2016.
 */
public interface ICubeData {
    /**
     * Через этот метод куб получает информацию о своём местоположении
     * @return
     */
    Vector3 GetSelfPosition();

    /**
     * Через этот метот куб сообшает позицию в которую он хочет переместиться
     * @param position позиция куда куб хочет переместиться
     */
    void SetNextSelfPosition(Vector3 position);

    //Long GetSelfID();
}
