package com.android.wm.shell.bubbles;

import android.content.Context;
import android.provider.Settings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class BubbleDebugConfig {
    public static boolean forceShowUserEducation(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "force_show_bubbles_user_education", 0) != 0;
    }

    public static boolean neverShowUserEducation(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "force_hide_bubbles_user_education", 0) != 0;
    }
}
