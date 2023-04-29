package test;

import api_flow.IssueFlow;
import org.testng.annotations.Test;

public class JiraIssueCRUD extends BaseTest {

    @Test
    public void testE2EFlow() {
        IssueFlow issueFlow = new IssueFlow(request, baseURI, projectKey, "task");
        System.out.println("----> Create");
        issueFlow.createIssue();
        issueFlow.verifyIssueDetails();

        System.out.println("----> Update");
        issueFlow.updateIssue("Done");
        issueFlow.verifyIssueDetails();

        System.out.println("----> Delete");
        issueFlow.deleteIssue();
    }
}
