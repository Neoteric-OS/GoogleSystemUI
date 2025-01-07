package androidx.compose.ui.node;

import androidx.compose.runtime.AbstractApplier;
import androidx.compose.ui.platform.AndroidComposeView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UiApplier extends AbstractApplier {
    @Override // androidx.compose.runtime.Applier
    public final void insertBottomUp(int i, Object obj) {
        ((LayoutNode) this.current).insertAt$ui_release(i, (LayoutNode) obj);
    }

    @Override // androidx.compose.runtime.Applier
    public final /* bridge */ /* synthetic */ void insertTopDown(int i, Object obj) {
    }

    @Override // androidx.compose.runtime.Applier
    public final void move(int i, int i2, int i3) {
        ((LayoutNode) this.current).move$ui_release(i, i2, i3);
    }

    @Override // androidx.compose.runtime.AbstractApplier
    public final void onClear() {
        ((LayoutNode) this.root).removeAll$ui_release();
    }

    @Override // androidx.compose.runtime.Applier
    public final void onEndChanges() {
        AndroidComposeView androidComposeView = ((LayoutNode) this.root).owner;
        if (androidComposeView != null) {
            androidComposeView.onEndApplyChanges();
        }
    }

    @Override // androidx.compose.runtime.Applier
    public final void remove(int i, int i2) {
        ((LayoutNode) this.current).removeAt$ui_release(i, i2);
    }

    @Override // androidx.compose.runtime.Applier
    public final void reuse() {
        ((LayoutNode) this.current).onReuse();
    }
}
