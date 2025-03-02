package authors;

public class Authors {

    private String authorID;
    private String fullName;

    public Authors(String authorID, String fullName) {
        this.authorID = authorID;
        this.fullName = fullName;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "authorID='" + authorID + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}