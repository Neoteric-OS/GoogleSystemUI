package platform.test.motion.compose.values;

import androidx.compose.ui.semantics.SemanticsPropertyKey;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MotionTestValueKey {
    public final SemanticsPropertyKey semanticsPropertyKey;

    public MotionTestValueKey(String str) {
        this.semanticsPropertyKey = new SemanticsPropertyKey(str);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MotionTestValueKey)) {
            return false;
        }
        return Intrinsics.areEqual(this.semanticsPropertyKey, ((MotionTestValueKey) obj).semanticsPropertyKey);
    }

    public final int hashCode() {
        return this.semanticsPropertyKey.hashCode();
    }
}
