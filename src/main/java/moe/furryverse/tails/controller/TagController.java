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

package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.PermissionCheck;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.security.Permission;
import moe.furryverse.tails.service.TagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/tag")
public class TagController {
    final HttpServletRequest request;
    final TagService tagService;

    @GetMapping()
    @PermissionCheck(access = {Permission.TAG_LIST})
    public Object listTag(int page, int size) {
        return tagService.listTag(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                page,
                size
        );
    }

    @PostMapping()
    @PermissionCheck(access = {Permission.TAG_WRITE})
    public Object createTag(
            @RequestParam String name,
            @RequestParam String color
    ) {
        return tagService.createTag(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                name,
                color
        );
    }

    @GetMapping("/{tagId}")
    @PermissionCheck(access = {Permission.TAG_READ})
    public Object getTag(@PathVariable String tagId) {
        return tagService.getTag(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                tagId
        );
    }

    @DeleteMapping("/{tagId}")
    @PermissionCheck(access = {Permission.TAG_DELETE})
    public Object deleteTag(@PathVariable String tagId) {
        return tagService.deleteTag(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                tagId
        );
    }
}
