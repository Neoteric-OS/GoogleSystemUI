package androidx.compose.material3.internal;

import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ElevationKt {
    public static final TweenSpec DefaultIncomingSpec;
    public static final TweenSpec DefaultOutgoingSpec;
    public static final TweenSpec HoveredOutgoingSpec;
    public static final CubicBezierEasing OutgoingSpecEasing = null;

    static {
        CubicBezierEasing cubicBezierEasing = new CubicBezierEasing(0.4f, 0.0f, 0.6f, 1.0f);
        DefaultIncomingSpec = new TweenSpec(120, 0, EasingKt.FastOutSlowInEasing);
        DefaultOutgoingSpec = new TweenSpec(150, 0, cubicBezierEasing);
        HoveredOutgoingSpec = new TweenSpec(120, 0, cubicBezierEasing);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0017, code lost:
    
        if ((r11 instanceof androidx.compose.foundation.interaction.FocusInteraction$Focus) != false) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0033, code lost:
    
        if ((r10 instanceof androidx.compose.foundation.interaction.FocusInteraction$Focus) != false) goto L6;
     */
    /* renamed from: animateElevation-rAjV9yQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m243animateElevationrAjV9yQ(androidx.compose.animation.core.Animatable r8, float r9, androidx.compose.foundation.interaction.Interaction r10, androidx.compose.foundation.interaction.Interaction r11, kotlin.coroutines.jvm.internal.SuspendLambda r12) {
        /*
            r0 = 0
            if (r11 == 0) goto L1c
            boolean r10 = r11 instanceof androidx.compose.foundation.interaction.PressInteraction$Press
            androidx.compose.animation.core.TweenSpec r1 = androidx.compose.material3.internal.ElevationKt.DefaultIncomingSpec
            if (r10 == 0) goto Lb
        L9:
            r0 = r1
            goto L1a
        Lb:
            boolean r10 = r11 instanceof androidx.compose.foundation.interaction.DragInteraction$Start
            if (r10 == 0) goto L10
            goto L9
        L10:
            boolean r10 = r11 instanceof androidx.compose.foundation.interaction.HoverInteraction$Enter
            if (r10 == 0) goto L15
            goto L9
        L15:
            boolean r10 = r11 instanceof androidx.compose.foundation.interaction.FocusInteraction$Focus
            if (r10 == 0) goto L1a
            goto L9
        L1a:
            r3 = r0
            goto L36
        L1c:
            if (r10 == 0) goto L1a
            boolean r11 = r10 instanceof androidx.compose.foundation.interaction.PressInteraction$Press
            androidx.compose.animation.core.TweenSpec r1 = androidx.compose.material3.internal.ElevationKt.DefaultOutgoingSpec
            if (r11 == 0) goto L25
        L24:
            goto L9
        L25:
            boolean r11 = r10 instanceof androidx.compose.foundation.interaction.DragInteraction$Start
            if (r11 == 0) goto L2a
            goto L24
        L2a:
            boolean r11 = r10 instanceof androidx.compose.foundation.interaction.HoverInteraction$Enter
            if (r11 == 0) goto L31
            androidx.compose.animation.core.TweenSpec r0 = androidx.compose.material3.internal.ElevationKt.HoveredOutgoingSpec
            goto L1a
        L31:
            boolean r10 = r10 instanceof androidx.compose.foundation.interaction.FocusInteraction$Focus
            if (r10 == 0) goto L1a
            goto L24
        L36:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            if (r3 == 0) goto L4f
            androidx.compose.ui.unit.Dp r2 = new androidx.compose.ui.unit.Dp
            r2.<init>(r9)
            r4 = 0
            r5 = 0
            r7 = 12
            r1 = r8
            r6 = r12
            java.lang.Object r8 = androidx.compose.animation.core.Animatable.animateTo$default(r1, r2, r3, r4, r5, r6, r7)
            kotlin.coroutines.intrinsics.CoroutineSingletons r9 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r8 != r9) goto L4e
            return r8
        L4e:
            return r10
        L4f:
            androidx.compose.ui.unit.Dp r11 = new androidx.compose.ui.unit.Dp
            r11.<init>(r9)
            java.lang.Object r8 = r8.snapTo(r11, r12)
            kotlin.coroutines.intrinsics.CoroutineSingletons r9 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r8 != r9) goto L5d
            return r8
        L5d:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.ElevationKt.m243animateElevationrAjV9yQ(androidx.compose.animation.core.Animatable, float, androidx.compose.foundation.interaction.Interaction, androidx.compose.foundation.interaction.Interaction, kotlin.coroutines.jvm.internal.SuspendLambda):java.lang.Object");
    }
}
