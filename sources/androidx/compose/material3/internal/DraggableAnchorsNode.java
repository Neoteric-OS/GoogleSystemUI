package androidx.compose.material3.internal;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.sync.MutexImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DraggableAnchorsNode extends Modifier.Node implements LayoutModifierNode {
    public Function2 anchors;
    public boolean didLookahead;
    public Orientation orientation;
    public AnchoredDraggableState state;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
        if (!measureScope.isLookingAhead() || !this.didLookahead) {
            Pair pair = (Pair) this.anchors.invoke(new IntSize((mo479measureBRTryo0.height & 4294967295L) | (mo479measureBRTryo0.width << 32)), new Constraints(j));
            final AnchoredDraggableState anchoredDraggableState = this.state;
            DraggableAnchors draggableAnchors = (DraggableAnchors) pair.getFirst();
            final Object second = pair.getSecond();
            if (!Intrinsics.areEqual(anchoredDraggableState.getAnchors(), draggableAnchors)) {
                ((SnapshotMutableStateImpl) anchoredDraggableState.anchors$delegate).setValue(draggableAnchors);
                Function0 function0 = new Function0() { // from class: androidx.compose.material3.internal.AnchoredDraggableState$trySnapTo$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        AnchoredDraggableState anchoredDraggableState2 = AnchoredDraggableState.this;
                        AnchoredDraggableState$anchoredDragScope$1 anchoredDraggableState$anchoredDragScope$1 = anchoredDraggableState2.anchoredDragScope;
                        Object obj = second;
                        float positionOf = ((MapDraggableAnchors) anchoredDraggableState2.getAnchors()).positionOf(obj);
                        if (!Float.isNaN(positionOf)) {
                            AnchoredDraggableState anchoredDraggableState3 = anchoredDraggableState$anchoredDragScope$1.this$0;
                            ((SnapshotMutableFloatStateImpl) anchoredDraggableState3.offset$delegate).setFloatValue(positionOf);
                            ((SnapshotMutableFloatStateImpl) anchoredDraggableState3.lastVelocity$delegate).setFloatValue(0.0f);
                            anchoredDraggableState2.setDragTarget(null);
                        }
                        anchoredDraggableState2.setCurrentValue(obj);
                        return Unit.INSTANCE;
                    }
                };
                MutexImpl mutexImpl = anchoredDraggableState.dragMutex.mutex;
                boolean tryLock = mutexImpl.tryLock();
                if (tryLock) {
                    try {
                        function0.invoke();
                    } finally {
                        mutexImpl.unlock(null);
                    }
                }
                if (!tryLock) {
                    anchoredDraggableState.setDragTarget(second);
                }
            }
        }
        this.didLookahead = measureScope.isLookingAhead() || this.didLookahead;
        layout$1 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.material3.internal.DraggableAnchorsNode$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                final float positionOf = MeasureScope.this.isLookingAhead() ? ((MapDraggableAnchors) this.state.getAnchors()).positionOf(this.state.targetValue$delegate.getValue()) : this.state.requireOffset();
                Orientation orientation = this.orientation;
                final float f = orientation == Orientation.Horizontal ? positionOf : 0.0f;
                if (orientation != Orientation.Vertical) {
                    positionOf = 0.0f;
                }
                final Placeable placeable = mo479measureBRTryo0;
                Function1 function1 = new Function1() { // from class: androidx.compose.material3.internal.DraggableAnchorsNode$measure$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        ((Placeable.PlacementScope) obj2).place(Placeable.this, MathKt.roundToInt(f), MathKt.roundToInt(positionOf), 0.0f);
                        return Unit.INSTANCE;
                    }
                };
                placementScope.motionFrameOfReferencePlacement = true;
                function1.invoke(placementScope);
                placementScope.motionFrameOfReferencePlacement = false;
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        this.didLookahead = false;
    }
}
