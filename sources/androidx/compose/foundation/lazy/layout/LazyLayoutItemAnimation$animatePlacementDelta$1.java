package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.FiniteAnimationSpec;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LazyLayoutItemAnimation$animatePlacementDelta$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ FiniteAnimationSpec $spec;
    final /* synthetic */ long $totalDelta;
    Object L$0;
    int label;
    final /* synthetic */ LazyLayoutItemAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyLayoutItemAnimation$animatePlacementDelta$1(LazyLayoutItemAnimation lazyLayoutItemAnimation, FiniteAnimationSpec finiteAnimationSpec, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lazyLayoutItemAnimation;
        this.$spec = finiteAnimationSpec;
        this.$totalDelta = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new LazyLayoutItemAnimation$animatePlacementDelta$1(this.this$0, this.$spec, this.$totalDelta, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((LazyLayoutItemAnimation$animatePlacementDelta$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00af A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r10.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L21
            if (r1 == r3) goto L19
            if (r1 != r2) goto L11
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.util.concurrent.CancellationException -> Lbc
            goto Lb0
        L11:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L19:
            java.lang.Object r1 = r10.L$0
            androidx.compose.animation.core.FiniteAnimationSpec r1 = (androidx.compose.animation.core.FiniteAnimationSpec) r1
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.util.concurrent.CancellationException -> Lbc
            goto L72
        L21:
            kotlin.ResultKt.throwOnFailure(r11)
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r11 = r10.this$0     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.animation.core.Animatable r11 = r11.placementDeltaAnimation     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.runtime.MutableState r11 = r11.isRunning$delegate     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.runtime.SnapshotMutableStateImpl r11 = (androidx.compose.runtime.SnapshotMutableStateImpl) r11     // Catch: java.util.concurrent.CancellationException -> Lbc
            java.lang.Object r11 = r11.getValue()     // Catch: java.util.concurrent.CancellationException -> Lbc
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch: java.util.concurrent.CancellationException -> Lbc
            boolean r11 = r11.booleanValue()     // Catch: java.util.concurrent.CancellationException -> Lbc
            if (r11 == 0) goto L45
            androidx.compose.animation.core.FiniteAnimationSpec r11 = r10.$spec     // Catch: java.util.concurrent.CancellationException -> Lbc
            boolean r1 = r11 instanceof androidx.compose.animation.core.SpringSpec     // Catch: java.util.concurrent.CancellationException -> Lbc
            if (r1 == 0) goto L41
            androidx.compose.animation.core.SpringSpec r11 = (androidx.compose.animation.core.SpringSpec) r11     // Catch: java.util.concurrent.CancellationException -> Lbc
            goto L43
        L41:
            androidx.compose.animation.core.SpringSpec r11 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimationKt.InterruptionSpec     // Catch: java.util.concurrent.CancellationException -> Lbc
        L43:
            r1 = r11
            goto L48
        L45:
            androidx.compose.animation.core.FiniteAnimationSpec r11 = r10.$spec     // Catch: java.util.concurrent.CancellationException -> Lbc
            goto L43
        L48:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r11 = r10.this$0     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.animation.core.Animatable r11 = r11.placementDeltaAnimation     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.runtime.MutableState r11 = r11.isRunning$delegate     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.runtime.SnapshotMutableStateImpl r11 = (androidx.compose.runtime.SnapshotMutableStateImpl) r11     // Catch: java.util.concurrent.CancellationException -> Lbc
            java.lang.Object r11 = r11.getValue()     // Catch: java.util.concurrent.CancellationException -> Lbc
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch: java.util.concurrent.CancellationException -> Lbc
            boolean r11 = r11.booleanValue()     // Catch: java.util.concurrent.CancellationException -> Lbc
            if (r11 != 0) goto L7b
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r11 = r10.this$0     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.animation.core.Animatable r11 = r11.placementDeltaAnimation     // Catch: java.util.concurrent.CancellationException -> Lbc
            long r4 = r10.$totalDelta     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.ui.unit.IntOffset r6 = new androidx.compose.ui.unit.IntOffset     // Catch: java.util.concurrent.CancellationException -> Lbc
            r6.<init>(r4)     // Catch: java.util.concurrent.CancellationException -> Lbc
            r10.L$0 = r1     // Catch: java.util.concurrent.CancellationException -> Lbc
            r10.label = r3     // Catch: java.util.concurrent.CancellationException -> Lbc
            java.lang.Object r11 = r11.snapTo(r6, r10)     // Catch: java.util.concurrent.CancellationException -> Lbc
            if (r11 != r0) goto L72
            return r0
        L72:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r11 = r10.this$0     // Catch: java.util.concurrent.CancellationException -> Lbc
            kotlin.jvm.functions.Function0 r11 = r11.onLayerPropertyChanged     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$ItemInfo$updateAnimation$1$animation$1 r11 = (androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$ItemInfo$updateAnimation$1$animation$1) r11     // Catch: java.util.concurrent.CancellationException -> Lbc
            r11.invoke()     // Catch: java.util.concurrent.CancellationException -> Lbc
        L7b:
            r5 = r1
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r11 = r10.this$0     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.animation.core.Animatable r11 = r11.placementDeltaAnimation     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.animation.core.AnimationState r11 = r11.internalState     // Catch: java.util.concurrent.CancellationException -> Lbc
            java.lang.Object r11 = r11.getValue()     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.ui.unit.IntOffset r11 = (androidx.compose.ui.unit.IntOffset) r11     // Catch: java.util.concurrent.CancellationException -> Lbc
            long r3 = r11.packedValue     // Catch: java.util.concurrent.CancellationException -> Lbc
            long r6 = r10.$totalDelta     // Catch: java.util.concurrent.CancellationException -> Lbc
            long r3 = androidx.compose.ui.unit.IntOffset.m675minusqkQi6aY(r3, r6)     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r11 = r10.this$0     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.animation.core.Animatable r1 = r11.placementDeltaAnimation     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.ui.unit.IntOffset r6 = new androidx.compose.ui.unit.IntOffset     // Catch: java.util.concurrent.CancellationException -> Lbc
            r6.<init>(r3)     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1$1 r7 = new androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1$1     // Catch: java.util.concurrent.CancellationException -> Lbc
            r7.<init>()     // Catch: java.util.concurrent.CancellationException -> Lbc
            r11 = 0
            r10.L$0 = r11     // Catch: java.util.concurrent.CancellationException -> Lbc
            r10.label = r2     // Catch: java.util.concurrent.CancellationException -> Lbc
            r11 = 0
            r9 = 4
            r3 = r1
            r4 = r6
            r6 = r11
            r8 = r10
            java.lang.Object r11 = androidx.compose.animation.core.Animatable.animateTo$default(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.util.concurrent.CancellationException -> Lbc
            if (r11 != r0) goto Lb0
            return r0
        Lb0:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r11 = r10.this$0     // Catch: java.util.concurrent.CancellationException -> Lbc
            int r0 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.$r8$clinit     // Catch: java.util.concurrent.CancellationException -> Lbc
            r0 = 0
            r11.setPlacementAnimationInProgress(r0)     // Catch: java.util.concurrent.CancellationException -> Lbc
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r10 = r10.this$0     // Catch: java.util.concurrent.CancellationException -> Lbc
            r10.isRunningMovingAwayAnimation = r0     // Catch: java.util.concurrent.CancellationException -> Lbc
        Lbc:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
