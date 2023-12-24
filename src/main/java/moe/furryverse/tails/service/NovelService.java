package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.repository.ChapterRepository;
import moe.furryverse.tails.repository.NovelRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelService {
    final NovelRepository novelRepository;
    final ChapterRepository chapterRepository;
}
