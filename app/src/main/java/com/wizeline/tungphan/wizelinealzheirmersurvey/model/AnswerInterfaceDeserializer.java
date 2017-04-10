package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wizeline.tungphan.wizelinealzheirmersurvey.constant.ViewConstant;

import java.lang.reflect.Type;

/**
 * Created by tungphan on 4/10/17.
 */

public class AnswerInterfaceDeserializer implements JsonDeserializer<AnswerInterface> {

    @Override
    public AnswerInterface deserialize(JsonElement json, Type typeOfT,
                                       JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = (JsonObject) json;
        JsonElement typeObj = jObject.get("type");
        if (typeObj != null) {
            String typeVal = typeObj.getAsString();
            switch (typeVal) {
                case ViewConstant.TYPE_YES_NO:
                    return context.deserialize(json, YesNoAnswer.class);
                case ViewConstant.TYPE_SINGLE_CHOICE:
                    return context.deserialize(json, SingleChoiceAnswer.class);
            }
        }
        return null;
    }
}
