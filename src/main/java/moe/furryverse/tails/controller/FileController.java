package moe.furryverse.tails.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.message.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/file")
public class FileController {
    @GetMapping("/{fileId}")
    public Message<?> getFile(@PathVariable String fileId) {
        return Message.success();
    }

    @PostMapping("/upload")
    public Message<?> uploadFile() {
        return Message.success();
    }
}
