package com.google.android.systemui.screenprotector;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ScreenProtectorSharedPreferenceUtils {
    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("screen_protector_shared_perf", 0);
    }
}
