/*
 * Copyright 2024 FurryVerse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moe.furryverse.tails.common.utils;

import moe.furryverse.tails.common.exception.*;
import moe.furryverse.tails.common.interfaces.Attributable;
import moe.furryverse.tails.common.interfaces.Contributable;
import moe.furryverse.tails.common.interfaces.Manageable;
import moe.furryverse.tails.common.interfaces.Traceable;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings("DuplicatedCode SpellCheckingInspection")
public class ManageStatusUtils {
    public static void checkReadStatus(Attributable attributable, String accountId) {
        checkReadStatus(attributable, accountId, () -> true);
    }

    /**
     * 执行读数据操作 状态检查
     *
     * @param attributable  具有属性接口的数据实体
     * @param accountId     请求携带的 Account ID
     * @param extendChecker 额外的检查器 直到几乎全部检查完成时才会调用它 如果不起效则进入到抛出异常环节
     */
    public static void checkReadStatus(Attributable attributable, String accountId, Supplier<Boolean> extendChecker) {
        if (attributable == null) throw new NotFoundDataException("resources is not found", null, null, null);

        // 一个正常的数据能被普通用户看到的条件是
        // 1. 公开
        // 2. 没有在审核
        // 3. 没有被锁定
        // 4. 没有被删除
        if (attributable.isPublic() && !attributable.isReviewing() && !attributable.isLocked() && !attributable.isDeleted())
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

        if (extendChecker != null && !extendChecker.get()) return;

        if (attributable.isDeleted()) throw new IsDeletedException("resource is deleted", null, null, null);
        if (attributable.isLocked()) throw new IsArchivedException("resource is locked", null, null, null);
        if (attributable.isReviewing()) throw new IsReviewingException("resource is reviewing", null, null, null);

        // 被删除的数据始终不可见
        throw new IsDeletedException("resource is deleted", null, null, null);
    }

    /**
     * 执行删除数据操作 状态检查
     *
     * @param attributable 具有属性接口的数据实体
     * @param accountId    请求携带的 Account ID
     */
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

    /**
     * 执行更新数据操作 状态检查
     *
     * @param attributable 具有属性接口的数据实体
     * @param accountId    请求携带的 Account ID
     */

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

    /**
     * 检查具有关联关系的两个数据实体
     *
     * @param parent        被关联的数据实体
     * @param child         关联了其他数据实体的数据实体
     * @param accountId     请求携带的 Account ID
     * @param extendChecker 额外的检查器 直到几乎全部检查完成时才会调用它 如果不起效则进入到抛出异常环节
     */
    public static void checkParentsStatus(Attributable parent, Attributable child, String accountId, Supplier<Boolean> extendChecker) {
        if (parent == null) throw new NotFoundDataException("resourec (parent) is not found", null, null, null);
        if (child == null) throw new NotFoundDataException("resourcec (child) is not found", null, null, null);
        if (accountId == null) throw new UnauthorizationException("unauthorized", null, null, null);

        // 正常显示票
        // 1. 公开
        // 2. 没有在审核
        // 3. 没有被锁定
        // 4. 没有被删除
        if (child.isPublic() && !child.isReviewing() && !child.isLocked() && !child.isDeleted()) return;

        // 如果被删除了 或审核 锁定 非公开等
        // 则需要管理员
        // 支持 Manageable 接口先检查是否在管理员里
        if (parent instanceof Manageable manageable) {
            if (manageable.administrators().contains(accountId)) return;
        }

        // 支持 Traceable 接口检查创建者
        if (parent instanceof Traceable traceable) {
            if (Objects.equals(traceable.createdBy(), accountId)) return;
        }

        // 如果没有管理员身份则需要执行自定义逻辑判断
        if (extendChecker != null && extendChecker.get()) return;

        if (child.isDeleted()) throw new IsDeletedException("resource (child) is deleted", null, null, null);
        if (child.isLocked()) throw new IsArchivedException("resource (child) is locked", null, null, null);
        if (child.isReviewing()) throw new IsReviewingException("resource (child) is reviewing", null, null, null);

        // 被删除的数据始终不可见
        throw new IsDeletedException("resource (child) is deleted", null, null, null);
    }
}
