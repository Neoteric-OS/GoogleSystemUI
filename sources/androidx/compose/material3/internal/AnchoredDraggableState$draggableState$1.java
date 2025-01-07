package androidx.compose.material3.internal;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnchoredDraggableState$draggableState$1 implements DraggableState {
    public final AnchoredDraggableState$draggableState$1$dragScope$1 dragScope;
    public final /* synthetic */ AnchoredDraggableState this$0;

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.material3.internal.AnchoredDraggableState$draggableState$1$dragScope$1] */
    public AnchoredDraggableState$draggableState$1(final AnchoredDraggableState anchoredDraggableState) {
        this.this$0 = anchoredDraggableState;
        this.dragScope = new DragScope() { // from class: androidx.compose.material3.internal.AnchoredDraggableState$draggableState$1$dragScope$1
            @Override // androidx.compose.foundation.gestures.DragScope
            public final void dragBy(float f) {
                AnchoredDraggableState anchoredDraggableState2 = AnchoredDraggableState.this;
                AnchoredDraggableState$anchoredDragScope$1 anchoredDraggableState$anchoredDragScope$1 = anchoredDraggableState2.anchoredDragScope;
                float newOffsetForDelta$material3_release = anchoredDraggableState2.newOffsetForDelta$material3_release(f);
                AnchoredDraggableState anchoredDraggableState3 = anchoredDraggableState$anchoredDragScope$1.this$0;
                ((SnapshotMutableFloatStateImpl) anchoredDraggableState3.offset$delegate).setFloatValue(newOffsetForDelta$material3_release);
                ((SnapshotMutableFloatStateImpl) anchoredDraggableState3.lastVelocity$delegate).setFloatValue(0.0f);
            }
        };
    }

    @Override // androidx.compose.foundation.gestures.DraggableState
    public final Object drag(Continuation continuation, Function2 function2) {
        Object anchoredDrag = this.this$0.anchoredDrag(MutatePriority.UserInput, new AnchoredDraggableState$draggableState$1$drag$2(this, function2, null), (ContinuationImpl) continuation);
        return anchoredDrag == CoroutineSingletons.COROUTINE_SUSPENDED ? anchoredDrag : Unit.INSTANCE;
    }
}
