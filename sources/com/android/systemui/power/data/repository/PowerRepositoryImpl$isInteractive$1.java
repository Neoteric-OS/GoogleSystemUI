package com.android.systemui.power.data.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PowerRepositoryImpl$isInteractive$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ BroadcastDispatcher $dispatcher;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PowerRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PowerRepositoryImpl$isInteractive$1(BroadcastDispatcher broadcastDispatcher, PowerRepositoryImpl powerRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.$dispatcher = broadcastDispatcher;
        this.this$0 = powerRepositoryImpl;
    }

    public static final void invokeSuspend$send(ProducerScope producerScope, PowerRepositoryImpl powerRepositoryImpl) {
        Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(Boolean.valueOf(powerRepositoryImpl.manager.isInteractive()));
        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
            FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "PowerRepository", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        PowerRepositoryImpl$isInteractive$1 powerRepositoryImpl$isInteractive$1 = new PowerRepositoryImpl$isInteractive$1(this.$dispatcher, this.this$0, continuation);
        powerRepositoryImpl$isInteractive$1.L$0 = obj;
        return powerRepositoryImpl$isInteractive$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((PowerRepositoryImpl$isInteractive$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.content.BroadcastReceiver, com.android.systemui.power.data.repository.PowerRepositoryImpl$isInteractive$1$receiver$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final PowerRepositoryImpl powerRepositoryImpl = this.this$0;
            final ?? r1 = new BroadcastReceiver() { // from class: com.android.systemui.power.data.repository.PowerRepositoryImpl$isInteractive$1$receiver$1
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context, Intent intent) {
                    PowerRepositoryImpl$isInteractive$1.invokeSuspend$send(ProducerScope.this, powerRepositoryImpl);
                }
            };
            BroadcastDispatcher broadcastDispatcher = this.$dispatcher;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, r1, intentFilter, null, null, 0, 60);
            invokeSuspend$send(producerScope, this.this$0);
            final BroadcastDispatcher broadcastDispatcher2 = this.$dispatcher;
            Function0 function0 = new Function0() { // from class: com.android.systemui.power.data.repository.PowerRepositoryImpl$isInteractive$1.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    BroadcastDispatcher.this.unregisterReceiver(r1);
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
