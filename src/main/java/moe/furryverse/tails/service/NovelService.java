package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.exception.NotFoundDataException;
import moe.furryverse.tails.model.Chapter;
import moe.furryverse.tails.model.Novel;
import moe.furryverse.tails.repository.ChapterRepository;
import moe.furryverse.tails.repository.NovelRepository;
import moe.furryverse.tails.utils.ManageStatusUtils;
import moe.furryverse.tails.utils.RandomUtils;
import moe.furryverse.tails.utils.TimeUtils;
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
            List<String> tags, List<String> contents, boolean isPublic
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
                tags == null ? List.of() : tags,
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
            String cover, List<String> tags, List<String> contents, boolean isPublic
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

        Novel deleted = novelRepository.save(record);

        // 遍历本小说的全部章节 并且一一删除
        List<Chapter> chapters = chapterRepository.findAllByNovelId(novelId);
        for (Chapter chapter : chapters) {
            Chapter deleting = new Chapter(
                    chapter.chapterId(),
                    chapter.created(),
                    chapter.createdBy(),
                    chapter.name(),
                    chapter.contents(),
                    chapter.price(),
                    chapter.novelId(),
                    chapter.isDraft(),
                    record.isPublic(),
                    chapter.isLocked(),
                    record.isArchived(),
                    chapter.isReviewing(),
                    true
            );

            chapterRepository.save(deleting);
        }

        return deleted;
    }

    public List<Chapter> listChapter(String accountId, String novelId) {
        Novel novel = novelRepository.findById(novelId).orElse(null);
        if (novel == null) throw new NotFoundDataException("resource not found", null, null, accountId);

        return null;
    }

    public Chapter createChapter(String novelId) {
        return null;
    }

    public Chapter getChapter(String novelId, String chapterId) {
        return null;
    }

    public Chapter updateChapter(String novelId, String chapterId) {
        return null;
    }

    public Chapter deleteChapter(String novelId, String chapterId) {
        return null;
    }
}
