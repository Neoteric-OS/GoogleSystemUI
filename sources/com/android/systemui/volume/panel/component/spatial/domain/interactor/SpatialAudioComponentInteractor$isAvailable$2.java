package com.android.systemui.volume.panel.component.spatial.domain.interactor;

import android.media.AudioDeviceAttributes;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SpatialAudioComponentInteractor$isAvailable$2 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SpatialAudioComponentInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpatialAudioComponentInteractor$isAvailable$2(SpatialAudioComponentInteractor spatialAudioComponentInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = spatialAudioComponentInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SpatialAudioComponentInteractor$isAvailable$2 spatialAudioComponentInteractor$isAvailable$2 = new SpatialAudioComponentInteractor$isAvailable$2(this.this$0, (Continuation) obj3);
        spatialAudioComponentInteractor$isAvailable$2.L$0 = (AudioDeviceAttributes) obj;
        return spatialAudioComponentInteractor$isAvailable$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0060  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r5.label
            com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioAvailabilityModel$Unavailable r2 = com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioAvailabilityModel.Unavailable.INSTANCE
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L22
            if (r1 == r4) goto L1a
            if (r1 != r3) goto L12
            kotlin.ResultKt.throwOnFailure(r6)
            goto L58
        L12:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L1a:
            java.lang.Object r1 = r5.L$0
            android.media.AudioDeviceAttributes r1 = (android.media.AudioDeviceAttributes) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L3e
        L22:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.Object r6 = r5.L$0
            r1 = r6
            android.media.AudioDeviceAttributes r1 = (android.media.AudioDeviceAttributes) r1
            if (r1 != 0) goto L2d
            return r2
        L2d:
            com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor r6 = r5.this$0
            com.android.settingslib.media.domain.interactor.SpatializerInteractor r6 = r6.spatializerInteractor
            r5.L$0 = r1
            r5.label = r4
            com.android.settingslib.media.data.repository.SpatializerRepositoryImpl r6 = r6.repository
            java.lang.Object r6 = r6.isSpatialAudioAvailableForDevice(r1, r5)
            if (r6 != r0) goto L3e
            return r0
        L3e:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L66
            com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor r6 = r5.this$0
            com.android.settingslib.media.domain.interactor.SpatializerInteractor r6 = r6.spatializerInteractor
            r2 = 0
            r5.L$0 = r2
            r5.label = r3
            com.android.settingslib.media.data.repository.SpatializerRepositoryImpl r6 = r6.repository
            java.lang.Object r6 = r6.isHeadTrackingAvailableForDevice(r1, r5)
            if (r6 != r0) goto L58
            return r0
        L58:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r5 = r6.booleanValue()
            if (r5 == 0) goto L63
            com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioAvailabilityModel$HeadTracking r5 = com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioAvailabilityModel.HeadTracking.INSTANCE
            return r5
        L63:
            com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioAvailabilityModel$SpatialAudio$Companion r5 = com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioAvailabilityModel.SpatialAudio.Companion.$$INSTANCE
            return r5
        L66:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$isAvailable$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
