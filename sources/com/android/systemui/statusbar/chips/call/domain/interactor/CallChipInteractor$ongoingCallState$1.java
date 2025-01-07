package com.android.systemui.statusbar.chips.call.domain.interactor;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.phone.ongoingcall.shared.model.OngoingCallModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CallChipInteractor$ongoingCallState$1 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CallChipInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallChipInteractor$ongoingCallState$1(CallChipInteractor callChipInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = callChipInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CallChipInteractor$ongoingCallState$1 callChipInteractor$ongoingCallState$1 = new CallChipInteractor$ongoingCallState$1(this.this$0, continuation);
        callChipInteractor$ongoingCallState$1.L$0 = obj;
        return callChipInteractor$ongoingCallState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CallChipInteractor$ongoingCallState$1 callChipInteractor$ongoingCallState$1 = (CallChipInteractor$ongoingCallState$1) create((OngoingCallModel) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        callChipInteractor$ongoingCallState$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        OngoingCallModel ongoingCallModel = (OngoingCallModel) this.L$0;
        LogBuffer logBuffer = this.this$0.logger;
        LogMessage obtain = logBuffer.obtain("OngoingCall", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.chips.call.domain.interactor.CallChipInteractor$ongoingCallState$1.2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("State: ", ((LogMessage) obj2).getStr1());
            }
        }, null);
        ((LogMessageImpl) obtain).str1 = Reflection.getOrCreateKotlinClass(ongoingCallModel.getClass()).getSimpleName();
        logBuffer.commit(obtain);
        return Unit.INSTANCE;
    }
}
