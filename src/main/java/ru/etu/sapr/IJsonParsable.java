package ru.etu.sapr;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * Created by Red on 20.11.2016.
 */
public interface IJsonParsable {
    void Parse(JSONObject obj) throws ParseException;
    JSONObject toJSONObject();
}
