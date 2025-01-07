package com.android.systemui.statusbar.gesture;

import android.content.Context;
import android.view.MotionEvent;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SwipeStatusBarAwayGestureHandler extends SwipeUpGestureHandler {
    public final StatusBarWindowControllerImpl statusBarWindowController;

    public SwipeStatusBarAwayGestureHandler(Context context, DisplayTracker displayTracker, SwipeUpGestureLogger swipeUpGestureLogger, StatusBarWindowControllerImpl statusBarWindowControllerImpl) {
        super(context, swipeUpGestureLogger, "SwipeStatusBarAway");
        this.statusBarWindowController = statusBarWindowControllerImpl;
    }

    @Override // com.android.systemui.statusbar.gesture.SwipeUpGestureHandler
    public final boolean startOfGestureIsWithinBounds(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        StatusBarWindowControllerImpl statusBarWindowControllerImpl = this.statusBarWindowController;
        return y >= ((float) statusBarWindowControllerImpl.mBarHeight) && motionEvent.getY() <= ((float) (statusBarWindowControllerImpl.mBarHeight * 3));
    }
}
