package moe.furryverse.server.hecatebolus.controller;

import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class FileController {
    @PutMapping("/upload")
    @AccessCheck(access = {Access.FILE_UPLOAD})
    public void uploadFile() {

    }

    @GetMapping("/file")
    @AccessCheck(access = {Access.FILE_LIST})
    public void listFiles() {

    }

    @GetMapping("/file/:fileId")
    public void getFile() {

    }

    @DeleteMapping("/file/:fileId")
    @AccessCheck(access = {Access.FILE_DELETE})
    public void deleteFile() {

    }
}
