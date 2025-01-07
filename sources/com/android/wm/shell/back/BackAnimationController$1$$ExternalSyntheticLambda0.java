package com.android.wm.shell.back;

import android.window.BackNavigationInfo;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.back.BackAnimationController;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BackAnimationController$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ BackAnimationController$1$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                BackAnimationController.AnonymousClass1 anonymousClass1 = (BackAnimationController.AnonymousClass1) obj;
                BackAnimationController backAnimationController = BackAnimationController.this;
                if (backAnimationController.mBackGestureStarted && !backAnimationController.mPostCommitAnimationInProgress) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[2]) {
                        ProtoLogImpl_411527699.i(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -4676570246121374196L, 0, null);
                    }
                    BackAnimationController.this.setTriggerBack(false);
                    BackAnimationController.this.resetTouchTracker();
                    BackAnimationController backAnimationController2 = BackAnimationController.this;
                    ((HandlerExecutor) backAnimationController2.mShellExecutor).removeCallbacks(backAnimationController2.mAnimationTimeoutRunnable);
                    break;
                }
                break;
            case 1:
                BackAnimationController.AnonymousClass3 anonymousClass3 = (BackAnimationController.AnonymousClass3) obj;
                BackAnimationController backAnimationController3 = BackAnimationController.this;
                BackNavigationInfo backNavigationInfo = backAnimationController3.mBackNavigationInfo;
                BackAnimationRunner backAnimationRunner = (BackAnimationRunner) backAnimationController3.mShellBackAnimationRegistry.mAnimationDefinition.get(backNavigationInfo != null ? backNavigationInfo.getType() : backAnimationController3.mPreviousNavigationType);
                if (backAnimationRunner != null) {
                    backAnimationRunner.mWaitingAnimation = false;
                    backAnimationRunner.mAnimationCancelled = true;
                    BackAnimationController backAnimationController4 = BackAnimationController.this;
                    if (!backAnimationController4.mBackGestureStarted) {
                        backAnimationController4.invokeOrCancelBack(backAnimationController4.mCurrentTracker);
                        break;
                    }
                }
                break;
            default:
                BackAnimationController.BackTransitionHandler backTransitionHandler = (BackAnimationController.BackTransitionHandler) obj;
                backTransitionHandler.applyFinishOpenTransition();
                backTransitionHandler.mCloseTransitionRequested = false;
                break;
        }
    }
}
