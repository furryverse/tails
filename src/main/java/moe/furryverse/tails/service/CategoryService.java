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
import moe.furryverse.tails.model.Category;
import moe.furryverse.tails.repository.CategoryRepository;
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
public class CategoryService {
    final CategoryRepository categoryRepository;

    public List<Category> listCategory(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Category> categories = accountId == null
                ? categoryRepository.findAll(pageable)
                : categoryRepository.findAllByCreatedBy(accountId, pageable);

        return categories.getContent();
    }

    public Category createCategory(
            String accountId,
            String categoryName, String icon, String color, String banner,
            String bannerBackground, String background, String description,
            boolean isPublic
    ) {
        Category category = new Category(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                categoryName,
                accountId,
                icon,
                color,
                banner,
                bannerBackground,
                background,
                description,
                Set.of(),
                Set.of(),
                Set.of(),
                Set.of(),
                isPublic,
                false,
                false,
                true,
                false
        );

        return categoryRepository.save(category);
    }

    public Category getCategory(String categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
