package androidx.compose.foundation.layout;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidWindowInsets implements WindowInsets {
    public final MutableState insets$delegate = SnapshotStateKt.mutableStateOf$default(Insets.NONE);
    public final MutableState isVisible$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);
    public final String name;
    public final int type;

    public AndroidWindowInsets(int i, String str) {
        this.type = i;
        this.name = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AndroidWindowInsets) {
            return this.type == ((AndroidWindowInsets) obj).type;
        }
        return false;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getBottom(Density density) {
        return getInsets$foundation_layout_release().bottom;
    }

    public final Insets getInsets$foundation_layout_release() {
        return (Insets) ((SnapshotMutableStateImpl) this.insets$delegate).getValue();
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getLeft(Density density, LayoutDirection layoutDirection) {
        return getInsets$foundation_layout_release().left;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getRight(Density density, LayoutDirection layoutDirection) {
        return getInsets$foundation_layout_release().right;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getTop(Density density) {
        return getInsets$foundation_layout_release().top;
    }

    public final int hashCode() {
        return this.type;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append('(');
        sb.append(getInsets$foundation_layout_release().left);
        sb.append(", ");
        sb.append(getInsets$foundation_layout_release().top);
        sb.append(", ");
        sb.append(getInsets$foundation_layout_release().right);
        sb.append(", ");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, getInsets$foundation_layout_release().bottom, ')');
    }

    public final void update$foundation_layout_release(WindowInsetsCompat windowInsetsCompat, int i) {
        int i2 = this.type;
        if (i == 0 || (i & i2) != 0) {
            ((SnapshotMutableStateImpl) this.insets$delegate).setValue(windowInsetsCompat.mImpl.getInsets(i2));
            boolean isVisible = windowInsetsCompat.mImpl.isVisible(i2);
            ((SnapshotMutableStateImpl) this.isVisible$delegate).setValue(Boolean.valueOf(isVisible));
        }
    }
}
