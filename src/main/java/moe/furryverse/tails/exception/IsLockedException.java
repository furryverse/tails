package moe.furryverse.tails.exception;

public class IsLockedException extends TailsException {
    public IsLockedException(String message, String path, String method, String accountId) {
        super(message, path, method, accountId);
    }
}
