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
