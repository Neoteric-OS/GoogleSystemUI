package androidx.compose.foundation.gestures;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DraggableNode extends DragGestureNode {
    public Function3 onDragStarted;
    public Function3 onDragStopped;
    public Orientation orientation;
    public boolean reverseDirection;
    public boolean startDragImmediately;
    public DraggableState state;

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public final Object drag(Continuation continuation, Function2 function2) {
        Object drag = this.state.drag(continuation, new DraggableNode$drag$2(function2, this, null));
        return drag == CoroutineSingletons.COROUTINE_SUSPENDED ? drag : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* renamed from: onDragStarted-k-4lQ0M */
    public final void mo66onDragStartedk4lQ0M(long j) {
        if (!this.isAttached || Intrinsics.areEqual(this.onDragStarted, DraggableKt.NoOpOnDragStarted)) {
            return;
        }
        BuildersKt.launch$default(getCoroutineScope(), null, null, new DraggableNode$onDragStarted$1(this, j, null), 3);
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* renamed from: onDragStopped-TH1AsA0 */
    public final void mo67onDragStoppedTH1AsA0(long j) {
        if (!this.isAttached || Intrinsics.areEqual(this.onDragStopped, DraggableKt.NoOpOnDragStopped)) {
            return;
        }
        BuildersKt.launch$default(getCoroutineScope(), null, null, new DraggableNode$onDragStopped$1(this, j, null), 3);
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public final boolean startDragImmediately() {
        return this.startDragImmediately;
    }
}
