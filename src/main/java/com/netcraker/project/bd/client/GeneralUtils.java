package com.netcraker.project.bd.client;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.netcraker.project.bd.shared.objects.ObjectType;
import com.netcraker.project.bd.shared.objects.Status;

import java.util.HashMap;
import java.util.Map;

class GeneralUtils {

    static Map<Integer,Status> toMapStatus(String jsonStr)
    {
        Map<Integer,Status> map = new HashMap<>();

        JSONValue parsed = JSONParser.parseStrict(jsonStr);
        JSONObject jsonObj = parsed.isObject();
        if(jsonObj == null)
            return map;
        for(String key: jsonObj.keySet())
        {
            map.put(Integer.valueOf(key),new Status(jsonObj.get(key).isObject()));
        }
        return map;
    }

    static Map<Integer,ObjectType> toMapObjects(String jsonStr)
    {
        Map<Integer,ObjectType> map = new HashMap<Integer, ObjectType>();

        JSONValue parsed = JSONParser.parseStrict(jsonStr);
        JSONObject jsonObj = parsed.isObject();
        if(jsonObj == null)
            return map;
        for(String key: jsonObj.keySet())
        {
            map.put(Integer.valueOf(key),new ObjectType(jsonObj.get(key).isObject()));
        }
        return map;
    }
}
