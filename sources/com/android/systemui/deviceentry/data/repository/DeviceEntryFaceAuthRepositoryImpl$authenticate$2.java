package com.android.systemui.deviceentry.data.repository;

import android.hardware.biometrics.CryptoObject;
import android.hardware.face.FaceAuthenticateOptions;
import android.hardware.face.FaceManager;
import android.os.CancellationSignal;
import android.os.Handler;
import android.util.Log;
import com.android.systemui.deviceentry.shared.FaceAuthUiEvent;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryFaceAuthRepositoryImpl$authenticate$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ FaceAuthUiEvent $uiEvent;
    int label;
    final /* synthetic */ DeviceEntryFaceAuthRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryFaceAuthRepositoryImpl$authenticate$2(DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl, FaceAuthUiEvent faceAuthUiEvent, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceEntryFaceAuthRepositoryImpl;
        this.$uiEvent = faceAuthUiEvent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DeviceEntryFaceAuthRepositoryImpl$authenticate$2(this.this$0, this.$uiEvent, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceEntryFaceAuthRepositoryImpl$authenticate$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.authCancellationSignal = new CancellationSignal();
        StateFlowImpl stateFlowImpl = this.this$0._isAuthRunning;
        Boolean bool = Boolean.TRUE;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl = this.this$0;
        int i = 1;
        deviceEntryFaceAuthRepositoryImpl.uiEventsLogger.logWithInstanceIdAndPosition(this.$uiEvent, 0, (String) null, deviceEntryFaceAuthRepositoryImpl.sessionTracker.getSessionId(1), this.$uiEvent.getExtraInfo());
        this.this$0.faceAuthLogger.authenticating(this.$uiEvent);
        DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl2 = this.this$0;
        FaceManager faceManager = deviceEntryFaceAuthRepositoryImpl2.faceManager;
        if (faceManager == null) {
            return null;
        }
        CancellationSignal cancellationSignal = deviceEntryFaceAuthRepositoryImpl2.authCancellationSignal;
        int i2 = deviceEntryFaceAuthRepositoryImpl2.userRepository.getSelectedUserInfo().id;
        FaceAuthUiEvent faceAuthUiEvent = this.$uiEvent;
        int extraInfo = faceAuthUiEvent.getExtraInfo();
        if (faceAuthUiEvent != FaceAuthUiEvent.FACE_AUTH_UPDATED_STARTED_WAKING_UP) {
            if (faceAuthUiEvent == FaceAuthUiEvent.FACE_AUTH_UPDATED_PRIMARY_BOUNCER_SHOWN || faceAuthUiEvent == FaceAuthUiEvent.FACE_AUTH_UPDATED_PRIMARY_BOUNCER_SHOWN_OR_WILL_BE_SHOWN) {
                i = 2;
            } else if (faceAuthUiEvent == FaceAuthUiEvent.FACE_AUTH_UPDATED_ASSISTANT_VISIBILITY_CHANGED) {
                i = 3;
            } else if (faceAuthUiEvent == FaceAuthUiEvent.FACE_AUTH_TRIGGERED_ALTERNATE_BIOMETRIC_BOUNCER_SHOWN) {
                i = 4;
            } else if (faceAuthUiEvent == FaceAuthUiEvent.FACE_AUTH_TRIGGERED_NOTIFICATION_PANEL_CLICKED) {
                i = 5;
            } else if (faceAuthUiEvent == FaceAuthUiEvent.FACE_AUTH_TRIGGERED_OCCLUDING_APP_REQUESTED) {
                i = 6;
            } else if (faceAuthUiEvent == FaceAuthUiEvent.FACE_AUTH_TRIGGERED_PICK_UP_GESTURE_TRIGGERED) {
                i = 7;
            } else if (faceAuthUiEvent == FaceAuthUiEvent.FACE_AUTH_TRIGGERED_SWIPE_UP_ON_BOUNCER) {
                i = 9;
            } else if (faceAuthUiEvent == FaceAuthUiEvent.FACE_AUTH_TRIGGERED_UDFPS_POINTER_DOWN) {
                i = 10;
            } else {
                Log.e("FaceAuthenticateOptions", " unmapped FaceAuthUiEvent " + faceAuthUiEvent);
                i = 0;
            }
        }
        faceManager.authenticate((CryptoObject) null, cancellationSignal, deviceEntryFaceAuthRepositoryImpl2.faceAuthCallback, (Handler) null, new FaceAuthenticateOptions.Builder().setUserId(i2).setAuthenticateReason(i).setWakeReason(extraInfo).build());
        return Unit.INSTANCE;
    }
}
