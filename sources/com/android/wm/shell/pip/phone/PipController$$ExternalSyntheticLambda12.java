package com.android.wm.shell.pip.phone;

import android.graphics.Point;
import android.graphics.Rect;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.TabletopModeController;
import com.android.wm.shell.common.pip.PipBoundsState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipController$$ExternalSyntheticLambda12 {
    public final /* synthetic */ PipController f$0;

    public final void onTabletopModeChanged(boolean z) {
        PipController pipController = this.f$0;
        PipBoundsState pipBoundsState = pipController.mPipBoundsState;
        if (!z) {
            pipBoundsState.setNamedUnrestrictedKeepClearArea(1, null);
            return;
        }
        Rect displayBounds = pipBoundsState.mPipDisplayLayoutState.getDisplayBounds();
        pipController.mTabletopModeController.getClass();
        if (TabletopModeController.PREFER_TOP_HALF_IN_TABLETOP) {
            pipBoundsState.setNamedUnrestrictedKeepClearArea(1, new Rect(displayBounds.left, displayBounds.centerY(), displayBounds.right, displayBounds.bottom));
        } else {
            pipBoundsState.setNamedUnrestrictedKeepClearArea(1, new Rect(displayBounds.left, displayBounds.top, displayBounds.right, displayBounds.centerY()));
        }
        if (pipController.mPipTransitionState.hasEnteredPip()) {
            Rect bounds = pipBoundsState.getBounds();
            Point point = pipController.mPipDisplayLayoutState.mScreenEdgeInsets;
            if ((point.y * 2) + bounds.height() > displayBounds.height() / 2) {
                return;
            }
            HandlerExecutor handlerExecutor = (HandlerExecutor) pipController.mMainExecutor;
            PipController$$ExternalSyntheticLambda3 pipController$$ExternalSyntheticLambda3 = pipController.mMovePipInResponseToKeepClearAreasChangeCallback;
            handlerExecutor.removeCallbacks(pipController$$ExternalSyntheticLambda3);
            handlerExecutor.execute(pipController$$ExternalSyntheticLambda3);
        }
    }
}
