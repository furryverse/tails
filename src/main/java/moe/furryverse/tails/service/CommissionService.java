package moe.furryverse.tails.service;


import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.repository.CommissionRepository;
import moe.furryverse.tails.repository.WorkflowRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommissionService {
    final CommissionRepository commissionRepository;
    final WorkflowRepository workflowRepository;
}
