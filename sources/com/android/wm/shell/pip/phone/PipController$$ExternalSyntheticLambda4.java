package com.android.wm.shell.pip.phone;

import android.window.WindowContainerTransaction;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda1;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.pip.PipDisplayLayoutState;
import com.android.wm.shell.pip.PipTransitionState;
import com.android.wm.shell.pip.phone.PipController;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipController$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ PipController$$ExternalSyntheticLambda4(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                PipController pipController = (PipController) this.f$0;
                DisplayLayout displayLayout = (DisplayLayout) this.f$1;
                pipController.getClass();
                boolean z = Transitions.ENABLE_SHELL_TRANSITIONS;
                PipDisplayLayoutState pipDisplayLayoutState = pipController.mPipDisplayLayoutState;
                boolean z2 = z && pipDisplayLayoutState.getDisplayLayout().mRotation != displayLayout.mRotation;
                pipDisplayLayoutState.mDisplayLayout.set(displayLayout);
                WindowContainerTransaction windowContainerTransaction = z2 ? new WindowContainerTransaction() : null;
                pipController.updateMovementBounds(null, z2, false, false, windowContainerTransaction);
                if (windowContainerTransaction != null) {
                    pipController.mPipTaskOrganizer.applyFinishBoundsResize(windowContainerTransaction, 1, false);
                    break;
                }
                break;
            default:
                PipController.PipImpl pipImpl = (PipController.PipImpl) this.f$0;
                EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda1 = (EdgeBackGestureHandler$$ExternalSyntheticLambda1) this.f$1;
                PipController pipController2 = PipController.this;
                pipController2.mOnIsInPipStateChangedListener = edgeBackGestureHandler$$ExternalSyntheticLambda1;
                if (edgeBackGestureHandler$$ExternalSyntheticLambda1 != null) {
                    edgeBackGestureHandler$$ExternalSyntheticLambda1.accept(Boolean.valueOf(PipTransitionState.isInPip(pipController2.mPipTransitionState.mState)));
                    break;
                }
                break;
        }
    }
}
