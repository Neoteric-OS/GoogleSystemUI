package com.android.systemui.clipboardoverlay;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.IRemoteAnimationRunner;
import android.view.RemoteAnimationTarget;
import com.android.systemui.settings.DisplayTracker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClipboardTransitionExecutor {
    public final ClipboardTransitionExecutor$NULL_ACTIVITY_TRANSITION$1 NULL_ACTIVITY_TRANSITION;

    public ClipboardTransitionExecutor(Context context, DisplayTracker displayTracker) {
        new IRemoteAnimationRunner.Stub() { // from class: com.android.systemui.clipboardoverlay.ClipboardTransitionExecutor$NULL_ACTIVITY_TRANSITION$1
            public final void onAnimationStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
                try {
                    iRemoteAnimationFinishedCallback.onAnimationFinished();
                } catch (RemoteException e) {
                    ClipboardTransitionExecutor.this.getClass();
                    Log.e("ClipboardTransitionExec", "Error finishing screenshot remote animation", e);
                }
            }

            public final void onAnimationCancelled() {
            }
        };
    }
}
