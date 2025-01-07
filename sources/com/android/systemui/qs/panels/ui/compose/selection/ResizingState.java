package com.android.systemui.qs.panels.ui.compose.selection;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ResizingState {
    public final Function0 onResize;
    public boolean passedThreshold;
    public float totalOffset;
    public final MutableIntState width$delegate;
    public final TileWidths widths;

    public ResizingState(TileWidths tileWidths, Function0 function0) {
        this.widths = tileWidths;
        this.onResize = function0;
        MutableIntState mutableIntStateOf = SnapshotIntStateKt.mutableIntStateOf(tileWidths.base);
        this.width$delegate = mutableIntStateOf;
        int intValue = ((SnapshotMutableIntStateImpl) mutableIntStateOf).getIntValue();
        int i = tileWidths.min;
        this.passedThreshold = RangesKt.coerceIn(((float) (intValue - i)) / ((float) (tileWidths.max - i)), 0.0f, 1.0f) >= 0.25f;
    }
}
