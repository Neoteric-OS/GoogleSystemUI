package com.android.systemui.media.controls.ui.viewmodel;

import com.android.systemui.media.controls.domain.pipeline.interactor.MediaControlInteractor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaControlViewModel$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ MediaControlViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaControlViewModel$special$$inlined$flatMapLatest$1(Continuation continuation, MediaControlViewModel mediaControlViewModel) {
        super(3, continuation);
        this.this$0 = mediaControlViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        MediaControlViewModel$special$$inlined$flatMapLatest$1 mediaControlViewModel$special$$inlined$flatMapLatest$1 = new MediaControlViewModel$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        mediaControlViewModel$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        mediaControlViewModel$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return mediaControlViewModel$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            MediaControlViewModel mediaControlViewModel = this.this$0;
            MediaControlInteractor mediaControlInteractor = mediaControlViewModel.interactor;
            this.label = 1;
            FlowKt.ensureActive(flowCollector);
            Object collect = mediaControlInteractor.mediaControl.collect(new MediaControlViewModel$player$lambda$2$$inlined$map$1$2(flowCollector, mediaControlViewModel), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
