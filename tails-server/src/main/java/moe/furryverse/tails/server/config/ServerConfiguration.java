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

package moe.furryverse.tails.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ServerConfiguration {
    @Value("${tails.server.nests.oauth.endpoint}")
    String nestsOAuthEndpoint;
    @Value("${tails.server.nests.oauth.client-id}")
    String nestsOAuthClientId;
    @Value("${tails.server.nests.oauth.client-secret}")
    String nestsOAuthClientSecret;
    @Value("${tails.server.nests.oauth.redirect-uri}")
    String nestsOAuthRedirectUri;
}
