/*
 * Copyright 2024 FurryVerse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moe.furryverse.tails.server.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.common.model.Item;
import moe.furryverse.tails.common.model.Shop;
import moe.furryverse.tails.common.utils.ManageStatusUtils;
import moe.furryverse.tails.common.utils.RandomUtils;
import moe.furryverse.tails.common.utils.TimeUtils;
import moe.furryverse.tails.server.config.PageConfiguration;
import moe.furryverse.tails.server.repository.ItemRepository;
import moe.furryverse.tails.server.repository.ShopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ShopService {
    final ShopRepository shopRepository;
    final ItemRepository itemRepository;

    public List<Shop> listShop(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE)
        );
        Page<Shop> shops = accountId == null
                ? shopRepository.findAll(true, false, false, false, pageable)
                : shopRepository.findAllByCreatedBy(accountId, false, pageable);

        return shops.getContent();
    }

    public Shop createShop(String accountId, String name, String cover, Set<String> tags, List<String> contents, boolean isPublic) {
        return new Shop(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                accountId,
                name,
                cover,
                tags == null ? Set.of() : tags,
                contents == null ? List.of() : contents,
                Set.of(),
                isPublic,
                false,
                false,
                true,
                false
        );
    }

    public Shop getShop(String accountId, String shopId) {
        Shop shop = shopRepository.findById(shopId).orElse(null);
        ManageStatusUtils.checkReadStatus(shop, accountId);

        return shop;
    }

    public Shop updateShop(String accountId, String shopId, String name, String cover, Set<String> tags, List<String> contents, boolean isPublic) {
        Shop shop = shopRepository.findById(shopId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(shop, accountId);

        Shop updated = new Shop(
                shop.shopId(),
                shop.created(),
                shop.createdBy(),
                name,
                cover,
                tags == null ? Set.of() : tags,
                contents == null ? List.of() : contents,
                shop.administrators(),
                isPublic,
                shop.isLocked(),
                shop.isArchived(),
                shop.isReviewing(),
                shop.isDeleted()
        );

        return shopRepository.save(updated);
    }

    public Shop deleteShop(String accountId, String shopId) {
        Shop shop = shopRepository.findById(shopId).orElse(null);
        ManageStatusUtils.checkDeleteStatus(shop, accountId);

        Shop deleted = new Shop(
                shop.shopId(),
                shop.created(),
                shop.createdBy(),
                shop.name(),
                shop.cover(),
                shop.tags(),
                shop.contents(),
                shop.administrators(),
                shop.isPublic(),
                shop.isLocked(),
                shop.isArchived(),
                shop.isReviewing(),
                true
        );

        // 下面的商品删除
        List<Item> items = itemRepository.findAll(shopId);
        for (Item item : items) {
            Item deletedItem = new Item(
                    item.itemId(),
                    item.created(),
                    item.createdBy(),
                    item.name(),
                    item.cover(),
                    item.tags(),
                    item.shows(),
                    item.contents(),
                    item.price(),
                    item.stock(),
                    item.shopId(),
                    item.isPublic(),
                    item.isLocked(),
                    item.isArchived(),
                    item.isReviewing(),
                    true
            );

            itemRepository.save(deletedItem);
        }

        return shopRepository.save(deleted);
    }

    public List<Item> listItem(String accountId, String shopId, int page, int size) {
        Shop shop = shopRepository.findById(shopId).orElse(null);
        ManageStatusUtils.checkReadStatus(shop, accountId);

        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE)
        );
        Page<Item> items = accountId == null
                ? itemRepository.findAll(false, false, false, false, shopId, pageable)
                : itemRepository.findAll(accountId, false, pageable);

        return items.getContent();
    }

    public Item createItem(String accountId, String shopId, String name, String cover, Set<String> tags, List<String> shows, List<String> contents, double price, int stock, boolean isPublic) {
        Item item = new Item(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                accountId,
                name,
                cover,
                tags == null ? Set.of() : tags,
                shows == null ? List.of() : shows,
                contents == null ? List.of() : contents,
                price,
                stock,
                shopId,
                isPublic,
                false,
                false,
                true,
                false
        );

        return itemRepository.save(item);
    }

    public Item getItem(String accountId, String shopId, String itemId) {
        Shop shop = shopRepository.findById(shopId).orElse(null);
        ManageStatusUtils.checkReadStatus(shop, accountId);

        Item item = itemRepository.findById(itemId).orElse(null);
        ManageStatusUtils.checkReadStatus(item, accountId);

        return item;
    }

    public Item updateItem(String accountId, String shopId, String itemId, String name, String cover, Set<String> tags, List<String> shows, List<String> contents, double price, int stock, boolean isPublic) {
        Shop shop = shopRepository.findById(shopId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(shop, accountId);

        Item item = itemRepository.findById(itemId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(item, accountId);

        Item updated = new Item(
                item.itemId(),
                item.created(),
                item.createdBy(),
                name,
                cover,
                tags == null ? Set.of() : tags,
                shows == null ? List.of() : shows,
                contents == null ? List.of() : contents,
                price,
                stock,
                item.shopId(),
                isPublic,
                item.isLocked(),
                item.isArchived(),
                item.isReviewing(),
                item.isDeleted()
        );

        return itemRepository.save(updated);
    }

    public Item deleteItem(String accountId, String shopId, String itemId) {
        Shop shop = shopRepository.findById(shopId).orElse(null);
        ManageStatusUtils.checkDeleteStatus(shop, accountId);

        Item item = itemRepository.findById(itemId).orElse(null);
        ManageStatusUtils.checkDeleteStatus(item, accountId);

        Item deleted = new Item(
                item.itemId(),
                item.created(),
                item.createdBy(),
                item.name(),
                item.cover(),
                item.tags(),
                item.shows(),
                item.contents(),
                item.price(),
                item.stock(),
                item.shopId(),
                item.isPublic(),
                item.isLocked(),
                item.isArchived(),
                item.isReviewing(),
                true
        );

        return itemRepository.save(deleted);
    }
}
