package ru.etu.sapr.game;

import org.json.simple.JSONObject;
import ru.etu.sapr.net.IJsonParsable;

/**
 * Created by Nikita on 20.11.2016.
 */
public abstract class GameObject implements IJsonParsable {
    private static Long globalObjectsCounter = 0L;
    private Transform transformation;
    private Long instanceID;

    public void setTransformation(Transform transformation) {
        this.transformation = transformation;
    }

    public Transform getTransformation() {
        return transformation;
    }

    public  GameObject() {
        globalObjectsCounter++;
        this.instanceID = globalObjectsCounter;
        transformation = new Transform();
    }

    public void Parse(JSONObject obj) {
        JSONObject jsonTransform = (JSONObject)obj.get("transformation");
        instanceID = (Long)obj.get("instanceID");
        this.transformation.Parse(jsonTransform);
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("transformation", this.transformation.toJSONObject());
        jsonObject.put("instanceID", this.instanceID);
        return jsonObject;
    }

    public Long getInstanceID() {
        return instanceID;
    }
}
