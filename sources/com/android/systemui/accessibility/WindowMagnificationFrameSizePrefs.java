package com.android.systemui.accessibility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Size;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowMagnificationFrameSizePrefs {
    public Context mContext;
    public SharedPreferences mWindowMagnificationSizePreferences;

    public final String getKey() {
        return String.valueOf(this.mContext.getResources().getConfiguration().smallestScreenWidthDp);
    }

    public final void saveIndexAndSizeForCurrentDensity(int i, Size size) {
        this.mWindowMagnificationSizePreferences.edit().putString(getKey(), i + "," + size).apply();
    }
}
