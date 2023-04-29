package test;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.BuildModelJSON;
import model.PostBody;
import model.RequestCapility;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PATCHMethod implements RequestCapility {
    public static void main(String[] args) {

        //Form up request with baseURI and header
        String baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecification request = given();
        request.baseUri(baseURI);
        request.header(defaultHeader);

        //Form up request with body
        PostBody postBody = new PostBody();
        postBody.setTitle("Hello new title");
        final String TARGET_POST_NUM = "1";
        Response response = request.body(BuildModelJSON.parseJSONString(postBody)).patch("/posts/".concat(TARGET_POST_NUM));
        response.prettyPrint();
        request.then().body("title", equalTo(postBody.getTitle()));
    }
}
