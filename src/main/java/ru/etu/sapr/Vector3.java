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
        Float x = (Float)obj.get("x");
        Float y = (Float)obj.get("y");
        Float z = (Float)obj.get("z");

        this.x = x.floatValue();
        this.y = y.floatValue();
        this.z = z.floatValue();
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", this.x);
        jsonObject.put("y", this.y);
        jsonObject.put("z", this.z);

        return jsonObject;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}
