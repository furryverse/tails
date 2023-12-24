package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Ticket;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    @NotNull <S extends Ticket> S save(@NotNull S entity);

    Page<Ticket> findAllByActivityId(String activityId, Pageable pageable);

    Ticket findTicketByTicketId(String ticketId);

    Ticket deleteTicketByTicketId(String ticketId);
}
