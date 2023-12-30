package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/category")
public class CategoryController {
    final HttpServletRequest request;
    final CategoryService categoryService;

    @GetMapping()
    public Message<?> listCategory(int page, int size) {
        return Message.success();
    }

    @PostMapping()
    public Message<?> createCategory(
            @RequestParam("category_name") String categoryName,
            @RequestParam("icon") String icon,
            @RequestParam("color") String color,
            @RequestParam("banner") String banner,
            @RequestParam("banner_background") String bannerBackground,
            @RequestParam("background") String background,
            @RequestParam("description") String description,
            @RequestParam("is_public") boolean isPublic
    ) {
        return Message.success(
                categoryService.createCategory(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        categoryName,
                        icon,
                        color,
                        banner,
                        bannerBackground,
                        background,
                        description,
                        isPublic
                )
        );
    }

    @GetMapping("/{categoryId}")
    public Message<?> getCategory(@PathVariable String categoryId) {
        return Message.success();
    }

    @PostMapping("/{categoryId}")
    public Message<?> updateCategory(@PathVariable String categoryId) {
        return Message.success();
    }

    @DeleteMapping("/{categoryId}")
    public Message<?> deleteCategory(@PathVariable String categoryId) {
        return Message.success();
    }

    //////////////////////////////////////////////////////////////// 管理功能
    @GetMapping("/{categoryId}/reviewer")
    public Message<?> listReviewer(@PathVariable String categoryId) {
        return Message.success();
    }

    @PostMapping("/{categoryId}/reviewer")
    public Message<?> addReviewer(@PathVariable String categoryId) {
        return Message.success();
    }

    @DeleteMapping("/{categoryId}/reviewer")
    public Message<?> deleteReviewer(@PathVariable String categoryId) {
        return Message.success();
    }

    @GetMapping("/{categoryId}/administrator")
    public Message<?> listAdministrator(@PathVariable String categoryId) {
        return Message.success();
    }

    @PostMapping("/{categoryId}/administrator")
    public Message<?> addAdministrator(@PathVariable String categoryId) {
        return Message.success();
    }

    @DeleteMapping("/{categoryId}/administrator")
    public Message<?> deleteAdministrator(@PathVariable String categoryId) {
        return Message.success();
    }
}
