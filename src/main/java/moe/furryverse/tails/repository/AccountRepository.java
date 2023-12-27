package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Account;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    @NotNull <S extends Account> S save(@NotNull S entity);

    // 根据 email 查找和删除
    Account findByEmail(@NotNull String email);

    // 根据 username 查找
    Account findByUsername(@NotNull String username);

    // 查询是否存在 email
    boolean existsByEmail(@NotNull String email);

    // 查询是否存在 username
    boolean existsByUsername(@NotNull String username);
}
