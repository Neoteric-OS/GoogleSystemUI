package com.android.wm.shell.pip2.phone;

import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda1;
import com.android.wm.shell.pip2.phone.PipController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipController$PipImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipController.PipImpl f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ PipController$PipImpl$$ExternalSyntheticLambda0(PipController.PipImpl pipImpl, EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda1) {
        this.$r8$classId = 2;
        this.f$0 = pipImpl;
        this.f$1 = edgeBackGestureHandler$$ExternalSyntheticLambda1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                PipController.this.mPipBoundsState.addPipExclusionBoundsChangeCallback((Consumer) this.f$1);
                break;
            case 1:
                PipController.this.mPipBoundsState.mOnPipExclusionBoundsChangeCallbacks.remove((Consumer) this.f$1);
                break;
            default:
                PipController.PipImpl pipImpl = this.f$0;
                EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda1 = (EdgeBackGestureHandler$$ExternalSyntheticLambda1) this.f$1;
                PipController pipController = PipController.this;
                pipController.mOnIsInPipStateChangedListener = edgeBackGestureHandler$$ExternalSyntheticLambda1;
                if (edgeBackGestureHandler$$ExternalSyntheticLambda1 != null) {
                    int i = pipController.mPipTransitionState.mState;
                    edgeBackGestureHandler$$ExternalSyntheticLambda1.accept(Boolean.valueOf(i > 2 && i < 7));
                    break;
                }
                break;
        }
    }

    public /* synthetic */ PipController$PipImpl$$ExternalSyntheticLambda0(PipController.PipImpl pipImpl, Consumer consumer, int i) {
        this.$r8$classId = i;
        this.f$0 = pipImpl;
        this.f$1 = consumer;
    }
}
