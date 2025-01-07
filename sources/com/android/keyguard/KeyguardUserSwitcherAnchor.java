package com.android.keyguard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import com.android.wm.shell.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardUserSwitcherAnchor extends LinearLayout {
    /* JADX WARN: Multi-variable type inference failed */
    public KeyguardUserSwitcherAnchor(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
    }

    @Override // android.view.View
    public final AccessibilityNodeInfo createAccessibilityNodeInfo() {
        AccessibilityNodeInfo createAccessibilityNodeInfo = super.createAccessibilityNodeInfo();
        createAccessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", getContext().getString(R.string.accessibility_multi_user_list_switcher));
        Intrinsics.checkNotNull(createAccessibilityNodeInfo);
        return createAccessibilityNodeInfo;
    }

    public /* synthetic */ KeyguardUserSwitcherAnchor(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public KeyguardUserSwitcherAnchor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
