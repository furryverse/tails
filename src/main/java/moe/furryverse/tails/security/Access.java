package moe.furryverse.tails.security;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public enum Access {
    // Cluster Category
    @JsonProperty("category.list") CATEGORY_LIST,
    @JsonProperty("category.get") CATEGORY_GET,
    @JsonProperty("category.create") CATEGORY_CREATE,
    @JsonProperty("category.update") CATEGORY_UPDATE,
    @JsonProperty("category.delete") CATEGORY_DELETE,

    // Galaxy Post
    @JsonProperty("post.list") POST_LIST,
    @JsonProperty("post.get") POST_GET,
    @JsonProperty("post.create") POST_CREATE,
    @JsonProperty("post.update") POST_UPDATE,
    @JsonProperty("post.delete") POST_DELETE,

    // Nucleus Tag
    @JsonProperty("tag.list") TAG_LIST,
    @JsonProperty("tag.get") TAG_GET,
    @JsonProperty("tag.create") TAG_CREATE,
    @JsonProperty("tag.update") TAG_UPDATE,
    @JsonProperty("tag.delete") TAG_DELETE,

    // Asteroid Thought
    @JsonProperty("thought.list") THOUGHT_LIST,
    @JsonProperty("thought.get") THOUGHT_GET,
    @JsonProperty("thought.create") THOUGHT_CREATE,
    @JsonProperty("thought.update") THOUGHT_UPDATE,
    @JsonProperty("thought.delete") THOUGHT_DELETE,

    // Comet Reaction
    @JsonProperty("reaction.list") REACTION_LIST,
    @JsonProperty("reaction.get") REACTION_GET,
    @JsonProperty("reaction.create") REACTION_CREATE,
    @JsonProperty("reaction.update") REACTION_UPDATE,
    @JsonProperty("reaction.delete") REACTION_DELETE,

    // Planet Comment
    @JsonProperty("comment.list") COMMENT_LIST,
    @JsonProperty("comment.get") COMMENT_GET,
    @JsonProperty("comment.create") COMMENT_CREATE,
    @JsonProperty("comment.update") COMMENT_UPDATE,
    @JsonProperty("comment.delete") COMMENT_DELETE,

    // Stardust History
    @JsonProperty("history.list") HISTORY_LIST,
    @JsonProperty("history.get") HISTORY_GET,
    @JsonProperty("history.create") HISTORY_CREATE,
    @JsonProperty("history.update") HISTORY_UPDATE,
    @JsonProperty("history.delete") HISTORY_DELETE,

    // File
    @JsonProperty("file.list") FILE_LIST,
    @JsonProperty("file.get") FILE_GET,
    @JsonProperty("file.create") FILE_UPLOAD,
    @JsonProperty("file.delete") FILE_DELETE;

    public static List<Access> all() {
        return new ArrayList<>() {{
            addAll(
                    Arrays.stream(Access.values()).toList()
            );
        }};
    }

    public Access value(String access) {
        return Access.valueOf(access.toUpperCase(Locale.ROOT).replaceAll("_", "."));
    }

    @Override
    public String toString() {
        return this.name().toLowerCase(Locale.ROOT).replaceAll("_", ".");
    }

}
