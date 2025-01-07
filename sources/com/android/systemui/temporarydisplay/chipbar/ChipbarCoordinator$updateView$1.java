package com.android.systemui.temporarydisplay.chipbar;

import android.view.MotionEvent;
import com.android.systemui.Gefingerpoken;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChipbarCoordinator$updateView$1 implements Gefingerpoken {
    public final /* synthetic */ ChipbarCoordinator this$0;

    public ChipbarCoordinator$updateView$1(ChipbarCoordinator chipbarCoordinator) {
        this.this$0 = chipbarCoordinator;
    }

    @Override // com.android.systemui.Gefingerpoken
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        this.this$0.falsingCollector.onTouchEvent(motionEvent);
        return false;
    }
}
