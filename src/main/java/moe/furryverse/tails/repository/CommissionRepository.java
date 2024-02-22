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

package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Commission;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CommissionRepository extends MongoRepository<Commission, String> {
    @NotNull <S extends Commission> S save(@NotNull S commission);

    @NotNull Page<Commission> findAll(@NotNull Pageable pageable);

    @Query("{'created_by': ?0}")
    @NotNull Page<Commission> findAllByCreatedBy(@NotNull String createdBy, @NotNull Pageable pageable);
}
