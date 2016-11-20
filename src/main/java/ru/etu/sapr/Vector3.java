package ru.etu.sapr;

import org.json.simple.JSONObject;

/**
 * Created by Red on 16.10.2016.
 */

public class Vector3 implements IJsonParsable {
    public float x;
    public float y;
    public float z;

    public void Parse(JSONObject obj) {
        JSONObject jsonPosition = (JSONObject) obj.get("position");
        Double x = (Double) jsonPosition.get("x");
        Double y = (Double)jsonPosition.get("y");
        Double z = (Double)jsonPosition.get("z");

        this.x = x.floatValue();
        this.y = y.floatValue();
        this.z = z.floatValue();
    }

    public JSONObject toJSONOject() {
        JSONObject position = new JSONObject();
        position.put("x", this.x);
        position.put("y", this.y);
        position.put("z", this.z);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("position", position);

        return jsonObject;
    }
}
