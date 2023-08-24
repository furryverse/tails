package moe.furryverse.server.hecatebolus.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 文件记录
 *
 * @param fid      文件 ID
 * @param created  创建时间
 * @param uid      用户账户 ID
 * @param name     文件名
 * @param path     文件路径
 * @param type     文件类型
 * @param size     文件大小
 * @param isPublic 是否公开
 */
@Document("file_record")
public record FileRecord(
        @NotNull @Id String fid,
        long created,
        @NotNull String uid,
        @NotNull String name,
        @NotNull String path,
        @NotNull String type,
        long size,
        boolean isPublic
) {
}
