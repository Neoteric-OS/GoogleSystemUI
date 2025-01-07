package com.android.wm.shell.pip2.phone;

import android.os.Bundle;
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
                pipMotionHelper.cancelPhysicsAnimation();
                PhonePipMenuController phonePipMenuController = pipMotionHelper.mMenuController;
                if (phonePipMenuController.isMenuVisible()) {
                    phonePipMenuController.getClass();
                    PipMenuView pipMenuView = null;
                    pipMenuView.hideMenu(null, true, false, 2);
                    break;
                }
                break;
            default:
                if (!pipMotionHelper.mDismissalPending && !pipMotionHelper.mSpringingToTouch && !pipMotionHelper.mMagnetizedPip.getObjectStuckToTarget()) {
                    pipMotionHelper.setAnimatingToBounds(pipMotionHelper.mPipBoundsState.mMotionBoundsState.mBoundsInMotion);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("fling_bounds_change", true);
                    pipMotionHelper.mPipTransitionState.setState(4, bundle);
                    break;
                } else {
                    pipMotionHelper.settlePipBoundsAfterPhysicsAnimation(true);
                    PipPerfHintController.PipHighPerfSession pipHighPerfSession = pipMotionHelper.mPipHighPerfSession;
                    if (pipHighPerfSession != null) {
                        pipHighPerfSession.close();
                        pipMotionHelper.mPipHighPerfSession = null;
                        break;
                    }
                }
                break;
        }
    }
}
