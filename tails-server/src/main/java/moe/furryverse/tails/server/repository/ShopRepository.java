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

import moe.furryverse.tails.common.model.Shop;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ShopRepository extends MongoRepository<Shop, String> {
    @NotNull <S extends Shop> S save(@NotNull S entity);

    @Query("{'is_public': ?0, 'is_locked': ?1, 'is_reviewing': ?2, 'is_deleted': ?3}")
    @NotNull Page<Shop> findAll(boolean isPublic, boolean isLocked, boolean isReviewing, boolean isDeleted, @NotNull Pageable pageable);

    @Query("{'created_by': ?0, 'is_deleted': ?1}")
    @NotNull Page<Shop> findAllByCreatedBy(@NotNull String createdBy, boolean isDeleted, @NotNull Pageable pageable);
}
