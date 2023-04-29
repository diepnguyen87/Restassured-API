package test;

import Utils.ProjectInfo;
import model.RequestCapility;

public class JiraIssueTypes implements RequestCapility {

    public static void main(String[] args) {

        String baseURI = "https://diepniit.atlassian.net";
        String projectKey = "EC";
        ProjectInfo projectInfo = new ProjectInfo(baseURI, projectKey);
        System.out.println("TaskID: " + projectInfo.getIssueTypeID("task"));
    }
}
