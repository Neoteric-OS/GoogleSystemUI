package com.android.systemui.keyguard;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.util.Log;
import android.util.Slog;
import com.android.internal.policy.IKeyguardStateCallback;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardViewMediator$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardViewMediator f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ KeyguardViewMediator$$ExternalSyntheticLambda0(KeyguardViewMediator keyguardViewMediator, boolean z, int i) {
        this.$r8$classId = i;
        this.f$0 = keyguardViewMediator;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                KeyguardViewMediator keyguardViewMediator = this.f$0;
                boolean z = this.f$1;
                if (!keyguardViewMediator.mPM.isInteractive() && !keyguardViewMediator.mPendingLock) {
                    Log.e("KeyguardViewMediator", "exitKeyguardAndFinishSurfaceBehindRemoteAnimation#postAfterTraversal: mPM.isInteractive()=" + keyguardViewMediator.mPM.isInteractive() + " mPendingLock=" + keyguardViewMediator.mPendingLock + ". One of these being false means we re-locked the device during unlock. Do not proceed to finish keyguard exit and unlock.");
                    keyguardViewMediator.doKeyguardLocked(null);
                    keyguardViewMediator.finishSurfaceBehindRemoteAnimation(true);
                    keyguardViewMediator.setShowingLocked("exitKeyguardAndFinishSurfaceBehindRemoteAnimation - relocked", true, true);
                    break;
                } else {
                    keyguardViewMediator.onKeyguardExitFinished("exitKeyguardAndFinishSurfaceBehindRemoteAnimation");
                    KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) keyguardViewMediator.mKeyguardStateController;
                    if (keyguardStateControllerImpl.mDismissingFromTouch || z) {
                        Log.d("KeyguardViewMediator", "onKeyguardExitRemoteAnimationFinished#hideKeyguardViewAfterRemoteAnimation");
                        KeyguardUnlockAnimationController keyguardUnlockAnimationController = (KeyguardUnlockAnimationController) keyguardViewMediator.mKeyguardUnlockAnimationControllerLazy.get();
                        if (((KeyguardStateControllerImpl) keyguardUnlockAnimationController.keyguardStateController).mShowing) {
                            keyguardUnlockAnimationController.keyguardViewController.hide(keyguardUnlockAnimationController.surfaceBehindRemoteAnimationStartTime, 0L);
                        } else {
                            Log.i("KeyguardUnlock", "#hideKeyguardViewAfterRemoteAnimation called when keyguard view is not showing. Ignoring...");
                        }
                    } else {
                        Log.d("KeyguardViewMediator", "skip hideKeyguardViewAfterRemoteAnimation dismissFromSwipe=" + keyguardStateControllerImpl.mDismissingFromTouch + " wasShowing=" + z);
                    }
                    keyguardViewMediator.finishSurfaceBehindRemoteAnimation(false);
                    keyguardViewMediator.mUpdateMonitor.mHandler.sendEmptyMessage(346);
                    break;
                }
                break;
            default:
                KeyguardViewMediator keyguardViewMediator2 = this.f$0;
                boolean z2 = this.f$1;
                for (int size = keyguardViewMediator2.mKeyguardStateCallbacks.size() - 1; size >= 0; size--) {
                    IKeyguardStateCallback iKeyguardStateCallback = (IKeyguardStateCallback) keyguardViewMediator2.mKeyguardStateCallbacks.get(size);
                    try {
                        iKeyguardStateCallback.onShowingStateChanged(z2, keyguardViewMediator2.mSelectedUserInteractor.getSelectedUserId());
                    } catch (RemoteException e) {
                        Slog.w("KeyguardViewMediator", "Failed to call onShowingStateChanged", e);
                        if (e instanceof DeadObjectException) {
                            keyguardViewMediator2.mKeyguardStateCallbacks.remove(iKeyguardStateCallback);
                        }
                    }
                }
                break;
        }
    }
}
