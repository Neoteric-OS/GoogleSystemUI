package com.android.systemui.screenshot.ui;

import android.util.DisplayMetrics;
import android.view.VelocityTracker;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SwipeGestureListener {
    public final DisplayMetrics displayMetrics;
    public final Function0 onCancel;
    public final Function1 onDismiss;
    public float startX;
    public final VelocityTracker velocityTracker = VelocityTracker.obtain();
    public final ScreenshotShelfView view;

    public SwipeGestureListener(ScreenshotShelfView screenshotShelfView, Function1 function1, Function0 function0) {
        this.view = screenshotShelfView;
        this.onDismiss = function1;
        this.onCancel = function0;
        this.displayMetrics = screenshotShelfView.getResources().getDisplayMetrics();
    }
}
