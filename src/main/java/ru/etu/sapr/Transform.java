package ru.etu.sapr;

import org.json.simple.JSONObject;

/**
 * Created by Red on 16.10.2016.
 */
public class Transform implements IJsonParsable {
    public Vector3 position;
    private JSONObject jsonObject;
    public Transform(){
        jsonObject = new JSONObject();
        position = new Vector3();
    }

    public void Parse(JSONObject obj) {

        JSONObject jsonPosition = (JSONObject)obj.get("position");
        this.position.Parse(jsonPosition);
    }

    public JSONObject toJSONObject() {
        jsonObject.clear();
        jsonObject.put("position", this.position.toJSONObject());
        return jsonObject;
    }
}
