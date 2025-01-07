package com.android.wm.shell.common.pip;

import android.graphics.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PipDoubleTapHelper {
    public static int nextSizeSpec(PipBoundsState pipBoundsState, Rect rect) {
        boolean z = pipBoundsState.getBounds().width() == pipBoundsState.mMaxSize.x;
        boolean z2 = pipBoundsState.getBounds().width() == pipBoundsState.mNormalBounds.width() && pipBoundsState.getBounds().height() == pipBoundsState.mNormalBounds.height();
        if (z2 && rect.width() == pipBoundsState.mNormalBounds.width()) {
            return 1;
        }
        if (z && rect.width() == pipBoundsState.mMaxSize.x) {
            return 0;
        }
        if (z2 || z) {
            return 2;
        }
        return pipBoundsState.getBounds().width() > (pipBoundsState.mMaxSize.x + pipBoundsState.mMinSize.x) / 2 ? 0 : 1;
    }
}
