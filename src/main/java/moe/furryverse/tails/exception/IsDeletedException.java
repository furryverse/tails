package moe.furryverse.tails.exception;

public class IsDeletedException extends TailsException {
    public IsDeletedException(String message, String path, String method, String accountId) {
        super(message, path, method, accountId);
    }
}
