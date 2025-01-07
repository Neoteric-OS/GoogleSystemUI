package com.android.settingslib.media.data.repository;

import android.media.AudioDeviceAttributes;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SpatializerRepositoryImpl$setHeadTrackingEnabled$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ AudioDeviceAttributes $audioDeviceAttributes;
    final /* synthetic */ boolean $isEnabled;
    int label;
    final /* synthetic */ SpatializerRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpatializerRepositoryImpl$setHeadTrackingEnabled$2(SpatializerRepositoryImpl spatializerRepositoryImpl, boolean z, AudioDeviceAttributes audioDeviceAttributes, Continuation continuation) {
        super(2, continuation);
        this.this$0 = spatializerRepositoryImpl;
        this.$isEnabled = z;
        this.$audioDeviceAttributes = audioDeviceAttributes;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SpatializerRepositoryImpl$setHeadTrackingEnabled$2(this.this$0, this.$isEnabled, this.$audioDeviceAttributes, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        SpatializerRepositoryImpl$setHeadTrackingEnabled$2 spatializerRepositoryImpl$setHeadTrackingEnabled$2 = (SpatializerRepositoryImpl$setHeadTrackingEnabled$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        spatializerRepositoryImpl$setHeadTrackingEnabled$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.spatializer.setHeadTrackerEnabled(this.$isEnabled, this.$audioDeviceAttributes);
        return Unit.INSTANCE;
    }
}
