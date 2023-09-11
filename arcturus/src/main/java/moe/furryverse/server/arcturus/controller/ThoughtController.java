package moe.furryverse.server.arcturus.controller;

import moe.furryverse.server.arcturus.model.Thought;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class ThoughtController {
    @GetMapping("/thought")
    @AccessCheck(access = {Access.THOUGHT_LIST})
    public Message<?> listThought() {
        return null;
    }

    @GetMapping("/thought/{thoughtId}")
    @AccessCheck(access = {Access.THOUGHT_GET})
    public Message<?> getThought(@PathVariable String thoughtId) {
        return null;
    }

    @PostMapping("/thought/{thoughtId}")
    @AccessCheck(access = {Access.THOUGHT_CREATE})
    public Message<?> createThought(@PathVariable String thoughtId, @RequestBody Thought thought) {
        return null;
    }

    @PutMapping("/thought/{thoughtId}")
    @AccessCheck(access = {Access.THOUGHT_UPDATE})
    public Message<?> updateThought(@PathVariable String thoughtId, @RequestBody Thought thought) {
        return null;
    }

    @DeleteMapping("/thought/{thoughtId}")
    @AccessCheck(access = {Access.THOUGHT_DELETE})
    public Message<?> deleteThought(@PathVariable String thoughtId) {
        return null;
    }
}
