package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 小说的章节
 *
 * @param chapterId   章节 ID
 * @param created     创建时间
 * @param title       标题
 * @param contents    内容
 * @param novelId     小说 ID - 与 Novel 的 novel_id 相关联
 * @param isDraft     是否为草稿（不公开 编辑不保存历史记录）
 * @param isLocked    是否锁定（不允许查看不允许修改）
 * @param isReviewing 是否正在审核
 * @param isDeleted   是否删除
 */
@Document("chapters")
public record Chapter(
        @Id @JsonProperty("chapter_id") @NotNull String chapterId,
        @Field("created") @JsonProperty("created") long created,
        @Field("title") @JsonProperty("title") String title,
        @Field("contents") @JsonProperty("contents") List<String> contents,

        // 关联键
        @Field("novel_id") @JsonProperty("novel_id") String novelId,

        // 管理功能
        @Field("is_draft") @JsonProperty("is_draft") boolean isDraft,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) {
}
