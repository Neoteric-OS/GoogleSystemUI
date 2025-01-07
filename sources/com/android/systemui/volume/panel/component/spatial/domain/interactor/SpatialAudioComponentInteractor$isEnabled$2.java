package com.android.systemui.volume.panel.component.spatial.domain.interactor;

import android.media.AudioDeviceAttributes;
import com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioAvailabilityModel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SpatialAudioComponentInteractor$isEnabled$2 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ SpatialAudioComponentInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpatialAudioComponentInteractor$isEnabled$2(SpatialAudioComponentInteractor spatialAudioComponentInteractor, Continuation continuation) {
        super(4, continuation);
        this.this$0 = spatialAudioComponentInteractor;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        SpatialAudioComponentInteractor$isEnabled$2 spatialAudioComponentInteractor$isEnabled$2 = new SpatialAudioComponentInteractor$isEnabled$2(this.this$0, (Continuation) obj4);
        spatialAudioComponentInteractor$isEnabled$2.L$0 = (AudioDeviceAttributes) obj2;
        spatialAudioComponentInteractor$isEnabled$2.L$1 = (SpatialAudioAvailabilityModel) obj3;
        return spatialAudioComponentInteractor$isEnabled$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0067  */
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
            com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel$Disabled r2 = com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel.Disabled.INSTANCE
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L22
            if (r1 == r4) goto L1a
            if (r1 != r3) goto L12
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5f
        L12:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L1a:
            java.lang.Object r1 = r5.L$0
            android.media.AudioDeviceAttributes r1 = (android.media.AudioDeviceAttributes) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L45
        L22:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.Object r6 = r5.L$0
            r1 = r6
            android.media.AudioDeviceAttributes r1 = (android.media.AudioDeviceAttributes) r1
            java.lang.Object r6 = r5.L$1
            com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioAvailabilityModel r6 = (com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioAvailabilityModel) r6
            boolean r6 = r6 instanceof com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioAvailabilityModel.Unavailable
            if (r6 == 0) goto L33
            return r2
        L33:
            if (r1 != 0) goto L36
            return r2
        L36:
            com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor r6 = r5.this$0
            com.android.settingslib.media.domain.interactor.SpatializerInteractor r6 = r6.spatializerInteractor
            r5.L$0 = r1
            r5.label = r4
            java.lang.Object r6 = r6.isSpatialAudioEnabled(r1, r5)
            if (r6 != r0) goto L45
            return r0
        L45:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L6d
            com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor r6 = r5.this$0
            com.android.settingslib.media.domain.interactor.SpatializerInteractor r6 = r6.spatializerInteractor
            r2 = 0
            r5.L$0 = r2
            r5.label = r3
            com.android.settingslib.media.data.repository.SpatializerRepositoryImpl r6 = r6.repository
            java.lang.Object r6 = r6.isHeadTrackingEnabled(r1, r5)
            if (r6 != r0) goto L5f
            return r0
        L5f:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r5 = r6.booleanValue()
            if (r5 == 0) goto L6a
            com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel$HeadTrackingEnabled r5 = com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel.HeadTrackingEnabled.INSTANCE
            return r5
        L6a:
            com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel$SpatialAudioEnabled$Companion r5 = com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel.SpatialAudioEnabled.Companion.$$INSTANCE
            return r5
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$isEnabled$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
