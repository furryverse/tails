package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.exception.NotFoundDataException;
import moe.furryverse.tails.model.Tag;
import moe.furryverse.tails.repository.TagRepository;
import moe.furryverse.tails.utils.Random;
import moe.furryverse.tails.utils.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    final TagRepository tagRepository;

    public List<Tag> listTag(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size == 0 ? 20 : Math.max(size, 20),
                Sort.by(Sort.Order.desc("created"))
        );
        Page<Tag> pages = tagRepository.findAll(pageable);

        return pages.getContent();
    }

    public Tag getTag(String accountId, String tagId) {
        Tag tag = tagRepository.findByTagId(tagId);
        if (tag == null)
            throw new NotFoundDataException("could not find tag", "/api/v0/tag/" + tagId, "GET", accountId);

        return tag;
    }

    public Tag createTag(
            String accountId,
            Tag tag
    ) {
        Tag join = new Tag(
                Time.getMilliUnixTime(),
                Random.uuid(),
                tag.name(),
                tag.color(),
                accountId
        );

        return tagRepository.save(join);
    }

    public Tag updateTag(String accountId, String tagId, Tag tag) {
        Tag tag1 = tagRepository.findByTagId(tagId);
        if (tag1 == null)
            throw new NotFoundDataException("could not find tag", "/api/v0/tag/" + tagId, "PUT", accountId);

        return tagRepository.save(tag);
    }

    public Tag deleteTag(String accountId, String tagId) {
        Tag tag = tagRepository.deleteByTagId(tagId);
        if (tag == null)
            throw new NotFoundDataException("could not find tag", "/api/v0/tag/" + tagId, "DELETE", accountId);

        return tag;
    }

    public boolean existsByTagId(String tagId) {
        return tagRepository.existsByTagId(tagId);
    }

    public boolean existsByName(String name) {
        return tagRepository.existsByName(name);
    }
}
