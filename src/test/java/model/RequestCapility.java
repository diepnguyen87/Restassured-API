package model;

import io.restassured.http.Header;

import java.util.function.Function;

public interface RequestCapility {

    Header defaultHeader = new Header("Content-type", "application/json; charset=UTF-8");
    Header acceptHeader = new Header("Accept", "application/json; charset=UTF-8");
    String email = "diepniit.nguyenhong@gmail.com";
    String token = "ATATT3xFfGF0cimutKqGdpIvm4cceVWWNmZoigxQzRsZXQUs8fu5tel6xpVjpwwiUa8lhRrpXSlFocitWwfwEfT-6D2Z_LZcMQijZVAuE-_NgHI4OEMthltYFlVNZE806GjTp3MxQahPqwCklsZlOQU3XZrjjEaAG4Yy7AMVpB6byVhUiTa2apY=C2B6C8BB";

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
