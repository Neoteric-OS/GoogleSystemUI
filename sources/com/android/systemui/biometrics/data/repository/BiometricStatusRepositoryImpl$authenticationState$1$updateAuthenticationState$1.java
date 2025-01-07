package com.android.systemui.biometrics.data.repository;

import android.util.Log;
import com.android.systemui.biometrics.shared.model.AuthenticationState;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BiometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1 extends Lambda implements Function1 {
    final /* synthetic */ ProducerScope $$this$callbackFlow;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BiometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1(ProducerScope producerScope) {
        super(1);
        this.$$this$callbackFlow = producerScope;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        AuthenticationState authenticationState = (AuthenticationState) obj;
        Log.d("BiometricStatusRepositoryImpl", "authenticationState updated: " + authenticationState);
        Object mo1790trySendJP2dKIU = ((ProducerCoroutine) this.$$this$callbackFlow)._channel.mo1790trySendJP2dKIU(authenticationState);
        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
            Log.e("BiometricStatusRepositoryImpl", "Failed to send Error sending AuthenticationState state - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
        }
        return Unit.INSTANCE;
    }
}
