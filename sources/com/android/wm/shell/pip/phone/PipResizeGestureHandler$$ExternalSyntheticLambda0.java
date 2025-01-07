package com.android.wm.shell.pip.phone;

import android.graphics.Rect;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipResizeGestureHandler$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipResizeGestureHandler f$0;

    public /* synthetic */ PipResizeGestureHandler$$ExternalSyntheticLambda0(PipResizeGestureHandler pipResizeGestureHandler, int i) {
        this.$r8$classId = i;
        this.f$0 = pipResizeGestureHandler;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        PipResizeGestureHandler pipResizeGestureHandler = this.f$0;
        switch (i) {
            case 0:
                pipResizeGestureHandler.getClass();
                break;
            default:
                pipResizeGestureHandler.mUserResizeBounds.set((Rect) obj);
                pipResizeGestureHandler.mMotionHelper.synchronizePinnedStackBounds();
                pipResizeGestureHandler.mUpdateMovementBoundsRunnable.run();
                pipResizeGestureHandler.mAngle = 0.0f;
                pipResizeGestureHandler.mOngoingPinchToResize = false;
                pipResizeGestureHandler.mAllowGesture = false;
                pipResizeGestureHandler.mThresholdCrossed = false;
                break;
        }
    }
}
