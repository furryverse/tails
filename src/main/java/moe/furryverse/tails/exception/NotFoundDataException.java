package moe.furryverse.tails.exception;

// 未找到数据异常
public class NotFoundDataException extends TailsException {
    public NotFoundDataException(String message, String path, String method, String accountId) {
        super(message, path, method, accountId);
    }
}
