package mailRu.business_objects.letter;

public class Letter {

    private String addressee;
    private String subject;
    private String body;

    public Letter(String addressee, String subject, String body) {
        this.addressee = addressee;
        this.subject = subject;
        this.body = body;
    }

    public Letter(String subject) {
        this.subject = subject;
    }

    public Letter(LetterBuilder builder) {
        this.addressee = builder.addressee;
        this.subject = builder.subject;
        this.body = builder.body;
    }

    public String getAddressee() {
        return addressee;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Addressee: " + getAddressee() + ", Subject: " + getSubject() + ", Body: " + getBody();
    }
}