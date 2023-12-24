package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.model.Item;
import moe.furryverse.tails.repository.ItemRepository;
import moe.furryverse.tails.repository.ShopRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {
    final ShopRepository shopRepository;
    final ItemRepository itemRepository;
}
