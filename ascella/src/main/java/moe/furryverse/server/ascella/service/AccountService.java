package moe.furryverse.server.ascella.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.ascella.repository.AccountRepository;
import moe.furryverse.server.ascella.repository.OAuthRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    final AccountRepository accountRepository;
    final OAuthRepository oAuthRepository;
}
