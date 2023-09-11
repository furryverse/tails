package moe.furryverse.server.alnitak.controller;

import moe.furryverse.server.alnitak.model.Tag;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class TagController {
    @GetMapping("/tag")
    @AccessCheck(access = {Access.TAG_LIST})
    public Message<?> listTag() {
        return null;
    }

    @GetMapping("/tag/{tagId}")
    @AccessCheck(access = {Access.TAG_GET})
    public Message<?> getTag(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String tagId
    ) {
        return null;
    }

    @PostMapping("/tag/{tagId}")
    @AccessCheck(access = {Access.TAG_CREATE})
    public Message<?> createTag(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String tagId,
            @RequestBody Tag tag
    ) {
        return null;
    }

    @PutMapping("/tag/{tagId}")
    @AccessCheck(access = {Access.TAG_UPDATE})
    public Message<?> updateTag(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String tagId,
            @RequestBody Tag tag
    ) {
        return null;
    }

    @DeleteMapping("/tag/{tagId}")
    @AccessCheck(access = {Access.TAG_DELETE})
    public Message<?> deleteTag(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String tagId
    ) {
        return null;
    }
}
