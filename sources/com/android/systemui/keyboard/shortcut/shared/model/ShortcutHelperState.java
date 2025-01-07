package com.android.systemui.keyboard.shortcut.shared.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ShortcutHelperState {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Active implements ShortcutHelperState {
        public final int deviceId;

        public Active(int i) {
            this.deviceId = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Active) && this.deviceId == ((Active) obj).deviceId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.deviceId);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("Active(deviceId="), this.deviceId, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Inactive implements ShortcutHelperState {
        public static final Inactive INSTANCE = new Inactive();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Inactive);
        }

        public final int hashCode() {
            return 1595827114;
        }

        public final String toString() {
            return "Inactive";
        }
    }
}
