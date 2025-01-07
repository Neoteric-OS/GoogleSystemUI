package com.android.systemui.deviceentry.data.repository;

import android.hardware.face.FaceAuthenticateOptions;
import android.hardware.face.FaceManager;
import android.os.CancellationSignal;
import android.util.Log;
import com.android.systemui.deviceentry.shared.FaceAuthUiEvent;
import com.android.systemui.log.FaceAuthenticationLogger;
import com.android.systemui.log.core.LogLevel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryFaceAuthRepositoryImpl$detect$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ FaceAuthUiEvent $uiEvent;
    int label;
    final /* synthetic */ DeviceEntryFaceAuthRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryFaceAuthRepositoryImpl$detect$2(DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl, FaceAuthUiEvent faceAuthUiEvent, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceEntryFaceAuthRepositoryImpl;
        this.$uiEvent = faceAuthUiEvent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DeviceEntryFaceAuthRepositoryImpl$detect$2(this.this$0, this.$uiEvent, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceEntryFaceAuthRepositoryImpl$detect$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        FaceAuthenticationLogger faceAuthenticationLogger = this.this$0.faceAuthLogger;
        faceAuthenticationLogger.getClass();
        faceAuthenticationLogger.logBuffer.log("DeviceEntryFaceAuthRepositoryLog", LogLevel.DEBUG, "Face detection started.", null);
        CancellationSignal cancellationSignal = this.this$0.detectCancellationSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        this.this$0.detectCancellationSignal = new CancellationSignal();
        DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl = this.this$0;
        CancellationSignal cancellationSignal2 = deviceEntryFaceAuthRepositoryImpl.detectCancellationSignal;
        if (cancellationSignal2 == null) {
            return null;
        }
        FaceAuthUiEvent faceAuthUiEvent = this.$uiEvent;
        FaceManager faceManager = deviceEntryFaceAuthRepositoryImpl.faceManager;
        if (faceManager == null) {
            return null;
        }
        int i2 = deviceEntryFaceAuthRepositoryImpl.userRepository.getSelectedUserInfo().id;
        int extraInfo = faceAuthUiEvent.getExtraInfo();
        if (faceAuthUiEvent == FaceAuthUiEvent.FACE_AUTH_UPDATED_STARTED_WAKING_UP) {
            i = 1;
        } else if (faceAuthUiEvent == FaceAuthUiEvent.FACE_AUTH_UPDATED_PRIMARY_BOUNCER_SHOWN || faceAuthUiEvent == FaceAuthUiEvent.FACE_AUTH_UPDATED_PRIMARY_BOUNCER_SHOWN_OR_WILL_BE_SHOWN) {
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
        faceManager.detectFace(cancellationSignal2, deviceEntryFaceAuthRepositoryImpl.detectionCallback, new FaceAuthenticateOptions.Builder().setUserId(i2).setAuthenticateReason(i).setWakeReason(extraInfo).build());
        return Unit.INSTANCE;
    }
}
