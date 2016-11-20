package ru.etu.sapr;

import org.json.simple.JSONObject;

/**
 * Created by Nikita on 20.11.2016.
 */
public class TestClass {
    public static void main(String[] args) throws Exception {
        Vector3 v3 = new Vector3();
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
        System.out.println("Parsed Transform: " + t2.toString() + ": " + t2.position.toString());
    }
}
