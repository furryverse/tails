package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.model.Tag;
import moe.furryverse.tails.repository.TagRepository;
import moe.furryverse.tails.utils.RandomUtils;
import moe.furryverse.tails.utils.TimeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    final TagRepository tagRepository;

    public List<Tag> listTag(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Tag> tags = accountId == null
                ? tagRepository.findAll(pageable)
                : tagRepository.findAllByAccountId(accountId, pageable);

        return tags.getContent();
    }

    public Tag createTag(String accountId, String name, String color) {
        Tag tag = new Tag(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                name,
                color,
                accountId
        );

        return tagRepository.save(tag);
    }

    public Tag getTag(String tagId) {
        return tagRepository.findById(tagId).orElse(null);
    }

    public Tag deleteTag(String tagId) {
        return tagRepository.deleteTagByTagId(tagId);
    }
}
