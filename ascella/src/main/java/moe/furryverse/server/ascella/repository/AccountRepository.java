package moe.furryverse.server.ascella.repository;

import moe.furryverse.server.common.model.Account;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    <S extends Account> @NotNull S save(@NotNull S entity);

    // 根据 uid 查找和删除
    Account findByUid(@NotNull String uid);

    Account deleteByUid(@NotNull String uid);

    // 根据 email 查找和删除
    Account findByEmail(@NotNull String email);

    Account deleteByEmail(@NotNull String email);

    // 根据 username 查找
    Account findByUsername(@NotNull String username);

    // 查询是否存在 email
    boolean existsByEmail(@NotNull String email);

    // 查询是否存在 username
    boolean existsByUsername(@NotNull String username);
}
