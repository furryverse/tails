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

package moe.furryverse.tails.server.repository;

import moe.furryverse.tails.common.model.Account;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    @NotNull <S extends Account> S save(@NotNull S entity);

    // 根据 email 查找和删除
    Account findByEmail(@NotNull String email);

    // 根据 username 查找
    Account findByUsername(@NotNull String username);

    // 查询是否存在 email
    boolean existsByEmail(@NotNull String email);

    // 查询是否存在 username
    boolean existsByUsername(@NotNull String username);
}
