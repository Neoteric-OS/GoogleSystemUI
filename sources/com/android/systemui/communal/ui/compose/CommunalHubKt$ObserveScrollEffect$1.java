package com.android.systemui.communal.ui.compose;

import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.runtime.SnapshotStateKt;
import com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalHubKt$ObserveScrollEffect$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ BaseCommunalViewModel $communalViewModel;
    final /* synthetic */ LazyGridState $gridState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalHubKt$ObserveScrollEffect$1(LazyGridState lazyGridState, BaseCommunalViewModel baseCommunalViewModel, Continuation continuation) {
        super(2, continuation);
        this.$gridState = lazyGridState;
        this.$communalViewModel = baseCommunalViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalHubKt$ObserveScrollEffect$1(this.$gridState, this.$communalViewModel, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CommunalHubKt$ObserveScrollEffect$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final LazyGridState lazyGridState = this.$gridState;
            SafeFlow snapshotFlow = SnapshotStateKt.snapshotFlow(new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$ObserveScrollEffect$1.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new Pair(Integer.valueOf(LazyGridState.this.scrollPosition.getIndex()), Integer.valueOf(LazyGridState.this.scrollPosition.getScrollOffset()));
                }
            });
            final BaseCommunalViewModel baseCommunalViewModel = this.$communalViewModel;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$ObserveScrollEffect$1.2
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    Pair pair = (Pair) obj2;
                    int intValue = ((Number) pair.getFirst()).intValue();
                    int intValue2 = ((Number) pair.getSecond()).intValue();
                    BaseCommunalViewModel baseCommunalViewModel2 = BaseCommunalViewModel.this;
                    baseCommunalViewModel2.currentScrollIndex = intValue;
                    baseCommunalViewModel2.currentScrollOffset = intValue2;
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (snapshotFlow.collect(flowCollector, this) == coroutineSingletons) {
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
