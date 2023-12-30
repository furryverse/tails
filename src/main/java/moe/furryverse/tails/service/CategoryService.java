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

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;

    public List<Category> listCategory(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Category> categories = accountId == null
                ? categoryRepository.findAll(pageable)
                : categoryRepository.findAllByAccountId(accountId, pageable);

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
                List.of(),
                List.of(),
                List.of(),
                List.of(),
                isPublic,
                false
        );

        return categoryRepository.save(category);
    }

    public Category getCategory(String categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
