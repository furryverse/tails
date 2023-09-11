package moe.furryverse.server.arcturus.controller;

import moe.furryverse.server.arcturus.model.History;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class HistoryController {
    @GetMapping("/history")
    @AccessCheck(access = {Access.HISTORY_LIST})
    public Message<?> listHistory() {
        return null;
    }

    @GetMapping("/history/{historyId}")
    @AccessCheck(access = {Access.HISTORY_GET})
    public Message<?> getHistory(@PathVariable String historyId) {
        return null;
    }

    @PostMapping("/history/{historyId}")
    @AccessCheck(access = {Access.HISTORY_CREATE})
    public Message<?> createHistory(@PathVariable String historyId, @RequestBody History history) {
        return null;
    }

    @PutMapping("/history/{historyId}")
    @AccessCheck(access = {Access.HISTORY_UPDATE})
    public Message<?> updateHistory(@PathVariable String historyId, @RequestBody History history) {
        return null;
    }

    @DeleteMapping("/history/{historyId}")
    @AccessCheck(access = {Access.HISTORY_DELETE})
    public Message<?> deleteHistory(@PathVariable String historyId) {
        return null;
    }
}
