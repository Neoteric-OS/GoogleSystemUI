package com.android.app.tracing.coroutines;

import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TraceDataKt {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        if (StringsKt.contains$default((CharSequence) "0x", '\n') || StringsKt.contains$default((CharSequence) "0x", '\r')) {
            throw new IllegalArgumentException("LF and CR characters are prohibited in prefix, but was ".concat("0x"));
        }
    }
}
