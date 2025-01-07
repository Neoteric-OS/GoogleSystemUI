package com.android.systemui.volume.panel.component.volume.ui.viewmodel;

import com.android.systemui.volume.panel.component.volume.ui.viewmodel.SlidersExpandableViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AudioVolumeComponentViewModel$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ AudioVolumeComponentViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioVolumeComponentViewModel$special$$inlined$flatMapLatest$2(AudioVolumeComponentViewModel audioVolumeComponentViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = audioVolumeComponentViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AudioVolumeComponentViewModel$special$$inlined$flatMapLatest$2 audioVolumeComponentViewModel$special$$inlined$flatMapLatest$2 = new AudioVolumeComponentViewModel$special$$inlined$flatMapLatest$2(this.this$0, (Continuation) obj3);
        audioVolumeComponentViewModel$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        audioVolumeComponentViewModel$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return audioVolumeComponentViewModel$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow audioVolumeComponentViewModel$isActive$lambda$1$$inlined$map$1 = ((Boolean) this.L$1).booleanValue() ? new AudioVolumeComponentViewModel$isActive$lambda$1$$inlined$map$1(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(this.this$0.mutableIsExpanded), 1) : new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(SlidersExpandableViewModel.Fixed.INSTANCE);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, audioVolumeComponentViewModel$isActive$lambda$1$$inlined$map$1, this) == coroutineSingletons) {
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
