package moe.furryverse.server.arcturus.service;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.Patch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextService {
    public List<String> unifiedDiff(List<String> original, List<String> modified) {
        Patch<String> patch = DiffUtils.diff(original, modified);
        return UnifiedDiffUtils.generateUnifiedDiff(
                "original",
                "modified",
                original,
                patch,
                0
        );
    }

    public List<String> getOriginal(List<String> modified, List<String> diff) {
        Patch<String> patch = UnifiedDiffUtils.parseUnifiedDiff(diff);
        return patch.restore(modified);
    }
}
