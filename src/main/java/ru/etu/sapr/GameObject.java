package ru.etu.sapr;

import org.json.simple.JSONObject;

/**
 * Created by Nikita on 20.11.2016.
 */
public abstract class GameObject implements IJsonParsable {
    Transform transformation;

    public  GameObject()
    {
        transformation = new Transform();
    }

    public void Parse(JSONObject obj) {
        JSONObject jsonTransform = (JSONObject)obj.get("transformation");
        this.transformation.Parse(jsonTransform);
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("transformation", this.transformation.toJSONObject());
        return jsonObject;
    }
}
