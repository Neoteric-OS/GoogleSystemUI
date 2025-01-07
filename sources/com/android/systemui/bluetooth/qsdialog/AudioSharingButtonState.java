package com.android.systemui.bluetooth.qsdialog;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AudioSharingButtonState {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Gone extends AudioSharingButtonState {
        public static final Gone INSTANCE = new Gone();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Visible extends AudioSharingButtonState {
        public final boolean isActive;
        public final int resId;

        public Visible(int i, boolean z) {
            this.resId = i;
            this.isActive = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Visible)) {
                return false;
            }
            Visible visible = (Visible) obj;
            return this.resId == visible.resId && this.isActive == visible.isActive;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isActive) + (Integer.hashCode(this.resId) * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Visible(resId=");
            sb.append(this.resId);
            sb.append(", isActive=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isActive, ")");
        }
    }
}
