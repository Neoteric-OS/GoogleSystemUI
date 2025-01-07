package com.android.systemui.screenshot;

import android.os.RemoteException;
import android.util.Log;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.IRemoteAnimationRunner;
import android.view.RemoteAnimationTarget;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActionIntentExecutorKt$SCREENSHOT_REMOTE_RUNNER$1 extends IRemoteAnimationRunner.Stub {
    public final void onAnimationStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
        try {
            iRemoteAnimationFinishedCallback.onAnimationFinished();
        } catch (RemoteException e) {
            Log.e("ActionIntentExecutor", "Error finishing screenshot remote animation", e);
        }
    }

    public final void onAnimationCancelled() {
    }
}
