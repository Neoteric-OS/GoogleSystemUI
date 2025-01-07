package com.android.systemui.keyboard.shortcut.ui.model;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutCategoryType;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ShortcutsUiState {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Active implements ShortcutsUiState {
        public final ShortcutCategoryType defaultSelectedCategory;
        public final String searchQuery;
        public final List shortcutCategories;

        public Active(String str, List list, ShortcutCategoryType shortcutCategoryType) {
            this.searchQuery = str;
            this.shortcutCategories = list;
            this.defaultSelectedCategory = shortcutCategoryType;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Active)) {
                return false;
            }
            Active active = (Active) obj;
            return Intrinsics.areEqual(this.searchQuery, active.searchQuery) && Intrinsics.areEqual(this.shortcutCategories, active.shortcutCategories) && Intrinsics.areEqual(this.defaultSelectedCategory, active.defaultSelectedCategory);
        }

        public final int hashCode() {
            int m = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.searchQuery.hashCode() * 31, 31, this.shortcutCategories);
            ShortcutCategoryType shortcutCategoryType = this.defaultSelectedCategory;
            return m + (shortcutCategoryType == null ? 0 : shortcutCategoryType.hashCode());
        }

        public final String toString() {
            return "Active(searchQuery=" + this.searchQuery + ", shortcutCategories=" + this.shortcutCategories + ", defaultSelectedCategory=" + this.defaultSelectedCategory + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Inactive implements ShortcutsUiState {
        public static final Inactive INSTANCE = new Inactive();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Inactive);
        }

        public final int hashCode() {
            return 500168522;
        }

        public final String toString() {
            return "Inactive";
        }
    }
}
