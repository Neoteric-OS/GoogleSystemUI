package com.android.systemui.lifecycle;

import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ExclusiveActivatable {
    public final AtomicBoolean _isActive;

    public ExclusiveActivatable() {
        new AtomicBoolean(false);
    }
}
