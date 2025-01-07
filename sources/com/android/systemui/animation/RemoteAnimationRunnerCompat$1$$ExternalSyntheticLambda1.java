package com.android.systemui.animation;

import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.view.SurfaceControl;
import android.window.IRemoteTransitionFinishedCallback;
import android.window.TransitionInfo;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.shared.CounterRotator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class RemoteAnimationRunnerCompat$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ CounterRotator f$0;
    public final /* synthetic */ CounterRotator f$1;
    public final /* synthetic */ TransitionInfo f$2;
    public final /* synthetic */ ArrayMap f$3;
    public final /* synthetic */ IRemoteTransitionFinishedCallback f$4;

    public /* synthetic */ RemoteAnimationRunnerCompat$1$$ExternalSyntheticLambda1(CounterRotator counterRotator, CounterRotator counterRotator2, TransitionInfo transitionInfo, ArrayMap arrayMap, IRemoteTransitionFinishedCallback iRemoteTransitionFinishedCallback) {
        this.f$0 = counterRotator;
        this.f$1 = counterRotator2;
        this.f$2 = transitionInfo;
        this.f$3 = arrayMap;
        this.f$4 = iRemoteTransitionFinishedCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CounterRotator counterRotator = this.f$0;
        CounterRotator counterRotator2 = this.f$1;
        TransitionInfo transitionInfo = this.f$2;
        ArrayMap arrayMap = this.f$3;
        IRemoteTransitionFinishedCallback iRemoteTransitionFinishedCallback = this.f$4;
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        SurfaceControl surfaceControl = counterRotator.mSurface;
        if (surfaceControl != null) {
            transaction.remove(surfaceControl);
        }
        SurfaceControl surfaceControl2 = counterRotator2.mSurface;
        if (surfaceControl2 != null) {
            transaction.remove(surfaceControl2);
        }
        transitionInfo.releaseAllSurfaces();
        arrayMap.clear();
        try {
            iRemoteTransitionFinishedCallback.onTransitionFinished((WindowContainerTransaction) null, transaction);
            transaction.close();
        } catch (RemoteException e) {
            Log.e("RemoteAnimRunnerCompat", "Failed to call app controlled animation finished callback", e);
        }
    }
}
