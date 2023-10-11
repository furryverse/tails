package moe.furryverse.tails.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.AccessCheck;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.model.Tag;
import moe.furryverse.tails.security.Access;
import moe.furryverse.tails.service.TagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class TagController {
    final TagService tagService;

    @GetMapping("/tag")
    @AccessCheck(access = {Access.TAG_LIST})
    public Message<?> listTag(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size
    ) {
        return Message.success(tagService.listTag(page, size));
    }

    @GetMapping("/tag/{tagId}")
    @AccessCheck(access = {Access.TAG_GET})
    public Message<?> getTag(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String tagId
    ) {
        return Message.success(tagService.getTag(accountId, tagId));
    }

    @PostMapping("/tag")
    @AccessCheck(access = {Access.TAG_CREATE})
    public Message<?> createTag(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @RequestBody Tag tag
    ) {
        return Message.success(tagService.createTag(accountId, tag));
    }

    @PutMapping("/tag/{tagId}")
    @AccessCheck(access = {Access.TAG_UPDATE})
    public Message<?> updateTag(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String tagId,
            @RequestBody Tag tag
    ) {
        return Message.success(tagService.updateTag(accountId, tagId, tag));
    }

    @DeleteMapping("/tag/{tagId}")
    @AccessCheck(access = {Access.TAG_DELETE})
    public Message<?> deleteTag(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String tagId
    ) {
        return Message.success(tagService.deleteTag(accountId, tagId));
    }
}
