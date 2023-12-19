package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Item;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
    @NotNull <S extends Item> S save(@NotNull S item);

    Item findByItemId(@NotNull String itemId);

    Item deleteByItemId(@NotNull String itemId);
}
