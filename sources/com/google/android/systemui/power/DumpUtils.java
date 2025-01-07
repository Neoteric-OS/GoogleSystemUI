package com.google.android.systemui.power;

import android.net.Uri;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DumpUtils {
    public static final Uri PROVIDER_URI = new Uri.Builder().scheme("content").authority("com.google.android.apps.turbo.battery_health_provider").build();

    public static String toReadableDateTime(long j) {
        return new SimpleDateFormat("MMM dd,yyyy HH:mm:ss", Locale.getDefault()).format(new Date(j));
    }
}
