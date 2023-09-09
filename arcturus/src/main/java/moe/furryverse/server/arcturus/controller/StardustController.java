package moe.furryverse.server.arcturus.controller;

import moe.furryverse.server.arcturus.model.Stardust;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class StardustController {
    @GetMapping("/stardust")
    @AccessCheck(access = {Access.STARDUST_LIST})
    public Message<?> listStardust() {
        return null;
    }

    @GetMapping("/stardust/{stardustId}")
    @AccessCheck(access = {Access.STARDUST_GET})
    public Message<?> getStardust(@PathVariable String stardustId) {
        return null;
    }

    @PostMapping("/stardust/{stardustId}")
    @AccessCheck(access = {Access.STARDUST_CREATE})
    public Message<?> createStardust(@PathVariable String stardustId, @RequestBody Stardust stardust) {
        return null;
    }

    @PutMapping("/stardust/{stardustId}")
    @AccessCheck(access = {Access.STARDUST_UPDATE})
    public Message<?> updateStardust(@PathVariable String stardustId, @RequestBody Stardust stardust) {
        return null;
    }

    @DeleteMapping("/stardust/{stardustId}")
    @AccessCheck(access = {Access.STARDUST_DELETE})
    public Message<?> deleteStardust(@PathVariable String stardustId) {
        return null;
    }
}
