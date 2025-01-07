package com.android.wm.shell.pip.phone;

import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipPerfHintController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipMotionHelper$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipMotionHelper f$0;

    public /* synthetic */ PipMotionHelper$$ExternalSyntheticLambda0(PipMotionHelper pipMotionHelper, int i) {
        this.$r8$classId = i;
        this.f$0 = pipMotionHelper;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        PipMotionHelper pipMotionHelper = this.f$0;
        switch (i) {
            case 0:
                pipMotionHelper.dismissPip();
                break;
            default:
                boolean z = pipMotionHelper.mDismissalPending;
                PipBoundsState pipBoundsState = pipMotionHelper.mPipBoundsState;
                if (!z && !pipMotionHelper.mSpringingToTouch && !pipMotionHelper.mMagnetizedPip.getObjectStuckToTarget()) {
                    pipBoundsState.setBounds(pipBoundsState.mMotionBoundsState.mBoundsInMotion);
                    pipMotionHelper.mFloatingContentCoordinator.onContentMoved(pipMotionHelper);
                    pipBoundsState.mMotionBoundsState.mBoundsInMotion.setEmpty();
                    if (!pipMotionHelper.mDismissalPending) {
                        pipMotionHelper.mPipTaskOrganizer.scheduleFinishResizePip(pipBoundsState.getBounds(), 0, null);
                    }
                }
                pipBoundsState.mMotionBoundsState.mAnimatingToBounds.setEmpty();
                pipMotionHelper.mSpringingToTouch = false;
                pipMotionHelper.mDismissalPending = false;
                PipPerfHintController.PipHighPerfSession pipHighPerfSession = pipMotionHelper.mPipHighPerfSession;
                if (pipHighPerfSession != null) {
                    pipHighPerfSession.close();
                    pipMotionHelper.mPipHighPerfSession = null;
                    break;
                }
                break;
        }
    }
}
