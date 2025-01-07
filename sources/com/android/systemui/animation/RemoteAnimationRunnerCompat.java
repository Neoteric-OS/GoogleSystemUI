package com.android.systemui.animation;

import android.os.IBinder;
import android.util.ArrayMap;
import android.view.IRemoteAnimationRunner;
import android.view.SurfaceControl;
import android.window.IRemoteTransitionFinishedCallback;
import android.window.RemoteTransitionStub;
import android.window.TransitionInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RemoteAnimationRunnerCompat extends IRemoteAnimationRunner.Stub {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.animation.RemoteAnimationRunnerCompat$1, reason: invalid class name */
    public final class AnonymousClass1 extends RemoteTransitionStub {
        public final ArrayMap mFinishRunnables = new ArrayMap();
        public final /* synthetic */ IRemoteAnimationRunner val$runner;

        public AnonymousClass1(IRemoteAnimationRunner iRemoteAnimationRunner) {
            this.val$runner = iRemoteAnimationRunner;
        }

        public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, IRemoteTransitionFinishedCallback iRemoteTransitionFinishedCallback) {
            Runnable runnable;
            synchronized (this.mFinishRunnables) {
                runnable = (Runnable) this.mFinishRunnables.remove(iBinder2);
            }
            transaction.close();
            transitionInfo.releaseAllSurfaces();
            if (runnable == null) {
                return;
            }
            this.val$runner.onAnimationCancelled();
            runnable.run();
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x0251 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void startAnimation(final android.os.IBinder r26, android.window.TransitionInfo r27, android.view.SurfaceControl.Transaction r28, android.window.IRemoteTransitionFinishedCallback r29) {
            /*
                Method dump skipped, instructions count: 619
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.animation.RemoteAnimationRunnerCompat.AnonymousClass1.startAnimation(android.os.IBinder, android.window.TransitionInfo, android.view.SurfaceControl$Transaction, android.window.IRemoteTransitionFinishedCallback):void");
        }
    }
}
