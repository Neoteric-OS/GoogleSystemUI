package com.android.systemui.util;

import android.content.res.Resources;
import android.util.PluralsMessageFormatter;
import kotlin.Pair;
import kotlin.collections.MapsKt__MapsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PluralMessageFormaterKt {
    public static final String icuMessageFormat(Resources resources, int i, int i2) {
        return PluralsMessageFormatter.format(resources, MapsKt__MapsJVMKt.mapOf(new Pair("count", Integer.valueOf(i2))), i);
    }
}
