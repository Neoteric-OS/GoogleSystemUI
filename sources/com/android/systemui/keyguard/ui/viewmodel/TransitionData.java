package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.ui.view.layout.blueprints.transitions.IntraBlueprintTransition;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TransitionData {
    public final IntraBlueprintTransition.Config config;
    public final long start;

    public TransitionData(IntraBlueprintTransition.Config config) {
        long currentTimeMillis = System.currentTimeMillis();
        this.config = config;
        this.start = currentTimeMillis;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransitionData)) {
            return false;
        }
        TransitionData transitionData = (TransitionData) obj;
        return Intrinsics.areEqual(this.config, transitionData.config) && this.start == transitionData.start;
    }

    public final int hashCode() {
        return Long.hashCode(this.start) + (this.config.hashCode() * 31);
    }

    public final String toString() {
        return "TransitionData(config=" + this.config + ", start=" + this.start + ")";
    }
}
