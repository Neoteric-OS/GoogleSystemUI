package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.deviceentry.shared.FaceAuthUiEvent;
import com.android.systemui.power.shared.model.WakefulnessModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SystemUIDeviceEntryFaceAuthInteractor$start$5 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SystemUIDeviceEntryFaceAuthInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemUIDeviceEntryFaceAuthInteractor$start$5(SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemUIDeviceEntryFaceAuthInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SystemUIDeviceEntryFaceAuthInteractor$start$5 systemUIDeviceEntryFaceAuthInteractor$start$5 = new SystemUIDeviceEntryFaceAuthInteractor$start$5(this.this$0, continuation);
        systemUIDeviceEntryFaceAuthInteractor$start$5.L$0 = obj;
        return systemUIDeviceEntryFaceAuthInteractor$start$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        SystemUIDeviceEntryFaceAuthInteractor$start$5 systemUIDeviceEntryFaceAuthInteractor$start$5 = (SystemUIDeviceEntryFaceAuthInteractor$start$5) create((WakefulnessModel) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        systemUIDeviceEntryFaceAuthInteractor$start$5.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        WakefulnessModel wakefulnessModel = (WakefulnessModel) this.L$0;
        this.this$0.faceAuthenticationLogger.lockscreenBecameVisible(wakefulnessModel);
        FaceAuthUiEvent faceAuthUiEvent = FaceAuthUiEvent.FACE_AUTH_UPDATED_KEYGUARD_VISIBILITY_CHANGED;
        faceAuthUiEvent.setExtraInfo(wakefulnessModel.lastWakeReason.getPowerManagerWakeReason());
        this.this$0.runFaceAuth(faceAuthUiEvent, true);
        return Unit.INSTANCE;
    }
}
