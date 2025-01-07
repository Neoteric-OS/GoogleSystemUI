package com.android.systemui.qs.tiles.impl.flashlight.domain.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface FlashlightTileModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FlashlightAvailable implements FlashlightTileModel {
        public final boolean isEnabled;

        public final boolean equals(Object obj) {
            if (obj instanceof FlashlightAvailable) {
                return this.isEnabled == ((FlashlightAvailable) obj).isEnabled;
            }
            return false;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isEnabled);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("FlashlightAvailable(isEnabled="), this.isEnabled, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FlashlightTemporarilyUnavailable implements FlashlightTileModel {
        public static final FlashlightTemporarilyUnavailable INSTANCE = new FlashlightTemporarilyUnavailable();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof FlashlightTemporarilyUnavailable);
        }

        public final int hashCode() {
            return 408229728;
        }

        public final String toString() {
            return "FlashlightTemporarilyUnavailable";
        }
    }
}
