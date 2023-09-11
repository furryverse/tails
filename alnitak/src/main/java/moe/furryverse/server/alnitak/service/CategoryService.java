package moe.furryverse.server.alnitak.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.alnitak.model.Category;
import moe.furryverse.server.alnitak.repository.CategoryRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;

    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategory(String accountId, String categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId);
        if (category == null)
            throw new NotFoundDataException("could not find category", "/api/v0/category/" + categoryId, "GET", accountId);

        return category;
    }

    public Category createCategory(
            String accountId,
            String categoryId,
            String categoryName,
            String icon,
            String banner,
            String bannerBackground,
            String background,
            String description,
            List<String> reviewers,
            List<String> administrators,
            List<String> editors,
            List<String> viewers,
            boolean isPublic,
            boolean isLocked
    ) {
        Category category = new Category(
                Time.getMilliUnixTime(),
                Random.uuid(),
                categoryId,
                categoryName,
                icon,
                banner,
                bannerBackground,
                background,
                description,
                reviewers,
                administrators,
                editors,
                viewers,
                isPublic,
                isLocked
        );

        return categoryRepository.save(category);
    }

    public Category updateCategory(String accountId, String categoryId, Category category) {
        Category old = categoryRepository.findByCategoryId(categoryId);
        if (old == null)
            throw new NotFoundDataException("could not find category", "/api/v0/category/" + categoryId, "PUT", accountId);

        return categoryRepository.updateByCategoryId(categoryId, category);
    }

    public Category deleteCategory(String accountId, String categoryId) {
        Category category = categoryRepository.deleteByCategoryId(categoryId);
        if (category == null)
            throw new NotFoundDataException("could not find category", "/api/v0/category/" + categoryId, "DELETE", accountId);

        return category;
    }
}
