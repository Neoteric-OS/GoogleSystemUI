package androidx.compose.material.ripple;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import kotlinx.coroutines.CompletableDeferredImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RippleAnimation {
    public final boolean bounded;
    public final MutableState finishRequested$delegate;
    public final CompletableDeferredImpl finishSignalDeferred;
    public final MutableState finishedFadingIn$delegate;
    public Offset origin;
    public final float radius;
    public Float startRadius;
    public Offset targetCenter;
    public final Animatable animatedAlpha = AnimatableKt.Animatable(0.0f, 0.01f);
    public final Animatable animatedRadiusPercent = AnimatableKt.Animatable(0.0f, 0.01f);
    public final Animatable animatedCenterPercent = AnimatableKt.Animatable(0.0f, 0.01f);

    public RippleAnimation(Offset offset, float f, boolean z) {
        this.origin = offset;
        this.radius = f;
        this.bounded = z;
        CompletableDeferredImpl completableDeferredImpl = new CompletableDeferredImpl(true);
        completableDeferredImpl.initParentJob(null);
        this.finishSignalDeferred = completableDeferredImpl;
        Boolean bool = Boolean.FALSE;
        this.finishedFadingIn$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.finishRequested$delegate = SnapshotStateKt.mutableStateOf$default(bool);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x008c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0074 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object animate(kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof androidx.compose.material.ripple.RippleAnimation$animate$1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.compose.material.ripple.RippleAnimation$animate$1 r0 = (androidx.compose.material.ripple.RippleAnimation$animate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material.ripple.RippleAnimation$animate$1 r0 = new androidx.compose.material.ripple.RippleAnimation$animate$1
            r0.<init>(r8, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L48
            if (r2 == r6) goto L40
            if (r2 == r5) goto L38
            if (r2 != r4) goto L30
            kotlin.ResultKt.throwOnFailure(r9)
            goto L8c
        L30:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L38:
            java.lang.Object r8 = r0.L$0
            androidx.compose.material.ripple.RippleAnimation r8 = (androidx.compose.material.ripple.RippleAnimation) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L75
        L40:
            java.lang.Object r8 = r0.L$0
            androidx.compose.material.ripple.RippleAnimation r8 = (androidx.compose.material.ripple.RippleAnimation) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L5f
        L48:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r8
            r0.label = r6
            androidx.compose.material.ripple.RippleAnimation$fadeIn$2 r9 = new androidx.compose.material.ripple.RippleAnimation$fadeIn$2
            r9.<init>(r8, r7)
            java.lang.Object r9 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r9)
            if (r9 != r1) goto L5b
            goto L5c
        L5b:
            r9 = r3
        L5c:
            if (r9 != r1) goto L5f
            return r1
        L5f:
            androidx.compose.runtime.MutableState r9 = r8.finishedFadingIn$delegate
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            androidx.compose.runtime.SnapshotMutableStateImpl r9 = (androidx.compose.runtime.SnapshotMutableStateImpl) r9
            r9.setValue(r2)
            r0.L$0 = r8
            r0.label = r5
            kotlinx.coroutines.CompletableDeferredImpl r9 = r8.finishSignalDeferred
            java.lang.Object r9 = r9.awaitInternal(r0)
            if (r9 != r1) goto L75
            return r1
        L75:
            r0.L$0 = r7
            r0.label = r4
            r8.getClass()
            androidx.compose.material.ripple.RippleAnimation$fadeOut$2 r9 = new androidx.compose.material.ripple.RippleAnimation$fadeOut$2
            r9.<init>(r8, r7)
            java.lang.Object r8 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r9)
            if (r8 != r1) goto L88
            goto L89
        L88:
            r8 = r3
        L89:
            if (r8 != r1) goto L8c
            return r1
        L8c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ripple.RippleAnimation.animate(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
