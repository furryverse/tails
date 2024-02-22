package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.dto.CategoryDto;
import moe.furryverse.tails.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/category")
public class CategoryController {
    final HttpServletRequest request;
    final CategoryService categoryService;

    @GetMapping()
    public Object listCategory(int page, int size) {
        return categoryService.listCategory(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                page,
                size
        );
    }

    @PostMapping()
    public Object createCategory(@RequestBody CategoryDto category) {
        return categoryService.createCategory(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                category.categoryName(),
                category.icon(),
                category.color(),
                category.banner(),
                category.bannerBackground(),
                category.background(),
                category.description(),
                category.isPublic()
        );
    }

    @GetMapping("/{categoryId}")
    public Object getCategory(@PathVariable String categoryId) {
        return null;
    }

    @PostMapping("/{categoryId}")
    public Object updateCategory(@PathVariable String categoryId) {
        return null;
    }

    @DeleteMapping("/{categoryId}")
    public Object deleteCategory(@PathVariable String categoryId) {
        return null;
    }

    //////////////////////////////////////////////////////////////// 管理功能
    @GetMapping("/{categoryId}/reviewer")
    public Object listReviewer(@PathVariable String categoryId) {
        return null;
    }

    @PostMapping("/{categoryId}/reviewer")
    public Object addReviewer(@PathVariable String categoryId) {
        return null;
    }

    @DeleteMapping("/{categoryId}/reviewer")
    public Object deleteReviewer(@PathVariable String categoryId) {
        return null;
    }

    @GetMapping("/{categoryId}/administrator")
    public Object listAdministrator(@PathVariable String categoryId) {
        return null;
    }

    @PostMapping("/{categoryId}/administrator")
    public Object addAdministrator(@PathVariable String categoryId) {
        return null;
    }

    @DeleteMapping("/{categoryId}/administrator")
    public Object deleteAdministrator(@PathVariable String categoryId) {
        return null;
    }
}
