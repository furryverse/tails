package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.exception.IsDeletedException;
import moe.furryverse.tails.exception.IsLockedException;
import moe.furryverse.tails.exception.NotFoundDataException;
import moe.furryverse.tails.exception.UnauthorizationException;
import moe.furryverse.tails.model.Activity;
import moe.furryverse.tails.model.Stub;
import moe.furryverse.tails.model.Ticket;
import moe.furryverse.tails.repository.ActivityRepository;
import moe.furryverse.tails.repository.StubRepository;
import moe.furryverse.tails.repository.TicketRepository;
import moe.furryverse.tails.utils.RandomUtils;
import moe.furryverse.tails.utils.TimeUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@SuppressWarnings("Duplicates")
public class ActivityService {
    final ActivityRepository activityRepository;
    final TicketRepository ticketRepository;
    final StubRepository stubRepository;

    public List<Activity> listActivity(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.MAX_PAGE_SIZE));

        Page<Activity> activities = accountId == null
                ? activityRepository.findAll(false, false, false, pageable)
                : activityRepository.findAllByAccountId(accountId, false, pageable);

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
        if (activity == null) throw new NotFoundDataException("activity not found", null, null, accountId);

        // 判断是否已删除
        if (activity.isDeleted()) throw new IsDeletedException("activity is deleted", null, null, accountId);

        // 判断是否为锁定
        if (activity.isLocked()) throw new IsLockedException("activity is locked", null, null, accountId);

        // 判断是否为创建者或管理员
        if (!activity.administrators().contains(accountId) || !Objects.equals(activity.accountId(), accountId)) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        Activity updated = new Activity(
                activity.activityId(),
                activity.created(),
                activity.accountId(),
                name == null ? activity.name() : name,
                description == null ? activity.description() : description,
                startTime == 0 ? activity.startTime() : startTime,
                endTime == 0 ? activity.endTime() : endTime,
                cover == null ? activity.cover() : cover,
                contents == null ? activity.contents() : contents,
                activity.administrators(),
                false,
                activity.isReviewing(),
                false,
                activity.secret()
        );

        return activityRepository.save(updated);
    }

    public Activity getActivity(String accountId, String activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) throw new NotFoundDataException("activity not found", null, null, accountId);

        // 判断是否为删除
        // 如果未删除正常返回
        if (!activity.isDeleted()) return activity;

        // 如果删除了则判断是否为创建者或管理员或购买了该活动票的其他用户
        if (activity.administrators().contains(accountId) || Objects.equals(activity.accountId(), accountId)) {
            return activity;
        }

        throw new UnauthorizationException("unauthorized", null, null, accountId);
    }

    public Activity deleteActivity(String accountId, String activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) throw new NotFoundDataException("activity not found", null, null, accountId);

        // 是否为创建者或管理员
        if (!activity.administrators().contains(accountId) || !Objects.equals(activity.accountId(), accountId)) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        // 是否已经被删除
        if (activity.isDeleted()) throw new IsDeletedException("activity is deleted", null, null, accountId);

        Activity deleted = new Activity(
                activity.activityId(),
                activity.created(),
                activity.accountId(),
                activity.name(),
                activity.description(),
                activity.startTime(),
                activity.endTime(),
                activity.cover(),
                activity.contents(),
                activity.administrators(),
                false,
                activity.isLocked(),
                true,
                activity.secret()
        );

        // 删除活动下面的票
        List<Ticket> tickets = ticketRepository.findAllByActivityId(activityId);
        for (Ticket ticket : tickets) {
            Ticket deletedTicket = new Ticket(
                    ticket.ticketId(),
                    ticket.created(),
                    ticket.accountId(),
                    ticket.name(),
                    ticket.cover(),
                    ticket.stubCover(),
                    ticket.description(),
                    ticket.price(),
                    ticket.stock(),
                    ticket.contents(),
                    ticket.activityId(),
                    ticket.isLocked(),
                    ticket.isReviewing(),
                    true
            );

            ticketRepository.save(deletedTicket);
        }

        return activityRepository.save(deleted);
    }

    public Activity addAdministrator(String accountId, String addAccountId, String activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) throw new NotFoundDataException("activity not found", null, null, accountId);

        // 是否已经被删除
        if (activity.isDeleted()) throw new IsDeletedException("activity is deleted", null, null, accountId);
        // 是否为创建者或管理员
        if (!activity.administrators().contains(accountId) || !Objects.equals(activity.accountId(), accountId)) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        Activity deleted = new Activity(
                activity.activityId(),
                activity.created(),
                activity.accountId(),
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
                activity.isLocked(),
                activity.isLocked(),
                false,
                activity.secret()
        );

        return activityRepository.save(deleted);
    }

    public Activity removeAdministrator(String accountId, String removeAccountId, String activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) throw new NotFoundDataException("activity not found", null, null, accountId);

        // 是否已经被删除
        if (activity.isDeleted()) throw new IsDeletedException("activity is deleted", null, null, accountId);
        // 是否为创建者或管理员
        if (!activity.administrators().contains(accountId) || !Objects.equals(activity.accountId(), accountId)) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        Activity deleted = new Activity(
                activity.activityId(),
                activity.created(),
                activity.accountId(),
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
                activity.isLocked(),
                activity.isLocked(),
                false,
                activity.secret()
        );

        return activityRepository.save(deleted);
    }

    public List<Ticket> listTicket(String accountId, String activityId, int page, int size) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) throw new NotFoundDataException("activity not found", null, null, accountId);

        // 是否被删除
        if (!activity.isDeleted()) {
            // 如果未删除正常返回
            Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.MAX_PAGE_SIZE));

            return ticketRepository.findAll(false, pageable).getContent();
        }

        // 如果删除了则判断是否为创建者或管理员或购买了该活动票的其他用户
        if (activity.administrators().contains(accountId) || Objects.equals(activity.accountId(), accountId)) {
            Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.MAX_PAGE_SIZE));

            return ticketRepository.findAllByActivityId(activityId, pageable).getContent();
        }

        throw new UnauthorizationException("unauthorized", null, null, accountId);
    }

    public Ticket createTicket(
            String accountId, String activityId,
            String name, String cover, String stubCover, String description,
            double price, int stock, List<String> contents
    ) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) throw new NotFoundDataException("activity not found", null, null, accountId);

        // 是否为创建者或管理员
        if (!activity.administrators().contains(accountId) || !Objects.equals(activity.accountId(), accountId)) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }
        // 是否已经被删除
        if (activity.isDeleted()) throw new IsDeletedException("activity already deleted", null, null, accountId);
        // 是否已经被锁定
        if (activity.isLocked()) throw new IsLockedException("activity already locked", null, null, accountId);

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
        if (ticket == null) throw new NotFoundDataException("ticket not found", null, null, accountId);

        // 是否已经被删除
        if (ticket.isDeleted()) throw new NotFoundDataException("ticket already deleted", null, null, accountId);

        // 是否已经被锁定
        if (ticket.isLocked()) throw new IsLockedException("ticket already locked", null, null, accountId);

        // 是否为创建者或管理员
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) throw new NotFoundDataException("activity not found", null, null, accountId);

        if (
                !activity.administrators().contains(accountId)
                        || !Objects.equals(ticket.accountId(), accountId)
                        || !Objects.equals(ticket.accountId(), activity.accountId())
        ) {
            throw new NotFoundDataException("ticket not found", null, null, accountId);
        }

        Ticket updated = new Ticket(
                ticket.ticketId(),
                ticket.created(),
                ticket.accountId(),
                name == null ? ticket.name() : name,
                cover == null ? ticket.cover() : cover,
                stubCover == null ? ticket.stubCover() : stubCover,
                description == null ? ticket.description() : description,
                price == 0 ? ticket.price() : price,
                stock == 0 ? ticket.stock() : stock,
                contents == null ? ticket.contents() : contents,
                ticket.activityId(),
                false,
                ticket.isReviewing(),
                false
        );

        return ticketRepository.save(updated);
    }

    public Ticket readTicket(String accountId, String activityId, String ticketId) {
        Ticket ticket = ticketRepository.findByActivityIdAndTicketId(activityId, ticketId);
        if (ticket == null) throw new NotFoundDataException("ticket not found", null, null, accountId);

        // 是否被删除
        if (ticket.isDeleted()) {
            Activity activity = activityRepository.findById(activityId).orElse(null);
            if (activity == null) throw new NotFoundDataException("activity not found", null, null, accountId);

            // 如果删除了则判断是否为创建者或管理员或购买了该活动票的其他用户
            if (
                    activity.administrators().contains(accountId)
                            || Objects.equals(ticket.accountId(), accountId)
                            || Objects.equals(ticket.accountId(), activity.accountId())
            ) {
                return ticket;
            }

            throw new NotFoundDataException("ticket already deleted", null, null, accountId);
        }

        // 如果未删除正常返回
        return ticket;
    }

    public Ticket deleteTicket(String accountId, String activityId, String ticketId) {
        Ticket ticket = ticketRepository.findByActivityIdAndTicketId(activityId, ticketId);
        if (ticket == null) throw new NotFoundDataException("ticket not found", null, null, accountId);

        // 是否已经被删除
        if (ticket.isDeleted()) throw new IsLockedException("ticket already deleted", null, null, accountId);

        // 是否已经被锁定
        if (ticket.isLocked()) throw new IsLockedException("ticket already locked", null, null, accountId);

        // 获取活动
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) throw new NotFoundDataException("activity not found", null, null, accountId);

        // 是否为创建者或管理员
        if (
                !activity.administrators().contains(accountId)
                        || !Objects.equals(ticket.accountId(), accountId)
                        || !Objects.equals(ticket.accountId(), activity.accountId())
        ) {
            throw new UnauthorizationException("cannot delete ticket", null, null, accountId);
        }

        return ticketRepository.save(
                new Ticket(
                        ticket.ticketId(),
                        ticket.created(),
                        ticket.accountId(),
                        ticket.name(),
                        ticket.cover(),
                        ticket.stubCover(),
                        ticket.description(),
                        ticket.price(),
                        ticket.stock(),
                        ticket.contents(),
                        ticket.activityId(),
                        false,
                        ticket.isReviewing(),
                        true
                )
        );
    }

    public List<Stub> listStub(@NotNull String accountId, String activityId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.MAX_PAGE_SIZE));

        return stubRepository.findAllByAccountIdAndActivityId(accountId, activityId, pageable).getContent();
    }

    public Stub readStub(@NotNull String accountId, String activityId, String stubId) {
        return stubRepository.findStubByAccountIdAndActivityIdAndStubId(accountId, activityId, stubId);
    }
}
