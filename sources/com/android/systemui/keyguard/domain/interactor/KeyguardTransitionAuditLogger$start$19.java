package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionAuditLogger$start$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardTransitionAuditLogger$start$19 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ KeyguardTransitionAuditLogger this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardTransitionAuditLogger$start$19(KeyguardTransitionAuditLogger keyguardTransitionAuditLogger, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardTransitionAuditLogger;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardTransitionAuditLogger$start$19(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardTransitionAuditLogger$start$19) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        KeyguardTransitionAuditLogger keyguardTransitionAuditLogger = this.this$0;
        KeyguardInteractor$special$$inlined$map$2 keyguardInteractor$special$$inlined$map$2 = keyguardTransitionAuditLogger.keyguardInteractor.onCameraLaunchDetected;
        KeyguardTransitionAuditLogger$start$1.AnonymousClass1 anonymousClass1 = new KeyguardTransitionAuditLogger$start$1.AnonymousClass1(keyguardTransitionAuditLogger, 10);
        this.label = 1;
        keyguardInteractor$special$$inlined$map$2.collect(anonymousClass1, this);
        return coroutineSingletons;
    }
}
