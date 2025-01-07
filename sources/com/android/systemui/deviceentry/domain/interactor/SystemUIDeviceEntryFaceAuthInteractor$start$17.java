package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.deviceentry.shared.FaceAuthUiEvent;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.YieldKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SystemUIDeviceEntryFaceAuthInteractor$start$17 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    boolean Z$0;
    int label;
    final /* synthetic */ SystemUIDeviceEntryFaceAuthInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemUIDeviceEntryFaceAuthInteractor$start$17(SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemUIDeviceEntryFaceAuthInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SystemUIDeviceEntryFaceAuthInteractor$start$17 systemUIDeviceEntryFaceAuthInteractor$start$17 = new SystemUIDeviceEntryFaceAuthInteractor$start$17(this.this$0, continuation);
        systemUIDeviceEntryFaceAuthInteractor$start$17.L$0 = obj;
        return systemUIDeviceEntryFaceAuthInteractor$start$17;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SystemUIDeviceEntryFaceAuthInteractor$start$17) create((Pair) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean booleanValue;
        boolean z;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Pair pair = (Pair) this.L$0;
            int intValue = ((Number) pair.component1()).intValue();
            booleanValue = ((Boolean) pair.component2()).booleanValue();
            SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = this.this$0;
            this.Z$0 = booleanValue;
            this.label = 1;
            if (SystemUIDeviceEntryFaceAuthInteractor.access$resetLockedOutState(systemUIDeviceEntryFaceAuthInteractor, intValue, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                z = this.Z$0;
                ResultKt.throwOnFailure(obj);
                this.this$0.runFaceAuth(FaceAuthUiEvent.FACE_AUTH_UPDATED_USER_SWITCHING, !z);
                return Unit.INSTANCE;
            }
            boolean z2 = this.Z$0;
            ResultKt.throwOnFailure(obj);
            booleanValue = z2;
        }
        this.Z$0 = booleanValue;
        this.label = 2;
        if (YieldKt.yield(this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        z = booleanValue;
        this.this$0.runFaceAuth(FaceAuthUiEvent.FACE_AUTH_UPDATED_USER_SWITCHING, !z);
        return Unit.INSTANCE;
    }
}
