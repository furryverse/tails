package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Ticket;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    @NotNull <S extends Ticket> S save(@NotNull S entity);

    @Query("{'is_deleted': ?0}")
    @NotNull Page<Ticket> findAll(boolean isDeleted, @NotNull Pageable pageable);

    Page<Ticket> findAllByActivityId(String activityId, Pageable pageable);
}
