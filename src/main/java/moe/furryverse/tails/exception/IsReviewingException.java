package moe.furryverse.tails.exception;

public class IsReviewingException extends TailsException {
    public IsReviewingException(String message, String path, String method, String accountId) {
        super(message, path, method, accountId);
    }
}
