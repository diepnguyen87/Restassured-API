package test;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.PostBody;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.equalTo;

public class POSTMethod {
    public static void main(String[] args) {
        String baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecification request = given();
        request.baseUri(baseURI);

        request.header(new Header("Content-type", "application/json; charset=UTF-8"));
        Gson gson = new Gson();
        List<PostBody> postBodyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            PostBody postBody = new PostBody();
            int temp = i+1;
            postBody.setUserId(temp);
            postBody.setTitle("request title " + temp);
            postBody.setBody("request body " + temp);
            Response response = request.body(gson.toJson(postBody)).post("/posts");
            postBodyList.add(postBody);
            System.out.println(postBody);
            response.prettyPrint();

            response.then().statusCode(201);
            response.then().statusLine(containsStringIgnoringCase("201 Created"));
            response.then().body("userId", equalTo(temp));
            response.then().body("title", equalTo("request title " + temp));
            response.then().body("body", equalTo("request body " + temp));
        }
    }
}
