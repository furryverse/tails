package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.PermissionCheck;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.security.Permission;
import moe.furryverse.tails.service.TagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/tag")
public class TagController {
    final HttpServletRequest request;
    final TagService tagService;

    @GetMapping()
    @PermissionCheck(access = {Permission.TAG_LIST})
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
    @PermissionCheck(access = {Permission.TAG_WRITE})
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
    @PermissionCheck(access = {Permission.TAG_READ})
    public Message<?> getTag(@PathVariable String tagId) {
        return Message.success(
                tagService.getTag(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        tagId
                )
        );
    }

    @DeleteMapping("/{tagId}")
    @PermissionCheck(access = {Permission.TAG_DELETE})
    public Message<?> deleteTag(@PathVariable String tagId) {
        return Message.success(
                tagService.deleteTag(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        tagId
                )
        );
    }
}
