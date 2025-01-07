package com.android.systemui.media.controls.shared.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SmartspaceMediaLoadingModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Loaded extends SmartspaceMediaLoadingModel {
        public final boolean isPrioritized;
        public final String key;

        public Loaded(String str, boolean z) {
            this.key = str;
            this.isPrioritized = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Loaded)) {
                return false;
            }
            Loaded loaded = (Loaded) obj;
            return Intrinsics.areEqual(this.key, loaded.key) && this.isPrioritized == loaded.isPrioritized;
        }

        @Override // com.android.systemui.media.controls.shared.model.SmartspaceMediaLoadingModel
        public final String getKey() {
            return this.key;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isPrioritized) + (this.key.hashCode() * 31);
        }

        public final String toString() {
            return "Loaded(key=" + this.key + ", isPrioritized=" + this.isPrioritized + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Removed extends SmartspaceMediaLoadingModel {
        public final boolean immediatelyUpdateUi;
        public final String key;

        public Removed(String str, boolean z) {
            this.key = str;
            this.immediatelyUpdateUi = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Removed)) {
                return false;
            }
            Removed removed = (Removed) obj;
            return Intrinsics.areEqual(this.key, removed.key) && this.immediatelyUpdateUi == removed.immediatelyUpdateUi;
        }

        @Override // com.android.systemui.media.controls.shared.model.SmartspaceMediaLoadingModel
        public final String getKey() {
            return this.key;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.immediatelyUpdateUi) + (this.key.hashCode() * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Removed(key=");
            sb.append(this.key);
            sb.append(", immediatelyUpdateUi=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.immediatelyUpdateUi, ")");
        }
    }

    public abstract String getKey();
}
