package com.android.systemui.mediaprojection.permission;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BaseMediaProjectionPermissionDialogDelegate$initScreenShareSpinner$1 extends View.AccessibilityDelegate {
    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK);
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
    }
}
