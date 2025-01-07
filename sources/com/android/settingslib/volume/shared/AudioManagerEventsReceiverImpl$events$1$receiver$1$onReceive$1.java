package com.android.settingslib.volume.shared;

import android.content.Intent;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AudioManagerEventsReceiverImpl$events$1$receiver$1$onReceive$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ProducerScope $$this$callbackFlow;
    final /* synthetic */ Intent $intent;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioManagerEventsReceiverImpl$events$1$receiver$1$onReceive$1(ProducerScope producerScope, Intent intent, Continuation continuation) {
        super(2, continuation);
        this.$$this$callbackFlow = producerScope;
        this.$intent = intent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new AudioManagerEventsReceiverImpl$events$1$receiver$1$onReceive$1(this.$$this$callbackFlow, this.$intent, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((AudioManagerEventsReceiverImpl$events$1$receiver$1$onReceive$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = this.$$this$callbackFlow;
            Intent intent = this.$intent;
            this.label = 1;
            if (((ProducerCoroutine) producerScope)._channel.send(intent, this) == coroutineSingletons) {
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
