package ru.etu.sapr;

import org.json.simple.JSONObject;

/**
 * Created by Red on 16.10.2016.
 */
public class Transform implements IJsonParsable {
    public Vector3 position;

    public Transform(){

        position = new Vector3();
    }

    public void Parse(JSONObject obj) {
        JSONObject jsonPosition = (JSONObject)obj.get("position");

        this.position = new Vector3();
        this.position.Parse(jsonPosition);
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("position", this.position.toJSONObject());
        return jsonObject;
    }
}
