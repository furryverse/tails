package moe.furryverse.server.arcturus.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.service.HistoryService;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class HistoryController {
    final HistoryService historyService;

    @GetMapping("/post/{postId}/history/{bindId}")
    @AccessCheck(access = {Access.HISTORY_LIST})
    public Message<?> listHistory(@PathVariable String postId, @PathVariable String bindId) {
        return Message.success(historyService.listHistory(postId, bindId));
    }

    @GetMapping("/post/{postId}/history/{bindId}/{historyId}")
    @AccessCheck(access = {Access.HISTORY_GET})
    public Message<?> getHistory(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @PathVariable String bindId,
            @PathVariable String historyId
    ) {
        return Message.success(historyService.getHistory(accountId, postId, bindId, historyId));
    }

    @DeleteMapping("/post/{postId}/history/{bindId}/{historyId}")
    @AccessCheck(access = {Access.HISTORY_DELETE})
    public Message<?> deleteHistory(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @PathVariable String bindId,
            @PathVariable String historyId
    ) {
        return Message.success(historyService.deleteHistory(accountId, postId, bindId, historyId));
    }
}
