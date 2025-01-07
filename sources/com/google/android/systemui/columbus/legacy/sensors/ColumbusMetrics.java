package com.google.android.systemui.columbus.legacy.sensors;

import android.frameworks.stats.IStats;
import android.util.Log;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ColumbusMetrics {
    public static final String ISTATS_INSTANCE_NAME = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(IStats.DESCRIPTOR, "/default");
    public static final boolean DEBUG = Log.isLoggable("Columbus/Metrics", 3);
}
