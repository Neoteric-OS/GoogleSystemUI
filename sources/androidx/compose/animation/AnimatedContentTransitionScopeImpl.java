package androidx.compose.animation;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnimatedContentTransitionScopeImpl implements AnimatedContentTransitionScope {
    public Alignment contentAlignment;
    public final MutableState measuredSize$delegate = SnapshotStateKt.mutableStateOf$default(new IntSize(0));
    public final MutableScatterMap targetSizeMap;
    public final Transition transition;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class SizeModifier extends LayoutModifierWithPassThroughIntrinsics {
        public final Transition.DeferredAnimation sizeAnimation;
        public final MutableState sizeTransform;

        public SizeModifier(Transition.DeferredAnimation deferredAnimation, MutableState mutableState) {
            this.sizeAnimation = deferredAnimation;
            this.sizeTransform = mutableState;
        }

        @Override // androidx.compose.ui.layout.LayoutModifier
        /* renamed from: measure-3p2s80s, reason: not valid java name */
        public final MeasureResult mo3measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
            final long j2;
            MeasureResult layout$1;
            final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
            final AnimatedContentTransitionScopeImpl animatedContentTransitionScopeImpl = AnimatedContentTransitionScopeImpl.this;
            Transition.DeferredAnimation.DeferredAnimationData animate = this.sizeAnimation.animate(new Function1() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$SizeModifier$measure$size$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Transition.Segment segment = (Transition.Segment) obj;
                    State state = (State) AnimatedContentTransitionScopeImpl.this.targetSizeMap.get(segment.getInitialState());
                    long j3 = state != null ? ((IntSize) state.getValue()).packedValue : 0L;
                    State state2 = (State) AnimatedContentTransitionScopeImpl.this.targetSizeMap.get(segment.getTargetState());
                    long j4 = state2 != null ? ((IntSize) state2.getValue()).packedValue : 0L;
                    SizeTransform sizeTransform = (SizeTransform) this.sizeTransform.getValue();
                    if (sizeTransform != null) {
                        FiniteAnimationSpec finiteAnimationSpec = (FiniteAnimationSpec) ((SizeTransformImpl) sizeTransform).sizeAnimationSpec.invoke(new IntSize(j3), new IntSize(j4));
                        if (finiteAnimationSpec != null) {
                            return finiteAnimationSpec;
                        }
                    }
                    return AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7);
                }
            }, new Function1() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$SizeModifier$measure$size$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    State state = (State) AnimatedContentTransitionScopeImpl.this.targetSizeMap.get(obj);
                    return new IntSize(state != null ? ((IntSize) state.getValue()).packedValue : 0L);
                }
            });
            if (measureScope.isLookingAhead()) {
                j2 = (mo479measureBRTryo0.width << 32) | (mo479measureBRTryo0.height & 4294967295L);
            } else {
                j2 = ((IntSize) animate.getValue()).packedValue;
            }
            layout$1 = measureScope.layout$1((int) (j2 >> 32), (int) (4294967295L & j2), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$SizeModifier$measure$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Alignment alignment = AnimatedContentTransitionScopeImpl.this.contentAlignment;
                    Placeable placeable = mo479measureBRTryo0;
                    Placeable.PlacementScope.m495place70tqf50$default((Placeable.PlacementScope) obj, mo479measureBRTryo0, alignment.mo274alignKFBX0sM((placeable.width << 32) | (placeable.height & 4294967295L), j2, LayoutDirection.Ltr));
                    return Unit.INSTANCE;
                }
            });
            return layout$1;
        }
    }

    public AnimatedContentTransitionScopeImpl(Transition transition, Alignment alignment) {
        this.transition = transition;
        this.contentAlignment = alignment;
        long[] jArr = ScatterMapKt.EmptyGroup;
        this.targetSizeMap = new MutableScatterMap();
    }

    @Override // androidx.compose.animation.core.Transition.Segment
    public final Object getInitialState() {
        return this.transition.getSegment().getInitialState();
    }

    @Override // androidx.compose.animation.core.Transition.Segment
    public final Object getTargetState() {
        return this.transition.getSegment().getTargetState();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ChildData implements ParentDataModifier {
        public final MutableState isTarget$delegate;

        public ChildData(boolean z) {
            this.isTarget$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z));
        }

        @Override // androidx.compose.ui.layout.ParentDataModifier
        public final Object modifyParentData() {
            return this;
        }
    }
}
