package moe.furryverse.tails.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.message.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/novel")
public class NovelController {
    @GetMapping()
    public Message<?> listNovel() {
        return Message.success();
    }

    @PostMapping()
    public Message<?> createNovel() {
        return Message.success();
    }

    @GetMapping("/{novelId}")
    public Message<?> getNovel(@PathVariable String novelId) {
        return Message.success();
    }

    @PostMapping("/{novelId}")
    public Message<?> updateNovel(@PathVariable String novelId) {
        return Message.success();
    }

    @DeleteMapping("/{novelId}")
    public Message<?> deleteNovel(@PathVariable String novelId) {
        return Message.success();
    }

    @GetMapping("/{novelId}/chapter")
    public Message<?> listChapter(@PathVariable String novelId) {
        return Message.success();
    }

    @PostMapping("/{novelId}/chapter")
    public Message<?> createChapter(@PathVariable String novelId) {
        return Message.success();
    }

    @GetMapping("/{novelId}/chapter/{chapterId}")
    public Message<?> getChapter(@PathVariable String novelId, @PathVariable String chapterId) {
        return Message.success();
    }

    @PostMapping("/{novelId}/chapter/{chapterId}")
    public Message<?> updateChapter(@PathVariable String novelId, @PathVariable String chapterId) {
        return Message.success();
    }

    @DeleteMapping("/{novelId}/chapter/{chapterId}")
    public Message<?> deleteChapter(@PathVariable String novelId, @PathVariable String chapterId) {
        return Message.success();
    }
}
