package com.android.wm.shell.pip;

import com.android.wm.shell.pip.phone.PipController;
import com.android.wm.shell.pip.phone.PipController$$ExternalSyntheticLambda7;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipTransitionState {
    public boolean mInSwipePipToHomeTransition;
    public final List mOnPipTransitionStateChangedListeners = new ArrayList();
    public int mState = 0;

    public static boolean isInPip(int i) {
        return i >= 1 && i != 5;
    }

    public final boolean hasEnteredPip() {
        return this.mState == 4;
    }

    public final void setTransitionState(int i) {
        if (this.mState != i) {
            for (int i2 = 0; i2 < ((ArrayList) this.mOnPipTransitionStateChangedListeners).size(); i2++) {
                PipController$$ExternalSyntheticLambda7 pipController$$ExternalSyntheticLambda7 = (PipController$$ExternalSyntheticLambda7) ((ArrayList) this.mOnPipTransitionStateChangedListeners).get(i2);
                int i3 = this.mState;
                PipController pipController = pipController$$ExternalSyntheticLambda7.f$0;
                if (pipController.mOnIsInPipStateChangedListener != null) {
                    boolean isInPip = isInPip(i3);
                    boolean isInPip2 = isInPip(i);
                    if (isInPip2 != isInPip) {
                        pipController.mOnIsInPipStateChangedListener.accept(Boolean.valueOf(isInPip2));
                    }
                }
            }
            this.mState = i;
        }
    }

    public final String toString() {
        String str;
        int i = this.mState;
        if (i == 0) {
            str = "undefined";
        } else if (i == 1) {
            str = "task-appeared";
        } else if (i == 2) {
            str = "entry-scheduled";
        } else if (i == 3) {
            str = "entering-pip";
        } else if (i == 4) {
            str = "entered-pip";
        } else {
            if (i != 5) {
                throw new IllegalStateException("Unknown state: " + this.mState);
            }
            str = "exiting-pip";
        }
        return "PipTransitionState(mState=" + str + ", mInSwipePipToHomeTransition=" + this.mInSwipePipToHomeTransition + ")";
    }
}
