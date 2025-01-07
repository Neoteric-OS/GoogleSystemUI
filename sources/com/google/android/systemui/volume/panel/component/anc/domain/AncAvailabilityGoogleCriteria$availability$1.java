package com.google.android.systemui.volume.panel.component.anc.domain;

import com.android.systemui.volume.domain.model.AudioOutputDevice;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class AncAvailabilityGoogleCriteria$availability$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        AncAvailabilityGoogleCriteria$availability$1 ancAvailabilityGoogleCriteria$availability$1 = new AncAvailabilityGoogleCriteria$availability$1(4, (Continuation) obj4);
        ancAvailabilityGoogleCriteria$availability$1.Z$0 = booleanValue;
        ancAvailabilityGoogleCriteria$availability$1.Z$1 = booleanValue2;
        ancAvailabilityGoogleCriteria$availability$1.L$0 = (AudioOutputDevice.Bluetooth) obj3;
        return ancAvailabilityGoogleCriteria$availability$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Boolean.valueOf(this.Z$0 && !(this.Z$1 && ((AudioOutputDevice.Bluetooth) this.L$0) == null));
    }
}
