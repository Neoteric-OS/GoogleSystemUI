package com.google.android.systemui.columbus.fetchers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.systemui.broadcast.BroadcastDispatcher;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class BroadcastFetcher$createDispatchedFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ IntentFilter $intentFilter;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BroadcastFetcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BroadcastFetcher$createDispatchedFlow$1(BroadcastFetcher broadcastFetcher, IntentFilter intentFilter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = broadcastFetcher;
        this.$intentFilter = intentFilter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BroadcastFetcher$createDispatchedFlow$1 broadcastFetcher$createDispatchedFlow$1 = new BroadcastFetcher$createDispatchedFlow$1(this.this$0, this.$intentFilter, continuation);
        broadcastFetcher$createDispatchedFlow$1.L$0 = obj;
        return broadcastFetcher$createDispatchedFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BroadcastFetcher$createDispatchedFlow$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.content.BroadcastReceiver, com.google.android.systemui.columbus.fetchers.BroadcastFetcher$createDispatchedFlow$1$broadcastReceiver$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new BroadcastReceiver() { // from class: com.google.android.systemui.columbus.fetchers.BroadcastFetcher$createDispatchedFlow$1$broadcastReceiver$1
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context, Intent intent) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(intent);
                }
            };
            BroadcastDispatcher.registerReceiver$default(this.this$0.broadcastDispatcher, r1, this.$intentFilter, null, null, 0, 60);
            final BroadcastFetcher broadcastFetcher = this.this$0;
            Function0 function0 = new Function0() { // from class: com.google.android.systemui.columbus.fetchers.BroadcastFetcher$createDispatchedFlow$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    BroadcastFetcher.this.broadcastDispatcher.unregisterReceiver(r1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
