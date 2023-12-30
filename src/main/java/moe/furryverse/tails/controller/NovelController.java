package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.service.NovelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/novel")
public class NovelController {
    final HttpServletRequest request;
    final NovelService novelService;

    //////////////////////////////////////////////////////////////// Novel

    @GetMapping()
    public Message<?> listNovel(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return Message.success(
                novelService.listNovel(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        page,
                        size
                )
        );
    }

    @PostMapping()
    public Message<?> createNovel(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "cover") String cover,
            @RequestParam(name = "tags") List<String> tags,
            @RequestParam(name = "contents") List<String> contents,
            @RequestParam(name = "is_public") boolean isPublic
    ) {
        return Message.success(
                novelService.createNovel(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        name,
                        description,
                        cover,
                        tags,
                        contents,
                        isPublic
                )
        );
    }

    @GetMapping("/{novelId}")
    public Message<?> getNovel(@PathVariable String novelId) {
        return Message.success(
                novelService.getNovel(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        novelId
                )
        );
    }

    @PostMapping("/{novelId}")
    public Message<?> updateNovel(
            @PathVariable String novelId,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "cover") String cover,
            @RequestParam(name = "tags") List<String> tags,
            @RequestParam(name = "contents") List<String> contents,
            @RequestParam(name = "is_public") boolean isPublic
    ) {
        return Message.success(
                novelService.updateNovel(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        novelId,
                        name,
                        description,
                        cover,
                        tags,
                        contents,
                        isPublic
                )
        );
    }

    @DeleteMapping("/{novelId}")
    public Message<?> deleteNovel(@PathVariable String novelId) {
        return Message.success(
                novelService.deleteNovel(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        novelId
                )
        );
    }

    //////////////////////////////////////////////////////////////// Chapter

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
