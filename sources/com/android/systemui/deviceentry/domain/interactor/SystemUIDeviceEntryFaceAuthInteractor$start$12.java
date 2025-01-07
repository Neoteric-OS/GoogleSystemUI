package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl;
import com.android.systemui.log.FaceAuthenticationLogger;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.user.data.model.SelectedUserModel;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SystemUIDeviceEntryFaceAuthInteractor$start$12 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SystemUIDeviceEntryFaceAuthInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemUIDeviceEntryFaceAuthInteractor$start$12(SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemUIDeviceEntryFaceAuthInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SystemUIDeviceEntryFaceAuthInteractor$start$12 systemUIDeviceEntryFaceAuthInteractor$start$12 = new SystemUIDeviceEntryFaceAuthInteractor$start$12(this.this$0, continuation);
        systemUIDeviceEntryFaceAuthInteractor$start$12.L$0 = obj;
        return systemUIDeviceEntryFaceAuthInteractor$start$12;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SystemUIDeviceEntryFaceAuthInteractor$start$12) create((Pair) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Pair pair = (Pair) this.L$0;
            boolean booleanValue = ((Boolean) pair.component1()).booleanValue();
            SelectedUserModel selectedUserModel = (SelectedUserModel) pair.component2();
            if (booleanValue) {
                FaceAuthenticationLogger faceAuthenticationLogger = this.this$0.faceAuthenticationLogger;
                faceAuthenticationLogger.getClass();
                faceAuthenticationLogger.logBuffer.log("DeviceEntryFaceAuthRepositoryLog", LogLevel.DEBUG, "Face auth has been locked out: ".concat("Fingerprint locked out"), null);
                if (this.this$0.isFaceAuthEnabledAndEnrolled()) {
                    DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl = this.this$0.repository;
                    Boolean bool = Boolean.TRUE;
                    StateFlowImpl stateFlowImpl = deviceEntryFaceAuthRepositoryImpl._isLockedOut;
                    stateFlowImpl.getClass();
                    stateFlowImpl.updateState(null, bool);
                }
            } else {
                SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = this.this$0;
                int i2 = selectedUserModel.userInfo.id;
                this.label = 1;
                if (SystemUIDeviceEntryFaceAuthInteractor.access$resetLockedOutState(systemUIDeviceEntryFaceAuthInteractor, i2, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
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
