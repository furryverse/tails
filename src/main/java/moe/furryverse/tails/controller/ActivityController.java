package moe.furryverse.tails.controller;


import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.AccountId;
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
    final ActivityService activityService;

    @GetMapping
    public Message<?> listAllActivity(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return Message.success(activityService.listActivity(page, size));
    }

    @PostMapping
    public Message<?> createActivity(
            @AccountId String accountId,
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
                        accountId,
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
            @AccountId String accountId,
            @PathVariable String activityId
    ) {
        return Message.success(activityService.readActivity(accountId, activityId));
    }

    @PostMapping("/{activityId}")
    public Message<?> updateActivity(
            @AccountId String accountId,
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
                        accountId,
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

    @DeleteMapping("/{activityId}")
    public Message<?> deleteActivity(
            @AccountId String accountId,
            @PathVariable String activityId
    ) {
        return Message.success(activityService.deleteActivity(accountId, activityId));
    }

    @GetMapping("/{activityId}/ticket")
    public Message<?> listTicket(
            @AccountId String accountId,
            @PathVariable String activityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return Message.success(activityService.listTicket(accountId, activityId, page, size));
    }

    @PostMapping("/{activityId}/ticket")
    public Message<?> createTicket(
            @AccountId String accountId,
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
                        accountId,
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
            @AccountId String accountId,
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
                        accountId,
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
            @AccountId String accountId,
            @PathVariable String activityId,
            @PathVariable String ticketId
    ) {
        return Message.success(activityService.readTicket(accountId, activityId, ticketId));
    }

    @DeleteMapping("/{activityId}/ticket/{ticketId}")
    public Message<?> deleteTicket(
            @AccountId String accountId,
            @PathVariable String activityId,
            @PathVariable String ticketId
    ) {
        return Message.success(activityService.deleteTicket(accountId, activityId, ticketId));
    }

    @GetMapping("/{activityId}/stub")
    public Message<?> listStub(
            @AccountId String accountId,
            @PathVariable String activityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return Message.success(activityService.listStub(accountId, activityId, page, size));
    }

    @GetMapping("/{activityId}/stub/{stubId}")
    public Message<?> listStub(
            @AccountId String accountId,
            @PathVariable String activityId,
            @PathVariable String stubId
    ) {
        return Message.success(activityService.readStub(accountId, activityId, stubId));
    }
}
