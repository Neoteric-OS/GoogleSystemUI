package com.android.systemui.temporarydisplay.chipbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChipbarRootView extends FrameLayout {
    public ChipbarCoordinator$updateView$1 touchHandler;

    public ChipbarRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        ChipbarCoordinator$updateView$1 chipbarCoordinator$updateView$1 = this.touchHandler;
        if (chipbarCoordinator$updateView$1 != null) {
            chipbarCoordinator$updateView$1.onTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
