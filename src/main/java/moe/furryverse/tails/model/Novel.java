package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
 * @param tags        标签
 */
@Document("novels")
public record Novel(
        @Field("novel_id") @JsonProperty("novel_id") String novelId,
        @Field("created") @JsonProperty("created") long created,
        @Field("title") @JsonProperty("title") String title,
        @Field("description") @JsonProperty("description") String description,
        @Field("cover") @JsonProperty("cover") String cover,
        @Field("account_id") @JsonProperty("account_id") String accountId,
        @Field("tags") @JsonProperty("tags") List<String> tags
) {
}
