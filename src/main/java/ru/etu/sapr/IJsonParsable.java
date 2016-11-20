package ru.etu.sapr;
import org.json.simple.JSONObject;
/**
 * Created by Red on 20.11.2016.
 */
public interface IJsonParsable {
    void Parse(JSONObject obj);
    JSONObject toJSONOject();
}
