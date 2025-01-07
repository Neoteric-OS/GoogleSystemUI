package com.android.systemui.accessibility;

import android.content.Context;
import com.android.systemui.navigationbar.NavBarHelper;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.util.settings.SecureSettings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AccessibilityGestureTargetsObserver extends SecureSettingsContentObserver {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TargetsChangedListener {
    }

    public AccessibilityGestureTargetsObserver(Context context, UserTracker userTracker, SecureSettings secureSettings) {
        super(context, userTracker, secureSettings, "accessibility_gesture_targets");
    }

    @Override // com.android.systemui.accessibility.SecureSettingsContentObserver
    public final void onValueChanged(Object obj, String str) {
        ((NavBarHelper) ((TargetsChangedListener) obj)).updateA11yState();
    }
}
