package test;

import Utils.AuthenticationHandler;
import io.restassured.specification.RequestSpecification;
import model.RequestCapility;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseTest implements RequestCapility {

    protected String baseURI;
    protected String projectKey;
    protected RequestSpecification request;

    @BeforeSuite
    public void beforeSuite(){
        baseURI = "https://diepniit.atlassian.net";
        projectKey = "EC";
    }

    @BeforeTest
    public void beforeTest(){
        request = given();
        request.baseUri(baseURI);
        request.header(defaultHeader);
        request.header(acceptHeader);
        System.out.println("Token: " + token);
        System.out.println("Email: " + email);
        request.header(getAuthenticationHeader.apply(AuthenticationHandler.getEncodeCredentialString(email, token)));
    }
}
