package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Ticket;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    @NotNull <S extends Ticket> S save(@NotNull S entity);

    Ticket findTicketByTicketId(String ticketId);

    Ticket deleteTicketByTicketId(String ticketId);
}
