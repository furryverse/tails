package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 小说的章节
 *
 * @param chapterId 章节 ID
 * @param created   创建时间
 * @param novelId   小说 ID
 * @param title     标题
 * @param contents  内容
 */
@Document("chapters")
public record Chapter(
        @Field("chapter_id") @JsonProperty("chapter_id") String chapterId,
        @Field("created") @JsonProperty("created") long created,
        @Field("novel_id") @JsonProperty("novel_id") String novelId,
        @Field("title") @JsonProperty("title") String title,
        @Field("contents") @JsonProperty("contents") List<String> contents
) {
}
