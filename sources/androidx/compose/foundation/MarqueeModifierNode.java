package androidx.compose.foundation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusStateImpl;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MarqueeModifierNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, FocusEventModifierNode {
    public StandaloneCoroutine animationJob;
    public int initialDelayMillis;
    public int iterations;
    public final MutableState spacing$delegate;
    public final State spacingPx$delegate;
    public float velocity;
    public final MutableIntState contentWidth$delegate = SnapshotIntStateKt.mutableIntStateOf(0);
    public final MutableIntState containerWidth$delegate = SnapshotIntStateKt.mutableIntStateOf(0);
    public final MutableState hasFocus$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
    public final MutableState animationMode$delegate = SnapshotStateKt.mutableStateOf$default(new MarqueeAnimationMode());
    public final Animatable offset = AnimatableKt.Animatable(0.0f, 0.01f);

    public MarqueeModifierNode(int i, int i2, final MarqueeSpacing$Companion$$ExternalSyntheticLambda0 marqueeSpacing$Companion$$ExternalSyntheticLambda0, float f) {
        this.iterations = i;
        this.initialDelayMillis = i2;
        this.velocity = f;
        this.spacing$delegate = SnapshotStateKt.mutableStateOf$default(marqueeSpacing$Companion$$ExternalSyntheticLambda0);
        this.spacingPx$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.MarqueeModifierNode$spacingPx$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                MarqueeSpacing marqueeSpacing = marqueeSpacing$Companion$$ExternalSyntheticLambda0;
                MarqueeModifierNode marqueeModifierNode = this;
                Density density = DelegatableNodeKt.requireLayoutNode(marqueeModifierNode).density;
                marqueeModifierNode.getContentWidth();
                float containerWidth = marqueeModifierNode.getContainerWidth();
                ((MarqueeSpacing$Companion$$ExternalSyntheticLambda0) marqueeSpacing).getClass();
                return Integer.valueOf(MathKt.roundToInt(0.33333334f * containerWidth));
            }
        });
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
        Animatable animatable = this.offset;
        float floatValue = ((Number) animatable.internalState.getValue()).floatValue() * getDirection();
        float direction = getDirection();
        AnimationState animationState = animatable.internalState;
        boolean z = direction != 1.0f ? ((Number) animationState.getValue()).floatValue() < ((float) getContainerWidth()) : ((Number) animationState.getValue()).floatValue() < ((float) getContentWidth());
        boolean z2 = getDirection() != 1.0f ? ((Number) animationState.getValue()).floatValue() > ((float) getSpacingPx()) : ((Number) animationState.getValue()).floatValue() > ((float) ((getContentWidth() + getSpacingPx()) - getContainerWidth()));
        float contentWidth = getDirection() == 1.0f ? getContentWidth() + getSpacingPx() : (-getContentWidth()) - getSpacingPx();
        float containerWidth = floatValue + getContainerWidth();
        CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
        float intBitsToFloat = Float.intBitsToFloat((int) (canvasDrawScope.mo432getSizeNHjbRc() & 4294967295L));
        CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
        long m418getSizeNHjbRc = canvasDrawScope$drawContext$1.m418getSizeNHjbRc();
        canvasDrawScope$drawContext$1.getCanvas().save();
        try {
            canvasDrawScope$drawContext$1.transform.m420clipRectN_I0leg(floatValue, 0.0f, containerWidth, intBitsToFloat, 1);
            if (z) {
                layoutNodeDrawScope.drawContent();
            }
            if (z2) {
                canvasDrawScope.drawContext.transform.translate(contentWidth, 0.0f);
                try {
                    layoutNodeDrawScope.drawContent();
                    canvasDrawScope.drawContext.transform.translate(-contentWidth, -0.0f);
                } catch (Throwable th) {
                    canvasDrawScope.drawContext.transform.translate(-contentWidth, -0.0f);
                    throw th;
                }
            }
        } finally {
            BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0.m(canvasDrawScope$drawContext$1, m418getSizeNHjbRc);
        }
    }

    public final int getContainerWidth() {
        return ((SnapshotMutableIntStateImpl) this.containerWidth$delegate).getIntValue();
    }

    public final int getContentWidth() {
        return ((SnapshotMutableIntStateImpl) this.contentWidth$delegate).getIntValue();
    }

    public final float getDirection() {
        float signum = Math.signum(this.velocity);
        int ordinal = DelegatableNodeKt.requireLayoutNode(this).layoutDirection.ordinal();
        int i = 1;
        if (ordinal != 0) {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            i = -1;
        }
        return signum * i;
    }

    public final int getSpacingPx() {
        return ((Number) this.spacingPx$delegate.getValue()).intValue();
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.maxIntrinsicHeight(Integer.MAX_VALUE);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(Constraints.m648copyZbe2FdA$default(j, 0, Integer.MAX_VALUE, 0, 0, 13));
        ((SnapshotMutableIntStateImpl) this.containerWidth$delegate).setIntValue(ConstraintsKt.m665constrainWidthK40F9xA(j, mo479measureBRTryo0.width));
        ((SnapshotMutableIntStateImpl) this.contentWidth$delegate).setIntValue(mo479measureBRTryo0.width);
        layout$1 = measureScope.layout$1(getContainerWidth(), mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.MarqueeModifierNode$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope.placeWithLayer$default((Placeable.PlacementScope) obj, Placeable.this, MathKt.roundToInt((-((Number) this.offset.internalState.getValue()).floatValue()) * this.getDirection()), 0, null, 12);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return intrinsicMeasurable.minIntrinsicHeight(Integer.MAX_VALUE);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return 0;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        restartAnimation();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        StandaloneCoroutine standaloneCoroutine = this.animationJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.animationJob = null;
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public final void onFocusEvent(FocusStateImpl focusStateImpl) {
        boolean hasFocus = focusStateImpl.getHasFocus();
        ((SnapshotMutableStateImpl) this.hasFocus$delegate).setValue(Boolean.valueOf(hasFocus));
    }

    public final void restartAnimation() {
        StandaloneCoroutine standaloneCoroutine = this.animationJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        if (this.isAttached) {
            this.animationJob = BuildersKt.launch$default(getCoroutineScope(), null, null, new MarqueeModifierNode$restartAnimation$1(standaloneCoroutine, this, null), 3);
        }
    }
}
