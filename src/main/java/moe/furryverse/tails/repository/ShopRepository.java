package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Shop;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ShopRepository extends MongoRepository<Shop, String> {
    @NotNull <S extends Shop> S save(@NotNull S entity);

    @Query("{'is_public': ?0, 'is_locked': ?1, 'is_reviewing': ?2, 'is_deleted': ?3}")
    @NotNull Page<Shop> findAll(boolean isPublic, boolean isLocked, boolean isReviewing, boolean isDeleted, @NotNull Pageable pageable);

    @Query("{'created_by': ?0, 'is_deleted': ?1}")
    @NotNull Page<Shop> findAllByCreatedBy(@NotNull String createdBy, boolean isDeleted, @NotNull Pageable pageable);
}
