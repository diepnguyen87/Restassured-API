package model;

public class IssueFields {

    private Fields fields;

    public Fields getFields() {
        return fields;
    }

    public IssueFields(Fields fields) {
        this.fields = fields;
    }

    public static class Fields{
        private String summary;
        private Project project;
        private IssueType issuetype;

        public Fields(String summary, Project project, IssueType issueType) {
            this.summary = summary;
            this.project = project;
            this.issuetype = issueType;
        }

        public String getSummary() {
            return summary;
        }

        public Project getProject() {
            return project;
        }

        public IssueType getIssuetype() {
            return issuetype;
        }
    }

    public static class Project{
        private String key;

        public Project(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    public static class IssueType{
        private String id;

        public IssueType(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}
