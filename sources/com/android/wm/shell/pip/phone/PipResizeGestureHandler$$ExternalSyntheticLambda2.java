package com.android.wm.shell.pip.phone;

import android.os.Looper;
import com.android.wm.shell.pip.phone.PipResizeGestureHandler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipResizeGestureHandler$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipResizeGestureHandler f$0;

    public /* synthetic */ PipResizeGestureHandler$$ExternalSyntheticLambda2(PipResizeGestureHandler pipResizeGestureHandler, int i) {
        this.$r8$classId = i;
        this.f$0 = pipResizeGestureHandler;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        PipResizeGestureHandler pipResizeGestureHandler = this.f$0;
        switch (i) {
            case 0:
                pipResizeGestureHandler.mPipTouchState.mAllowInputEvents = true;
                break;
            default:
                pipResizeGestureHandler.getClass();
                pipResizeGestureHandler.mInputEventReceiver = new PipResizeGestureHandler.PipResizeInputEventReceiver(pipResizeGestureHandler, pipResizeGestureHandler.mInputMonitor.getInputChannel(), Looper.myLooper());
                break;
        }
    }
}
