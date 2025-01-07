package com.android.systemui.deviceentry.domain.interactor;

import android.hardware.biometrics.BiometricSourceType;
import android.hardware.face.FaceManager;
import android.os.Trace;
import com.android.keyguard.ActiveUnlockConfig;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger;
import com.android.systemui.deviceentry.shared.model.AcquiredFaceAuthenticationStatus;
import com.android.systemui.deviceentry.shared.model.ErrorFaceAuthenticationStatus;
import com.android.systemui.deviceentry.shared.model.FaceAuthenticationStatus;
import com.android.systemui.deviceentry.shared.model.FailedFaceAuthenticationStatus;
import com.android.systemui.deviceentry.shared.model.HelpFaceAuthenticationStatus;
import com.android.systemui.deviceentry.shared.model.SuccessFaceAuthenticationStatus;
import com.android.systemui.util.Assert;
import com.android.wm.shell.R;
import java.lang.ref.WeakReference;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$1 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SystemUIDeviceEntryFaceAuthInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$1(SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemUIDeviceEntryFaceAuthInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$1 systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$1 = new SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$1(this.this$0, continuation);
        systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$1.L$0 = obj;
        return systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$1 systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$1 = (SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$1) create((FaceAuthenticationStatus) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        FaceAuthenticationStatus faceAuthenticationStatus = (FaceAuthenticationStatus) this.L$0;
        for (KeyguardUpdateMonitor.AnonymousClass6 anonymousClass6 : this.this$0.listeners) {
            anonymousClass6.getClass();
            boolean z = faceAuthenticationStatus instanceof AcquiredFaceAuthenticationStatus;
            KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
            int i = 0;
            if (z) {
                int i2 = ((AcquiredFaceAuthenticationStatus) faceAuthenticationStatus).acquiredInfo;
                int i3 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                Assert.isMainThread();
                while (i < keyguardUpdateMonitor.mCallbacks.size()) {
                    KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i)).get();
                    if (keyguardUpdateMonitorCallback != null) {
                        keyguardUpdateMonitorCallback.onBiometricAcquired(BiometricSourceType.FACE, i2);
                    }
                    i++;
                }
                if (keyguardUpdateMonitor.mActiveUnlockConfig.faceAcquireInfoToTriggerBiometricFailOn.contains(Integer.valueOf(i2))) {
                    keyguardUpdateMonitor.requestActiveUnlock(ActiveUnlockConfig.ActiveUnlockRequestOrigin.BIOMETRIC_FAIL, "faceAcquireInfo-" + i2);
                }
            } else if (faceAuthenticationStatus instanceof ErrorFaceAuthenticationStatus) {
                ErrorFaceAuthenticationStatus errorFaceAuthenticationStatus = (ErrorFaceAuthenticationStatus) faceAuthenticationStatus;
                int i4 = errorFaceAuthenticationStatus.msgId;
                int i5 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                Assert.isMainThread();
                KeyguardUpdateMonitorLogger keyguardUpdateMonitorLogger = keyguardUpdateMonitor.mLogger;
                String str = errorFaceAuthenticationStatus.msg;
                keyguardUpdateMonitorLogger.logFaceAuthError(i4, str);
                boolean isSensorPrivacyEnabled = keyguardUpdateMonitor.mSensorPrivacyManager.isSensorPrivacyEnabled(1, 2);
                boolean z2 = i4 == 1;
                if (i4 == 9 && (systemUIDeviceEntryFaceAuthInteractor = keyguardUpdateMonitor.mFaceAuthInteractor) != null && systemUIDeviceEntryFaceAuthInteractor.isFaceAuthStrong()) {
                    keyguardUpdateMonitor.updateFingerprintListeningState(1);
                }
                if (z2 && isSensorPrivacyEnabled) {
                    str = keyguardUpdateMonitor.mContext.getString(R.string.kg_face_sensor_privacy_enabled);
                }
                while (i < keyguardUpdateMonitor.mCallbacks.size()) {
                    KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback2 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i)).get();
                    if (keyguardUpdateMonitorCallback2 != null) {
                        keyguardUpdateMonitorCallback2.onBiometricError(i4, str, BiometricSourceType.FACE);
                    }
                    i++;
                }
                if (keyguardUpdateMonitor.mActiveUnlockConfig.faceErrorsToTriggerBiometricFailOn.contains(Integer.valueOf(i4))) {
                    keyguardUpdateMonitor.requestActiveUnlock(ActiveUnlockConfig.ActiveUnlockRequestOrigin.BIOMETRIC_FAIL, "faceError-" + i4);
                }
            } else if (faceAuthenticationStatus instanceof FailedFaceAuthenticationStatus) {
                int i6 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                Assert.isMainThread();
                keyguardUpdateMonitor.requestActiveUnlock(ActiveUnlockConfig.ActiveUnlockRequestOrigin.BIOMETRIC_FAIL, "faceFailure-".concat(keyguardUpdateMonitor.mKeyguardBypassController.canBypass() ? "bypass" : keyguardUpdateMonitor.mAlternateBouncerShowing ? "alternateBouncer" : keyguardUpdateMonitor.mPrimaryBouncerFullyShown ? "bouncer" : "udfpsFpDown"));
                keyguardUpdateMonitor.mLogger.d("onFaceAuthFailed");
                while (i < keyguardUpdateMonitor.mCallbacks.size()) {
                    KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback3 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i)).get();
                    if (keyguardUpdateMonitorCallback3 != null) {
                        keyguardUpdateMonitorCallback3.onBiometricAuthFailed(BiometricSourceType.FACE);
                    }
                    i++;
                }
                keyguardUpdateMonitor.handleFaceHelp(-2, keyguardUpdateMonitor.mContext.getString(R.string.kg_face_not_recognized));
            } else if (faceAuthenticationStatus instanceof HelpFaceAuthenticationStatus) {
                HelpFaceAuthenticationStatus helpFaceAuthenticationStatus = (HelpFaceAuthenticationStatus) faceAuthenticationStatus;
                int i7 = helpFaceAuthenticationStatus.msgId;
                int i8 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                keyguardUpdateMonitor.handleFaceHelp(i7, helpFaceAuthenticationStatus.msg);
            } else if (faceAuthenticationStatus instanceof SuccessFaceAuthenticationStatus) {
                FaceManager.AuthenticationResult authenticationResult = ((SuccessFaceAuthenticationStatus) faceAuthenticationStatus).successResult;
                int userId = authenticationResult.getUserId();
                boolean isStrongBiometric = authenticationResult.isStrongBiometric();
                int i9 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                Trace.beginSection("KeyGuardUpdateMonitor#handlerFaceAuthenticated");
                if (keyguardUpdateMonitor.mGoingToSleep) {
                    keyguardUpdateMonitor.mLogger.d("Aborted successful auth because device is going to sleep.");
                } else {
                    int selectedUserId = keyguardUpdateMonitor.mSelectedUserInteractor.getSelectedUserId();
                    if (selectedUserId != userId) {
                        keyguardUpdateMonitor.mLogger.logFaceAuthForWrongUser(userId);
                    } else {
                        keyguardUpdateMonitor.mLogger.logFaceAuthSuccess(selectedUserId);
                        keyguardUpdateMonitor.onFaceAuthenticated(selectedUserId, isStrongBiometric);
                        Trace.endSection();
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }
}
