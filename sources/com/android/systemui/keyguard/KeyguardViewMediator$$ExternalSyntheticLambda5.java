package com.android.systemui.keyguard;

import android.os.RemoteException;
import android.util.Log;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.SyncRtSurfaceTransactionApplier;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardViewMediator$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardViewMediator f$0;

    public /* synthetic */ KeyguardViewMediator$$ExternalSyntheticLambda5(KeyguardViewMediator keyguardViewMediator, int i) {
        this.$r8$classId = i;
        this.f$0 = keyguardViewMediator;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        KeyguardViewMediator keyguardViewMediator = this.f$0;
        switch (i) {
            case 0:
                keyguardViewMediator.mWallpaperSupportsAmbientMode = ((Boolean) obj).booleanValue();
                break;
            case 1:
                keyguardViewMediator.getClass();
                keyguardViewMediator.mShowCommunalWhenUnoccluding = ((Boolean) obj).booleanValue();
                break;
            case 2:
                IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback = keyguardViewMediator.mUnoccludeFinishedCallback;
                if (iRemoteAnimationFinishedCallback != null) {
                    try {
                        iRemoteAnimationFinishedCallback.onAnimationFinished();
                        keyguardViewMediator.mUnoccludeFinishedCallback = null;
                    } catch (RemoteException e) {
                        Log.e("KeyguardViewMediator", "Wasn't able to callback", e);
                    }
                    keyguardViewMediator.mInteractionJankMonitor.end(64);
                    break;
                }
                break;
            default:
                Float f = (Float) obj;
                if (keyguardViewMediator.mRemoteAnimationTarget != null) {
                    new SyncRtSurfaceTransactionApplier(((StatusBarKeyguardViewManager) keyguardViewMediator.mKeyguardViewControllerLazy.get()).getViewRootImpl().getView()).scheduleApply(new SyncRtSurfaceTransactionApplier.SurfaceParams[]{new SyncRtSurfaceTransactionApplier.SurfaceParams.Builder(keyguardViewMediator.mRemoteAnimationTarget.leash).withAlpha(f.floatValue()).build()});
                    break;
                } else {
                    Log.e("KeyguardViewMediator", "Attempting to set alpha on null animation target");
                    break;
                }
        }
    }
}
