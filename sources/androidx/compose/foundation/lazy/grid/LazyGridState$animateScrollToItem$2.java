package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.ui.unit.Density;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyGridState$animateScrollToItem$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $index;
    final /* synthetic */ int $scrollOffset;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ LazyGridState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyGridState$animateScrollToItem$2(LazyGridState lazyGridState, int i, int i2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lazyGridState;
        this.$index = i;
        this.$scrollOffset = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        LazyGridState$animateScrollToItem$2 lazyGridState$animateScrollToItem$2 = new LazyGridState$animateScrollToItem$2(this.this$0, this.$index, this.$scrollOffset, continuation);
        lazyGridState$animateScrollToItem$2.L$0 = obj;
        return lazyGridState$animateScrollToItem$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((LazyGridState$animateScrollToItem$2) create((ScrollScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ScrollScope scrollScope = (ScrollScope) this.L$0;
            LazyGridState lazyGridState = this.this$0;
            LazyGridScrollScopeKt$LazyLayoutScrollScope$1 lazyGridScrollScopeKt$LazyLayoutScrollScope$1 = new LazyGridScrollScopeKt$LazyLayoutScrollScope$1(scrollScope, lazyGridState);
            int i2 = this.$index;
            int i3 = this.$scrollOffset;
            SaverKt$Saver$1 saverKt$Saver$1 = LazyGridState.Saver;
            int i4 = ((LazyGridMeasureResult) ((SnapshotMutableStateImpl) lazyGridState.layoutInfoState).getValue()).slotsPerLine * 100;
            Density density = ((LazyGridMeasureResult) ((SnapshotMutableStateImpl) this.this$0.layoutInfoState).getValue()).density;
            this.label = 1;
            if (LazyLayoutScrollScopeKt.animateScrollToItem(lazyGridScrollScopeKt$LazyLayoutScrollScope$1, i2, i3, i4, density, scrollScope, this) == coroutineSingletons) {
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
