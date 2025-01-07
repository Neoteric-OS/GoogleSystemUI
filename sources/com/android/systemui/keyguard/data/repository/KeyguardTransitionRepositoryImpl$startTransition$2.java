package com.android.systemui.keyguard.data.repository;

import com.android.systemui.keyguard.shared.model.TransitionInfo;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardTransitionRepositoryImpl$startTransition$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ TransitionInfo $info;
    float F$0;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ KeyguardTransitionRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardTransitionRepositoryImpl$startTransition$2(KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, TransitionInfo transitionInfo, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardTransitionRepositoryImpl;
        this.$info = transitionInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        KeyguardTransitionRepositoryImpl$startTransition$2 keyguardTransitionRepositoryImpl$startTransition$2 = new KeyguardTransitionRepositoryImpl$startTransition$2(this.this$0, this.$info, continuation);
        keyguardTransitionRepositoryImpl$startTransition$2.L$0 = obj;
        return keyguardTransitionRepositoryImpl$startTransition$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardTransitionRepositoryImpl$startTransition$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x00c9  */
    /* JADX WARN: Type inference failed for: r12v7, types: [android.animation.ValueAnimator$AnimatorUpdateListener, com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$startTransition$2$2$updateListener$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl$startTransition$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
