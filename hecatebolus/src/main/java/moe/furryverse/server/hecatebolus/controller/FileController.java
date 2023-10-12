package moe.furryverse.server.hecatebolus.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import moe.furryverse.server.hecatebolus.model.FileRecord;
import moe.furryverse.server.hecatebolus.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class FileController {
    final FileService fileService;

    @PutMapping("/upload")
    @AccessCheck(access = {Access.FILE_UPLOAD})
    public Message<?> uploadFile(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "files", required = false) List<MultipartFile> files
    ) {
        List<FileRecord> records = new ArrayList<>();

        if (file != null) records.add(fileService.uploadFile(accountId, file));
        if (files != null) files.forEach(f -> records.add(fileService.uploadFile(accountId, f)));

        return Message.success(Map.of("files", records));
    }

    @GetMapping("/file")
    @AccessCheck(access = {Access.FILE_LIST})
    public Message<?> listFiles(@RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId) {
        return Message.success(
                Map.of("files", fileService.listFiles(accountId))
        );
    }

    @GetMapping("/file/{fileId}")
    @AccessCheck(access = {Access.FILE_GET})
    public Message<?> getFile(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable("fileId") String fileId
    ) {
        return Message.success(
                Map.of("files", fileService.getFile(accountId, fileId))
        );
    }

    @DeleteMapping("/file/{fileId}")
    @AccessCheck(access = {Access.FILE_DELETE})
    public Message<?> deleteFile(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable("fileId") String fileId
    ) {
        return Message.success(fileService.deleteFile(accountId, fileId));
    }
}