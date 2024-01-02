package moe.furryverse.tails.exception;

public class IsArchivedException extends TailsException {
    public IsArchivedException(String message, String path, String method, String accountId) {
        super(message, path, method, accountId);
    }
}
