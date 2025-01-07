package com.android.systemui.accessibility.data.repository;

import android.view.accessibility.CaptioningManager;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CaptioningRepositoryImpl$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ CaptioningRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CaptioningRepositoryImpl$special$$inlined$flatMapLatest$1(Continuation continuation, CaptioningRepositoryImpl captioningRepositoryImpl) {
        super(3, continuation);
        this.this$0 = captioningRepositoryImpl;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        CaptioningRepositoryImpl$special$$inlined$flatMapLatest$1 captioningRepositoryImpl$special$$inlined$flatMapLatest$1 = new CaptioningRepositoryImpl$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        captioningRepositoryImpl$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        captioningRepositoryImpl$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return captioningRepositoryImpl$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            CaptioningManager captioningManager = (CaptioningManager) this.L$1;
            CaptioningRepositoryImpl captioningRepositoryImpl = this.this$0;
            captioningRepositoryImpl.getClass();
            Flow flowOn = FlowKt.flowOn(new CaptioningRepositoryImpl$special$$inlined$map$1(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new CaptioningRepositoryImpl$captioningModel$3(2, null), FlowConflatedKt.conflatedCallbackFlow(new CaptioningRepositoryImpl$captioningModel$2(captioningManager, null))), captioningManager, 1), captioningRepositoryImpl.backgroundCoroutineContext);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowOn, this) == coroutineSingletons) {
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
