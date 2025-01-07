package com.android.wm.shell.pip2.phone;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceControl;
import android.window.WindowContainerToken;
import com.android.internal.util.Preconditions;
import com.android.wm.shell.pip2.phone.PipTransitionState;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipTransitionState {
    public boolean mInSwipePipToHomeTransition;
    public final Handler mMainHandler;
    public Runnable mOnIdlePipTransitionStateRunnable;
    public SurfaceControl mPinnedTaskLeash;
    public WindowContainerToken mPipTaskToken;
    public int mState;
    public SurfaceControl mSwipePipToHomeOverlay;
    public final Rect mSwipePipToHomeAppBounds = new Rect();
    public final List mCallbacks = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface PipTransitionStateChangedListener {
        void onPipTransitionStateChanged(int i, int i2, Bundle bundle);
    }

    public PipTransitionState(Handler handler) {
        this.mMainHandler = handler;
    }

    public static String stateToString(int i) {
        switch (i) {
            case 0:
                return "undefined";
            case 1:
                return "swiping_to_pip";
            case 2:
                return "entering-pip";
            case 3:
                return "entered-pip";
            case 4:
                return "scheduled_bounds_change";
            case 5:
                return "changing-bounds";
            case 6:
                return "changed-bounds";
            case 7:
                return "exiting-pip";
            case 8:
                return "exited-pip";
            default:
                throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unknown state: "));
        }
    }

    public final void addPipTransitionStateChangedListener(PipTransitionStateChangedListener pipTransitionStateChangedListener) {
        if (this.mCallbacks.contains(pipTransitionStateChangedListener)) {
            return;
        }
        this.mCallbacks.add(pipTransitionStateChangedListener);
    }

    public final void setState(final int i, final Bundle bundle) {
        if (i == 2 || i == 1 || i == 4 || i == 5) {
            Preconditions.checkArgument((bundle == null || bundle.isEmpty()) ? false : true, "No extra bundle for " + stateToString(i) + " state.");
        }
        final int i2 = this.mState;
        if (i2 != i) {
            this.mState = i;
            ((ArrayList) this.mCallbacks).forEach(new Consumer() { // from class: com.android.wm.shell.pip2.phone.PipTransitionState$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((PipTransitionState.PipTransitionStateChangedListener) obj).onPipTransitionStateChanged(i2, i, bundle);
                }
            });
        }
        Runnable runnable = this.mOnIdlePipTransitionStateRunnable;
        if (runnable != null) {
            int i3 = this.mState;
            if (i3 == 3 || i3 == 6) {
                runnable.run();
                this.mOnIdlePipTransitionStateRunnable = null;
            }
        }
    }

    public final String toString() {
        return "PipTransitionState(mState=" + stateToString(this.mState) + ", mInSwipePipToHomeTransition=" + this.mInSwipePipToHomeTransition + ")";
    }
}
