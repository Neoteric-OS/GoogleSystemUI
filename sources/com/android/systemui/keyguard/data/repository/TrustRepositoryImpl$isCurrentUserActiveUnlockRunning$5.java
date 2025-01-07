package com.android.systemui.keyguard.data.repository;

import com.android.systemui.communal.data.repository.CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0;
import com.android.systemui.keyguard.shared.model.ActiveUnlockModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TrustRepositoryImpl$isCurrentUserActiveUnlockRunning$5 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TrustRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrustRepositoryImpl$isCurrentUserActiveUnlockRunning$5(TrustRepositoryImpl trustRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = trustRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        TrustRepositoryImpl$isCurrentUserActiveUnlockRunning$5 trustRepositoryImpl$isCurrentUserActiveUnlockRunning$5 = new TrustRepositoryImpl$isCurrentUserActiveUnlockRunning$5(this.this$0, continuation);
        trustRepositoryImpl$isCurrentUserActiveUnlockRunning$5.L$0 = obj;
        return trustRepositoryImpl$isCurrentUserActiveUnlockRunning$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((TrustRepositoryImpl$isCurrentUserActiveUnlockRunning$5) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            TrustRepositoryImpl trustRepositoryImpl = this.this$0;
            ActiveUnlockModel activeUnlockModel = (ActiveUnlockModel) CommunalWidgetRepositoryImpl$restoreWidgets$1$$ExternalSyntheticOutline0.m(trustRepositoryImpl.userRepository.getSelectedUserInfo().id, trustRepositoryImpl.activeUnlockRunningForUser);
            boolean z = false;
            if (activeUnlockModel != null && activeUnlockModel.isRunning) {
                z = true;
            }
            Boolean valueOf = Boolean.valueOf(z);
            this.label = 1;
            if (flowCollector.emit(valueOf, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
