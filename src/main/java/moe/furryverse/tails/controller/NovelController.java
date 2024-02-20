package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.PermissionCheck;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.security.Permission;
import moe.furryverse.tails.service.NovelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public Object createNovel(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "cover") String cover,
            @RequestParam(name = "tags") Set<String> tags,
            @RequestParam(name = "contents") List<String> contents,
            @RequestParam(name = "is_public") boolean isPublic
    ) {
        return novelService.createNovel(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                name,
                description,
                cover,
                tags,
                contents,
                isPublic
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
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "cover") String cover,
            @RequestParam(name = "tags") Set<String> tags,
            @RequestParam(name = "contents") List<String> contents,
            @RequestParam(name = "is_public") boolean isPublic
    ) {
        return novelService.updateNovel(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                novelId,
                name,
                description,
                cover,
                tags,
                contents,
                isPublic
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
            @RequestParam(name = "name") String name,
            @RequestParam(name = "contents") List<String> contents,
            @RequestParam(name = "is_public") boolean isPublic,
            @RequestParam(name = "is_draft") boolean isDraft
    ) {
        return novelService.createChapter(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                novelId,
                name,
                contents,
                isPublic,
                isDraft
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
            @RequestParam(name = "name") String name,
            @RequestParam(name = "contents") List<String> contents,
            @RequestParam(name = "is_public") boolean isPublic,
            @RequestParam(name = "is_draft") boolean isDraft
    ) {
        return novelService.updateChapter(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                novelId,
                chapterId,
                name,
                contents,
                isPublic,
                isDraft
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
