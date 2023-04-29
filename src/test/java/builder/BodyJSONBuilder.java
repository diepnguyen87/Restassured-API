package builder;

import com.google.gson.Gson;

public class BodyJSONBuilder {

    public static <T> String getJSONContent(T dataObject){
        if(dataObject == null){
            throw new IllegalArgumentException("[ERROR] - dataObject can not be NULL");
        }
        return new Gson().toJson(dataObject);
    }
}
