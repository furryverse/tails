package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.exception.IsDeletedException;
import moe.furryverse.tails.exception.UnauthorizationException;
import moe.furryverse.tails.model.Activity;
import moe.furryverse.tails.model.Stub;
import moe.furryverse.tails.model.Ticket;
import moe.furryverse.tails.repository.ActivityRepository;
import moe.furryverse.tails.repository.StubRepository;
import moe.furryverse.tails.repository.TicketRepository;
import moe.furryverse.tails.utils.RandomUtils;
import moe.furryverse.tails.utils.TimeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@SuppressWarnings("Duplicates")
public class ActivityService {
    final ActivityRepository activityRepository;
    final TicketRepository ticketRepository;
    final StubRepository stubRepository;

    public List<Activity> listActivity(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE)
        );
        Page<Activity> activities = activityRepository.findAll(false, pageable);

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
                contents == null ? List.of() : contents,
                new HashSet<>(),
                false,
                secret
        );

        return activityRepository.save(activity);
    }

    public Activity updateActivity(
            String accountId, String activityId, String name, String description,
            long startTime, long endTime, String cover, List<String> contents
    ) {
        Activity record = activityRepository.findById(activityId).orElse(null);
        if (record == null) return null;

        // 查询是否为活动管理员
        if (!record.administrators().contains(accountId) || !Objects.equals(accountId, record.accountId())) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        // 如果活动被删除了则无法修改
        if (record.isDeleted()) throw new IsDeletedException("resource is deleted", null, null, accountId);

        Activity activity = new Activity(
                record.activityId(),
                record.created(),
                record.accountId(),
                name == null ? record.name() : name,
                description == null ? record.description() : description,
                startTime == -1 ? record.startTime() : startTime,
                endTime == -1 ? record.endTime() : endTime,
                cover == null ? record.cover() : cover,
                contents == null ? record.contents() : contents,
                record.administrators(),
                false,
                record.secret()
        );

        return activityRepository.save(activity);
    }

    public Activity readActivity(String accountId, String activityId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) return null;
        if (!activity.isDeleted()) return activity;

        // 如果已经删除的 查询是否为活动管理员
        if (activity.administrators().contains(accountId) || Objects.equals(accountId, activity.accountId())) {
            return activity;
        }

        // 如果不是管理员 查询用户是否购买过该活动的票
        Pageable pageable = PageRequest.of(0, PageConfiguration.DEFAULT_PAGE_SIZE);
        Page<Stub> page = stubRepository.findAllByAccountIdAndActivityId(accountId, activityId, pageable);
        if (page.getSize() > 0) return activity;

        return null;
    }

    public Activity deleteActivity(String accountId, String activityId) {
        Activity record = activityRepository.findById(activityId).orElse(null);
        if (record == null) return null;

        // 查询是否为活动管理员
        if (!record.administrators().contains(accountId) || !Objects.equals(accountId, record.accountId())) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        Activity activity = new Activity(
                record.activityId(),
                record.created(),
                record.accountId(),
                record.name(),
                record.description(),
                record.startTime(),
                record.endTime(),
                record.cover(),
                record.contents(),
                record.administrators(),
                true,
                record.secret()
        );

        return activityRepository.save(activity);
    }

    public List<Ticket> listTicket(String accountId, String activityId, int page, int size) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) return null;
        // 考虑到活动可能会被删除了
        if (activity.isDeleted()) {
            // 查询是否为活动管理员
            if (!activity.administrators().contains(accountId) || !Objects.equals(accountId, activity.accountId())) {
                throw new UnauthorizationException("unauthorized", null, null, accountId);
            }
        }

        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE)
        );
        Page<Ticket> tickets = ticketRepository.findAllByActivityId(activityId, pageable);

        return tickets.getContent();
    }

    public Ticket createTicket(
            String accountId, String activityId,
            String name, String cover, String stubCover, String description,
            double price, int stock, List<String> contents
    ) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) return null;

        // 查询是否为活动管理员
        if (!activity.administrators().contains(accountId) || !Objects.equals(accountId, activity.accountId())) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

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
                false
        );

        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(
            String accountId, String activityId, String ticketId,
            String name, String cover, String stubCover, String description,
            double price, int stock, List<String> contents
    ) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) return null;

        // 查询是否为活动管理员
        if (!activity.administrators().contains(accountId) || !Objects.equals(accountId, activity.accountId())) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        Ticket record = ticketRepository.findById(ticketId).orElse(null);
        if (record == null) return null;
        if (!Objects.equals(activity.activityId(), activityId)) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        Ticket ticket = new Ticket(
                ticketId,
                record.created(),
                accountId,
                name == null ? record.name() : name,
                cover == null ? record.cover() : cover,
                stubCover == null ? record.stubCover() : stubCover,
                description == null ? record.description() : description,
                price == -1 ? record.price() : price,
                stock == -1 ? record.stock() : stock,
                contents == null ? record.contents() : contents,
                activityId,
                false
        );

        return ticketRepository.save(ticket);
    }

    public Ticket readTicket(String accountId, String activityId, String ticketId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) return null;

        // 考虑到活动可能会被删除了
        if (activity.isDeleted()) {
            // 查询是否为活动管理员
            if (!activity.administrators().contains(accountId) || !Objects.equals(accountId, activity.accountId())) {
                throw new UnauthorizationException("unauthorized", null, null, accountId);
            }
        }

        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (ticket == null) return null;

        return Objects.equals(ticket.activityId(), activity.activityId()) ? ticket : null;
    }

    public Ticket deleteTicket(String accountId, String activityId, String ticketId) {
        Activity activity = activityRepository.findById(activityId).orElse(null);
        if (activity == null) return null;

        // 查询是否为活动管理员
        if (!activity.administrators().contains(accountId) || !Objects.equals(accountId, activity.accountId())) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        Ticket record = ticketRepository.findById(ticketId).orElse(null);
        if (record == null) return null;

        Ticket ticket = new Ticket(
                ticketId,
                record.created(),
                accountId,
                record.name(),
                record.cover(),
                record.stubCover(),
                record.description(),
                record.price(),
                record.stock(),
                record.contents(),
                record.activityId(),
                true
        );

        return ticketRepository.save(ticket);
    }

    public List<Stub> listStub(String accountId, String activityId, int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE)
        );
        Page<Stub> stubs = stubRepository.findAllByAccountIdAndActivityId(accountId, activityId, pageable);

        return stubs.getContent();
    }

    public Stub readStub(String accountId, String activityId, String stubId) {
        // Account ID 与 Activity ID 匹配
        Stub stub = stubRepository.findStubByStubId(stubId);
        if (stub == null) return null;

        if (!Objects.equals(stub.activityId(), activityId)) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        if (!Objects.equals(stub.accountId(), accountId)) {
            throw new UnauthorizationException("unauthorized", null, null, accountId);
        }

        return stub;
    }
}
