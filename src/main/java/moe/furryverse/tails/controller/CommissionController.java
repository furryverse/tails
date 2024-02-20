package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.service.CommissionService;
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
