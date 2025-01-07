package androidx.compose.foundation.text.selection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SingleSelectionLayout implements SelectionLayout {
    public final SelectableInfo info;
    public final boolean isStartHandle;
    public final Selection previousSelection;

    public SingleSelectionLayout(boolean z, Selection selection, SelectableInfo selectableInfo) {
        this.isStartHandle = z;
        this.previousSelection = selection;
        this.info = selectableInfo;
    }

    public final CrossStatus getCrossStatus() {
        SelectableInfo selectableInfo = this.info;
        int i = selectableInfo.rawStartHandleOffset;
        int i2 = selectableInfo.rawEndHandleOffset;
        return i < i2 ? CrossStatus.NOT_CROSSED : i > i2 ? CrossStatus.CROSSED : CrossStatus.COLLAPSED;
    }

    public final String toString() {
        return "SingleSelectionLayout(isStartHandle=" + this.isStartHandle + ", crossed=" + getCrossStatus() + ", info=\n\t" + this.info + ')';
    }
}
