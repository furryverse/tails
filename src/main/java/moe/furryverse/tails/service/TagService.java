package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.model.Tag;
import moe.furryverse.tails.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    final TagRepository tagRepository;

    public List<Tag> listTag(String accountId, int page, int size) {
        return null;
    }

    public Tag createTag(String accountId, String name, String color) {
        return null;
    }

    public Tag getTag(String tagId) {
        return null;
    }

    public Tag deleteTag(String tagId) {
        return null;
    }
}
