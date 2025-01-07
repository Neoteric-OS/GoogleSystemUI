package com.android.settingslib;

import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SignalIcon$MobileIconGroup extends SignalIcon$IconGroup {
    public final int dataContentDescription;
    public final int dataType;

    public SignalIcon$MobileIconGroup(String str, int i, int i2) {
        super(str, null, null, AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH, 0, 0, 0, 0, R.string.accessibility_no_phone);
        this.dataContentDescription = i;
        this.dataType = i2;
    }
}
