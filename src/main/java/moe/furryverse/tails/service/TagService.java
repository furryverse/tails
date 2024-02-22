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

        return tag;
    }
}
