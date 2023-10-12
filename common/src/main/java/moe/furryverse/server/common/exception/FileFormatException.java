package moe.furryverse.server.common.exception;

public class FileFormatException extends TailsException {
    public FileFormatException(String message, String path, String method, String accountId) {
        super(message, path, method, accountId);
    }
}
