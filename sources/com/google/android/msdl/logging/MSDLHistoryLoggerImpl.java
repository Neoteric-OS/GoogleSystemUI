package com.google.android.msdl.logging;

import java.util.ArrayDeque;
import java.util.Deque;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MSDLHistoryLoggerImpl {
    public final Deque history;

    public MSDLHistoryLoggerImpl() {
        new ArrayDeque(20);
    }
}
