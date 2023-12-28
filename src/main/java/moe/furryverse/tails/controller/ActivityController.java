package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.PermissionCheck;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.service.ActivityService;
import moe.furryverse.tails.utils.RandomUtils;
import moe.furryverse.tails.utils.TimeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/activity")
public class ActivityController {
    final HttpServletRequest request;
    final ActivityService activityService;

    @GetMapping
    public Message<?> listAllActivity(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return Message.success(activityService.listActivity(page, size));
    }

    @PostMapping
    @PermissionCheck(access = {})
    public Message<?> createActivity(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "startTime") Long startTime,
            @RequestParam(name = "endTime") Long endTime,
            @RequestParam(name = "cover", required = false) String cover,
            @RequestParam(name = "contents", required = false) List<String> contents,
            @RequestParam(name = "secret", required = false) String secret
    ) {
        return Message.success(
                activityService.createActivity(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        name,
                        description,
                        startTime == null ? TimeUtils.getMilliUnixTime() : startTime,
                        endTime == null ? TimeUtils.getMilliUnixTime() : endTime,
                        cover,
                        contents == null ? List.of() : contents,
                        secret == null ? RandomUtils.string(128) : secret
                )
        );
    }

    @GetMapping("/{activityId}")
    public Message<?> readActivity(
            String accountId,
            @PathVariable String activityId
    ) {
        return Message.success(activityService.readActivity(accountId, activityId));
    }

    @PostMapping("/{activityId}")
    public Message<?> updateActivity(
            @PathVariable String activityId,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "startTime", required = false) Long startTime,
            @RequestParam(name = "endTime", required = false) Long endTime,
            @RequestParam(name = "cover", required = false) String cover,
            @RequestParam(name = "contents", required = false) List<String> contents
    ) {
        return Message.success(
                activityService.updateActivity(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        activityId,
                        name,
                        description,
                        startTime == null ? -1 : startTime,
                        endTime == null ? -1 : endTime,
                        cover,
                        contents == null ? List.of() : contents
                )
        );
    }

    @PostMapping("/{activityId}/administrator")
    public Message<?> addAdministrator(
            @PathVariable String activityId,
            @RequestParam(name = "administrator") String administrator
    ) {
        return Message.success(activityService.addAdministrator(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                administrator,
                activityId)
        );
    }

    @DeleteMapping("/{activityId}/administrator")
    public Message<?> deleteAdministrator(
            @PathVariable String activityId,
            @RequestParam(name = "administrator") String administrator
    ) {
        return Message.success(activityService.removeAdministrator(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                administrator,
                activityId)
        );
    }

    @DeleteMapping("/{activityId}")
    public Message<?> deleteActivity(
            @PathVariable String activityId
    ) {
        return Message.success(activityService.deleteActivity(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId)
        );
    }

    @GetMapping("/{activityId}/ticket")
    public Message<?> listTicket(
            @PathVariable String activityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return Message.success(activityService.listTicket(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                page,
                size)
        );
    }

    @PostMapping("/{activityId}/ticket")
    public Message<?> createTicket(
            @PathVariable String activityId,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String cover,
            @RequestParam(name = "stubCover") String stubCover,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "price") Double price,
            @RequestParam(name = "stock") Integer stock,
            @RequestParam(name = "contents") List<String> contents
    ) {
        return Message.success(
                activityService.createTicket(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        activityId,
                        name == null ? RandomUtils.string(6) : name,
                        cover == null ? "" : cover,
                        stubCover == null ? "" : stubCover,
                        description == null ? "" : description,
                        price == null ? 0 : price,
                        stock == null ? 0 : stock,
                        contents == null ? List.of() : contents
                )
        );
    }


    @PostMapping("/{activityId}/ticket/{ticketId}")
    public Message<?> updateTicket(
            @PathVariable String activityId,
            @PathVariable String ticketId,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "description", required = false) String cover,
            @RequestParam(name = "stubCover", required = false) String stubCover,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "price", required = false) Double price,
            @RequestParam(name = "stock", required = false) Integer stock,
            @RequestParam(name = "contents", required = false) List<String> contents
    ) {
        return Message.success(
                activityService.updateTicket(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        activityId,
                        ticketId,
                        name,
                        cover,
                        stubCover,
                        description,
                        price == null ? -1 : price,
                        stock == null ? -1 : stock,
                        contents == null ? List.of() : contents
                )
        );
    }

    @GetMapping("/{activityId}/ticket/{ticketId}")
    public Message<?> readTicket(
            @PathVariable String activityId,
            @PathVariable String ticketId
    ) {
        return Message.success(activityService.readTicket(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                ticketId)
        );
    }

    @DeleteMapping("/{activityId}/ticket/{ticketId}")
    public Message<?> deleteTicket(
            @PathVariable String activityId,
            @PathVariable String ticketId
    ) {
        return Message.success(activityService.deleteTicket(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                ticketId)
        );
    }

    @GetMapping("/{activityId}/stub")
    public Message<?> listStub(
            @PathVariable String activityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return Message.success(activityService.listStub(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                page,
                size)
        );
    }

    @GetMapping("/{activityId}/stub/{stubId}")
    public Message<?> listStub(
            @PathVariable String activityId,
            @PathVariable String stubId
    ) {
        return Message.success(activityService.readStub(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                stubId)
        );
    }
}
