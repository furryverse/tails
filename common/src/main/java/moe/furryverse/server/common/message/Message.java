package moe.furryverse.server.common.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message<T> {
    int status;
    long timestamp;
    String message;
    T data;

    public static <T> Message<T> success(T data) {
        return new Message<>(ReturnCode.SUCCESS, System.currentTimeMillis(), ReturnMessage.SUCCESS, data);
    }

    public static <T> Message<T> success() {
        return new Message<>(ReturnCode.SUCCESS, System.currentTimeMillis(), ReturnMessage.SUCCESS, null);
    }

    public static class ReturnCode {
        // 标准状态的
        public static final int SUCCESS = 200;
        public static final int BAD_REQUEST = 400;
        public static final int UNAUTHORIZED = 401;
        public static final int FORBIDDEN = 403;
        public static final int NOT_FOUND = 404;
        public static final int METHOD_NOT_ALLOWED = 405;
        public static final int CONFLICT = 409;
        public static final int INTERNAL_SERVER_ERROR = 500;
        public static final int SERVICE_UNAVAILABLE = 503;
    }

    public static class ReturnMessage {
        // 标准状态的
        public static final String SUCCESS = "Success ฅ ฅ";
        public static final String BAD_REQUEST = "Bad Request o(￣ヘ￣o＃)";
        public static final String UNAUTHORIZED = "Unauthorized Σ(-`Д´-ﾉ；)ﾉ";
        public static final String FORBIDDEN = "Forbidden (╯°Д°)╯︵ ┻━┻";
        public static final String NOT_FOUND = "Not Found ʕ•̀ o • ʔ";
        public static final String METHOD_NOT_ALLOWED = "Method Not Allowed o(一︿一+)o";
        public static final String CONFLICT = "Conflict (＠_＠;)";
        public static final String INTERNAL_SERVER_ERROR = "Internal Server Error (￣^￣)";
        public static final String SERVICE_UNAVAILABLE = "Service Unavailable  ( >﹏<。)～";
    }


    public static class ExceptionMessage {
        public static final String NOT_LOGIN = "Required Login";
        public static final String NOT_FOUND_ACCOUNT_WITH_ID = "Could not find account with id";
        public static final String NOT_FOUND_ACCOUNT_ACCESS_IN_REQUEST = "Could not find account access in request";
        public static final String NOT_FOUND_ACCOUNT_WITH_USERNAME = "Could not find account with username";
        public static final String NOT_FOUND_ACCOUNT_ID_IN_REQUEST = "Could not find account id in request";
        public static final String NOT_FOUND_TOKEN_IN_REQUEST = "Could not find token in request";
        public static final String PERMISSION_DENIED = "Permission denied";
    }
}


