package com.android.systemui.deviceentry.domain.interactor;

import android.content.pm.UserInfo;
import android.hardware.biometrics.BiometricSourceType;
import com.android.systemui.log.FaceAuthenticationLogger;
import com.android.systemui.log.core.LogLevel;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$7 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SystemUIDeviceEntryFaceAuthInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$7(SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemUIDeviceEntryFaceAuthInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$7 systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$7 = new SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$7(this.this$0, continuation);
        systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$7.L$0 = obj;
        return systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$7;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$7 systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$7 = (SystemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$7) create((Pair) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        systemUIDeviceEntryFaceAuthInteractor$observeFaceAuthStateUpdates$7.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Pair pair = (Pair) this.L$0;
        boolean booleanValue = ((Boolean) pair.component1()).booleanValue();
        UserInfo userInfo = (UserInfo) pair.component2();
        if (!booleanValue) {
            FaceAuthenticationLogger faceAuthenticationLogger = this.this$0.faceAuthenticationLogger;
            faceAuthenticationLogger.getClass();
            faceAuthenticationLogger.logBuffer.log("DeviceEntryFaceAuthRepositoryLog", LogLevel.DEBUG, "Clear face recognized", null);
            this.this$0.trustManager.clearAllBiometricRecognized(BiometricSourceType.FACE, userInfo.id);
        }
        return Unit.INSTANCE;
    }
}
