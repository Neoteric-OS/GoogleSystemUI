package com.android.systemui.authentication.data.repository;

import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.systemui.broadcast.BroadcastDispatcher;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthenticationRepositoryImpl$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ BroadcastDispatcher $broadcastDispatcher$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthenticationRepositoryImpl$special$$inlined$flatMapLatest$1(Continuation continuation, BroadcastDispatcher broadcastDispatcher) {
        super(3, continuation);
        this.$broadcastDispatcher$inlined = broadcastDispatcher;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AuthenticationRepositoryImpl$special$$inlined$flatMapLatest$1 authenticationRepositoryImpl$special$$inlined$flatMapLatest$1 = new AuthenticationRepositoryImpl$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$broadcastDispatcher$inlined);
        authenticationRepositoryImpl$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        authenticationRepositoryImpl$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return authenticationRepositoryImpl$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            int intValue = ((Number) this.L$1).intValue();
            FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AuthenticationRepositoryImpl$authenticationMethod$2$1(2, null), BroadcastDispatcher.broadcastFlow$default(this.$broadcastDispatcher$inlined, new IntentFilter("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED"), UserHandle.of(intValue), 12));
            this.label = 1;
            FlowKt.ensureActive(flowCollector);
            Object collect = flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.collect(new AuthenticationRepositoryImpl$authenticationMethod$lambda$1$$inlined$map$1$2(flowCollector, intValue), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
