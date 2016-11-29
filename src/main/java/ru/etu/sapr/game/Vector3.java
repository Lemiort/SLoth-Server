package ru.etu.sapr.game;

import org.json.simple.JSONObject;
import ru.etu.sapr.net.IJsonParsable;

/**
 * Created by Red on 16.10.2016.
 */

public class Vector3 implements IJsonParsable {
    public float x;
    public float y;
    public float z;
    private JSONObject jsonObject;

    public Vector3(){
        jsonObject = new JSONObject();
    }

    public void Parse(JSONObject obj) {
        Double x = (Double)obj.get("x");
        Double y = (Double)obj.get("y");
        Double z = (Double)obj.get("z");

        this.x = x.floatValue();
        this.y = y.floatValue();
        this.z = z.floatValue();
    }

    public JSONObject toJSONObject() {
        jsonObject.clear();
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