package com.android.systemui.deviceentry.data.repository;

import android.util.Log;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
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
final class DeviceEntryFaceAuthRepositoryImpl$isBypassEnabled$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ KeyguardBypassController $it;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryFaceAuthRepositoryImpl$isBypassEnabled$1$1(KeyguardBypassController keyguardBypassController, Continuation continuation) {
        super(2, continuation);
        this.$it = keyguardBypassController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceEntryFaceAuthRepositoryImpl$isBypassEnabled$1$1 deviceEntryFaceAuthRepositoryImpl$isBypassEnabled$1$1 = new DeviceEntryFaceAuthRepositoryImpl$isBypassEnabled$1$1(this.$it, continuation);
        deviceEntryFaceAuthRepositoryImpl$isBypassEnabled$1$1.L$0 = obj;
        return deviceEntryFaceAuthRepositoryImpl$isBypassEnabled$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceEntryFaceAuthRepositoryImpl$isBypassEnabled$1$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isBypassEnabled$1$1$callback$1, com.android.systemui.statusbar.phone.KeyguardBypassController$OnBypassStateChangedListener] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new KeyguardBypassController.OnBypassStateChangedListener() { // from class: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isBypassEnabled$1$1$callback$1
                @Override // com.android.systemui.statusbar.phone.KeyguardBypassController.OnBypassStateChangedListener
                public final void onBypassStateChanged(boolean z) {
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(Boolean.valueOf(z));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("DeviceEntryFaceAuthRepository", "Failed to send BypassStateChanged - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }
            };
            this.$it.registerOnBypassStateChangedListener(r1);
            Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(Boolean.valueOf(this.$it.getBypassEnabled()));
            if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                Log.e("DeviceEntryFaceAuthRepository", "Failed to send BypassStateChanged - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
            }
            final KeyguardBypassController keyguardBypassController = this.$it;
            Function0 function0 = new Function0() { // from class: com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$isBypassEnabled$1$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    KeyguardBypassController.this.unregisterOnBypassStateChangedListener(r1);
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
