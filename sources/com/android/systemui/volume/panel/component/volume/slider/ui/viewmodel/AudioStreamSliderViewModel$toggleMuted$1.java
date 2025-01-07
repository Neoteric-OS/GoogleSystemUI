package com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel;

import com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor;
import com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel.AudioStreamSliderViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class AudioStreamSliderViewModel$toggleMuted$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ AudioStreamSliderViewModel.State $audioViewModel;
    int label;
    final /* synthetic */ AudioStreamSliderViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioStreamSliderViewModel$toggleMuted$1(AudioStreamSliderViewModel audioStreamSliderViewModel, AudioStreamSliderViewModel.State state, Continuation continuation) {
        super(2, continuation);
        this.this$0 = audioStreamSliderViewModel;
        this.$audioViewModel = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new AudioStreamSliderViewModel$toggleMuted$1(this.this$0, this.$audioViewModel, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((AudioStreamSliderViewModel$toggleMuted$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AudioStreamSliderViewModel audioStreamSliderViewModel = this.this$0;
            AudioVolumeInteractor audioVolumeInteractor = audioStreamSliderViewModel.audioVolumeInteractor;
            boolean z = !this.$audioViewModel.audioStreamModel.isMuted;
            this.label = 1;
            if (audioVolumeInteractor.m769setMutedZdW0WiI(audioStreamSliderViewModel.audioStream, this, z) == coroutineSingletons) {
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
