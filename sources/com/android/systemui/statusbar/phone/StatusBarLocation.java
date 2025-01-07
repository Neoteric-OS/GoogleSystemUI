package com.android.systemui.statusbar.phone;

import com.android.systemui.plugins.qs.QS;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarLocation {
    public static final /* synthetic */ StatusBarLocation[] $VALUES;
    public static final StatusBarLocation HOME;
    public static final StatusBarLocation KEYGUARD;
    public static final StatusBarLocation QS;
    public static final StatusBarLocation SHADE_CARRIER_GROUP;

    static {
        StatusBarLocation statusBarLocation = new StatusBarLocation("HOME", 0);
        HOME = statusBarLocation;
        StatusBarLocation statusBarLocation2 = new StatusBarLocation("KEYGUARD", 1);
        KEYGUARD = statusBarLocation2;
        StatusBarLocation statusBarLocation3 = new StatusBarLocation(QS.TAG, 2);
        QS = statusBarLocation3;
        StatusBarLocation statusBarLocation4 = new StatusBarLocation("SHADE_CARRIER_GROUP", 3);
        SHADE_CARRIER_GROUP = statusBarLocation4;
        StatusBarLocation[] statusBarLocationArr = {statusBarLocation, statusBarLocation2, statusBarLocation3, statusBarLocation4};
        $VALUES = statusBarLocationArr;
        EnumEntriesKt.enumEntries(statusBarLocationArr);
    }

    public static StatusBarLocation valueOf(String str) {
        return (StatusBarLocation) Enum.valueOf(StatusBarLocation.class, str);
    }

    public static StatusBarLocation[] values() {
        return (StatusBarLocation[]) $VALUES.clone();
    }
}
