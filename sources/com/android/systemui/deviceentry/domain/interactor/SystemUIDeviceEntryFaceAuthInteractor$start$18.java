package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.biometrics.data.repository.CameraInfo;
import com.android.systemui.deviceentry.shared.FaceAuthUiEvent;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SystemUIDeviceEntryFaceAuthInteractor$start$18 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SystemUIDeviceEntryFaceAuthInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemUIDeviceEntryFaceAuthInteractor$start$18(SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemUIDeviceEntryFaceAuthInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SystemUIDeviceEntryFaceAuthInteractor$start$18 systemUIDeviceEntryFaceAuthInteractor$start$18 = new SystemUIDeviceEntryFaceAuthInteractor$start$18(this.this$0, continuation);
        systemUIDeviceEntryFaceAuthInteractor$start$18.L$0 = obj;
        return systemUIDeviceEntryFaceAuthInteractor$start$18;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        SystemUIDeviceEntryFaceAuthInteractor$start$18 systemUIDeviceEntryFaceAuthInteractor$start$18 = (SystemUIDeviceEntryFaceAuthInteractor$start$18) create((CameraInfo) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        systemUIDeviceEntryFaceAuthInteractor$start$18.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (((CameraInfo) this.L$0) != null && this.this$0.isRunning()) {
            this.this$0.repository.cancel();
            this.this$0.runFaceAuth(FaceAuthUiEvent.FACE_AUTH_CAMERA_AVAILABLE_CHANGED, true);
        }
        return Unit.INSTANCE;
    }
}
