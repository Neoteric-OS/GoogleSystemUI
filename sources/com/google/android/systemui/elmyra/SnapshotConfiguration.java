package com.google.android.systemui.elmyra;

import android.content.Context;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SnapshotConfiguration {
    public final int mCompleteGestures;
    public final int mIncompleteGestures;
    public final int mSnapshotDelayAfterGesture;

    public SnapshotConfiguration(Context context) {
        this.mCompleteGestures = context.getResources().getInteger(R.integer.elmyra_snapshot_logger_gesture_size);
        this.mIncompleteGestures = context.getResources().getInteger(R.integer.elmyra_snapshot_logger_incomplete_gesture_size);
        this.mSnapshotDelayAfterGesture = context.getResources().getInteger(R.integer.elmyra_snapshot_gesture_delay_ms);
    }
}
