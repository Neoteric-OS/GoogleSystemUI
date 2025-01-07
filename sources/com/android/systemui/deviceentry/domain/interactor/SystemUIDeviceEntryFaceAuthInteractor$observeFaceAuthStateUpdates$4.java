package com.android.systemui.deviceentry.domain.interactor;

import android.hardware.biometrics.BiometricSourceType;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.util.Assert;
import java.lang.ref.WeakReference;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$4 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ SystemUIDeviceEntryFaceAuthInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$4(SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemUIDeviceEntryFaceAuthInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$4 systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$4 = new SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$4(this.this$0, continuation);
        systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$4.Z$0 = ((Boolean) obj).booleanValue();
        return systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$4 systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$4 = (SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$4) create(bool, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$4.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        for (KeyguardUpdateMonitor.AnonymousClass6 anonymousClass6 : this.this$0.listeners) {
            anonymousClass6.getClass();
            int i = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
            KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
            keyguardUpdateMonitor.getClass();
            Assert.isMainThread();
            for (int i2 = 0; i2 < keyguardUpdateMonitor.mCallbacks.size(); i2++) {
                KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i2)).get();
                if (keyguardUpdateMonitorCallback != null) {
                    keyguardUpdateMonitorCallback.onBiometricRunningStateChanged(BiometricSourceType.FACE, keyguardUpdateMonitor.isFaceDetectionRunning());
                }
            }
        }
        return Unit.INSTANCE;
    }
}
