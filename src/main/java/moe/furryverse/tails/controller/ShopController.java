package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.PermissionCheck;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.security.Permission;
import moe.furryverse.tails.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/shop")
public class ShopController {
    final HttpServletRequest request;
    final ShopService shopService;

    //////////////////////////////////////////////////////////////// Shop

    @GetMapping()
    @PermissionCheck(access = {Permission.SHOP_LIST}, requiredLogin = false)
    public Message<?> listShop(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return Message.success(
                shopService.listShop(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        page,
                        size
                )
        );
    }

    @PostMapping()
    @PermissionCheck(access = {Permission.SHOP_WRITE})
    public Message<?> createShop(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "cover") String cover,
            @RequestParam(name = "tags") List<String> tags,
            @RequestParam(name = "contents") List<String> contents,
            @RequestParam(name = "is_public") boolean isPublic
    ) {
        return Message.success(
                shopService.createShop(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        name,
                        cover,
                        tags,
                        contents,
                        isPublic
                )
        );
    }

    @PostMapping("/{shopId}")
    @PermissionCheck(access = {Permission.SHOP_UPDATE})
    public Message<?> updateShop(
            @PathVariable String shopId,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "cover") String cover,
            @RequestParam(name = "tags") List<String> tags,
            @RequestParam(name = "contents") List<String> contents,
            @RequestParam(name = "is_public") boolean isPublic
    ) {
        return Message.success(
                shopService.updateShop(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        shopId,
                        name,
                        cover,
                        tags,
                        contents,
                        isPublic
                )
        );
    }

    @GetMapping("/{shopId}")
    @PermissionCheck(access = {Permission.SHOP_READ})
    public Message<?> getShop(@PathVariable String shopId) {
        return Message.success(
                shopService.getShop(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        shopId
                )
        );
    }

    @DeleteMapping("/{shopId}")
    @PermissionCheck(access = {Permission.SHOP_DELETE})
    public Message<?> deleteShop(@PathVariable String shopId) {
        return Message.success(
                shopService.deleteShop(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        shopId
                )
        );
    }

    //////////////////////////////////////////////////////////////// Item

    @GetMapping("/{shopId}/item")
    @PermissionCheck(access = {Permission.ITEM_LIST}, requiredLogin = false)
    public Message<?> listShopItem(@PathVariable String shopId) {
        return Message.success();
    }

    @PostMapping("/{shopId}/item")
    @PermissionCheck(access = {Permission.ITEM_WRITE})
    public Message<?> createShopItem(@PathVariable String shopId) {
        return Message.success();
    }

    @PostMapping("/{shopId}/item/{itemId}")
    @PermissionCheck(access = {Permission.ITEM_UPDATE})
    public Message<?> updateShopItem(@PathVariable String shopId, @PathVariable String itemId) {
        return Message.success();
    }

    @GetMapping("/{shopId}/item/{itemId}")
    @PermissionCheck(access = {Permission.ITEM_READ})
    public Message<?> getShopItem(@PathVariable String shopId, @PathVariable String itemId) {
        return Message.success();
    }

    @DeleteMapping("/{shopId}/item/{itemId}")
    @PermissionCheck(access = {Permission.ITEM_DELETE})
    public Message<?> deleteShopItem(@PathVariable String shopId, @PathVariable String itemId) {
        return Message.success();
    }
}
