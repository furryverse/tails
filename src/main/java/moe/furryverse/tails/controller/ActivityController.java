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

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.PermissionCheck;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.dto.ActivityDto;
import moe.furryverse.tails.dto.TicketDto;
import moe.furryverse.tails.security.Permission;
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
    @PermissionCheck(access = {Permission.ACTIVITY_LIST}, requiredLogin = false)
    public Object listAllActivity(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return activityService.listActivity(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                page,
                size
        );
    }

    @PostMapping
    @PermissionCheck(access = {Permission.ACTIVITY_WRITE})
    public Object createActivity(@RequestBody ActivityDto activity) {
        return activityService.createActivity(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activity.name(),
                activity.description(),
                activity.startTime() == null ? TimeUtils.getMilliUnixTime() : activity.startTime(),
                activity.endTime() == null ? TimeUtils.getMilliUnixTime() : activity.endTime(),
                activity.cover(),
                activity.contents() == null ? List.of() : activity.contents(),
                activity.secret() == null ? RandomUtils.string(128) : activity.secret()
        );
    }

    @GetMapping("/{activityId}")
    @PermissionCheck(access = {Permission.ACTIVITY_READ})
    public Object readActivity(
            String accountId,
            @PathVariable String activityId
    ) {
        return activityService.getActivity(accountId, activityId);
    }

    @PostMapping("/{activityId}")
    @PermissionCheck(access = {Permission.ACTIVITY_UPDATE})
    public Object updateActivity(
            @PathVariable String activityId,
            @RequestBody ActivityDto activity
    ) {
        return activityService.updateActivity(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                activity.name(),
                activity.description(),
                activity.startTime() == null ? -1 : activity.startTime(),
                activity.endTime() == null ? -1 : activity.endTime(),
                activity.cover(),
                activity.contents() == null ? List.of() : activity.contents()
        );
    }

    @PostMapping("/{activityId}/administrator/{administrator}")
    @PermissionCheck(access = {Permission.ACTIVITY_UPDATE})
    public Object addAdministrator(
            @PathVariable String activityId,
            @PathVariable String administrator
    ) {
        return activityService.addAdministrator(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                administrator,
                activityId
        );
    }

    @DeleteMapping("/{activityId}/administrator/{administrator}")
    @PermissionCheck(access = {Permission.ACTIVITY_UPDATE})
    public Object deleteAdministrator(
            @PathVariable String activityId,
            @PathVariable String administrator
    ) {
        return activityService.removeAdministrator(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                administrator,
                activityId
        );
    }

    @DeleteMapping("/{activityId}")
    @PermissionCheck(access = {Permission.ACTIVITY_DELETE})
    public Object deleteActivity(
            @PathVariable String activityId
    ) {
        return activityService.deleteActivity(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId
        );
    }

    @GetMapping("/{activityId}/ticket")
    @PermissionCheck(access = {Permission.ACTIVITY_TICKET_LIST}, requiredLogin = false)
    public Object listTicket(
            @PathVariable String activityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return activityService.listTicket(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                page,
                size
        );
    }

    @PostMapping("/{activityId}/ticket")
    @PermissionCheck(access = {Permission.ACTIVITY_TICKET_WRITE})
    public Object createTicket(
            @PathVariable String activityId,
            @RequestBody TicketDto ticket
    ) {
        return activityService.createTicket(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                ticket.name() == null ? RandomUtils.string(6) : ticket.name(),
                ticket.cover() == null ? "" : ticket.cover(),
                ticket.stubCover() == null ? "" : ticket.stubCover(),
                ticket.description() == null ? "" : ticket.description(),
                ticket.price() == null ? 0 : ticket.price(),
                ticket.stock() == null ? 0 : ticket.stock(),
                ticket.contents() == null ? List.of() : ticket.contents()
        );
    }


    @PostMapping("/{activityId}/ticket/{ticketId}")
    @PermissionCheck(access = {Permission.ACTIVITY_TICKET_UPDATE})
    public Object updateTicket(
            @PathVariable String activityId,
            @PathVariable String ticketId,
            @RequestBody TicketDto ticket
    ) {
        return activityService.updateTicket(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                ticketId,
                ticket.name(),
                ticket.cover(),
                ticket.stubCover(),
                ticket.description(),
                ticket.price() == null ? -1 : ticket.price(),
                ticket.stock() == null ? -1 : ticket.stock(),
                ticket.contents() == null ? List.of() : ticket.contents()
        );
    }

    @GetMapping("/{activityId}/ticket/{ticketId}")
    @PermissionCheck(access = {Permission.ACTIVITY_TICKET_READ})
    public Object readTicket(
            @PathVariable String activityId,
            @PathVariable String ticketId
    ) {
        return activityService.readTicket(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                ticketId
        );
    }

    @DeleteMapping("/{activityId}/ticket/{ticketId}")
    @PermissionCheck(access = {Permission.ACTIVITY_TICKET_DELETE})
    public Object deleteTicket(
            @PathVariable String activityId,
            @PathVariable String ticketId
    ) {
        return activityService.deleteTicket(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                ticketId
        );
    }

    @GetMapping("/{activityId}/stub")
    @PermissionCheck(access = {Permission.ACTIVITY_STUB_LIST})
    public Object listStub(
            @PathVariable String activityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return activityService.listStub(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                page,
                size
        );
    }

    @GetMapping("/{activityId}/stub/{stubId}")
    @PermissionCheck(access = {Permission.ACTIVITY_STUB_READ})
    public Object getStub(
            @PathVariable String activityId,
            @PathVariable String stubId
    ) {
        return activityService.readStub(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                activityId,
                stubId
        );
    }
}
