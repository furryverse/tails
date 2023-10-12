package moe.furryverse.tails.content;

public class Resource {
    public static class Info {
        public static final String VERSION = "1.0.0";
        public static final String NAME = "Tails";
        public static final String OPEN_SOURCE_LICENSE = "Apache 2.0";
        public static final String OPEN_SOURCE_REPO = "https://github.com/furryverse/tails";
    }

    public static class CustomHeader {
        public static final String ACCOUNT_ID_HEADER = "X-Tails-Account-Id";
        public static final String AUTHORIZE_HEADER = "Authorization";
        public static final String AUTHORIZE_HEADER_PREFIX = "Bearer ";
    }

    public static class OAuthProvider {
        public static final String NESTS_ID = "nests";
    }
}
