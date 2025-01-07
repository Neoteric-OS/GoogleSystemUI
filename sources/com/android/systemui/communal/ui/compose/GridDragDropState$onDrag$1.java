package com.android.systemui.communal.ui.compose;

import androidx.compose.foundation.lazy.grid.LazyGridItemInfo;
import androidx.compose.foundation.lazy.grid.LazyGridMeasuredItem;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class GridDragDropState$onDrag$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ LazyGridItemInfo $draggingItem;
    final /* synthetic */ Integer $scrollToIndex;
    final /* synthetic */ LazyGridItemInfo $targetItem;
    int label;
    final /* synthetic */ GridDragDropState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GridDragDropState$onDrag$1(GridDragDropState gridDragDropState, Integer num, LazyGridItemInfo lazyGridItemInfo, LazyGridItemInfo lazyGridItemInfo2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = gridDragDropState;
        this.$scrollToIndex = num;
        this.$draggingItem = lazyGridItemInfo;
        this.$targetItem = lazyGridItemInfo2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new GridDragDropState$onDrag$1(this.this$0, this.$scrollToIndex, this.$draggingItem, this.$targetItem, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((GridDragDropState$onDrag$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LazyGridState lazyGridState = this.this$0.state;
            int intValue = this.$scrollToIndex.intValue();
            int scrollOffset = this.this$0.state.scrollPosition.getScrollOffset();
            this.label = 1;
            if (lazyGridState.scrollToItem(intValue, scrollOffset, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        ContentListState contentListState = this.this$0.contentListState;
        int i2 = ((LazyGridMeasuredItem) this.$draggingItem).index;
        int i3 = ((LazyGridMeasuredItem) this.$targetItem).index;
        SnapshotStateList snapshotStateList = contentListState.list;
        snapshotStateList.add(i3, snapshotStateList.remove(i2));
        return Unit.INSTANCE;
    }
}
