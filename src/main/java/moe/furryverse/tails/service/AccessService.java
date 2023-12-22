package moe.furryverse.tails.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessService {

    // todo: 记得去掉这个 SuppressWarnings
    @SuppressWarnings("all")
    public boolean check(String token, List<String> requires) {
        return true;
    }

    // todo: 记得去掉这个 SuppressWarnings
    @SuppressWarnings("all")
    public boolean check(String token, String require) {
        return true;
    }
}
