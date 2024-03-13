/*
 * Copyright 2024 FurryVerse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moe.furryverse.tails.controller;

import com.qiniu.util.Auth;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.ServerConfiguration;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/file")
public class FileController {
    final FileService fileService;

    @GetMapping("/{fileId}")
    public Object getFile(@PathVariable String fileId) {
        return null;
    }

    @PostMapping("/upload")
    public Message<?> uploadFile(MultipartFile file, List<MultipartFile> files) {
        return null;
    }
}
