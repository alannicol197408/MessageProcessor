/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anicol.messageprocessor.utility;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 *
 * @author alan.nicol
 */
public class Serializer implements JsonSerializer<Object>, JsonDeserializer<Object> {

    private static final String CLASS = "class";

    @Override
    public Object deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

        Class clazz = null;
        Gson gson;

        String className = getClassName(json);

        if (className != null) {
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new JsonParseException(e.getMessage());
            }
        } else {
            clazz = type.getClass();
        }

        gson = new Gson();

        return gson.fromJson(json, clazz);

    }

    private String getClassName(JsonElement json) {
        if (json.isJsonObject()) {
            return json.getAsJsonObject().get(CLASS).getAsString();
        }

        return null;

    }

    @Override
    public JsonElement serialize(Object object, Type type, JsonSerializationContext context) throws IllegalArgumentException {
        Gson gson;
        JsonElement jsonElement;

        gson = new Gson();

        jsonElement = gson.toJsonTree(object);
        jsonElement = addObjectTypeToJson(jsonElement, object);

        return jsonElement;
    }

    public JsonElement addObjectTypeToJson(JsonElement jsonElement, Object object) {

        if (jsonElement instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) jsonElement;
            jsonObject.addProperty(CLASS, object.getClass().getName());
        }

        return jsonElement;
    }
}
