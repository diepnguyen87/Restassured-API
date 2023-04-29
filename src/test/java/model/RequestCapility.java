package model;

import io.restassured.http.Header;

import java.util.function.Function;

public interface RequestCapility {

    Header defaultHeader = new Header("Content-type", "application/json; charset=UTF-8");
    Header acceptHeader = new Header("Accept", "application/json; charset=UTF-8");

    String email = "diepniit.nguyenhong@gmail.com";
    String token = "ATATT3xFfGF0OdpHY1NEXh4EtTCr01v8JEOjk0BBXxQADsJvsGTsQl_lCKSa3pSDyg021LV9-1TeGGwPZ-3uS4Ky161wbb1gumnNpVnuWc0htYZC8TRhsBlZpwPo3sEqzlpANMkD55K8iScGavKLTSulwPkr0dpEuD2-kX745uNCed27168eziE=DBE7C47E";
    //String email = System.getProperty("email");
    //String token = System.getProperty("token");
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
