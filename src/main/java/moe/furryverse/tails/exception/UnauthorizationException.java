package moe.furryverse.tails.exception;

@SuppressWarnings("SpellCheckingInspection")
public class UnauthorizationException extends TailsException {
    public UnauthorizationException(String message, String path, String method, String accountId) {
        super(message, path, method, accountId);
    }
}
