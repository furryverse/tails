package moe.furryverse.server.alnitak.controller;

import moe.furryverse.server.alnitak.model.Category;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class CategoryController {
    @GetMapping("/category")
    @AccessCheck(access = {Access.CATEGORY_LIST})
    public Message<?> listCategory() {
        return null;
    }

    @GetMapping("/category/{categoryId}")
    @AccessCheck(access = {Access.CATEGORY_GET})
    public Message<?> getCategory(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String categoryId
    ) {
        return null;
    }

    @PostMapping("/category/{categoryId}")
    @AccessCheck(access = {Access.CATEGORY_CREATE})
    public Message<?> createCategory(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String categoryId,
            @RequestBody Category category
    ) {
        return null;
    }

    @PutMapping("/category/{categoryId}")
    @AccessCheck(access = {Access.CATEGORY_UPDATE})
    public Message<?> updateCategory(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String categoryId,
            @RequestBody Category category
    ) {
        return null;
    }

    @DeleteMapping("/category/{categoryId}")
    @AccessCheck(access = {Access.CATEGORY_DELETE})
    public Message<?> deleteCategory(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String categoryId,
            @RequestBody Category category
    ) {
        return null;
    }
}