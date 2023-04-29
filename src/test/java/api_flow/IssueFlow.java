package api_flow;

import Utils.ProjectInfo;
import Utils.TransitionInfo;
import builder.BodyJSONBuilder;
import builder.IssueContentBuilder;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.IssueFields;
import model.TransitionIssue;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IssueFlow {

    private RequestSpecification request;
    private Response response;
    private String baseURI;
    private String projectKey;
    private String issueTypeID;
    private String issueTypeStr;
    private IssueFields issueFields;
    private String todoStatus = "To Do";
    private String issuePathPrefix = "/rest/api/3/issue";

    private String createdIssueID;

    public IssueFlow(RequestSpecification request, String baseURI, String projectKey, String issueTypeStr) {
        this.request = request;
        this.baseURI = baseURI;
        this.projectKey = projectKey;
        this.issueTypeStr = issueTypeStr;
    }

    @Step("Creating issue")
    public void createIssue(){
        ProjectInfo projectInfo = new ProjectInfo(baseURI, projectKey);
        issueTypeID = projectInfo.getIssueTypeID(issueTypeStr);

        int desiredLength = 20;
        boolean hasLetters = true;
        boolean hasNumber = true;
        String summary = RandomStringUtils.random(desiredLength, hasLetters, hasNumber);

        IssueContentBuilder issueContentBuilder = new IssueContentBuilder();
        String jsonBody = issueContentBuilder.build(summary, projectKey, issueTypeID);
        issueFields = issueContentBuilder.getIssueFields();

        //Create Jira ticket
        response = request.body(jsonBody).post(issuePathPrefix);
        createdIssueID = JsonPath.from(response.body().asString()).get("id");
    }

    @Step("Verify issue details")
    public void verifyIssueDetails(){
        String expectedSummary = issueFields.getFields().getSummary();
        String expectedStatus = todoStatus;

        Map<String, String> issueInfo = getIssueInfoByID(createdIssueID);
        String actualSummary = issueInfo.get("actualSummary");
        String actualStatus = issueInfo.get("actualStatus");

        System.out.println("Expected Summary: " + expectedSummary);
        System.out.println("Actual Summary: " + actualSummary);

        System.out.println("Expected Status: " + expectedStatus);
        System.out.println("Actual Status: " + actualStatus);
    }

    @Step("Updating issue")
    public void updateIssue(String issueTransitionStr){
        if(issueTransitionStr == null){
            throw new IllegalArgumentException("[ERROR] issueStatusStr can NOT be NULL");
        }

        String transitionID = null;

        String transitionPath = issuePathPrefix + "/" + createdIssueID + "/transitions";
        TransitionInfo transitionInfo = new TransitionInfo(baseURI, createdIssueID);
        transitionID = transitionInfo.getTransID(issueTransitionStr);

        if(transitionID == null){
            throw new RuntimeException("[ERROR]" + issueTransitionStr + " is NOT supported" );
        }

        TransitionIssue.Transition transition = new TransitionIssue.Transition(transitionID);
        TransitionIssue transitionIssue = new TransitionIssue(transition);
        String updateTransitionBody = BodyJSONBuilder.getJSONContent(transitionIssue);

        request.body(updateTransitionBody).post(transitionPath);

        //Verity the ticket after updating transition
        Map<String, String> issueInfo = getIssueInfoByID(createdIssueID);
        System.out.println("Actual Status: " + issueInfo.get("actualStatus"));
        System.out.println("Expected Status: " + issueTransitionStr);
    }

    @Step("Deleting issue")
    public void deleteIssue(){
        String path = issuePathPrefix + "/" + createdIssueID;
        request.delete(path).prettyPrint();

        response = request.get(path);
        List<String> errorMessageList = JsonPath.from(response.body().asString()).get("errorMessages");
        System.out.println("Error message: " + errorMessageList.get(0));
    }
    private Map<String, String> getIssueInfoByID(String createdIssueID){
        Response res = request.get(issuePathPrefix.concat("/").concat(createdIssueID));

        Map<String, Object> fields = JsonPath.from(res.body().asString()).get("fields");
        String actualSummary = fields.get("summary").toString();

        Map<String, Object> status = (Map<String, Object>) fields.get("status");
        Map<String, Object> statusCategory = (Map<String, Object>) status.get("statusCategory");
        String actualStatus = statusCategory.get("name").toString();

        Map<String, String> map = new HashMap<>();
        map.put("actualSummary", actualSummary);
        map.put("actualStatus", actualStatus);

        return map;
    }
}
