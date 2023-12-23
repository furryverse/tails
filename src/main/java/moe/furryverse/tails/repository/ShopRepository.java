package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Shop;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopRepository extends MongoRepository<Shop, String> {
    @NotNull <S extends Shop> S save(@NotNull S entity);

    Shop findShopByShopId(String shopId);

    Shop deleteShopByShopId(String shopId);
}
