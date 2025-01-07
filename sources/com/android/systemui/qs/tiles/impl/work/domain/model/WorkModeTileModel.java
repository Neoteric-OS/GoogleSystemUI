package com.android.systemui.qs.tiles.impl.work.domain.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface WorkModeTileModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HasActiveProfile implements WorkModeTileModel {
        public final boolean isEnabled;

        public HasActiveProfile(boolean z) {
            this.isEnabled = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof HasActiveProfile) && this.isEnabled == ((HasActiveProfile) obj).isEnabled;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isEnabled);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("HasActiveProfile(isEnabled="), this.isEnabled, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NoActiveProfile implements WorkModeTileModel {
        public static final NoActiveProfile INSTANCE = new NoActiveProfile();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof NoActiveProfile);
        }

        public final int hashCode() {
            return -872501935;
        }

        public final String toString() {
            return "NoActiveProfile";
        }
    }
}
