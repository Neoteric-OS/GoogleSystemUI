package com.android.wm.shell.splitscreen;

import android.graphics.Rect;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SplitScreen {
    static String stageTypeToString(int i) {
        return i != -1 ? i != 0 ? i != 1 ? GenericDocument$$ExternalSyntheticOutline0.m("UNKNOWN(", ")", i) : "SIDE" : "MAIN" : "UNDEFINED";
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface SplitScreenListener {
        void onTaskStageChanged(int i, int i2, boolean z);

        default void onSplitVisibilityChanged(boolean z) {
        }

        default void onStagePositionChanged(int i, int i2) {
        }

        default void onSplitBoundsChanged(Rect rect, Rect rect2, Rect rect3) {
        }
    }
}
