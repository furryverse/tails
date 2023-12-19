package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.exception.NotFoundDataException;
import moe.furryverse.tails.model.Category;
import moe.furryverse.tails.repository.CategoryRepository;
import moe.furryverse.tails.utils.Random;
import moe.furryverse.tails.utils.Time;
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
                category.name(),
                category.icon(),
                category.color(),
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

        return categoryRepository.save(category);
    }

    public Category deleteCategory(String accountId, String categoryId) {
        Category category = categoryRepository.deleteByCategoryId(categoryId);
        if (category == null)
            throw new NotFoundDataException("could not find category", "/api/v0/category/" + categoryId, "DELETE", accountId);

        return category;
    }

    public boolean existsByCategoryId(String categoryId) {
        return categoryRepository.existsByCategoryId(categoryId);
    }

    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
