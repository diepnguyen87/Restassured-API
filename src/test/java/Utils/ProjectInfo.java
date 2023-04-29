package Utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapility;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectInfo implements RequestCapility {

    private String baseURI;
    private String projectKey;

    private Map<String, List<Map<String, String>>> projectInfo;
    private List<Map<String, String>> issueTypeList;

    public ProjectInfo(String baseURI, String projectKey) {
        this.baseURI = baseURI;
        this.projectKey = projectKey;
        getProjectInfo();
    }

    public String getIssueTypeID (String issueTypeStr){
        String issueTypeID = null;
        if(issueTypeStr == null){
            throw new RuntimeException("[ERROR] issueType can NOT be null");
        }

        issueTypeList = getIssueTypeList();
        for (Map<String, String> issueType : issueTypeList) {
            if(issueType.get("name").equalsIgnoreCase(issueTypeStr)){
                issueTypeID = issueType.get("id");
                break;
            }
        }
        return issueTypeID;
    }

    private void getProjectInfo(){
        String path = "/rest/api/3/project/";
        String encodedCredStr = AuthenticationHandler.getEncodeCredentialString(email, token);

        RequestSpecification request = given();
        request.baseUri(baseURI);
        request.header(defaultHeader);
        request.header(getAuthenticationHeader.apply(encodedCredStr));

        Response response = request.get(path.concat(projectKey));
        projectInfo = JsonPath.from(response.asString()).get();
    }

    private List<Map<String, String>> getIssueTypeList(){
        return projectInfo.get("issueTypes");
    }
}
