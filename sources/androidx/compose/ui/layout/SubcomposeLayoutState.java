package androidx.compose.ui.layout;

import androidx.compose.runtime.CompositionContext;
import androidx.compose.ui.layout.LayoutNodeSubcompositionsState;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SubcomposeLayoutState {
    public LayoutNodeSubcompositionsState _state;
    public final SubcomposeSlotReusePolicy slotReusePolicy;
    public final Function2 setRoot = new Function2() { // from class: androidx.compose.ui.layout.SubcomposeLayoutState$setRoot$1
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            LayoutNode layoutNode = (LayoutNode) obj;
            SubcomposeLayoutState subcomposeLayoutState = SubcomposeLayoutState.this;
            LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = layoutNode.subcompositionsState;
            if (layoutNodeSubcompositionsState == null) {
                layoutNodeSubcompositionsState = new LayoutNodeSubcompositionsState(layoutNode, subcomposeLayoutState.slotReusePolicy);
                layoutNode.subcompositionsState = layoutNodeSubcompositionsState;
            }
            subcomposeLayoutState._state = layoutNodeSubcompositionsState;
            SubcomposeLayoutState.this.getState().makeSureStateIsConsistent();
            LayoutNodeSubcompositionsState state = SubcomposeLayoutState.this.getState();
            SubcomposeSlotReusePolicy subcomposeSlotReusePolicy = SubcomposeLayoutState.this.slotReusePolicy;
            if (state.slotReusePolicy != subcomposeSlotReusePolicy) {
                state.slotReusePolicy = subcomposeSlotReusePolicy;
                state.markActiveNodesAsReused(false);
                LayoutNode.requestRemeasure$ui_release$default(state.root, false, 7);
            }
            return Unit.INSTANCE;
        }
    };
    public final Function2 setCompositionContext = new Function2() { // from class: androidx.compose.ui.layout.SubcomposeLayoutState$setCompositionContext$1
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SubcomposeLayoutState.this.getState().compositionContext = (CompositionContext) obj2;
            return Unit.INSTANCE;
        }
    };
    public final Function2 setMeasurePolicy = new Function2() { // from class: androidx.compose.ui.layout.SubcomposeLayoutState$setMeasurePolicy$1
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            final Function2 function2 = (Function2) obj2;
            final LayoutNodeSubcompositionsState state = SubcomposeLayoutState.this.getState();
            ((LayoutNode) obj).setMeasurePolicy(new LayoutNode.NoIntrinsicsMeasurePolicy(state.NoIntrinsicsMessage) { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$createMeasurePolicy$1
                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* renamed from: measure-3p2s80s */
                public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, List list, long j) {
                    final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
                    LayoutDirection layoutDirection = measureScope.getLayoutDirection();
                    LayoutNodeSubcompositionsState.Scope scope = layoutNodeSubcompositionsState.scope;
                    scope.layoutDirection = layoutDirection;
                    scope.density = measureScope.getDensity();
                    scope.fontScale = measureScope.getFontScale();
                    boolean isLookingAhead = measureScope.isLookingAhead();
                    Function2 function22 = function2;
                    if (isLookingAhead || layoutNodeSubcompositionsState.root.lookaheadRoot == null) {
                        layoutNodeSubcompositionsState.currentIndex = 0;
                        final MeasureResult measureResult = (MeasureResult) function22.invoke(scope, new Constraints(j));
                        final int i = layoutNodeSubcompositionsState.currentIndex;
                        return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$createMeasurePolicy$1$measure-3p2s80s$$inlined$createMeasureResult$2
                            @Override // androidx.compose.ui.layout.MeasureResult
                            public final Map getAlignmentLines() {
                                return measureResult.getAlignmentLines();
                            }

                            @Override // androidx.compose.ui.layout.MeasureResult
                            public final int getHeight() {
                                return measureResult.getHeight();
                            }

                            @Override // androidx.compose.ui.layout.MeasureResult
                            public final Function1 getRulers() {
                                return measureResult.getRulers();
                            }

                            @Override // androidx.compose.ui.layout.MeasureResult
                            public final int getWidth() {
                                return measureResult.getWidth();
                            }

                            @Override // androidx.compose.ui.layout.MeasureResult
                            public final void placeChildren() {
                                LayoutNodeSubcompositionsState layoutNodeSubcompositionsState2 = layoutNodeSubcompositionsState;
                                layoutNodeSubcompositionsState2.currentIndex = i;
                                measureResult.placeChildren();
                                layoutNodeSubcompositionsState2.disposeOrReuseStartingFromIndex(layoutNodeSubcompositionsState2.currentIndex);
                            }
                        };
                    }
                    layoutNodeSubcompositionsState.currentPostLookaheadIndex = 0;
                    final MeasureResult measureResult2 = (MeasureResult) function22.invoke(layoutNodeSubcompositionsState.postLookaheadMeasureScope, new Constraints(j));
                    final int i2 = layoutNodeSubcompositionsState.currentPostLookaheadIndex;
                    return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$createMeasurePolicy$1$measure-3p2s80s$$inlined$createMeasureResult$1
                        @Override // androidx.compose.ui.layout.MeasureResult
                        public final Map getAlignmentLines() {
                            return measureResult2.getAlignmentLines();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        public final int getHeight() {
                            return measureResult2.getHeight();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        public final Function1 getRulers() {
                            return measureResult2.getRulers();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        public final int getWidth() {
                            return measureResult2.getWidth();
                        }

                        @Override // androidx.compose.ui.layout.MeasureResult
                        public final void placeChildren() {
                            final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState2 = layoutNodeSubcompositionsState;
                            layoutNodeSubcompositionsState2.currentPostLookaheadIndex = i2;
                            measureResult2.placeChildren();
                            CollectionsKt__MutableCollectionsKt.filterInPlace$CollectionsKt__MutableCollectionsKt(layoutNodeSubcompositionsState2.postLookaheadPrecomposeSlotHandleMap.entrySet(), new Function1() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$disposeUnusedSlotsInPostLookahead$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj3) {
                                    boolean z;
                                    Map.Entry entry = (Map.Entry) obj3;
                                    Object key = entry.getKey();
                                    SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = (SubcomposeLayoutState.PrecomposedSlotHandle) entry.getValue();
                                    int indexOf = LayoutNodeSubcompositionsState.this.postLookaheadComposedSlotIds.indexOf(key);
                                    if (indexOf < 0 || indexOf >= LayoutNodeSubcompositionsState.this.currentPostLookaheadIndex) {
                                        precomposedSlotHandle.dispose();
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    return Boolean.valueOf(z);
                                }
                            }, true);
                        }
                    };
                }
            });
            return Unit.INSTANCE;
        }
    };

    public SubcomposeLayoutState(SubcomposeSlotReusePolicy subcomposeSlotReusePolicy) {
        this.slotReusePolicy = subcomposeSlotReusePolicy;
    }

    public final LayoutNodeSubcompositionsState getState() {
        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = this._state;
        if (layoutNodeSubcompositionsState != null) {
            return layoutNodeSubcompositionsState;
        }
        throw new IllegalArgumentException("SubcomposeLayoutState is not attached to SubcomposeLayout");
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface PrecomposedSlotHandle {
        void dispose();

        default int getPlaceablesCount() {
            return 0;
        }

        default void traverseDescendants(Function1 function1) {
        }

        /* renamed from: premeasure-0kLqBqw */
        default void mo490premeasure0kLqBqw(long j, int i) {
        }
    }
}
