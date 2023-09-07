package moe.furryverse.server.common.exception;

import java.io.IOException;

public class FileFormatException extends TailsException {
    public FileFormatException(String message, String path, String method, String accountId) {
        super(message, path, method, accountId);
    }
}
