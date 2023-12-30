package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.message.Message;
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
    public Message<?> listCommissions() {
        return Message.success();
    }

    @PostMapping()
    public Message<?> createCommission() {
        return Message.success();
    }

    @GetMapping("/{commissionId}")
    public Message<?> getCommission(@PathVariable String commissionId) {
        return Message.success();
    }

    @PostMapping("/{commissionId}")
    public Message<?> updateCommission(@PathVariable String commissionId) {
        return Message.success();
    }

    @DeleteMapping("/{commissionId}")
    public Message<?> deleteCommission(@PathVariable String commissionId) {
        return Message.success();
    }

    //////////////////////////////////////////////////////////////// Workflow

    @GetMapping("/{commissionId}/workflow")
    public Message<?> listCommissionWorkflows(@PathVariable String commissionId) {
        return Message.success();
    }

    @PostMapping("/{commissionId}/workflow")
    public Message<?> createCommissionWorkflow(@PathVariable String commissionId) {
        return Message.success();
    }

    @GetMapping("/{commissionId}/workflow/{workflowId}")
    public Message<?> getCommissionWorkflow(@PathVariable String commissionId, @PathVariable String workflowId) {
        return Message.success();
    }

    @PostMapping("/{commissionId}/workflow/{workflowId}")
    public Message<?> updateCommissionWorkflow(@PathVariable String commissionId, @PathVariable String workflowId) {
        return Message.success();
    }
}
