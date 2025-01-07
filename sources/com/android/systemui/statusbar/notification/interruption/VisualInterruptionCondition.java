package com.android.systemui.statusbar.notification.interruption;

import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class VisualInterruptionCondition implements VisualInterruptionSuppressor {
    public final String reason;
    public final Set types;

    public VisualInterruptionCondition(String str, Set set) {
        this.types = set;
        this.reason = str;
    }

    public abstract boolean shouldSuppress();
}
