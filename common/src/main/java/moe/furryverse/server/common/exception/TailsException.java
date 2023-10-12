package moe.furryverse.server.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TailsException extends RuntimeException {
    String message;
    String path;
    String method;
    String accountId;

    @Override
    public String getMessage() {
        return "TailsException: " + message + " at " + path + " with method " + method + " with accountId " + accountId + ".";
    }
}
