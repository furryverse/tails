package moe.furryverse.server.common.content;

public class Resource {
    public static class CustomHeader {
        public static final String ACCOUNT_ID_HEADER = "X-Tails-Account-Id";
        public static final String RPC_MACHINE_ID_HEADER = "X-Tails-Rpc-Machine-Id";
        public static final String RPC_MACHINE_SIGNATURE_HEADER = "X-Tails-Rpc-Signature";
        public static final String AUTHORIZE_HEADER_PREFIX = "Bearer ";

    }

    public static class OAuthProvider {
        public static final String NESTS_ID = "nests";
    }
}
