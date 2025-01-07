package com.android.systemui.volume.panel.component.anc.ui.composable;

import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.slice.widget.SliceView;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ComposeSliceView extends SliceView {
    public boolean enableAccessibility;
    public OnWidthChangedLayoutListener layoutListener;

    @Override // android.view.ViewGroup, android.view.View
    public final void addChildrenForAccessibility(ArrayList arrayList) {
        if (this.enableAccessibility) {
            super.addChildrenForAccessibility(arrayList);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (this.enableAccessibility) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (this.enableAccessibility) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        }
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i, Bundle bundle) {
        if (this.enableAccessibility) {
            return super.performAccessibilityAction(i, bundle);
        }
        return false;
    }
}
