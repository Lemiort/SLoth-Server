package ru.etu.sapr.net;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Red on 21.11.2016.
 */
public class JsonContainer implements IJsonParsable {
    private  String objectType;
    private JSONArray objects;
    private  JSONObject jsonObject;
    private JSONParser parser;
    private Iterator<String> argsIter;

    public String getObjectType(){
        return  objectType;
    }

    public void setObjectType(String str){
        objectType = str;
    }

    public void addObject(JSONObject obj, int index){
        objects.add(index, obj.toJSONString());
    }

    public void addObject(JSONObject obj){
        objects.add(obj.toJSONString());
    }

    public String getObject(int index) throws ParseException {
        argsIter = objects.iterator();
        for(int i=0; i<index && argsIter.hasNext(); i++) {
        //nothing
        }
        return argsIter.next();
    }

    public  JsonContainer() {
        objectType = "";
        objects = new JSONArray();
        jsonObject = new JSONObject();
        parser = new JSONParser();
    }

    public void Parse(JSONObject obj) {
        this.clear();
        objectType = (String)obj.get("objectType");
        objects = (JSONArray)obj.get("objects");
        argsIter = objects.iterator();
    }

    public void clear(){
        jsonObject.clear();
        objects.clear();
    }

    public JSONObject toJSONObject() {
        jsonObject.put("objectType", objectType);
        jsonObject.put("objects", objects);
        return jsonObject;
    }
}
