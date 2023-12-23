package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 一本小说
 *
 * @param novelId     小说唯一 ID
 * @param created     创建时间
 * @param title       标题
 * @param description 简介
 * @param cover       封面
 * @param accountId   创建小说的账户 ID
 * @param tags        标签 - 本字段的值与 Tag 中的 tag_id 相关联
 */
@Document("novels")
public record Novel(
        @Field("novel_id") @JsonProperty("novel_id") @NotNull @Id String novelId,
        @Field("created") @JsonProperty("created") long created,
        @Field("title") @JsonProperty("title") @NotNull String title,
        @Field("description") @JsonProperty("description") @Nullable String description,
        @Field("cover") @JsonProperty("cover") @Nullable String cover,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,

        // 关联键
        @Field("tags") @JsonProperty("tags") @NotNull List<String> tags
) {
}
