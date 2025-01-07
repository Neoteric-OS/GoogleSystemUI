package com.android.systemui.keyguard.ui;

import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionState;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StateToValue {
    public final KeyguardState from;
    public final KeyguardState to;
    public final TransitionState transitionState;
    public final Float value;

    public StateToValue(KeyguardState keyguardState, KeyguardState keyguardState2, TransitionState transitionState, Float f) {
        this.from = keyguardState;
        this.to = keyguardState2;
        this.transitionState = transitionState;
        this.value = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StateToValue)) {
            return false;
        }
        StateToValue stateToValue = (StateToValue) obj;
        return this.from == stateToValue.from && this.to == stateToValue.to && this.transitionState == stateToValue.transitionState && Intrinsics.areEqual(this.value, stateToValue.value);
    }

    public final int hashCode() {
        KeyguardState keyguardState = this.from;
        int hashCode = (keyguardState == null ? 0 : keyguardState.hashCode()) * 31;
        KeyguardState keyguardState2 = this.to;
        int hashCode2 = (this.transitionState.hashCode() + ((hashCode + (keyguardState2 == null ? 0 : keyguardState2.hashCode())) * 31)) * 31;
        Float f = this.value;
        return hashCode2 + (f != null ? f.hashCode() : 0);
    }

    public final String toString() {
        return "StateToValue(from=" + this.from + ", to=" + this.to + ", transitionState=" + this.transitionState + ", value=" + this.value + ")";
    }

    public /* synthetic */ StateToValue(Float f, int i) {
        this(null, (i & 2) != 0 ? null : KeyguardState.AOD, TransitionState.FINISHED, (i & 8) != 0 ? Float.valueOf(0.0f) : f);
    }
}
