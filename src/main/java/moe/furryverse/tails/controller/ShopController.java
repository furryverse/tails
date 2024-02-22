package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.PermissionCheck;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.dto.ItemDto;
import moe.furryverse.tails.dto.ShopDto;
import moe.furryverse.tails.security.Permission;
import moe.furryverse.tails.service.ShopService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/shop")
public class ShopController {
    final HttpServletRequest request;
    final ShopService shopService;

    //////////////////////////////////////////////////////////////// Shop

    @GetMapping()
    @PermissionCheck(access = {Permission.SHOP_LIST}, requiredLogin = false)
    public Object listShop(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return shopService.listShop(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                page,
                size
        );
    }

    @PostMapping()
    @PermissionCheck(access = {Permission.SHOP_WRITE})
    public Object createShop(@RequestBody ShopDto shop) {
        return shopService.createShop(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                shop.name(),
                shop.cover(),
                shop.tags(),
                shop.contents(),
                shop.isPublic()
        );
    }

    @PostMapping("/{shopId}")
    @PermissionCheck(access = {Permission.SHOP_UPDATE})
    public Object updateShop(
            @PathVariable String shopId,
            @RequestBody ShopDto shop
    ) {
        return shopService.updateShop(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                shopId,
                shop.name(),
                shop.cover(),
                shop.tags(),
                shop.contents(),
                shop.isPublic()
        );
    }

    @GetMapping("/{shopId}")
    @PermissionCheck(access = {Permission.SHOP_READ})
    public Object getShop(@PathVariable String shopId) {
        return shopService.getShop(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                shopId
        );
    }

    @DeleteMapping("/{shopId}")
    @PermissionCheck(access = {Permission.SHOP_DELETE})
    public Object deleteShop(@PathVariable String shopId) {
        return shopService.deleteShop(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                shopId
        );
    }

    //////////////////////////////////////////////////////////////// Item

    @GetMapping("/{shopId}/item")
    @PermissionCheck(access = {Permission.SHOP_ITEM_LIST}, requiredLogin = false)
    public Object listShopItem(
            @PathVariable String shopId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return shopService.listItem(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                shopId,
                page,
                size
        );
    }

    @PostMapping("/{shopId}/item")
    @PermissionCheck(access = {Permission.SHOP_ITEM_WRITE})
    public Object createShopItem(
            @PathVariable String shopId,
            @RequestBody ItemDto item
    ) {
        return shopService.createItem(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                shopId,
                item.name(),
                item.cover(),
                item.tags(),
                item.shows(),
                item.contents(),
                item.price(),
                item.stock(),
                item.isPublic()
        );
    }

    @PostMapping("/{shopId}/item/{itemId}")
    @PermissionCheck(access = {Permission.SHOP_ITEM_UPDATE})
    public Object updateShopItem(
            @PathVariable String shopId,
            @PathVariable String itemId,
            @RequestBody ItemDto item
    ) {
        return shopService.updateItem(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                shopId,
                itemId,
                item.name(),
                item.cover(),
                item.tags(),
                item.shows(),
                item.contents(),
                item.price(),
                item.stock(),
                item.isPublic()
        );
    }

    @GetMapping("/{shopId}/item/{itemId}")
    @PermissionCheck(access = {Permission.SHOP_ITEM_READ})
    public Object getShopItem(@PathVariable String shopId, @PathVariable String itemId) {
        return shopService.getItem(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                shopId,
                itemId
        );
    }

    @DeleteMapping("/{shopId}/item/{itemId}")
    @PermissionCheck(access = {Permission.SHOP_ITEM_DELETE})
    public Object deleteShopItem(@PathVariable String shopId, @PathVariable String itemId) {
        return shopService.deleteItem(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                shopId,
                itemId
        );
    }
}
