package com.android.systemui.unfold.progress;

import android.os.Looper;
import android.view.Choreographer;
import androidx.dynamicanimation.animation.AnimationHandler$$ExternalSyntheticLambda0;
import androidx.dynamicanimation.animation.FrameCallbackScheduler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldFrameCallbackScheduler implements FrameCallbackScheduler {
    public final Choreographer choreographer = Choreographer.getInstance();
    public final Looper looper;

    public UnfoldFrameCallbackScheduler() {
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            throw new IllegalStateException("This should be created in a thread with a looper.");
        }
        this.looper = myLooper;
    }

    @Override // androidx.dynamicanimation.animation.FrameCallbackScheduler
    public final boolean isCurrentThread() {
        return this.looper.isCurrentThread();
    }

    @Override // androidx.dynamicanimation.animation.FrameCallbackScheduler
    public final void postFrameCallback(final AnimationHandler$$ExternalSyntheticLambda0 animationHandler$$ExternalSyntheticLambda0) {
        this.choreographer.postFrameCallback(new Choreographer.FrameCallback() { // from class: com.android.systemui.unfold.progress.UnfoldFrameCallbackScheduler$postFrameCallback$1
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                AnimationHandler$$ExternalSyntheticLambda0.this.run();
            }
        });
    }
}
