package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.service.TagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/tag")
public class TagController {
    final HttpServletRequest request;
    final TagService tagService;

    @GetMapping()
    public Message<?> listTag(int page, int size) {
        return Message.success(
                tagService.listTag(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        page,
                        size
                )
        );
    }

    @PostMapping()
    public Message<?> createTag(
            @RequestParam String name,
            @RequestParam String color
    ) {
        return Message.success(
                tagService.createTag(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        name,
                        color
                )
        );
    }

    @GetMapping("/{tagId}")
    public Message<?> getTag(@PathVariable String tagId) {
        return Message.success(tagService.getTag(tagId));
    }

    @DeleteMapping("/{tagId}")
    public Message<?> deleteTag(@PathVariable String tagId) {
        return Message.success(tagService.deleteTag(tagId));
    }
}
