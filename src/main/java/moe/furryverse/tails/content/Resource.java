package moe.furryverse.tails.content;

public class Resource {
    public static class ServiceName {
        public static final String POST_SERVICE = "alnitak";
        public static final String COMMENT_SERVICE = "ascella";
        public static final String ACCOUNT_SERVICE = "ascella";
        @SuppressWarnings("SpellCheckingInspection")
        public static final String MANAGER_SERVICE = "borealis";
        @SuppressWarnings("SpellCheckingInspection")
        public static final String FILE_SERVICE = "hecatebolus";
        public static final String FRIEND_SERVICE = "mira";
        @SuppressWarnings("SpellCheckingInspection")
        public static final String MAP_SERVICE = "naos";
        @SuppressWarnings("SpellCheckingInspection")
        public static final String CHAT_SERVICE = "nanto";
        public static final String GATEWAYS_SERVICE = "nunki";
        @SuppressWarnings("SpellCheckingInspection")
        public static final String PUSH_SERVICE = "polis";
    }

    public static class CustomHeader {
        public static final String ACCOUNT_ID_HEADER = "X-Tails-Account-Id";
        public static final String ACCOUNT_ACCESS_HEADER = "X-Tails-Account-Access";
        public static final String AUTHORIZE_HEADER = "Authorization";
        public static final String AUTHORIZE_HEADER_PREFIX = "Bearer ";
    }

    public static class ExtendInfo {
        public static int AUTHORIZE_BANNER_LENGTH = 7;
        public static int NOT_LOGIN_ACCOUNT_ID = -1;
    }

    public static class OAuthProvider {
        public static final String NESTS_ID = "nests";
    }
}
