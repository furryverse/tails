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

package moe.furryverse.tails.security;

public class Permission {
    // Account
    public static final String ACCOUNT_LIST = "account:list";
    public static final String ACCOUNT_READ = "account:read";
    public static final String ACCOUNT_WRITE = "account:write";
    public static final String ACCOUNT_UPDATE = "account:update";
    public static final String ACCOUNT_DELETE = "account:delete";

    // Activity
    public static final String ACTIVITY_LIST = "activity:list";
    public static final String ACTIVITY_READ = "activity:read";
    public static final String ACTIVITY_WRITE = "activity:write";
    public static final String ACTIVITY_UPDATE = "activity:update";
    public static final String ACTIVITY_DELETE = "activity:delete";

    // Activity - Ticket
    public static final String ACTIVITY_TICKET_LIST = "activity.ticket:list";
    public static final String ACTIVITY_TICKET_READ = "activity.ticket:read";
    public static final String ACTIVITY_TICKET_WRITE = "activity.ticket:write";
    public static final String ACTIVITY_TICKET_UPDATE = "activity.ticket:update";
    public static final String ACTIVITY_TICKET_DELETE = "activity.ticket:delete";

    // Activity - Stub
    public static final String ACTIVITY_STUB_LIST = "activity.stub:list";
    public static final String ACTIVITY_STUB_READ = "activity.stub:read";
    public static final String ACTIVITY_STUB_WRITE = "activity.stub:write";
    public static final String ACTIVITY_STUB_UPDATE = "activity.stub:update";
    public static final String ACTIVITY_STUB_DELETE = "activity.stub:delete";

    // Novel
    public static final String NOVEL_LIST = "novel:list";
    public static final String NOVEL_READ = "novel:read";
    public static final String NOVEL_WRITE = "novel:write";
    public static final String NOVEL_UPDATE = "novel:update";
    public static final String NOVEL_DELETE = "novel:delete";

    // Novel - Chapter
    public static final String NOVEL_CHAPTER_LIST = "novel.chapter:list";
    public static final String NOVEL_CHAPTER_READ = "novel.chapter:read";
    public static final String NOVEL_CHAPTER_WRITE = "novel.chapter:write";
    public static final String NOVEL_CHAPTER_UPDATE = "novel.chapter:update";
    public static final String NOVEL_CHAPTER_DELETE = "novel.chapter:delete";

    // Shop
    public static final String SHOP_LIST = "shop:list";
    public static final String SHOP_READ = "shop:read";
    public static final String SHOP_WRITE = "shop:write";
    public static final String SHOP_UPDATE = "shop:update";
    public static final String SHOP_DELETE = "shop:delete";

    // Shop - Item
    public static final String SHOP_ITEM_LIST = "shop.item:list";
    public static final String SHOP_ITEM_READ = "shop.item:read";
    public static final String SHOP_ITEM_WRITE = "shop.item:write";
    public static final String SHOP_ITEM_UPDATE = "shop.item:update";
    public static final String SHOP_ITEM_DELETE = "shop.item:delete";

    // Tag
    public static final String TAG_LIST = "tag:list";
    public static final String TAG_READ = "tag:read";
    public static final String TAG_WRITE = "tag:write";
    public static final String TAG_UPDATE = "tag:update";
    public static final String TAG_DELETE = "tag:delete";

    // POST
    public static final String POST_LIST = "post:list";
    public static final String POST_READ = "post:read";
    public static final String POST_WRITE = "post:write";
    public static final String POST_UPDATE = "post:update";
    public static final String POST_DELETE = "post:delete";

    // POST - COMMENT
    public static final String POST_COMMENT_LIST = "post.comment:list";
    public static final String POST_COMMENT_READ = "post.comment:read";
    public static final String POST_COMMENT_WRITE = "post.comment:write";
    public static final String POST_COMMENT_UPDATE = "post.comment:update";
    public static final String POST_COMMENT_DELETE = "post.comment:delete";

    // POST - THOUGHT
    public static final String POST_THOUGHT_LIST = "post.thought:list";
    public static final String POST_THOUGHT_READ = "post.thought:read";
    public static final String POST_THOUGHT_WRITE = "post.thought:write";
    public static final String POST_THOUGHT_UPDATE = "post.thought:update";
    public static final String POST_THOUGHT_DELETE = "post.thought:delete";

    // POST - REACTION
    public static final String POST_REACTION_LIST = "post.reaction:list";
    public static final String POST_REACTION_READ = "post.reaction:read";
    public static final String POST_REACTION_WRITE = "post.reaction:write";
    public static final String POST_REACTION_UPDATE = "post.reaction:update";
    public static final String POST_REACTION_DELETE = "post.reaction:delete";
}
