package moe.furryverse.tails.utils;

import moe.furryverse.tails.interfaces.Attributable;
import moe.furryverse.tails.interfaces.Manageable;
import moe.furryverse.tails.interfaces.Traceable;

import java.util.Objects;

public class ManageStatusUtils {
    public static boolean checkReadStatus(Attributable attributable, String accountId) {
        // 一个正常的数据能被普通用户看到的条件是
        // 1. 公开或归档
        // 2. 没有在审核
        // 3. 没有被锁定
        // 4. 没有被删除
        if ((attributable.isPublic() || attributable.isArchived()) && !attributable.isArchived() && !attributable.isLocked() && !attributable.isDeleted())
            return true;

        // 一个不公开的数据或被审核或被锁定的帖子只有管理员或创建者可见
        if ((!attributable.isPublic() || attributable.isReviewing() || attributable.isLocked()) && !attributable.isDeleted()) {
            if (accountId == null) return false;

            // 支持 Manageable 接口先检查是否在管理员里
            if (attributable instanceof Manageable manageable) {
                if (manageable.administrators().contains(accountId)) return true;
            }

            // 支持 Traceable 接口检查创建者
            if (attributable instanceof Traceable traceable) {
                // 如果以上全部条件都不满足了，直接返回 false 就好
                return Objects.equals(traceable.createdBy(), accountId);
            }
        }

        // 被删除的数据始终不可见
        return false;
    }

    public static boolean checkDeleteStatus(Attributable attributable, String accountId) {
        if (accountId == null) return false;
        if (attributable.isDeleted()) return false;

        // 支持 Manageable 接口先检查是否在管理员里
        if (attributable instanceof Manageable manageable) {
            if (manageable.administrators().contains(accountId)) return true;
        }

        // 支持 Traceable 接口检查创建者
        if (attributable instanceof Traceable traceable) {
            // 如果以上全部条件都不满足了，直接返回 false 就好
            return Objects.equals(traceable.createdBy(), accountId);
        }

        return false;
    }

    public static boolean checkUpdateStatus(Attributable attributable, String accountId) {
        if (accountId == null) return false;
        if (attributable.isDeleted()) return false;
        if (attributable.isArchived()) return false;

        // 支持 Manageable 接口先检查是否在管理员里
        if (attributable instanceof Manageable manageable) {
            if (manageable.administrators().contains(accountId)) return true;
        }

        // 支持 Traceable 接口检查创建者
        if (attributable instanceof Traceable traceable) {
            // 如果以上全部条件都不满足了，直接返回 false 就好
            return Objects.equals(traceable.createdBy(), accountId);
        }

        return false;
    }
}
