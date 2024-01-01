package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.model.Item;
import moe.furryverse.tails.model.Shop;
import moe.furryverse.tails.repository.ItemRepository;
import moe.furryverse.tails.repository.ShopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Page<Shop> shops = shopRepository.findAllByCreatedBy(accountId, pageable);

        return shops.getContent();
    }

    public List<Item> listItem(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, 30));
        Page<Item> items = itemRepository.findAllByCreatedBy(accountId, pageable);

        return items.getContent();
    }

    public Shop getShop(String accountId, String shopId) {
        return null;
    }

    public Shop updateShop(String accountId, String shopId) {
        return null;
    }
}
