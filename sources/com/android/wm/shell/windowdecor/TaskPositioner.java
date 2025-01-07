package com.android.wm.shell.windowdecor;

import android.graphics.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface TaskPositioner {
    boolean isResizingOrAnimating();

    Rect onDragPositioningEnd(float f, float f2);

    Rect onDragPositioningMove(float f, float f2);

    Rect onDragPositioningStart(int i, float f, float f2);
}
