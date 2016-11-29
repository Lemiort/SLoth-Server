package ru.etu.sapr.net;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.etu.sapr.game.Container;
import ru.etu.sapr.game.ContainerType;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Red on 21.11.2016.
 */
public class JsonContainer implements IJsonParsable {
    private  String objectTypeStr;
    private JSONArray jsonArray;
    private  JSONObject jsonObject;
    private JSONParser parser;
    private Iterator<String> argsIter;
    private ArrayList<Object> objects;
    private ContainerType containerType;

    public ContainerType getContainerType() {
        return containerType;
    }

    public void  setContainerType(ContainerType type) {
        this.containerType = type;
    }

    public String getObjectTypeStr(){
        return objectTypeStr;
    }

    public void setObjectTypeStr(String str){
        objectTypeStr = str;
    }

    public void addObject(JSONObject obj, int index){
        jsonArray.add(index, obj.toJSONString());
    }

    public void addObject(JSONObject obj){
        jsonArray.add(obj.toJSONString());
    }

    public String getObject(int index) throws ParseException {
        argsIter = jsonArray.iterator();
        for(int i=0; i<index && argsIter.hasNext(); i++) {
        //nothing
        }
        return argsIter.next();
    }

    public  JsonContainer() {
        containerType = ContainerType.unknown;
        objectTypeStr = "";
        objects = new ArrayList<Object>();
        jsonArray = new JSONArray();
        jsonObject = new JSONObject();
        parser = new JSONParser();
    }

    public void Parse(JSONObject obj) throws  ParseException {
        this.clear();
        objectTypeStr = (String)obj.get("objectType");
        jsonArray = (JSONArray)obj.get("objects");
        argsIter = jsonArray.iterator();
        switch (ContainerType.valueOf(this.objectTypeStr)){
            case setPosition:
                break;
            case createCube:
                break;
            case getCurrentNum:
                this.containerType = ContainerType.getCurrentNum;
                //нет аргументов вообще
                break;
            case currentNum:
                this.containerType = ContainerType.currentNum;
                jsonObject = (JSONObject) parser.parse(argsIter.next());
                this.objects.add (0,((Long)(jsonObject).get("number")).intValue());
                break;
            default:
                this.containerType = ContainerType.unknown;
                break;
        }
    }

    public void clear(){
        jsonObject.clear();
        jsonArray.clear();
        objects.clear();
        containerType = ContainerType.unknown;
        objectTypeStr = "";
    }

    public JSONObject toJSONObject() {
        jsonObject.put("objectType", objectTypeStr);
        jsonObject.put("objects", jsonArray);
        return jsonObject;
    }
}
