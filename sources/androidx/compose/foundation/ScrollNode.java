package androidx.compose.foundation;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScrollNode extends Modifier.Node implements LayoutModifierNode, SemanticsModifierNode {
    public boolean isVertical;
    public boolean reverseScrolling;
    public ScrollState state;

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver);
        ScrollAxisRange scrollAxisRange = new ScrollAxisRange(new Function0() { // from class: androidx.compose.foundation.ScrollNode$applySemantics$accessibilityScrollState$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Float.valueOf(ScrollNode.this.state.getValue());
            }
        }, new Function0() { // from class: androidx.compose.foundation.ScrollNode$applySemantics$accessibilityScrollState$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Float.valueOf(((SnapshotMutableIntStateImpl) ScrollNode.this.state._maxValueState).getIntValue());
            }
        }, this.reverseScrolling);
        if (this.isVertical) {
            SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.VerticalScrollAxisRange;
            KProperty kProperty = SemanticsPropertiesKt.$$delegatedProperties[11];
            semanticsPropertyKey.setValue(semanticsPropertyReceiver, scrollAxisRange);
        } else {
            SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.HorizontalScrollAxisRange;
            KProperty kProperty2 = SemanticsPropertiesKt.$$delegatedProperties[10];
            semanticsPropertyKey2.setValue(semanticsPropertyReceiver, scrollAxisRange);
        }
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return this.isVertical ? intrinsicMeasurable.maxIntrinsicHeight(i) : intrinsicMeasurable.maxIntrinsicHeight(Integer.MAX_VALUE);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return this.isVertical ? intrinsicMeasurable.maxIntrinsicWidth(Integer.MAX_VALUE) : intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        CheckScrollableContainerConstraintsKt.m29checkScrollableContainerConstraintsK40F9xA(j, this.isVertical ? Orientation.Vertical : Orientation.Horizontal);
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(Constraints.m648copyZbe2FdA$default(j, 0, this.isVertical ? Constraints.m655getMaxWidthimpl(j) : Integer.MAX_VALUE, 0, this.isVertical ? Integer.MAX_VALUE : Constraints.m654getMaxHeightimpl(j), 5));
        int i = mo479measureBRTryo0.width;
        int m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j);
        if (i > m655getMaxWidthimpl) {
            i = m655getMaxWidthimpl;
        }
        int i2 = mo479measureBRTryo0.height;
        int m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
        if (i2 > m654getMaxHeightimpl) {
            i2 = m654getMaxHeightimpl;
        }
        final int i3 = mo479measureBRTryo0.height - i2;
        int i4 = mo479measureBRTryo0.width - i;
        if (!this.isVertical) {
            i3 = i4;
        }
        ScrollState scrollState = this.state;
        ((SnapshotMutableIntStateImpl) scrollState._maxValueState).setIntValue(i3);
        Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot makeCurrentNonObservable = Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            if (scrollState.getValue() > i3) {
                ((SnapshotMutableIntStateImpl) scrollState.value$delegate).setIntValue(i3);
            }
            Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
            ((SnapshotMutableIntStateImpl) this.state.viewportSize$delegate).setIntValue(this.isVertical ? i2 : i);
            layout$1 = measureScope.layout$1(i, i2, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.ScrollNode$measure$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                    int value = ScrollNode.this.state.getValue();
                    int i5 = i3;
                    if (value < 0) {
                        value = 0;
                    }
                    if (value > i5) {
                        value = i5;
                    }
                    ScrollNode scrollNode = ScrollNode.this;
                    final int i6 = scrollNode.reverseScrolling ? value - i5 : -value;
                    boolean z = scrollNode.isVertical;
                    final int i7 = z ? 0 : i6;
                    if (!z) {
                        i6 = 0;
                    }
                    final Placeable placeable = mo479measureBRTryo0;
                    Function1 function1 = new Function1() { // from class: androidx.compose.foundation.ScrollNode$measure$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            Placeable.PlacementScope.placeRelativeWithLayer$default((Placeable.PlacementScope) obj2, placeable, i7, i6);
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
        } catch (Throwable th) {
            Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
            throw th;
        }
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return this.isVertical ? intrinsicMeasurable.minIntrinsicHeight(i) : intrinsicMeasurable.minIntrinsicHeight(Integer.MAX_VALUE);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return this.isVertical ? intrinsicMeasurable.minIntrinsicWidth(Integer.MAX_VALUE) : intrinsicMeasurable.minIntrinsicWidth(i);
    }
}
