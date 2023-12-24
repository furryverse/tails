package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
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
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ActivityService {
    final ActivityRepository activityRepository;
    final TicketRepository ticketRepository;
    final StubRepository stubRepository;

    public List<Activity> listActivity(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE)
        );
        Page<Activity> activities = activityRepository.findAll(pageable);

        return activities.getContent();
    }
    public Activity createActivity(
            String accountId,
            String name, String description,
            long startTime, long endTime,
            String cover, List<String> contents,
            String secret, Set<String> administrators
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
                secret,
                new HashSet<>(administrators)
        );

        return activityRepository.save(activity);
    }

    public Activity updateActivity(
            String activityId,
            String name, String description,
            long startTime, long endTime,
            String cover, List<String> contents
    ) {
        Activity record = activityRepository.findById(activityId).orElse(null);
        if (record == null) return null;



        return null;
    }
    public Activity readActivity() {
        return null;
    }
    public Activity deleteActivity() {
        return null;
    }

    public List<Ticket> listTicket(String activityId, int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE)
        );
        Page<Ticket> tickets = ticketRepository.findAllByActivityId(activityId, pageable);

        return tickets.getContent();
    }
    public Ticket createTicket() {
        return null;
    }
    public Ticket updateTicket() {
        return null;
    }
    public Ticket readTicket() {
        return null;
    }
    public Ticket deleteTicket() {
        return null;
    }

    public List<Stub> listStub(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE)
        );
        Page<Stub> stubs = stubRepository.findAll(pageable);

        return stubs.getContent();
    }
    public List<Stub> listStub(String activityId, int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE)
        );
        Page<Stub> stubs = stubRepository.findAllByActivityId(activityId, pageable);

        return stubs.getContent();
    }
    public Stub createStub() {
        return null;
    }

    public Stub readStub() {
        return null;
    }
}
