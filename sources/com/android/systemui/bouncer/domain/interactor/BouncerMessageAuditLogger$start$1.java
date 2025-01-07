package com.android.systemui.bouncer.domain.interactor;

import android.util.Log;
import com.android.systemui.bouncer.shared.model.BouncerMessageModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BouncerMessageAuditLogger$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BouncerMessageAuditLogger this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bouncer.domain.interactor.BouncerMessageAuditLogger$start$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            Log.d(BouncerMessageAuditLoggerKt.TAG, "bouncerMessage: " + ((BouncerMessageModel) obj));
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BouncerMessageAuditLogger$start$1(BouncerMessageAuditLogger bouncerMessageAuditLogger, Continuation continuation) {
        super(2, continuation);
        this.this$0 = bouncerMessageAuditLogger;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BouncerMessageAuditLogger$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BouncerMessageAuditLogger$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        StateFlowImpl stateFlowImpl = this.this$0.repository.bouncerMessage;
        AnonymousClass1 anonymousClass1 = AnonymousClass1.INSTANCE;
        this.label = 1;
        stateFlowImpl.collect(anonymousClass1, this);
        return coroutineSingletons;
    }
}
