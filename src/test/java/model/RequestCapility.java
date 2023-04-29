package model;

import io.restassured.http.Header;

import java.util.function.Function;

public interface RequestCapility {

    Header defaultHeader = new Header("Content-type", "application/json; charset=UTF-8");
    Header acceptHeader = new Header("Accept", "application/json; charset=UTF-8");
    String email = System.getProperty("email");
    String token = System.getProperty("token");
    static Header getAuthenticationHeader(String encodedCredStr){
        if(encodedCredStr == null){
            throw new IllegalArgumentException("[ERROR] - Encoded Credential can not be Null");
        }
        return new Header("Authorization", "Basic ".concat(encodedCredStr));
    }

    Function<String, Header> getAuthenticationHeader = encodedCredStr -> {
        if(encodedCredStr == null){
            throw new IllegalArgumentException("[ERROR] - Encoded Credential can not be Null");
        }
        return new Header("Authorization", "Basic ".concat(encodedCredStr));
    };
}
