package builder;

import model.IssueFields;

public class IssueContentBuilder {

    private IssueFields issueFields;

    public IssueFields getIssueFields() {
        return issueFields;
    }

    public String build(String summary, String projectKey, String issueTypeID){
        IssueFields.Project project = new IssueFields.Project(projectKey);
        IssueFields.IssueType issueType = new IssueFields.IssueType(issueTypeID);
        IssueFields.Fields fields = new IssueFields.Fields(summary, project, issueType);
        issueFields = new IssueFields(fields);
        return BodyJSONBuilder.getJSONContent(issueFields);
    }
}
