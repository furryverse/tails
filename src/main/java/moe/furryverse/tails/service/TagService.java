package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.model.Tag;
import moe.furryverse.tails.repository.TagRepository;
import moe.furryverse.tails.utils.RandomUtils;
import moe.furryverse.tails.utils.TimeUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TagService {
    final TagRepository tagRepository;

    public List<Tag> listTag(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.MAX_PAGE_SIZE));

        return accountId == null
                ? tagRepository.findAll(pageable).getContent()
                : tagRepository.findAllByCreatedBy(accountId, pageable).getContent();
    }

    public Tag createTag(String accountId, String name, String color) {
        Tag tag = new Tag(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                accountId,
                name,
                color,
                true,
                false,
                false,
                false,
                false
        );

        return tagRepository.save(tag);
    }

    public Tag getTag(String accountId, String tagId) {
        return tagRepository.findById(tagId).orElse(null);
    }

    public Tag deleteTag(String accountId, String tagId) {
        Tag tag = tagRepository.findById(tagId).orElse(null);
        if (tag == null) return null;
        if (Objects.equals(accountId, tag.createdBy())) tagRepository.delete(tag);

        return null;
    }
}
