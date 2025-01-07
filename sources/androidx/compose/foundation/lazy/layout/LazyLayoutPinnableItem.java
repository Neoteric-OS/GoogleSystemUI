package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.layout.PinnableContainer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LazyLayoutPinnableItem implements PinnableContainer, PinnableContainer.PinnedHandle, LazyLayoutPinnedItemList.PinnedItem {
    public final Object key;
    public final LazyLayoutPinnedItemList pinnedItemList;
    public final MutableIntState index$delegate = SnapshotIntStateKt.mutableIntStateOf(-1);
    public final MutableIntState pinsCount$delegate = SnapshotIntStateKt.mutableIntStateOf(0);
    public final MutableState parentHandle$delegate = SnapshotStateKt.mutableStateOf$default(null);
    public final MutableState _parentPinnableContainer$delegate = SnapshotStateKt.mutableStateOf$default(null);

    public LazyLayoutPinnableItem(Object obj, LazyLayoutPinnedItemList lazyLayoutPinnedItemList) {
        this.key = obj;
        this.pinnedItemList = lazyLayoutPinnedItemList;
    }

    public final int getPinsCount() {
        return ((SnapshotMutableIntStateImpl) this.pinsCount$delegate).getIntValue();
    }

    @Override // androidx.compose.ui.layout.PinnableContainer
    public final PinnableContainer.PinnedHandle pin() {
        LazyLayoutPinnableItem lazyLayoutPinnableItem;
        if (getPinsCount() == 0) {
            this.pinnedItemList.items.add(this);
            PinnableContainer pinnableContainer = (PinnableContainer) ((SnapshotMutableStateImpl) this._parentPinnableContainer$delegate).getValue();
            if (pinnableContainer != null) {
                lazyLayoutPinnableItem = (LazyLayoutPinnableItem) pinnableContainer;
                lazyLayoutPinnableItem.pin();
            } else {
                lazyLayoutPinnableItem = null;
            }
            ((SnapshotMutableStateImpl) this.parentHandle$delegate).setValue(lazyLayoutPinnableItem);
        }
        ((SnapshotMutableIntStateImpl) this.pinsCount$delegate).setIntValue(getPinsCount() + 1);
        return this;
    }

    @Override // androidx.compose.ui.layout.PinnableContainer.PinnedHandle
    public final void release() {
        if (getPinsCount() <= 0) {
            InlineClassHelperKt.throwIllegalStateException("Release should only be called once");
        }
        ((SnapshotMutableIntStateImpl) this.pinsCount$delegate).setIntValue(getPinsCount() - 1);
        if (getPinsCount() == 0) {
            this.pinnedItemList.items.remove(this);
            MutableState mutableState = this.parentHandle$delegate;
            PinnableContainer.PinnedHandle pinnedHandle = (PinnableContainer.PinnedHandle) ((SnapshotMutableStateImpl) mutableState).getValue();
            if (pinnedHandle != null) {
                ((LazyLayoutPinnableItem) pinnedHandle).release();
            }
            ((SnapshotMutableStateImpl) mutableState).setValue(null);
        }
    }
}
