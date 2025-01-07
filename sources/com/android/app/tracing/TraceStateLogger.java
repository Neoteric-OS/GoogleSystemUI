package com.android.app.tracing;

import android.os.Trace;
import android.util.Log;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TraceStateLogger {
    public final boolean logcat;
    public String previousValue;
    public final String trackName;

    public TraceStateLogger(String str, int i) {
        boolean z = (i & 8) == 0;
        this.trackName = str;
        this.logcat = z;
    }

    public final void log(String str) {
        String str2 = this.trackName;
        Trace.instantForTrack(4096L, str2, str);
        if (Intrinsics.areEqual(this.previousValue, str)) {
            return;
        }
        if (this.previousValue != null) {
            Trace.asyncTraceForTrackEnd(4096L, str2, 0);
        }
        Trace.asyncTraceForTrackBegin(4096L, str2, str, 0);
        if (this.logcat) {
            Log.d(str2, "newValue: ".concat(str));
        }
        this.previousValue = str;
    }
}
