package ru.etu.sapr;

import org.json.simple.JSONObject;

/**
 * Created by Nikita on 20.11.2016.
 */
public abstract class GameObject implements IJsonParsable {
    Transform transform;

    public void Parse(JSONObject obj) {
        JSONObject jsonTransform = (JSONObject)obj.get("transform");
        this.transform.Parse(jsonTransform);
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("transform", this.transform.toJSONObject());
        return jsonObject;
    }
}
