package com.android.systemui.deviceentry.domain.interactor;

import android.hardware.biometrics.BiometricSourceType;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.deviceentry.shared.model.FaceDetectionStatus;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SystemUIDeviceEntryFaceAuthInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$2(SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemUIDeviceEntryFaceAuthInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$2 systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$2 = new SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$2(this.this$0, continuation);
        systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$2.L$0 = obj;
        return systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$2 systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$2 = (SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$2) create((FaceDetectionStatus) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        FaceDetectionStatus faceDetectionStatus = (FaceDetectionStatus) this.L$0;
        for (KeyguardUpdateMonitor.AnonymousClass6 anonymousClass6 : this.this$0.listeners) {
            anonymousClass6.getClass();
            KeyguardUpdateMonitor.m753$$Nest$mhandleBiometricDetected(KeyguardUpdateMonitor.this, faceDetectionStatus.userId, BiometricSourceType.FACE, faceDetectionStatus.isStrongBiometric);
        }
        return Unit.INSTANCE;
    }
}
