package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.exception.IsDeletedException;
import moe.furryverse.tails.exception.UnauthorizationException;
import moe.furryverse.tails.model.Novel;
import moe.furryverse.tails.repository.ChapterRepository;
import moe.furryverse.tails.repository.NovelRepository;
import moe.furryverse.tails.utils.RandomUtils;
import moe.furryverse.tails.utils.TimeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@SuppressWarnings("Duplicates")
public class NovelService {
    final NovelRepository novelRepository;
    final ChapterRepository chapterRepository;

    public List<Novel> listNovel(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE)
        );
        Page<Novel> novels = accountId == null
                ? novelRepository.findAll(pageable)
                : novelRepository.findAllByAccountId(accountId, pageable);

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
                List.of(),
                Set.of(),
                isPublic,
                false,
                false,
                false
        );

        return novelRepository.save(novel);
    }

    public Novel getNovel(String accountId, String novelId) {
        Novel novel = novelRepository.findById(novelId).orElse(null);
        if (novel == null) return null;

        if (novel.isLocked() || novel.isDeleted() || !novel.isPublic()) {
            if (!Objects.equals(novel.accountId(), accountId) || !novel.viewers().contains(accountId)) {
                return null;
            }
        }

        return novel;
    }

    public Novel updateNovel(
            String accountId, String novelId, String name, String description,
            String cover, List<String> tags, List<String> contents, boolean isPublic
    ) {
        Novel record = novelRepository.findById(novelId).orElse(null);
        if (record == null) return null;

        // 查询是否为小说管理员
        if (!record.collaborators().contains(accountId) || !Objects.equals(accountId, record.accountId())) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        // 如果小说被删除了则无法修改
        if (record.isDeleted()) throw new IsDeletedException("resource is deleted", null, null, accountId);

        Novel novel = new Novel(
                record.novelId(),
                record.created(),
                record.accountId(),
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
                record.isReviewing(),
                false
        );

        return novelRepository.save(novel);
    }

    public Novel deleteNovel(String accountId, String novelId) {
        Novel record = novelRepository.findById(novelId).orElse(null);
        if (record == null) return null;

        // 查询是否为小说管理员
        if (!record.collaborators().contains(accountId) || !Objects.equals(accountId, record.accountId())) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        // 如果小说被删除了则无法修改
        if (record.isDeleted()) throw new IsDeletedException("resource is deleted", null, null, accountId);

        Novel novel = new Novel(
                record.novelId(),
                record.created(),
                record.accountId(),
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
                record.isReviewing(),
                true
        );

        return novelRepository.save(novel);
    }
}
