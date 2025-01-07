package com.android.systemui.keyboard.shortcut.shared.model;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ShortcutCategoryType {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AppCategories implements ShortcutCategoryType {
        public static final AppCategories INSTANCE = new AppCategories();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof AppCategories);
        }

        public final int hashCode() {
            return 630118651;
        }

        public final String toString() {
            return "AppCategories";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CurrentApp implements ShortcutCategoryType {
        public final String packageName;

        public CurrentApp(String str) {
            this.packageName = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof CurrentApp) && Intrinsics.areEqual(this.packageName, ((CurrentApp) obj).packageName);
        }

        public final int hashCode() {
            return this.packageName.hashCode();
        }

        public final String toString() {
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("CurrentApp(packageName="), this.packageName, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InputMethodEditor implements ShortcutCategoryType {
        public static final InputMethodEditor INSTANCE = new InputMethodEditor();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof InputMethodEditor);
        }

        public final int hashCode() {
            return -371863178;
        }

        public final String toString() {
            return "InputMethodEditor";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MultiTasking implements ShortcutCategoryType {
        public static final MultiTasking INSTANCE = new MultiTasking();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof MultiTasking);
        }

        public final int hashCode() {
            return 1497658150;
        }

        public final String toString() {
            return "MultiTasking";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class System implements ShortcutCategoryType {
        public static final System INSTANCE = new System();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof System);
        }

        public final int hashCode() {
            return 211418641;
        }

        public final String toString() {
            return "System";
        }
    }
}
