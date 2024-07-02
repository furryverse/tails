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

package moe.furryverse.tails.server.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.common.content.Resource;
import moe.furryverse.tails.common.security.Permission;
import moe.furryverse.tails.server.annotation.PermissionCheck;
import moe.furryverse.tails.server.dto.ChapterDto;
import moe.furryverse.tails.server.dto.NovelDto;
import moe.furryverse.tails.server.service.NovelService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/novel")
public class NovelController {
    final HttpServletRequest request;
    final NovelService novelService;

    //////////////////////////////////////////////////////////////// Novel

    @GetMapping()
    @PermissionCheck(access = {Permission.NOVEL_LIST}, requiredLogin = false)
    public Object listNovel(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return novelService.listNovel(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                page,
                size
        );
    }

    @PostMapping()
    @PermissionCheck(access = {Permission.NOVEL_WRITE})
    public Object createNovel(@RequestBody NovelDto novel) {
        return novelService.createNovel(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                novel.name(),
                novel.description(),
                novel.cover(),
                novel.tags(),
                novel.contents(),
                novel.isPublic()
        );
    }

    @GetMapping("/{novelId}")
    @PermissionCheck(access = {Permission.NOVEL_READ})
    public Object getNovel(@PathVariable String novelId) {
        return novelService.getNovel(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                novelId
        );
    }

    @PostMapping("/{novelId}")
    @PermissionCheck(access = {Permission.NOVEL_UPDATE})
    public Object updateNovel(
            @PathVariable String novelId,
            @RequestBody NovelDto novel
    ) {
        return novelService.updateNovel(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                novelId,
                novel.name(),
                novel.description(),
                novel.cover(),
                novel.tags(),
                novel.contents(),
                novel.isPublic()
        );
    }

    @DeleteMapping("/{novelId}")
    @PermissionCheck(access = {Permission.NOVEL_DELETE})
    public Object deleteNovel(@PathVariable String novelId) {
        return novelService.deleteNovel(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                novelId
        );
    }

    //////////////////////////////////////////////////////////////// Chapter

    @GetMapping("/{novelId}/chapter")
    @PermissionCheck(access = {Permission.NOVEL_CHAPTER_LIST}, requiredLogin = false)
    public Object listChapter(@PathVariable String novelId) {
        return novelService.listChapter(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                novelId
        );
    }

    @PostMapping("/{novelId}/chapter")
    @PermissionCheck(access = {Permission.NOVEL_CHAPTER_WRITE})
    public Object createChapter(
            @PathVariable String novelId,
            @RequestBody ChapterDto chapter
    ) {
        return novelService.createChapter(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                novelId,
                chapter.name(),
                chapter.contents(),
                chapter.isPublic(),
                chapter.isDraft()
        );
    }

    @GetMapping("/{novelId}/chapter/{chapterId}")
    @PermissionCheck(access = {Permission.NOVEL_CHAPTER_READ})
    public Object getChapter(@PathVariable String novelId, @PathVariable String chapterId) {
        return novelService.getChapter(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                novelId,
                chapterId
        );
    }

    @PostMapping("/{novelId}/chapter/{chapterId}")
    @PermissionCheck(access = {Permission.NOVEL_CHAPTER_UPDATE})
    public Object updateChapter(
            @PathVariable String novelId,
            @PathVariable String chapterId,
            @RequestBody ChapterDto chapter
    ) {
        return novelService.updateChapter(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                novelId,
                chapterId,
                chapter.name(),
                chapter.contents(),
                chapter.isPublic(),
                chapter.isDraft()
        );
    }

    @DeleteMapping("/{novelId}/chapter/{chapterId}")
    @PermissionCheck(access = {Permission.NOVEL_CHAPTER_DELETE})
    public Object deleteChapter(@PathVariable String novelId, @PathVariable String chapterId) {
        return novelService.deleteChapter(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                novelId,
                chapterId
        );
    }
}
