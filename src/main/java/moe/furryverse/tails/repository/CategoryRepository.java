package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CategoryRepository extends MongoRepository<Category, String> {
    @NotNull <S extends Category> S save(@NotNull S entity);

    @NotNull Page<Category> findAll(@NotNull Pageable pageable);

    @Query("{'created_by': ?0}")
    @NotNull Page<Category> findAllByCreatedBy(@NotNull String createdBy, @NotNull Pageable pageable);
}
