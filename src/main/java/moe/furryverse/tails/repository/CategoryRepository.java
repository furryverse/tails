package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    <S extends Category> @NotNull S save(@NotNull S category);

    @NotNull List<Category> findAll();

    Category findByCategoryId(String categoryId);

    Category deleteByCategoryId(String categoryId);

    boolean existsByCategoryId(String categoryId);

    boolean existsByName(String name);
}
