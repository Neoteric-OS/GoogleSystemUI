package com.android.wm.shell.pip2.phone;

import android.os.Looper;
import com.android.wm.shell.common.pip.PipPerfHintController;
import com.android.wm.shell.pip2.phone.PipResizeGestureHandler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipResizeGestureHandler$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipResizeGestureHandler f$0;

    public /* synthetic */ PipResizeGestureHandler$$ExternalSyntheticLambda1(PipResizeGestureHandler pipResizeGestureHandler, int i) {
        this.$r8$classId = i;
        this.f$0 = pipResizeGestureHandler;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        PipResizeGestureHandler pipResizeGestureHandler = this.f$0;
        switch (i) {
            case 0:
                pipResizeGestureHandler.getClass();
                pipResizeGestureHandler.mInputEventReceiver = new PipResizeGestureHandler.PipResizeInputEventReceiver(pipResizeGestureHandler, pipResizeGestureHandler.mInputMonitor.getInputChannel(), Looper.myLooper());
                break;
            default:
                pipResizeGestureHandler.mUserResizeBounds.set(pipResizeGestureHandler.mLastResizeBounds);
                pipResizeGestureHandler.mAngle = 0.0f;
                pipResizeGestureHandler.mOngoingPinchToResize = false;
                pipResizeGestureHandler.mAllowGesture = false;
                pipResizeGestureHandler.mThresholdCrossed = false;
                PipPerfHintController.PipHighPerfSession pipHighPerfSession = pipResizeGestureHandler.mPipHighPerfSession;
                if (pipHighPerfSession != null) {
                    pipHighPerfSession.close();
                    pipResizeGestureHandler.mPipHighPerfSession = null;
                }
                pipResizeGestureHandler.mPipScheduler.scheduleFinishResizePip(pipResizeGestureHandler.mLastResizeBounds, true);
                break;
        }
    }
}
