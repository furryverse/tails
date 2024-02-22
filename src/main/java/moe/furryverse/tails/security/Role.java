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

package moe.furryverse.tails.security;

import lombok.Getter;
import lombok.experimental.Accessors;
import moe.furryverse.tails.utils.ReflectionUtils;

import java.util.Set;

public class Role {
    @Getter
    @Accessors(fluent = true)
    static Set<String> allPermissions;

    static {
        try {
            allPermissions = ReflectionUtils.getAllStaticPublicStrings(Permission.class);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("unable to get all permissions", e);
        }
    }
}
