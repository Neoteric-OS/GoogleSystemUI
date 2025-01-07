package com.android.systemui.temporarydisplay.chipbar;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.time.Duration;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChipbarCoordinator$updateView$2 extends View.AccessibilityDelegate {
    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        accessibilityNodeInfo.setMinDurationBetweenContentChanges(Duration.ofMillis(1000L));
    }
}
