package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class GETMethod {

    public static void main(String[] args) {
        String baseURI = "https://jsonplaceholder.typicode.com";

        //Request
        RequestSpecification request = given();
        request.baseUri(baseURI);
        request.basePath("/todos");

        //Response
        final String FIRST_TODOS = "/1";
        Response response = request.get(FIRST_TODOS);
        response.prettyPrint();
        response.then().body("userId", equalTo(2));
        response.then().body("id", equalTo(1));
        response.then().body("title", equalTo("delectus aut autem"));
        response.then().body("completed", equalTo(false));
    }
}
