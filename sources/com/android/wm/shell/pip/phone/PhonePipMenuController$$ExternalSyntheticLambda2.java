package com.android.wm.shell.pip.phone;

import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.pip.phone.PipTouchHandler;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PhonePipMenuController$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        PipTouchHandler.PipMenuListener pipMenuListener = (PipTouchHandler.PipMenuListener) obj;
        switch (this.$r8$classId) {
            case 0:
                PipTouchHandler pipTouchHandler = PipTouchHandler.this;
                PipTouchState pipTouchState = pipTouchHandler.mTouchState;
                pipTouchState.mIsWaitingForDoubleTap = false;
                ((HandlerExecutor) pipTouchState.mMainExecutor).removeCallbacks(pipTouchState.mDoubleTapTimeoutCallback);
                pipTouchHandler.mMotionHelper.dismissPip();
                break;
            case 1:
                PipTouchHandler pipTouchHandler2 = PipTouchHandler.this;
                pipTouchHandler2.mMenuController.showMenuInternal(1, pipTouchHandler2.mPipBoundsState.getBounds(), true, pipTouchHandler2.willResizeMenu(), false);
                break;
            default:
                PipTouchHandler.this.mMotionHelper.expandLeavePip$1(false, false);
                break;
        }
    }
}
