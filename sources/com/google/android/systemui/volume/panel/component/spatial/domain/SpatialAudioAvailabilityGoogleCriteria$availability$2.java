package com.google.android.systemui.volume.panel.component.spatial.domain;

import com.android.internal.logging.UiEventLogger;
import com.android.systemui.volume.panel.ui.VolumePanelUiEvent;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SpatialAudioAvailabilityGoogleCriteria$availability$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ UiEventLogger $uiEventLogger;
    /* synthetic */ boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpatialAudioAvailabilityGoogleCriteria$availability$2(UiEventLogger uiEventLogger, Continuation continuation) {
        super(2, continuation);
        this.$uiEventLogger = uiEventLogger;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SpatialAudioAvailabilityGoogleCriteria$availability$2 spatialAudioAvailabilityGoogleCriteria$availability$2 = new SpatialAudioAvailabilityGoogleCriteria$availability$2(this.$uiEventLogger, continuation);
        spatialAudioAvailabilityGoogleCriteria$availability$2.Z$0 = ((Boolean) obj).booleanValue();
        return spatialAudioAvailabilityGoogleCriteria$availability$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        SpatialAudioAvailabilityGoogleCriteria$availability$2 spatialAudioAvailabilityGoogleCriteria$availability$2 = (SpatialAudioAvailabilityGoogleCriteria$availability$2) create(bool, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        spatialAudioAvailabilityGoogleCriteria$availability$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.$uiEventLogger.log(this.Z$0 ? VolumePanelUiEvent.VOLUME_PANEL_SPATIAL_AUDIO_BUTTON_SHOWN : VolumePanelUiEvent.VOLUME_PANEL_SPATIAL_AUDIO_BUTTON_GONE);
        return Unit.INSTANCE;
    }
}
