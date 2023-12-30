package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
    public Message<?> createCategory() {
        return Message.success();
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
}
