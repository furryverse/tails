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
import moe.furryverse.tails.service.IndexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/index")
public class IndexController {
    final HttpServletRequest request;
    final IndexService indexService;

    @GetMapping
    public Object search() {
        return null;
    }

    @GetMapping("/recommendation/shop")
    public Object recommendingShop() {
        return null;
    }

    @GetMapping("/recommendation/item")
    public Object recommendingItem() {
        return null;
    }

    @GetMapping("/recommendation/novel")
    public Object recommendingNovel() {
        return null;
    }

    @GetMapping("/recommendation/commission")
    public Object recommendingCommission() {
        return null;
    }

    @GetMapping("/recommendation/user")
    public Object recommendingUser() {
        return null;
    }

    @GetMapping("/recommendation/keyword")
    public Object recommendingKeyword() {
        return null;
    }
}
