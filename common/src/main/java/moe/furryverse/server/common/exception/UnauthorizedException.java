package moe.furryverse.server.common.exception;

public class UnauthorizedException extends RuntimeException {
    String path;
    String uid;

    public UnauthorizedException(String path, String uid) {
        super("Unauthorized access to " + path + " with uid " + uid);
        this.path = path;
        this.uid = uid;
    }
}
