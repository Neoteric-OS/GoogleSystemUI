package com.android.systemui.keyevent.data.repository;

import android.util.Log;
import android.view.KeyEvent;
import com.android.systemui.statusbar.CommandQueue;
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
final class KeyEventRepositoryImpl$isPowerButtonDown$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ KeyEventRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyEventRepositoryImpl$isPowerButtonDown$1(KeyEventRepositoryImpl keyEventRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyEventRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        KeyEventRepositoryImpl$isPowerButtonDown$1 keyEventRepositoryImpl$isPowerButtonDown$1 = new KeyEventRepositoryImpl$isPowerButtonDown$1(this.this$0, continuation);
        keyEventRepositoryImpl$isPowerButtonDown$1.L$0 = obj;
        return keyEventRepositoryImpl$isPowerButtonDown$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyEventRepositoryImpl$isPowerButtonDown$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.keyevent.data.repository.KeyEventRepositoryImpl$isPowerButtonDown$1$callback$1, com.android.systemui.statusbar.CommandQueue$Callbacks] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new CommandQueue.Callbacks() { // from class: com.android.systemui.keyevent.data.repository.KeyEventRepositoryImpl$isPowerButtonDown$1$callback$1
                @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                public final void handleSystemKey(KeyEvent keyEvent) {
                    if (keyEvent.getKeyCode() == 26) {
                        Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(Boolean.valueOf(keyEvent.isDown()));
                        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                            Log.e("KeyEventRepositoryImpl", "Failed to send updated isPowerButtonDown - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                        }
                    }
                }
            };
            Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(Boolean.FALSE);
            if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                Log.e("KeyEventRepositoryImpl", "Failed to send init isPowerButtonDown - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
            }
            this.this$0.commandQueue.addCallback((CommandQueue.Callbacks) r1);
            final KeyEventRepositoryImpl keyEventRepositoryImpl = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.keyevent.data.repository.KeyEventRepositoryImpl$isPowerButtonDown$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    KeyEventRepositoryImpl.this.commandQueue.removeCallback((CommandQueue.Callbacks) r1);
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
