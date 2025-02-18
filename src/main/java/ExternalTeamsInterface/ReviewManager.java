package ExternalTeamsInterface;

public abstract class ReviewManager {
    protected String customerName;
    protected String customerEmail;

    public ReviewManager(String customerName, String customerEmail) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public abstract void submitReview(String review, String showName);
}
