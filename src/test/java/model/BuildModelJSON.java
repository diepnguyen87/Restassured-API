package model;

import com.google.gson.Gson;

public class BuildModelJSON {

    public static String parseJSONString(PostBody body){
        if(body == null){
            throw new IllegalArgumentException("[ERROR] - Body can not be NULL");
        }
        Gson gson = new Gson();
        return gson.toJson(body);
    }
}
