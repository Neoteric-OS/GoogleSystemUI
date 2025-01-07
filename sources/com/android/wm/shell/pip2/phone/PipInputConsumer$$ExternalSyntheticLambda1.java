package com.android.wm.shell.pip2.phone;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipInputConsumer$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipInputConsumer f$0;

    public /* synthetic */ PipInputConsumer$$ExternalSyntheticLambda1(PipInputConsumer pipInputConsumer, int i) {
        this.$r8$classId = i;
        this.f$0 = pipInputConsumer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        PipInputConsumer pipInputConsumer = this.f$0;
        switch (i) {
            case 0:
                PipTouchHandler$$ExternalSyntheticLambda10 pipTouchHandler$$ExternalSyntheticLambda10 = pipInputConsumer.mRegistrationListener;
                if (pipTouchHandler$$ExternalSyntheticLambda10 != null) {
                    boolean z = pipInputConsumer.mInputEventReceiver != null;
                    PipTouchHandler pipTouchHandler = pipTouchHandler$$ExternalSyntheticLambda10.f$0;
                    if (!z) {
                        pipTouchHandler.mAccessibilityManager.setPictureInPictureActionReplacingConnection(null);
                    }
                    if (!z) {
                        if (pipTouchHandler.mTouchState.mIsUserInteracting) {
                            PipDismissTargetHandler pipDismissTargetHandler = pipTouchHandler.mPipDismissTargetHandler;
                            if (pipDismissTargetHandler.mTargetViewContainer.getParent() != null) {
                                pipDismissTargetHandler.mWindowManager.removeViewImmediate(pipDismissTargetHandler.mTargetViewContainer);
                                break;
                            }
                        }
                    } else {
                        pipTouchHandler.getClass();
                        break;
                    }
                }
                break;
            default:
                PipTouchHandler$$ExternalSyntheticLambda10 pipTouchHandler$$ExternalSyntheticLambda102 = pipInputConsumer.mRegistrationListener;
                if (pipTouchHandler$$ExternalSyntheticLambda102 != null) {
                    PipTouchHandler pipTouchHandler2 = pipTouchHandler$$ExternalSyntheticLambda102.f$0;
                    pipTouchHandler2.mAccessibilityManager.setPictureInPictureActionReplacingConnection(null);
                    if (pipTouchHandler2.mTouchState.mIsUserInteracting) {
                        PipDismissTargetHandler pipDismissTargetHandler2 = pipTouchHandler2.mPipDismissTargetHandler;
                        if (pipDismissTargetHandler2.mTargetViewContainer.getParent() != null) {
                            pipDismissTargetHandler2.mWindowManager.removeViewImmediate(pipDismissTargetHandler2.mTargetViewContainer);
                            break;
                        }
                    }
                }
                break;
        }
    }
}
