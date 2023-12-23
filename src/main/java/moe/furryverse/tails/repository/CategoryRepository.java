package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
    @NotNull <S extends Category> S save(@NotNull S entity);

    Category findCategoryByCategoryId(String categoryId);

    Category deleteCategoryByCategoryId(String categoryId);
}
