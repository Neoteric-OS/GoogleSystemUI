package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.deviceentry.shared.FaceAuthUiEvent;
import com.android.systemui.log.FaceAuthenticationLogger;
import com.android.systemui.log.core.LogLevel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SystemUIDeviceEntryFaceAuthInteractor$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ SystemUIDeviceEntryFaceAuthInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemUIDeviceEntryFaceAuthInteractor$start$1(SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemUIDeviceEntryFaceAuthInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SystemUIDeviceEntryFaceAuthInteractor$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        SystemUIDeviceEntryFaceAuthInteractor$start$1 systemUIDeviceEntryFaceAuthInteractor$start$1 = (SystemUIDeviceEntryFaceAuthInteractor$start$1) create(bool, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        systemUIDeviceEntryFaceAuthInteractor$start$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        FaceAuthenticationLogger faceAuthenticationLogger = this.this$0.faceAuthenticationLogger;
        faceAuthenticationLogger.getClass();
        faceAuthenticationLogger.logBuffer.log("DeviceEntryFaceAuthRepositoryLog", LogLevel.DEBUG, "Triggering face auth because primary bouncer is visible", null);
        this.this$0.runFaceAuth(FaceAuthUiEvent.FACE_AUTH_UPDATED_PRIMARY_BOUNCER_SHOWN, false);
        return Unit.INSTANCE;
    }
}
