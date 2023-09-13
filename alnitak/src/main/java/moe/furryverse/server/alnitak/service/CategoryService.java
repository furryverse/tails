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
            Category category
    ) {
        Category join = new Category(
                Time.getMilliUnixTime(),
                Random.uuid(),
                category.categoryId(),
                category.name(),
                category.icon(),
                category.banner(),
                category.bannerBackground(),
                category.background(),
                category.description(),
                category.reviewers(),
                category.administrators(),
                category.editors(),
                category.viewers(),
                category.isPublic(),
                category.isLocked()
        );

        return categoryRepository.save(join);
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
