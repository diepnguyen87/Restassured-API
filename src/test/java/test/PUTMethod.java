package test;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.PostBody;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.equalTo;

public class PUTMethod {

    public static void main(String[] args) {

        String baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecification request = given();
        request.baseUri(baseURI);

        request.header(new Header("Content-type", "application/json; charset=UTF-8"));
        PostBody postBody1 = new PostBody(1, 1, "new title 1", "new body 1");
        PostBody postBody2 = new PostBody(2, 2, "new title 2", "new body 2");
        PostBody postBody3 = new PostBody(3, 3, "new title 3", "new body 3");

        List<PostBody> postBodyList = Arrays.asList(postBody1, postBody2, postBody3);
        Gson gson = new Gson();
        final int TARTGET_POST_NUM = 1;

        postBodyList.stream().forEach(body -> {
            Response response = request.body(gson.toJson(body)).put("/posts/".concat(String.valueOf(TARTGET_POST_NUM)));
            System.out.println(body);

            response.then().body("userId", equalTo(body.getUserId()));
            response.then().body("title", equalTo(body.getTitle()));
            response.then().body("body", equalTo(body.getBody()));
        });
    }
}
