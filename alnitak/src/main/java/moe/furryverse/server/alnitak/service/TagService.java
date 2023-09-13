package moe.furryverse.server.alnitak.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.alnitak.model.Tag;
import moe.furryverse.server.alnitak.repository.TagRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    final TagRepository tagRepository;

    public List<Tag> listTag() {
        Pageable pageable = PageRequest.of(0, 20);

        return tagRepository.findTagOrderByCreated(pageable);
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

        return tagRepository.updateByTagId(tagId, tag);
    }

    public Tag deleteTag(String accountId, String tagId) {
        Tag tag = tagRepository.deleteByTagId(tagId);
        if (tag == null)
            throw new NotFoundDataException("could not find tag", "/api/v0/tag/" + tagId, "DELETE", accountId);

        return tag;
    }
}
