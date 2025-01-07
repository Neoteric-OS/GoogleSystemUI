package com.google.android.systemui.volume.panel.component.spatial.domain;

import com.android.systemui.volume.domain.model.AudioOutputDevice;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function6;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SpatialAudioAvailabilityGoogleCriteria$availability$1 extends SuspendLambda implements Function6 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;

    public SpatialAudioAvailabilityGoogleCriteria$availability$1(Continuation continuation) {
        super(6, continuation);
    }

    @Override // kotlin.jvm.functions.Function6
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        boolean booleanValue3 = ((Boolean) obj5).booleanValue();
        SpatialAudioAvailabilityGoogleCriteria$availability$1 spatialAudioAvailabilityGoogleCriteria$availability$1 = new SpatialAudioAvailabilityGoogleCriteria$availability$1((Continuation) obj6);
        spatialAudioAvailabilityGoogleCriteria$availability$1.Z$0 = booleanValue;
        spatialAudioAvailabilityGoogleCriteria$availability$1.Z$1 = booleanValue2;
        spatialAudioAvailabilityGoogleCriteria$availability$1.L$0 = (AudioOutputDevice.Bluetooth) obj3;
        spatialAudioAvailabilityGoogleCriteria$availability$1.L$1 = (AudioOutputDevice) obj4;
        spatialAudioAvailabilityGoogleCriteria$availability$1.Z$2 = booleanValue3;
        return spatialAudioAvailabilityGoogleCriteria$availability$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        AudioOutputDevice.Bluetooth bluetooth = (AudioOutputDevice.Bluetooth) this.L$0;
        AudioOutputDevice audioOutputDevice = (AudioOutputDevice) this.L$1;
        boolean z3 = this.Z$2;
        boolean z4 = false;
        if (z && !z3 && (!z2 || bluetooth != null || (audioOutputDevice instanceof AudioOutputDevice.BuiltIn))) {
            z4 = true;
        }
        return Boolean.valueOf(z4);
    }
}
