package ru.etu.sapr;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.etu.sapr.game.SimpleCube;
import ru.etu.sapr.net.JsonContainer;

/**
 * Created by Nikita on 20.11.2016.
 */
public class TestClass {
    public static void main(String[] args) throws Exception {
        /*Vector3 v3 = new Vector3();
        v3.x = 1;
        v3.y = 2;
        v3.z = 3;

        JSONObject jObj = v3.toJSONObject();
        System.out.println("JSONObject Vector3: " + jObj.toString());
        Vector3 newV3 = new Vector3();
        newV3.Parse(jObj);
        System.out.println("Parsed Vector3: " + newV3.toString());

        Transform t1 = new Transform();
        t1.position = v3;

        JSONObject jT1 = t1.toJSONObject();
        System.out.println("JSONObject Transform: " + jT1.toString());
        Transform t2 = new Transform();
        t2.Parse(jT1);
        System.out.println("Parsed Transform: " + t2.toString() + ": " + t2.position.toString());*/


        SimpleCube simpleCube = new SimpleCube();

        JSONParser jsonParser = new JSONParser();
        String str = new String("{\"transformation\":{\"position\":{\"x\":0.0,\"y\":0.0,\"z\":0.0}}}");
        str = simpleCube.toJSONObject().toJSONString().toString();
        System.out.println(simpleCube.toJSONObject().toJSONString());

        Object object = jsonParser.parse(str);

        JSONObject jsonObject = (JSONObject) object;
        simpleCube.Parse(jsonObject);
        System.out.println(simpleCube.toJSONObject().toJSONString());

        JsonContainer message = new JsonContainer();
        str ="{\"objectType\":\"setPosition\",\"objects\":[\"{\\\"transformation\\\":{\\\"position\\\":{\\\"x\\\":0.0,\\\"y\\\":0.0,\\\"z\\\":0.0}}}\"]}";
        jsonObject = (JSONObject) jsonParser.parse( str);
        message.Parse(jsonObject);
        if(message.getObjectTypeStr().contains("setPosition"))
        {
            str = message.getObject(0);
            System.out.println("returned "+str);
            //String str2 = str.substring(1,str.length()-2);
            //System.out.println("returned2 "+str2);
            jsonObject = (JSONObject) jsonParser.parse(str);
            simpleCube.Parse(jsonObject);
            simpleCube.getTransformation().position.y = 0.0f;

            // сериализация
            //message = new JsonContainer();
            message.clear();

            message.addObject(simpleCube.toJSONObject(),0);

            System.out.println("SENT: " + message.toJSONObject().toJSONString());
        }
        else
        {
            System.out.println("Type is : " + message.getObjectTypeStr());
        }
    }
}
