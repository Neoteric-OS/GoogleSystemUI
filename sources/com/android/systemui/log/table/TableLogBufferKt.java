package com.android.systemui.log.table;

import android.icu.text.SimpleDateFormat;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TableLogBufferKt {
    public static final SimpleDateFormat TABLE_LOG_DATE_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);
}
