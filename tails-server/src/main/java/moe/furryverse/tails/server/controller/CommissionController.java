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

package moe.furryverse.tails.server.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.server.service.CommissionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/commission")
public class CommissionController {
    final HttpServletRequest request;
    final CommissionService commissionService;

    //////////////////////////////////////////////////////////////// Commission

    @GetMapping()
    public Object listCommissions() {
        return null;
    }

    @PostMapping()
    public Object createCommission() {
        return null;
    }

    @GetMapping("/{commissionId}")
    public Object getCommission(@PathVariable String commissionId) {
        return null;
    }

    @PostMapping("/{commissionId}")
    public Object updateCommission(@PathVariable String commissionId) {
        return null;
    }

    @DeleteMapping("/{commissionId}")
    public Object deleteCommission(@PathVariable String commissionId) {
        return null;
    }

    //////////////////////////////////////////////////////////////// Workflow

    @GetMapping("/{commissionId}/workflow")
    public Object listCommissionWorkflows(@PathVariable String commissionId) {
        return null;
    }

    @PostMapping("/{commissionId}/workflow")
    public Object createCommissionWorkflow(@PathVariable String commissionId) {
        return null;
    }

    @GetMapping("/{commissionId}/workflow/{workflowId}")
    public Object getCommissionWorkflow(@PathVariable String commissionId, @PathVariable String workflowId) {
        return null;
    }

    @PostMapping("/{commissionId}/workflow/{workflowId}")
    public Object updateCommissionWorkflow(@PathVariable String commissionId, @PathVariable String workflowId) {
        return null;
    }
}
