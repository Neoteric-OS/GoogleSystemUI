package com.android.wm.shell.bubbles;

import java.util.function.ToLongFunction;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleData$$ExternalSyntheticLambda4 implements ToLongFunction {
    @Override // java.util.function.ToLongFunction
    public final long applyAsLong(Object obj) {
        Bubble bubble = (Bubble) obj;
        return Math.max(bubble.mLastUpdated, bubble.mLastAccessed);
    }
}
