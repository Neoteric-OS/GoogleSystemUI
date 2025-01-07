package com.android.systemui.communal.ui.compose;

import androidx.compose.foundation.lazy.grid.LazyGridItemInfo;
import androidx.compose.foundation.lazy.grid.LazyGridMeasureResult;
import androidx.compose.foundation.lazy.grid.LazyGridMeasuredItem;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GridDragDropState {
    public final ContentListState contentListState;
    public final ContextScope scope;
    public final LazyGridState state;
    public final Function1 updateDragPositionForRemove;
    public final MutableState draggingItemIndex$delegate = SnapshotStateKt.mutableStateOf$default(null);
    public final MutableState isDraggingToRemove$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
    public final BufferedChannel scrollChannel = ChannelKt.Channel$default(0, null, null, 7);
    public final MutableState draggingItemDraggedDelta$delegate = SnapshotStateKt.mutableStateOf$default(new Offset(0));
    public final MutableState draggingItemInitialOffset$delegate = SnapshotStateKt.mutableStateOf$default(new Offset(0));
    public final MutableState dragStartPointerOffset$delegate = SnapshotStateKt.mutableStateOf$default(new Offset(0));

    public GridDragDropState(LazyGridState lazyGridState, ContentListState contentListState, ContextScope contextScope, Function1 function1) {
        this.state = lazyGridState;
        this.contentListState = contentListState;
        this.scope = contextScope;
        this.updateDragPositionForRemove = function1;
    }

    public final LazyGridItemInfo getDraggingItemLayoutInfo() {
        Object obj;
        Iterator it = ((LazyGridMeasureResult) this.state.getLayoutInfo()).visibleItemsInfo.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            int i = ((LazyGridMeasuredItem) ((LazyGridItemInfo) obj)).index;
            Integer num = (Integer) ((SnapshotMutableStateImpl) this.draggingItemIndex$delegate).getValue();
            if (num != null && i == num.intValue()) {
                break;
            }
        }
        return (LazyGridItemInfo) obj;
    }

    /* renamed from: getDraggingItemOffset-F1C5BW0$frameworks__base__packages__SystemUI__android_common__SystemUI_core, reason: not valid java name */
    public final long m798x6f1c0cc2() {
        LazyGridItemInfo draggingItemLayoutInfo = getDraggingItemLayoutInfo();
        if (draggingItemLayoutInfo == null) {
            return 0L;
        }
        long m315plusMKHz9U = Offset.m315plusMKHz9U(((Offset) ((SnapshotMutableStateImpl) this.draggingItemInitialOffset$delegate).getValue()).packedValue, ((Offset) ((SnapshotMutableStateImpl) this.draggingItemDraggedDelta$delegate).getValue()).packedValue);
        long j = ((LazyGridMeasuredItem) draggingItemLayoutInfo).offset;
        return Offset.m314minusMKHz9U(m315plusMKHz9U, (Float.floatToRawIntBits((int) (j & 4294967295L)) & 4294967295L) | (Float.floatToRawIntBits((int) (j >> 32)) << 32));
    }

    public final void onDragInterrupted$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        MutableState mutableState = this.draggingItemIndex$delegate;
        Integer num = (Integer) ((SnapshotMutableStateImpl) mutableState).getValue();
        if (num != null) {
            int intValue = num.intValue();
            MutableState mutableState2 = this.isDraggingToRemove$delegate;
            boolean booleanValue = ((Boolean) ((SnapshotMutableStateImpl) mutableState2).getValue()).booleanValue();
            ContentListState contentListState = this.contentListState;
            if (booleanValue) {
                contentListState.onRemove(intValue);
                ((SnapshotMutableStateImpl) mutableState2).setValue(Boolean.FALSE);
                this.updateDragPositionForRemove.invoke(new Offset(0L));
            }
            contentListState.onSaveList(null, null, null);
            ((SnapshotMutableStateImpl) mutableState).setValue(null);
        }
        ((SnapshotMutableStateImpl) this.draggingItemDraggedDelta$delegate).setValue(new Offset(0L));
        ((SnapshotMutableStateImpl) this.draggingItemInitialOffset$delegate).setValue(new Offset(0L));
        ((SnapshotMutableStateImpl) this.dragStartPointerOffset$delegate).setValue(new Offset(0L));
    }
}
