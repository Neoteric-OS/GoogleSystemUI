package com.android.systemui.media.controls.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MediaDataUtils {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.CharSequence] */
    public static String getAppLabel(Context context, String str, String str2) {
        ApplicationInfo applicationInfo = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (applicationInfo != null) {
            str2 = packageManager.getApplicationLabel(applicationInfo);
        }
        return (String) str2;
    }

    public static Double getDescriptionProgress(Bundle bundle) {
        if (bundle == null || !bundle.containsKey("android.media.extra.PLAYBACK_STATUS")) {
            return null;
        }
        int i = bundle.getInt("android.media.extra.PLAYBACK_STATUS");
        if (i == 0) {
            return Double.valueOf(0.0d);
        }
        if (i != 1) {
            if (i != 2) {
                return null;
            }
            return Double.valueOf(1.0d);
        }
        if (!bundle.containsKey("androidx.media.MediaItem.Extras.COMPLETION_PERCENTAGE")) {
            return Double.valueOf(0.5d);
        }
        double d = bundle.getDouble("androidx.media.MediaItem.Extras.COMPLETION_PERCENTAGE");
        return Double.valueOf(d >= 0.0d ? d > 1.0d ? 1.0d : d : 0.0d);
    }
}
