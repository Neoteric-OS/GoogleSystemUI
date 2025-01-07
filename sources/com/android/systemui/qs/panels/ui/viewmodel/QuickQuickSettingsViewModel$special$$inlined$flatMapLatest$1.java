package com.android.systemui.qs.panels.ui.viewmodel;

import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QuickQuickSettingsViewModel$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ CurrentTilesInteractor $tilesInteractor$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ QuickQuickSettingsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QuickQuickSettingsViewModel$special$$inlined$flatMapLatest$1(Continuation continuation, CurrentTilesInteractor currentTilesInteractor, QuickQuickSettingsViewModel quickQuickSettingsViewModel) {
        super(3, continuation);
        this.$tilesInteractor$inlined = currentTilesInteractor;
        this.this$0 = quickQuickSettingsViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        QuickQuickSettingsViewModel$special$$inlined$flatMapLatest$1 quickQuickSettingsViewModel$special$$inlined$flatMapLatest$1 = new QuickQuickSettingsViewModel$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$tilesInteractor$inlined, this.this$0);
        quickQuickSettingsViewModel$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        quickQuickSettingsViewModel$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return quickQuickSettingsViewModel$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            int intValue = ((Number) this.L$1).intValue();
            ReadonlyStateFlow readonlyStateFlow = ((CurrentTilesInteractorImpl) this.$tilesInteractor$inlined).currentTiles;
            QuickQuickSettingsViewModel quickQuickSettingsViewModel = this.this$0;
            ChannelFlowTransformLatest mapLatest = FlowKt.mapLatest(new QuickQuickSettingsViewModel$tileViewModels$1$3(quickQuickSettingsViewModel, intValue, null), new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow, quickQuickSettingsViewModel.rows, QuickQuickSettingsViewModel$tileViewModels$1$2.INSTANCE));
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, mapLatest, this) == coroutineSingletons) {
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
