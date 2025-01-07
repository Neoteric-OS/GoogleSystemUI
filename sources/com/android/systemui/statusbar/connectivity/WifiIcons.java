package com.android.systemui.statusbar.connectivity;

import android.R;
import com.android.settingslib.AccessibilityContentDescriptions;
import com.android.settingslib.SignalIcon$IconGroup;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WifiIcons {
    public static final SignalIcon$IconGroup UNMERGED_WIFI;
    public static final int[] WIFI_FULL_ICONS;
    public static final int[] WIFI_NO_INTERNET_ICONS;

    static {
        int[] iArr = {R.drawable.ic_volume, R.drawable.ic_volume_bluetooth_ad2p, R.drawable.ic_volume_bluetooth_in_call, R.drawable.ic_volume_off, R.drawable.ic_volume_off_small};
        WIFI_FULL_ICONS = iArr;
        int[] iArr2 = {com.android.wm.shell.R.drawable.ic_no_internet_wifi_signal_0, com.android.wm.shell.R.drawable.ic_no_internet_wifi_signal_1, com.android.wm.shell.R.drawable.ic_no_internet_wifi_signal_2, com.android.wm.shell.R.drawable.ic_no_internet_wifi_signal_3, com.android.wm.shell.R.drawable.ic_no_internet_wifi_signal_4};
        WIFI_NO_INTERNET_ICONS = iArr2;
        int[][] iArr3 = {iArr2, iArr};
        int length = iArr3[0].length;
        UNMERGED_WIFI = new SignalIcon$IconGroup("Wi-Fi Icons", iArr3, iArr3, AccessibilityContentDescriptions.WIFI_CONNECTION_STRENGTH, R.drawable.ic_volume, R.drawable.ic_volume, R.drawable.ic_volume, R.drawable.ic_volume, com.android.wm.shell.R.string.accessibility_no_wifi);
    }
}
