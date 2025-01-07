package androidx.compose.foundation.layout;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ValueInsets implements WindowInsets {
    public final String name;
    public final MutableState value$delegate;

    public ValueInsets(InsetsValues insetsValues, String str) {
        this.name = str;
        this.value$delegate = SnapshotStateKt.mutableStateOf$default(insetsValues);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ValueInsets) {
            return Intrinsics.areEqual(getValue$foundation_layout_release(), ((ValueInsets) obj).getValue$foundation_layout_release());
        }
        return false;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getBottom(Density density) {
        return getValue$foundation_layout_release().bottom;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getLeft(Density density, LayoutDirection layoutDirection) {
        return getValue$foundation_layout_release().left;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getRight(Density density, LayoutDirection layoutDirection) {
        return getValue$foundation_layout_release().right;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getTop(Density density) {
        return getValue$foundation_layout_release().top;
    }

    public final InsetsValues getValue$foundation_layout_release() {
        return (InsetsValues) ((SnapshotMutableStateImpl) this.value$delegate).getValue();
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final void setValue$foundation_layout_release(InsetsValues insetsValues) {
        ((SnapshotMutableStateImpl) this.value$delegate).setValue(insetsValues);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append("(left=");
        sb.append(getValue$foundation_layout_release().left);
        sb.append(", top=");
        sb.append(getValue$foundation_layout_release().top);
        sb.append(", right=");
        sb.append(getValue$foundation_layout_release().right);
        sb.append(", bottom=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, getValue$foundation_layout_release().bottom, ')');
    }
}
