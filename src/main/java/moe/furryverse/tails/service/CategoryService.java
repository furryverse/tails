package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.model.Category;
import moe.furryverse.tails.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;

    public List<Category> listCategory(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Category> categories = accountId == null
                ? categoryRepository.findAll(pageable)
                : categoryRepository.findAllByAccountId(accountId, pageable);

        return categories.getContent();
    }
}
