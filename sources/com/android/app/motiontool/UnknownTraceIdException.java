package com.android.app.motiontool;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UnknownTraceIdException extends Exception {
    private final int traceId;

    public UnknownTraceIdException(int i) {
        this.traceId = i;
    }

    public final int getTraceId() {
        return this.traceId;
    }
}
