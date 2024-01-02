package moe.furryverse.tails.utils;

import moe.furryverse.tails.exception.*;
import moe.furryverse.tails.interfaces.Attributable;
import moe.furryverse.tails.interfaces.Contributable;
import moe.furryverse.tails.interfaces.Manageable;
import moe.furryverse.tails.interfaces.Traceable;

import java.util.Objects;

@SuppressWarnings("DuplicatedCode SpellCheckingInspection")
public class ManageStatusUtils {
    public static void checkReadStatus(Attributable attributable, String accountId) {
        if (attributable == null) throw new NotFoundDataException("resources is not found", null, null, null);

        // 一个正常的数据能被普通用户看到的条件是
        // 1. 公开或归档
        // 2. 没有在审核
        // 3. 没有被锁定
        // 4. 没有被删除
        if ((attributable.isPublic() || attributable.isArchived()) && !attributable.isReviewing() && !attributable.isLocked() && !attributable.isDeleted())
            return;

        // 一个不公开的数据或被审核或被锁定的帖子只有管理员或创建者可见
        if ((!attributable.isPublic() || attributable.isReviewing() || attributable.isLocked()) && !attributable.isDeleted()) {
            if (accountId == null) throw new UnauthorizationException("unauthorized", null, null, null);

            // 支持 Manageable 接口先检查是否在管理员里
            if (attributable instanceof Manageable manageable) {
                if (manageable.administrators().contains(accountId)) return;
            }

            // 支持 Contributable 接口检查协作者
            if (attributable instanceof Contributable contributable) {
                if (contributable.collaborators().contains(accountId)) return;
            }

            // 支持 Traceable 接口检查创建者
            if (attributable instanceof Traceable traceable) {
                if (Objects.equals(traceable.createdBy(), accountId)) return;
            }
        }

        if (attributable.isDeleted()) throw new IsDeletedException("resource is deleted", null, null, null);
        if (attributable.isLocked()) throw new IsArchivedException("resource is locked", null, null, null);
        if (attributable.isReviewing()) throw new IsReviewingException("resource is reviewing", null, null, null);

        // 被删除的数据始终不可见
        throw new IsDeletedException("resource is deleted", null, null, null);
    }

    public static void checkDeleteStatus(Attributable attributable, String accountId) {
        if (attributable == null) throw new NotFoundDataException("resources is not found", null, null, null);
        if (accountId == null) throw new UnauthorizationException("unauthorized", null, null, null);
        if (attributable.isDeleted()) throw new IsDeletedException("resource is deleted", null, null, accountId);

        // 支持 Manageable 接口先检查是否在管理员里
        if (attributable instanceof Manageable manageable) {
            if (manageable.administrators().contains(accountId)) return;
        }

        // 支持 Contributable 接口检查协作者
        if (attributable instanceof Contributable contributable) {
            if (contributable.collaborators().contains(accountId)) return;
        }

        // 支持 Traceable 接口检查创建者
        if (attributable instanceof Traceable traceable) {
            if (Objects.equals(traceable.createdBy(), accountId)) return;
        }

        throw new UnauthorizationException("unauthorized", null, null, accountId);
    }

    public static void checkUpdateStatus(Attributable attributable, String accountId) {
        if (attributable == null) throw new NotFoundDataException("resources is not found", null, null, null);
        if (accountId == null) throw new UnauthorizationException("unauthorized", null, null, null);
        if (attributable.isArchived()) throw new IsArchivedException("resource is archived", null, null, accountId);
        if (attributable.isDeleted()) throw new IsDeletedException("resource is deleted", null, null, accountId);

        // 支持 Manageable 接口先检查是否在管理员里
        if (attributable instanceof Manageable manageable) {
            if (manageable.administrators().contains(accountId)) return;
        }

        // 支持 Contributable 接口检查协作者
        if (attributable instanceof Contributable contributable) {
            if (contributable.collaborators().contains(accountId)) return;
        }

        // 支持 Traceable 接口检查创建者
        if (attributable instanceof Traceable traceable) {
            if (Objects.equals(traceable.createdBy(), accountId)) return;
        }

        throw new UnauthorizationException("unauthorized", null, null, accountId);
    }
}
