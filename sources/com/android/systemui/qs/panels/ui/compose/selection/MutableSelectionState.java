package com.android.systemui.qs.panels.ui.compose.selection;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MutableSelectionState {
    public final MutableState _resizingState;
    public final MutableState _selection;
    public final Function1 onResize;
    public final Function1 onResizeEnd;
    public final MutableState resizingState$delegate;
    public final MutableState selection$delegate;

    public MutableSelectionState(Function1 function1, Function1 function12) {
        this.onResize = function1;
        this.onResizeEnd = function12;
        MutableState mutableStateOf$default = SnapshotStateKt.mutableStateOf$default(null);
        this._selection = mutableStateOf$default;
        MutableState mutableStateOf$default2 = SnapshotStateKt.mutableStateOf$default(null);
        this._resizingState = mutableStateOf$default2;
        this.selection$delegate = mutableStateOf$default;
        this.resizingState$delegate = mutableStateOf$default2;
    }

    public final void onResizingDragEnd() {
        ((SnapshotMutableStateImpl) this._resizingState).setValue(null);
        MutableState mutableState = this._selection;
        Selection selection = (Selection) ((SnapshotMutableStateImpl) mutableState).getValue();
        if (selection != null) {
            Function1 function1 = this.onResizeEnd;
            TileSpec tileSpec = selection.tileSpec;
            function1.invoke(tileSpec);
            ((SnapshotMutableStateImpl) mutableState).setValue(new Selection(tileSpec, false));
        }
    }
}
