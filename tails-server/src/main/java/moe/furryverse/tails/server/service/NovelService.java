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

package moe.furryverse.tails.server.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.common.exception.NotFoundDataException;
import moe.furryverse.tails.common.model.Chapter;
import moe.furryverse.tails.common.model.Novel;
import moe.furryverse.tails.common.utils.ManageStatusUtils;
import moe.furryverse.tails.common.utils.RandomUtils;
import moe.furryverse.tails.common.utils.TimeUtils;
import moe.furryverse.tails.server.config.PageConfiguration;
import moe.furryverse.tails.server.repository.ChapterRepository;
import moe.furryverse.tails.server.repository.NovelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@SuppressWarnings("DuplicatedCode")
public class NovelService {
    final NovelRepository novelRepository;
    final ChapterRepository chapterRepository;

    public List<Novel> listNovel(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE)
        );
        Page<Novel> novels = accountId == null
                ? novelRepository.findAll(true, false, false, false, pageable)
                : novelRepository.findAllByCreatedBy(accountId, false, pageable);

        return novels.getContent();
    }

    public Novel createNovel(
            String accountId, String name, String description, String cover,
            Set<String> tags, List<String> contents, boolean isPublic
    ) {
        Novel novel = new Novel(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                accountId,
                name,
                description,
                cover,
                0,
                contents == null ? List.of() : contents,
                tags == null ? Set.of() : tags,
                Set.of(),
                Set.of(),
                isPublic,
                false,
                false,
                true,
                false
        );

        return novelRepository.save(novel);
    }

    public Novel getNovel(String accountId, String novelId) {
        Novel novel = novelRepository.findById(novelId).orElse(null);
        ManageStatusUtils.checkReadStatus(novel, accountId);

        return novel;
    }

    public Novel updateNovel(
            String accountId, String novelId, String name, String description,
            String cover, Set<String> tags, List<String> contents, boolean isPublic
    ) {
        Novel record = novelRepository.findById(novelId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(record, accountId);

        Novel novel = new Novel(
                record.novelId(),
                record.created(),
                record.createdBy(),
                name == null ? record.name() : name,
                description == null ? record.description() : description,
                cover == null ? record.cover() : cover,
                record.price(),
                contents == null ? record.contents() : contents,
                tags == null ? record.tags() : tags,
                record.viewers(),
                record.collaborators(),
                isPublic,
                record.isLocked(),
                record.isArchived(),
                record.isReviewing(),
                false
        );

        return novelRepository.save(novel);
    }

    public Novel deleteNovel(String accountId, String novelId) {
        Novel record = novelRepository.findById(novelId).orElse(null);
        ManageStatusUtils.checkDeleteStatus(record, accountId);

        Novel novel = new Novel(
                record.novelId(),
                record.created(),
                record.createdBy(),
                record.name(),
                record.description(),
                record.cover(),
                record.price(),
                record.contents(),
                record.tags(),
                record.viewers(),
                record.collaborators(),
                record.isPublic(),
                record.isLocked(),
                record.isArchived(),
                record.isReviewing(),
                true
        );

        return novelRepository.save(novel);
    }

    public List<Chapter> listChapter(String accountId, String novelId) {
        Novel novel = novelRepository.findById(novelId).orElse(null);
        ManageStatusUtils.checkReadStatus(novel, accountId);

        Pageable pageable = PageRequest.of(0, PageConfiguration.DEFAULT_PAGE_SIZE);
        Page<Chapter> chapters = accountId == null
                ? chapterRepository.findAll(false, false, false, false, true, novelId, pageable)
                : chapterRepository.findAll(accountId, false, novelId, pageable);

        return chapters.getContent();
    }

    public Chapter createChapter(String accountId, String novelId, String name, List<String> contents, boolean isPublic, boolean isDraft) {
        Chapter chapter = new Chapter(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                accountId,
                name,
                contents == null ? List.of() : contents,
                0,
                novelId,
                isDraft,
                isPublic,
                false,
                false,
                true,
                false
        );

        return chapterRepository.save(chapter);
    }

    public Chapter getChapter(String accountId, String novelId, String chapterId) {
        Novel novel = novelRepository.findById(novelId).orElse(null);
        ManageStatusUtils.checkReadStatus(novel, accountId);

        Chapter chapter = chapterRepository.findById(chapterId).orElse(null);
        ManageStatusUtils.checkReadStatus(chapter, accountId);

        if (!novel.novelId().equals(chapter.novelId())) {
            throw new NotFoundDataException("chapter not found", null, null, accountId);
        }

        return chapter;
    }

    public Chapter updateChapter(String accountId, String novelId, String chapterId, String name, List<String> contents, boolean isPublic, boolean isDraft) {
        Novel novel = novelRepository.findById(novelId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(novel, accountId);

        Chapter chapter = chapterRepository.findById(chapterId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(chapter, accountId);

        if (!novel.novelId().equals(chapter.novelId())) {
            throw new NotFoundDataException("chapter not found", null, null, accountId);
        }

        Chapter updated = new Chapter(
                chapter.chapterId(),
                chapter.created(),
                chapter.createdBy(),
                name == null ? chapter.name() : name,
                contents == null ? chapter.contents() : contents,
                chapter.price(),
                chapter.novelId(),
                isDraft,
                isPublic,
                chapter.isLocked(),
                chapter.isArchived(),
                chapter.isReviewing(),
                false
        );

        return chapterRepository.save(updated);
    }

    public Chapter deleteChapter(String accountId, String novelId, String chapterId) {
        Novel novel = novelRepository.findById(novelId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(novel, accountId);

        Chapter chapter = chapterRepository.findById(chapterId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(chapter, accountId);

        if (!novel.novelId().equals(chapter.novelId())) {
            throw new NotFoundDataException("chapter not found", null, null, accountId);
        }

        Chapter deleted = new Chapter(
                chapter.chapterId(),
                chapter.created(),
                chapter.createdBy(),
                chapter.name(),
                chapter.contents(),
                chapter.price(),
                chapter.novelId(),
                chapter.isDraft(),
                chapter.isPublic(),
                chapter.isLocked(),
                chapter.isArchived(),
                chapter.isReviewing(),
                true
        );

        return chapterRepository.save(deleted);
    }
}
