package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.model.Activity;
import moe.furryverse.tails.model.Stub;
import moe.furryverse.tails.model.Ticket;
import moe.furryverse.tails.repository.ActivityRepository;
import moe.furryverse.tails.repository.StubRepository;
import moe.furryverse.tails.repository.TicketRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("Duplicates")
public class ActivityService {
    final ActivityRepository activityRepository;
    final TicketRepository ticketRepository;
    final StubRepository stubRepository;

    public List<Activity> listActivity(String accountId, int page, int size) {
        return null;
    }

    public Activity createActivity(
            String accountId, String name, String description, long startTime, long endTime,
            String cover, List<String> contents, String secret
    ) {
        return null;
    }

    public Activity updateActivity(
            String accountId, String activityId, String name, String description,
            long startTime, long endTime, String cover, List<String> contents
    ) {
        return null;
    }

    public Activity readActivity(String accountId, String activityId) {
        return null;
    }

    public Activity deleteActivity(String accountId, String activityId) {
        return null;
    }

    public Activity addAdministrator(String accountId, String addAccountId, String activityId) {
        return null;
    }

    public Activity removeAdministrator(String accountId, String removeAccountId, String activityId) {
        return null;
    }

    public List<Ticket> listTicket(String accountId, String activityId, int page, int size) {
        return null;
    }

    public Ticket createTicket(
            String accountId, String activityId,
            String name, String cover, String stubCover, String description,
            double price, int stock, List<String> contents
    ) {
        return null;
    }

    public Ticket updateTicket(
            String accountId, String activityId, String ticketId,
            String name, String cover, String stubCover, String description,
            double price, int stock, List<String> contents
    ) {
        return null;
    }

    public Ticket readTicket(String accountId, String activityId, String ticketId) {
        return null;
    }

    public Ticket deleteTicket(String accountId, String activityId, String ticketId) {
        return null;
    }

    public List<Stub> listStub(@NotNull String accountId, String activityId, int page, int size) {
        return null;
    }

    public Stub readStub(String accountId, String activityId, String stubId) {
        return null;
    }
}
