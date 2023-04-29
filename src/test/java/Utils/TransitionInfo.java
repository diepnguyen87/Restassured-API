package Utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapility;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TransitionInfo implements RequestCapility {

    private String baseURI;
    private String issueID;
    List<Map<String, Object>> transitionList;

    public TransitionInfo(String baseURI, String issueID) {
        this.baseURI = baseURI;
        this.issueID = issueID;
        getTransitionList();
    }

    public String getTransID(String transitionName) {
        String transID = null;
        if(transitionName == null){
            throw new IllegalArgumentException("[ERROR] - transitionName can not be NULL");
        }

        for (Map<String, Object> trans : transitionList) {
            if(trans.get("name").toString().equalsIgnoreCase(transitionName)){
                transID = trans.get("id").toString();
                break;
            }
        }
        return transID;
    }

    private void getTransitionList(){
        String transitionPath = " /rest/api/3/issue/" + issueID + "/transitions";
        String encodedCredStr = AuthenticationHandler.getEncodeCredentialString(email, token);

        RequestSpecification request = given();
        request.baseUri(baseURI);
        request.header(defaultHeader);
        request.header(getAuthenticationHeader.apply(encodedCredStr));

        Response response = request.get(transitionPath);
        transitionList = JsonPath.from(response.body().asString()).get("transitions");
    }
}
