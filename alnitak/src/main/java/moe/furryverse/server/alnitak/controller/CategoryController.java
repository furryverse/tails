package moe.furryverse.server.alnitak.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.alnitak.model.Category;
import moe.furryverse.server.alnitak.service.CategoryService;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class CategoryController {
    final CategoryService categoryService;

    @GetMapping("/category")
    @AccessCheck(access = {Access.CATEGORY_LIST})
    public Message<?> listCategory() {
        return Message.success(categoryService.listCategory());
    }

    @GetMapping("/category/{categoryId}")
    @AccessCheck(access = {Access.CATEGORY_GET})
    public Message<?> getCategory(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String categoryId
    ) {
        return Message.success(categoryService.getCategory(accountId, categoryId));
    }

    @PostMapping("/category")
    @AccessCheck(access = {Access.CATEGORY_CREATE})
    public Message<?> createCategory(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @RequestBody Category category
    ) {
        return Message.success(categoryService.createCategory(accountId, category));
    }

    @PutMapping("/category/{categoryId}")
    @AccessCheck(access = {Access.CATEGORY_UPDATE})
    public Message<?> updateCategory(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String categoryId,
            @RequestBody Category category
    ) {
        return Message.success(categoryService.updateCategory(accountId, categoryId, category));
    }

    @DeleteMapping("/category/{categoryId}")
    @AccessCheck(access = {Access.CATEGORY_DELETE})
    public Message<?> deleteCategory(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String categoryId
    ) {
        return Message.success(categoryService.deleteCategory(accountId, categoryId));
    }
}