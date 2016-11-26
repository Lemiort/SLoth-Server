package ru.etu.sapr.game;

import org.json.simple.JSONObject;
import ru.etu.sapr.net.IJsonParsable;

/**
 * Created by Nikita on 20.11.2016.
 */
public abstract class GameObject implements IJsonParsable {
    private Transform transformation;

    public void setTransformation(Transform transformation) {
        this.transformation = transformation;
    }

    public Transform getTransformation() {
        return transformation;
    }

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
