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

import moe.furryverse.tails.common.model.Stub;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StubRepository extends MongoRepository<Stub, String> {
    @NotNull <S extends Stub> S save(@NotNull S entity);

    Page<Stub> findAllByCreatedByAndActivityId(String createdBy, String activityId, Pageable pageable);

    int countAllByCreatedByAndActivityId(String createdBy, String activityId);

    @NotNull Page<Stub> findAll(@NotNull Pageable pageable);

    Stub findStubByCreatedByAndActivityIdAndStubId(String createdBy, String activityId, String stubId);
}
