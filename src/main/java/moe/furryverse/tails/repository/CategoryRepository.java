package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    <S extends Category> @NotNull S save(@NotNull S category);

    @NotNull List<Category> findAll();

    Category findByCategoryId(String categoryId);

    @Query(value = "{ 'category_id' : ?0 }")
    Category updateByCategoryId(String categoryId, Category category);

    Category deleteByCategoryId(String categoryId);

    boolean existsByCategoryId(String categoryId);

    boolean existsByName(String name);
}
