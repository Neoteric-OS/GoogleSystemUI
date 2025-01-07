package com.android.wm.shell.back;

import android.content.Context;
import android.os.Handler;
import android.view.IRemoteAnimationRunner;
import android.view.RemoteAnimationTarget;
import android.window.IOnBackInvokedCallback;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BackAnimationRunner {
    public boolean mAnimationCancelled;
    public final IOnBackInvokedCallback mCallback;
    public final Context mContext;
    public final int mCujType;
    public final Handler mHandler;
    public final IRemoteAnimationRunner mRunner;
    public boolean mWaitingAnimation;

    public BackAnimationRunner(IOnBackInvokedCallback iOnBackInvokedCallback, IRemoteAnimationRunner iRemoteAnimationRunner, Context context, int i, Handler handler) {
        this.mCallback = iOnBackInvokedCallback;
        this.mRunner = iRemoteAnimationRunner;
        this.mCujType = i;
        this.mContext = context;
        this.mHandler = handler;
    }

    public boolean shouldMonitorCUJ(RemoteAnimationTarget[] remoteAnimationTargetArr) {
        return remoteAnimationTargetArr.length > 0 && this.mCujType != -1;
    }
}
