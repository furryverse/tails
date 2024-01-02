package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.model.Activity;
import moe.furryverse.tails.model.Stub;
import moe.furryverse.tails.model.Ticket;
import moe.furryverse.tails.repository.ActivityRepository;
import moe.furryverse.tails.repository.StubRepository;
import moe.furryverse.tails.repository.TicketRepository;
import moe.furryverse.tails.utils.ManageStatusUtils;
import moe.furryverse.tails.utils.RandomUtils;
import moe.furryverse.tails.utils.TimeUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@SuppressWarnings("DuplicatedCode")
public class ActivityService {
    final ActivityRepository activityRepository;
    final TicketRepository ticketRepository;
    final StubRepository stubRepository;

    public List<Activity> listActivity(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.MAX_PAGE_SIZE));

        Page<Activity> activities = accountId == null
                ? activityRepository.findAll(false, false, false, pageable)
                : activityRepository.findAllByCreatedBy(accountId, false, pageable);

        return activities.getContent();
    }

    public Activity createActivity(
            String accountId, String name, String description, long startTime, long endTime,
            String cover, List<String> contents, String secret
    ) {
        Activity activity = new Activity(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                accountId,
                name,
                description,
                startTime,
                endTime,
                cover,
                contents,
                Set.of(),
                true,
                false,
                false,
                true,
                false,
                secret
        );

        return activityRepository.save(activity);
    }

    public Activity updateActivity(
            String accountId, String activityId, String name, String description,
            long startTime, long endTime, String cover, List<String> contents
    ) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(activity, accountId);

        Activity updated = new Activity(
                activity.activityId(),
                activity.created(),
                activity.createdBy(),
                name == null ? activity.name() : name,
                description == null ? activity.description() : description,
                startTime == 0 ? activity.startTime() : startTime,
                endTime == 0 ? activity.endTime() : endTime,
                cover == null ? activity.cover() : cover,
                contents == null ? activity.contents() : contents,
                activity.administrators(),
                activity.isPublic(),
                activity.isLocked(),
                activity.isReviewing(),
                activity.isArchived(),
                false,
                activity.secret()
        );

        return activityRepository.save(updated);
    }

    public Activity getActivity(String accountId, String activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        ManageStatusUtils.checkReadStatus(
                activity,
                accountId,
                () -> {
                    // 如果用户购买过该票则无论票处于什么状态都能显示
                    int count = stubRepository.countAllByCreatedByAndActivityId(activityId, activityId);
                    return count != 0;
                });

        return activity;
    }

    public Activity deleteActivity(String accountId, String activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        ManageStatusUtils.checkDeleteStatus(activity, accountId);

        Activity deleted = new Activity(
                activity.activityId(),
                activity.created(),
                activity.createdBy(),
                activity.name(),
                activity.description(),
                activity.startTime(),
                activity.endTime(),
                activity.cover(),
                activity.contents(),
                activity.administrators(),
                activity.isPublic(),
                activity.isLocked(),
                activity.isArchived(),
                activity.isReviewing(),
                true,
                activity.secret()
        );

        // 删除活动下面的票
        List<Ticket> tickets = ticketRepository.findAllByActivityId(activityId);
        for (Ticket ticket : tickets) {
            Ticket deletedTicket = new Ticket(
                    ticket.ticketId(),
                    ticket.created(),
                    ticket.createdBy(),
                    ticket.name(),
                    ticket.cover(),
                    ticket.stubCover(),
                    ticket.description(),
                    ticket.price(),
                    ticket.stock(),
                    ticket.contents(),
                    ticket.activityId(),
                    ticket.isPublic(),
                    ticket.isLocked(),
                    ticket.isArchived(),
                    ticket.isReviewing(),
                    true
            );

            ticketRepository.save(deletedTicket);
        }

        return activityRepository.save(deleted);
    }

    public Activity addAdministrator(String accountId, String addAccountId, String activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(activity, accountId);

        Activity deleted = new Activity(
                activity.activityId(),
                activity.created(),
                activity.createdBy(),
                activity.name(),
                activity.description(),
                activity.startTime(),
                activity.endTime(),
                activity.cover(),
                activity.contents(),
                new HashSet<>() {{
                    addAll(activity.administrators());
                    add(addAccountId);
                }},
                activity.isPublic(),
                activity.isLocked(),
                activity.isLocked(),
                activity.isReviewing(),
                activity.isArchived(),
                activity.secret()
        );

        return activityRepository.save(deleted);
    }

    public Activity removeAdministrator(String accountId, String removeAccountId, String activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(activity, accountId);

        Activity deleted = new Activity(
                activity.activityId(),
                activity.created(),
                activity.createdBy(),
                activity.name(),
                activity.description(),
                activity.startTime(),
                activity.endTime(),
                activity.cover(),
                activity.contents(),
                new HashSet<>() {{
                    addAll(activity.administrators());
                    remove(removeAccountId);
                }},
                activity.isPublic(),
                activity.isLocked(),
                activity.isArchived(),
                activity.isReviewing(),
                false,
                activity.secret()
        );

        return activityRepository.save(deleted);
    }

    public List<Ticket> listTicket(String accountId, String activityId, int page, int size) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        ManageStatusUtils.checkReadStatus(activity, accountId);

        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.MAX_PAGE_SIZE));
        return ticketRepository.findAllByActivityId(activityId, pageable).getContent();
    }

    public Ticket createTicket(
            String accountId, String activityId,
            String name, String cover, String stubCover, String description,
            double price, int stock, List<String> contents
    ) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(activity, accountId);

        Ticket ticket = new Ticket(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                accountId,
                name,
                cover,
                stubCover,
                description,
                price,
                stock,
                contents,
                activityId,
                true,
                false,
                false,
                true,
                false
        );

        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(
            String accountId, String activityId, String ticketId,
            String name, String cover, String stubCover, String description,
            double price, int stock, List<String> contents
    ) {
        Ticket ticket = ticketRepository.findByActivityIdAndTicketId(activityId, ticketId);
        ManageStatusUtils.checkUpdateStatus(ticket, accountId);

        // 是否为创建者或管理员
        Activity activity = activityRepository.findById(activityId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(activity, accountId);

        Ticket updated = new Ticket(
                ticket.ticketId(),
                ticket.created(),
                ticket.createdBy(),
                name == null ? ticket.name() : name,
                cover == null ? ticket.cover() : cover,
                stubCover == null ? ticket.stubCover() : stubCover,
                description == null ? ticket.description() : description,
                price == 0 ? ticket.price() : price,
                stock == 0 ? ticket.stock() : stock,
                contents == null ? ticket.contents() : contents,
                ticket.activityId(),
                ticket.isPublic(),
                ticket.isLocked(),
                ticket.isArchived(),
                ticket.isReviewing(),
                false
        );

        return ticketRepository.save(updated);
    }

    public Ticket readTicket(String accountId, String activityId, String ticketId) {
        Ticket ticket = ticketRepository.findByActivityIdAndTicketId(activityId, ticketId);
        Activity activity = activityRepository.findById(activityId).orElse(null);

        ManageStatusUtils.checkParentsStatus(
                ticket,
                activity,
                accountId,
                () -> {
                    // 如果用户购买过该票则无论票处于什么状态都能显示
                    int count = stubRepository.countAllByCreatedByAndActivityId(activityId, activityId);
                    return count != 0;
                });

        // 如果未删除正常返回
        return ticket;
    }

    public Ticket deleteTicket(String accountId, String activityId, String ticketId) {
        Ticket ticket = ticketRepository.findByActivityIdAndTicketId(activityId, ticketId);
        ManageStatusUtils.checkDeleteStatus(ticket, accountId);

        // 获取活动
        Activity activity = activityRepository.findById(activityId).orElse(null);
        ManageStatusUtils.checkDeleteStatus(activity, accountId);

        return ticketRepository.save(
                new Ticket(
                        ticket.ticketId(),
                        ticket.created(),
                        ticket.createdBy(),
                        ticket.name(),
                        ticket.cover(),
                        ticket.stubCover(),
                        ticket.description(),
                        ticket.price(),
                        ticket.stock(),
                        ticket.contents(),
                        ticket.activityId(),
                        ticket.isPublic(),
                        ticket.isLocked(),
                        ticket.isArchived(),
                        ticket.isReviewing(),
                        true
                )
        );
    }

    public List<Stub> listStub(@NotNull String accountId, String activityId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.MAX_PAGE_SIZE));

        return stubRepository.findAllByCreatedByAndActivityId(accountId, activityId, pageable).getContent();
    }

    public Stub readStub(@NotNull String accountId, String activityId, String stubId) {
        return stubRepository.findStubByCreatedByAndActivityIdAndStubId(accountId, activityId, stubId);
    }
}
