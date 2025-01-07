package androidx.compose.material3;

import androidx.compose.ui.window.SecureFlagPolicy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ModalBottomSheetProperties {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ModalBottomSheetProperties)) {
            return false;
        }
        ((ModalBottomSheetProperties) obj).getClass();
        return true;
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + (SecureFlagPolicy.Inherit.hashCode() * 31);
    }
}
