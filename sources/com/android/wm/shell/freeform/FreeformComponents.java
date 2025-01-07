package com.android.wm.shell.freeform;

import android.content.Context;
import android.provider.Settings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FreeformComponents {
    public final FreeformTaskListener mTaskListener;

    public FreeformComponents(FreeformTaskListener freeformTaskListener) {
    }

    public static boolean isFreeformEnabled(Context context) {
        return context.getPackageManager().hasSystemFeature("android.software.freeform_window_management") || Settings.Global.getInt(context.getContentResolver(), "enable_freeform_support", 0) != 0;
    }
}
