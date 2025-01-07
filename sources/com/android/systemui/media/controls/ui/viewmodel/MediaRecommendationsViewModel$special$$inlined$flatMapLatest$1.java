package com.android.systemui.media.controls.ui.viewmodel;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaRecommendationsViewModel$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ MediaRecommendationsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRecommendationsViewModel$special$$inlined$flatMapLatest$1(Continuation continuation, MediaRecommendationsViewModel mediaRecommendationsViewModel) {
        super(3, continuation);
        this.this$0 = mediaRecommendationsViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        MediaRecommendationsViewModel$special$$inlined$flatMapLatest$1 mediaRecommendationsViewModel$special$$inlined$flatMapLatest$1 = new MediaRecommendationsViewModel$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        mediaRecommendationsViewModel$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        mediaRecommendationsViewModel$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return mediaRecommendationsViewModel$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            MediaRecommendationsViewModel mediaRecommendationsViewModel = this.this$0;
            Flow flow = mediaRecommendationsViewModel.interactor.recommendations;
            this.label = 1;
            FlowKt.ensureActive(flowCollector);
            Object collect = flow.collect(new MediaRecommendationsViewModel$mediaRecsCard$lambda$1$$inlined$map$1$2(flowCollector, mediaRecommendationsViewModel), this);
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
