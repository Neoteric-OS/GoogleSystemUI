package com.google.android.systemui.biometrics.repository;

import android.hardware.biometrics.AuthenticationStateListener;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricSourceType;
import android.hardware.biometrics.events.AuthenticationAcquiredInfo;
import android.hardware.biometrics.events.AuthenticationErrorInfo;
import android.hardware.biometrics.events.AuthenticationFailedInfo;
import android.hardware.biometrics.events.AuthenticationHelpInfo;
import android.hardware.biometrics.events.AuthenticationStartedInfo;
import android.hardware.biometrics.events.AuthenticationStoppedInfo;
import android.hardware.biometrics.events.AuthenticationSucceededInfo;
import android.util.Log;
import com.android.systemui.biometrics.shared.model.FingerprintSensorType;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.google.android.systemui.biometrics.DeviceEntryUnlockEvent;
import com.google.android.systemui.biometrics.DeviceEntryUnlockStage;
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
final class DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DeviceEntryUnlockTrackerRepositoryGoogle this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1(DeviceEntryUnlockTrackerRepositoryGoogle deviceEntryUnlockTrackerRepositoryGoogle, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceEntryUnlockTrackerRepositoryGoogle;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1 deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1 = new DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1(this.this$0, continuation);
        deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1.L$0 = obj;
        return deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [com.google.android.systemui.biometrics.repository.DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1$keyguardUnlockAnimationListener$1, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final AuthenticationStateListener authenticationStateListener = new AuthenticationStateListener.Stub() { // from class: com.google.android.systemui.biometrics.repository.DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1$authenticationListener$1
                public final void onAuthenticationAcquired(AuthenticationAcquiredInfo authenticationAcquiredInfo) {
                    if (authenticationAcquiredInfo.getRequestReason() != 4) {
                        return;
                    }
                    ProducerScope producerScope2 = ProducerScope.this;
                    DeviceEntryUnlockStage deviceEntryUnlockStage = DeviceEntryUnlockStage.CANCELED;
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope2)._channel.mo1790trySendJP2dKIU(new DeviceEntryUnlockEvent.Acquired(authenticationAcquiredInfo.getBiometricSourceType(), authenticationAcquiredInfo));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("DEUTrackerRepository", "Failed to send onAuthenticationAcquired - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }

                public final void onAuthenticationError(AuthenticationErrorInfo authenticationErrorInfo) {
                    if (authenticationErrorInfo.getRequestReason() != 4) {
                        return;
                    }
                    ProducerScope producerScope2 = ProducerScope.this;
                    DeviceEntryUnlockStage deviceEntryUnlockStage = DeviceEntryUnlockStage.CANCELED;
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope2)._channel.mo1790trySendJP2dKIU(new DeviceEntryUnlockEvent.Error(authenticationErrorInfo.getBiometricSourceType(), authenticationErrorInfo));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("DEUTrackerRepository", "Failed to send onAuthenticationError - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }

                public final void onAuthenticationStarted(AuthenticationStartedInfo authenticationStartedInfo) {
                    if (authenticationStartedInfo.getRequestReason() != 4) {
                        return;
                    }
                    ProducerScope producerScope2 = ProducerScope.this;
                    DeviceEntryUnlockStage deviceEntryUnlockStage = DeviceEntryUnlockStage.STARTED;
                    BiometricSourceType biometricSourceType = authenticationStartedInfo.getBiometricSourceType();
                    FingerprintSensorType fingerprintSensorType = FingerprintSensorType.UNKNOWN;
                    DeviceEntryUnlockEvent.Started started = new DeviceEntryUnlockEvent.Started(deviceEntryUnlockStage, biometricSourceType);
                    started.fpsSensorType = fingerprintSensorType;
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope2)._channel.mo1790trySendJP2dKIU(started);
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("DEUTrackerRepository", "Failed to send onAuthenticationStarted - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }

                public final void onAuthenticationStopped(AuthenticationStoppedInfo authenticationStoppedInfo) {
                    if (authenticationStoppedInfo.getRequestReason() != 4) {
                        return;
                    }
                    ProducerScope producerScope2 = ProducerScope.this;
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope2)._channel.mo1790trySendJP2dKIU(new DeviceEntryUnlockEvent.Stopped(DeviceEntryUnlockStage.STOPPED, authenticationStoppedInfo.getBiometricSourceType()));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("DEUTrackerRepository", "Failed to send onAuthenticationStopped - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }

                public final void onAuthenticationSucceeded(AuthenticationSucceededInfo authenticationSucceededInfo) {
                    if (authenticationSucceededInfo.getRequestReason() != 4) {
                        return;
                    }
                    ProducerScope producerScope2 = ProducerScope.this;
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope2)._channel.mo1790trySendJP2dKIU(new DeviceEntryUnlockEvent.Succeeded(DeviceEntryUnlockStage.HAL_AUTHENTICATED, authenticationSucceededInfo.getBiometricSourceType()));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("DEUTrackerRepository", "Failed to send onAuthenticationSucceeded - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }

                public final void onAuthenticationFailed(AuthenticationFailedInfo authenticationFailedInfo) {
                }

                public final void onAuthenticationHelp(AuthenticationHelpInfo authenticationHelpInfo) {
                }
            };
            final ?? r3 = new KeyguardUnlockAnimationController.KeyguardUnlockAnimationListener() { // from class: com.google.android.systemui.biometrics.repository.DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1$keyguardUnlockAnimationListener$1
                @Override // com.android.systemui.keyguard.KeyguardUnlockAnimationController.KeyguardUnlockAnimationListener
                public final void onUnlockAnimationFinished() {
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(new DeviceEntryUnlockEvent.Unlocked(DeviceEntryUnlockStage.UNLOCKED, null));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("DEUTrackerRepository", "Failed to send onUnlockAnimationFinished - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }

                @Override // com.android.systemui.keyguard.KeyguardUnlockAnimationController.KeyguardUnlockAnimationListener
                public final void onUnlockAnimationStarted(boolean z, boolean z2) {
                    DeviceEntryUnlockStage deviceEntryUnlockStage = DeviceEntryUnlockStage.CANCELED;
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(new DeviceEntryUnlockEvent.ExitKeyguard(new DeviceEntryUnlockEvent.ExitKeyguardInfo(z, z2)));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("DEUTrackerRepository", "Failed to send onUnlockAnimationStarted - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }
            };
            BiometricManager biometricManager = this.this$0.biometricManager;
            if (biometricManager != null) {
                biometricManager.registerAuthenticationStateListener(authenticationStateListener);
            }
            this.this$0.keyguardUnlockAnimationController.listeners.add(r3);
            final DeviceEntryUnlockTrackerRepositoryGoogle deviceEntryUnlockTrackerRepositoryGoogle = this.this$0;
            Function0 function0 = new Function0() { // from class: com.google.android.systemui.biometrics.repository.DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    BiometricManager biometricManager2 = DeviceEntryUnlockTrackerRepositoryGoogle.this.biometricManager;
                    if (biometricManager2 != null) {
                        biometricManager2.unregisterAuthenticationStateListener(authenticationStateListener);
                    }
                    KeyguardUnlockAnimationController keyguardUnlockAnimationController = DeviceEntryUnlockTrackerRepositoryGoogle.this.keyguardUnlockAnimationController;
                    keyguardUnlockAnimationController.listeners.remove(r3);
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
