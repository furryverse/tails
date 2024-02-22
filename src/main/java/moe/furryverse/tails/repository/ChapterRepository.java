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

import moe.furryverse.tails.model.Chapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChapterRepository extends MongoRepository<Chapter, String> {
    @NotNull <S extends Chapter> S save(@NotNull S entity);

    @Query("{'is_locked': ?0,'is_reviewing': ?1, 'is_deleted': ?2, 'is_draft': ?3, 'is_public': ?4, 'novel_id': ?5}")
    @NotNull Page<Chapter> findAll(boolean isLocked, boolean isReviewing, boolean isDeleted, boolean isDraft, boolean isPublic, String novelId, @NotNull Pageable pageable);

    @Query("{'created_by': ?0, 'is_deleted': ?1, 'novel_id':  ?2}")
    @NotNull Page<Chapter> findAll(@NotNull String createdBy, boolean isDeleted, String novelId, @NotNull Pageable pageable);

    @NotNull List<Chapter> findAllByNovelId(String novelId);
}
