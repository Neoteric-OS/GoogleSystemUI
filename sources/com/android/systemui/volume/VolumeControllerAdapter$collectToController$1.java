package com.android.systemui.volume;

import android.media.IVolumeController;
import com.android.settingslib.volume.data.model.VolumeControllerEvent;
import com.android.settingslib.volume.data.repository.AudioRepositoryImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class VolumeControllerAdapter$collectToController$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ IVolumeController $controller;
    int label;
    final /* synthetic */ VolumeControllerAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumeControllerAdapter$collectToController$1(VolumeControllerAdapter volumeControllerAdapter, IVolumeController iVolumeController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = volumeControllerAdapter;
        this.$controller = iVolumeController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new VolumeControllerAdapter$collectToController$1(this.this$0, this.$controller, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((VolumeControllerAdapter$collectToController$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow flow = ((AudioRepositoryImpl) this.this$0.audioRepository).volumeControllerEvents;
            final IVolumeController iVolumeController = this.$controller;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.volume.VolumeControllerAdapter$collectToController$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    VolumeControllerEvent volumeControllerEvent = (VolumeControllerEvent) obj2;
                    if (volumeControllerEvent instanceof VolumeControllerEvent.VolumeChanged) {
                        VolumeControllerEvent.VolumeChanged volumeChanged = (VolumeControllerEvent.VolumeChanged) volumeControllerEvent;
                        iVolumeController.volumeChanged(volumeChanged.streamType, volumeChanged.flags);
                    } else if (Intrinsics.areEqual(volumeControllerEvent, VolumeControllerEvent.Dismiss.INSTANCE)) {
                        iVolumeController.dismiss();
                    } else if (volumeControllerEvent instanceof VolumeControllerEvent.DisplayCsdWarning) {
                        VolumeControllerEvent.DisplayCsdWarning displayCsdWarning = (VolumeControllerEvent.DisplayCsdWarning) volumeControllerEvent;
                        iVolumeController.displayCsdWarning(displayCsdWarning.csdWarning, displayCsdWarning.displayDurationMs);
                    } else if (volumeControllerEvent instanceof VolumeControllerEvent.DisplaySafeVolumeWarning) {
                        iVolumeController.displaySafeVolumeWarning(((VolumeControllerEvent.DisplaySafeVolumeWarning) volumeControllerEvent).flags);
                    } else if (volumeControllerEvent instanceof VolumeControllerEvent.MasterMuteChanged) {
                        iVolumeController.masterMuteChanged(((VolumeControllerEvent.MasterMuteChanged) volumeControllerEvent).flags);
                    } else if (volumeControllerEvent instanceof VolumeControllerEvent.SetA11yMode) {
                        iVolumeController.setA11yMode(((VolumeControllerEvent.SetA11yMode) volumeControllerEvent).mode);
                    } else if (volumeControllerEvent instanceof VolumeControllerEvent.SetLayoutDirection) {
                        iVolumeController.setLayoutDirection(((VolumeControllerEvent.SetLayoutDirection) volumeControllerEvent).layoutDirection);
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (flow.collect(flowCollector, this) == coroutineSingletons) {
                return coroutineSingletons;
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
