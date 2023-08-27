package moe.furryverse.server.hecatebolus.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class FileController {
    @PutMapping("/upload")
    public void uploadFile() {

    }

    @GetMapping("/file")
    public void listFiles() {

    }

    @GetMapping("/file/:fileId")
    public void getFile() {

    }

    @DeleteMapping("/file/:fileId")
    public void deleteFile() {

    }
}
