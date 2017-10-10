package md.utm.fcim.common.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter {

    private JsonConverter() { }

    public static <T> T converterToObject(String json, Class<T> jsonType) {
        return createInstanceOfGson().fromJson(json, jsonType);
    }

    public static String converterToJson(Object object) {
        return createInstanceOfGson().toJson(object);
    }

    private static Gson createInstanceOfGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }
}
