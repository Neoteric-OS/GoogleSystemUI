package com.android.systemui.keyguard.shared.model;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TransitionStep {
    public final KeyguardState from;
    public final String ownerName;
    public final KeyguardState to;
    public final TransitionState transitionState;
    public final float value;

    public TransitionStep(KeyguardState keyguardState, KeyguardState keyguardState2, float f, TransitionState transitionState, String str) {
        this.from = keyguardState;
        this.to = keyguardState2;
        this.value = f;
        this.transitionState = transitionState;
        this.ownerName = str;
    }

    public static TransitionStep copy$default(TransitionStep transitionStep, float f, TransitionState transitionState, int i) {
        KeyguardState keyguardState = transitionStep.from;
        KeyguardState keyguardState2 = transitionStep.to;
        if ((i & 8) != 0) {
            transitionState = transitionStep.transitionState;
        }
        String str = transitionStep.ownerName;
        transitionStep.getClass();
        return new TransitionStep(keyguardState, keyguardState2, f, transitionState, str);
    }

    public static boolean isTransitioning$default(TransitionStep transitionStep, KeyguardState keyguardState) {
        return keyguardState == null || transitionStep.to == keyguardState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransitionStep)) {
            return false;
        }
        TransitionStep transitionStep = (TransitionStep) obj;
        return this.from == transitionStep.from && this.to == transitionStep.to && Float.compare(this.value, transitionStep.value) == 0 && this.transitionState == transitionStep.transitionState && Intrinsics.areEqual(this.ownerName, transitionStep.ownerName);
    }

    public final int hashCode() {
        return this.ownerName.hashCode() + ((this.transitionState.hashCode() + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m((this.to.hashCode() + (this.from.hashCode() * 31)) * 31, this.value, 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TransitionStep(from=");
        sb.append(this.from);
        sb.append(", to=");
        sb.append(this.to);
        sb.append(", value=");
        sb.append(this.value);
        sb.append(", transitionState=");
        sb.append(this.transitionState);
        sb.append(", ownerName=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.ownerName, ")");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ TransitionStep(int r7) {
        /*
            r6 = this;
            com.android.systemui.keyguard.shared.model.TransitionState r4 = com.android.systemui.keyguard.shared.model.TransitionState.FINISHED
            com.android.systemui.keyguard.shared.model.KeyguardState r2 = com.android.systemui.keyguard.shared.model.KeyguardState.OFF
            r7 = r7 & 4
            if (r7 == 0) goto Lb
            r7 = 0
        L9:
            r3 = r7
            goto Le
        Lb:
            r7 = 1065353216(0x3f800000, float:1.0)
            goto L9
        Le:
            java.lang.String r5 = ""
            r0 = r6
            r1 = r2
            r0.<init>(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.shared.model.TransitionStep.<init>(int):void");
    }

    public TransitionStep(TransitionInfo transitionInfo, float f, TransitionState transitionState) {
        this(transitionInfo.from, transitionInfo.to, f, transitionState, transitionInfo.ownerName);
    }
}
