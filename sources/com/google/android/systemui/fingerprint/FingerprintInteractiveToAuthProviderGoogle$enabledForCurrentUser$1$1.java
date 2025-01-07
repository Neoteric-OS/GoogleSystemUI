package com.google.android.systemui.fingerprint;

import android.database.ContentObserver;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.util.settings.SecureSettingsImpl;
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
/* loaded from: classes2.dex */
final class FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function0 $getCurrentSettingValue;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FingerprintInteractiveToAuthProviderGoogle this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1(FingerprintInteractiveToAuthProviderGoogle fingerprintInteractiveToAuthProviderGoogle, Function0 function0, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fingerprintInteractiveToAuthProviderGoogle;
        this.$getCurrentSettingValue = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1 fingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1 = new FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1(this.this$0, this.$getCurrentSettingValue, continuation);
        fingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1.L$0 = obj;
        return fingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.ContentObserver, com.google.android.systemui.fingerprint.FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final Function0 function0 = this.$getCurrentSettingValue;
            final ?? r1 = new ContentObserver() { // from class: com.google.android.systemui.fingerprint.FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1$callback$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(null);
                }

                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    ProducerScope producerScope2 = ProducerScope.this;
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope2)._channel.mo1790trySendJP2dKIU(function0.invoke());
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("FingerprintInteractiveToAuthProviderGoogle", "Failed to send setting value updated - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }
            };
            SecureSettingsImpl secureSettingsImpl = (SecureSettingsImpl) this.this$0.secureSettings;
            secureSettingsImpl.getClass();
            secureSettingsImpl.registerContentObserverSync(Settings.Secure.getUriFor("sfps_performant_auth_enabled_v2"), true, r1);
            Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(this.$getCurrentSettingValue.invoke());
            if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                Log.e("FingerprintInteractiveToAuthProviderGoogle", "Failed to send emitting initial value - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
            }
            final FingerprintInteractiveToAuthProviderGoogle fingerprintInteractiveToAuthProviderGoogle = this.this$0;
            Function0 function02 = new Function0() { // from class: com.google.android.systemui.fingerprint.FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    FingerprintInteractiveToAuthProviderGoogle.this.secureSettings.unregisterContentObserverSync(r1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function02, this) == coroutineSingletons) {
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
