package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Item;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {
    @NotNull <S extends Item> S save(@NotNull S entity);

    @Query("{'is_public': ?0, 'is_locked': ?1, 'is_reviewing': ?2, 'is_deleted': ?3, 'shop_id': ?4}")
    @NotNull Page<Item> findAll(boolean isPublic, boolean isLocked, boolean isReviewing, boolean isDeleted, @NotNull String shopId, @NotNull Pageable pageable);

    @Query("{'created_by': ?0, 'is_deleted': ?1}")
    @NotNull Page<Item> findAll(@NotNull String createdBy, boolean isDeleted, @NotNull Pageable pageable);

    @Query("{'shop_id': ?0}")
    @NotNull List<Item> findAll(@NotNull String shopId);
}
