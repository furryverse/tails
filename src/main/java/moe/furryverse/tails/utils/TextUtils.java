package moe.furryverse.tails.utils;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.Patch;

import java.util.List;

public class TextUtils {
    public static List<String> unifiedDiff(List<String> original, List<String> modified) {
        Patch<String> patch = DiffUtils.diff(original, modified);
        return UnifiedDiffUtils.generateUnifiedDiff(
                "original",
                "modified",
                original,
                patch,
                0
        );
    }

    public static List<String> getOriginal(List<String> modified, List<String> diff) {
        Patch<String> patch = UnifiedDiffUtils.parseUnifiedDiff(diff);
        return patch.restore(modified);
    }
}
