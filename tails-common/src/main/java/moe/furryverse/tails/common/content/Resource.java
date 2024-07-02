/*
 * Copyright 2024 FurryVerse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moe.furryverse.tails.common.content;

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
