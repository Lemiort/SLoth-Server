package ru.etu.sapr.game;

import org.json.simple.parser.ParseException;
import ru.etu.sapr.net.JsonContainer;

import java.util.ArrayList;

/**
 * Created by Nikita on 29.11.2016.
 */
public class Container {

    private String objectType;
    private ArrayList<Object> objects;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public void setObjects(ArrayList<Object> objects) {
        this.objects = objects;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void addObject (Object obj, int index) {
        objects.add(index, obj);
    }

    public void addObject(Object obj){
        objects.add(obj);
    }

    // TODO: методы извлечения объектов из массива (если getObjects() не достаточно)

    public Container(){
        this.objectType = "";
        this.objects = new ArrayList<Object>();
    }

    public Container(String objectType)
    {
        this();
        this.objectType = objectType;
    }






    /**
     * Возможно метод стоит переименовать
     * TODO: заставить это работать (если он нужен)
     * @return
     */
    public JsonContainer toJsonContainer() {
        return null;
    }

    /**
     * Возможно метод стоит переименовать
     * TODO: заставить это работать
     * @param obj
     * @throws ParseException
     */
    public void Parse(JsonContainer obj) throws ParseException {
        this.setObjectType(obj.getObjectTypeStr());
        try {

        }
        catch (IllegalArgumentException e){
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
        switch (ContainerType.valueOf(this.getObjectType())){
            // распаковка
            case getCurrentNum:
                this.objects.clear();
                break;
            case  createCube:
                Transform transform = new Transform();
                //JSONObject jsonTransfotm = TODO
                break;


            default: // TODO: throw exception
        }
    }
}
