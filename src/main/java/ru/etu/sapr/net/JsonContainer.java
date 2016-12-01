package ru.etu.sapr.net;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.etu.sapr.game.ContainerType;
import ru.etu.sapr.game.SimpleCube;


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

    public Object getObject(int index){
        return objects.get(index);
    }

    @Deprecated
    private void  setContainerType(ContainerType type) {
        this.containerType = type;
    }

    @Deprecated
    private String getObjectTypeStr(){
        return objectTypeStr;
    }

    @Deprecated
    private void setObjectTypeStr(String str){
        objectTypeStr = str;
    }

    private void addObject(JSONObject obj, int index){
        jsonArray.add(index, obj.toJSONString());
    }

    private void addObject(JSONObject obj){
        jsonArray.add(obj.toJSONString());
    }


    private String getJsonObject(int index) throws ParseException {
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
                this.containerType = ContainerType.setPosition;
                jsonObject = (JSONObject) parser.parse( this.getJsonObject(0));
                SimpleCube simpleCube = new SimpleCube();
                simpleCube.Parse(jsonObject);
                this.objects.add(simpleCube);
                break;
            case createCube:
                this.containerType = ContainerType.createCube;
                jsonObject = (JSONObject) parser.parse( this.getJsonObject(0));
                simpleCube = new SimpleCube();
                simpleCube.Parse(jsonObject);
                this.objects.add(simpleCube);
                break;
            case getCurrentNum:
                this.containerType = ContainerType.getCurrentNum;
                //нет аргументов вообще
                break;
            case getTransaction:
                this.containerType = ContainerType.getTransaction;
                jsonObject = (JSONObject) parser.parse(argsIter.next());
                this.objects.add (0,((Long)(jsonObject).get("number")).intValue());
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

    public void FormGetCurrentNumContainer()
    {
        this.clear();
        this.containerType = ContainerType.getCurrentNum;
        this.objectTypeStr = this.containerType.name();
    }

    public void FormCurrentNumContainer(int num)
    {
        this.clear();
        this.containerType = ContainerType.currentNum;
        this.objectTypeStr = this.containerType.name();
        this.jsonObject.put("number",num);
        this.addObject(jsonObject,0);
    }

    public void FormGetTransaction(int num)
    {
        this.clear();
        this.containerType = ContainerType.getTransaction;
        this.objectTypeStr = this.containerType.name();
        this.jsonObject.put("number",num);
        this.addObject(jsonObject,0);
    }

    public void FormSetPositionContainer(SimpleCube simpleCube)
    {
        this.clear();
        this.containerType = ContainerType.setPosition;
        this.objectTypeStr = this.containerType.name();
        this.addObject(simpleCube.toJSONObject(), 0);
    }

    public void FormCreateCubeContainer(SimpleCube simpleCube)
    {
        this.clear();
        this.containerType = ContainerType.createCube;
        this.objectTypeStr = this.containerType.name();
        this.addObject(simpleCube.toJSONObject(), 0);
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
