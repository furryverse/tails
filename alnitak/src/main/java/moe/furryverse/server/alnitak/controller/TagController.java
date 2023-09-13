package moe.furryverse.server.alnitak.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.alnitak.model.Tag;
import moe.furryverse.server.alnitak.service.TagService;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class TagController {
    final TagService tagService;

    @GetMapping("/tag")
    @AccessCheck(access = {Access.TAG_LIST})
    public Message<?> listTag() {
        return Message.success(tagService.listTag());
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
