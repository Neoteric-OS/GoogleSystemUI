package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.unit.Density;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SheetState {
    public AnimationSpec anchoredDraggableMotionSpec;
    public final AnchoredDraggableState anchoredDraggableState;
    public FiniteAnimationSpec hideMotionSpec;
    public FiniteAnimationSpec showMotionSpec;
    public final boolean skipHiddenState;
    public final boolean skipPartiallyExpanded;

    public SheetState(boolean z, final Density density, SheetValue sheetValue, Function1 function1, boolean z2) {
        this.skipPartiallyExpanded = z;
        this.skipHiddenState = z2;
        if (z && sheetValue == SheetValue.PartiallyExpanded) {
            throw new IllegalArgumentException("The initial value must not be set to PartiallyExpanded if skipPartiallyExpanded is set to true.");
        }
        if (z2 && sheetValue == SheetValue.Hidden) {
            throw new IllegalArgumentException("The initial value must not be set to Hidden if skipHiddenState is set to true.");
        }
        this.anchoredDraggableMotionSpec = SheetDefaultsKt.BottomSheetAnimationSpec;
        this.anchoredDraggableState = new AnchoredDraggableState(sheetValue, new Function1() { // from class: androidx.compose.material3.SheetState$anchoredDraggableState$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Number) obj).floatValue();
                return Float.valueOf(Density.this.mo51toPx0680j_4(56));
            }
        }, new Function0() { // from class: androidx.compose.material3.SheetState$anchoredDraggableState$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Float.valueOf(Density.this.mo51toPx0680j_4(125));
            }
        }, new Function0() { // from class: androidx.compose.material3.SheetState$anchoredDraggableState$3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SheetState.this.anchoredDraggableMotionSpec;
            }
        }, function1);
        this.showMotionSpec = AnimationSpecKt.snap$default();
        this.hideMotionSpec = AnimationSpecKt.snap$default();
    }

    public static Object animateTo$material3_release$default(SheetState sheetState, SheetValue sheetValue, FiniteAnimationSpec finiteAnimationSpec, SuspendLambda suspendLambda) {
        float floatValue = ((SnapshotMutableFloatStateImpl) sheetState.anchoredDraggableState.lastVelocity$delegate).getFloatValue();
        sheetState.getClass();
        Object anchoredDrag = sheetState.anchoredDraggableState.anchoredDrag(sheetValue, MutatePriority.Default, new SheetState$animateTo$2(sheetState, floatValue, finiteAnimationSpec, null), suspendLambda);
        return anchoredDrag == CoroutineSingletons.COROUTINE_SUSPENDED ? anchoredDrag : Unit.INSTANCE;
    }

    public final Object hide(SuspendLambda suspendLambda) {
        if (this.skipHiddenState) {
            throw new IllegalStateException("Attempted to animate to hidden when skipHiddenState was enabled. Set skipHiddenState to false to use this function.");
        }
        Object animateTo$material3_release$default = animateTo$material3_release$default(this, SheetValue.Hidden, this.hideMotionSpec, suspendLambda);
        return animateTo$material3_release$default == CoroutineSingletons.COROUTINE_SUSPENDED ? animateTo$material3_release$default : Unit.INSTANCE;
    }

    public final boolean isVisible() {
        return ((SnapshotMutableStateImpl) this.anchoredDraggableState.currentValue$delegate).getValue() != SheetValue.Hidden;
    }

    public final Object partialExpand(SuspendLambda suspendLambda) {
        if (this.skipPartiallyExpanded) {
            throw new IllegalStateException("Attempted to animate to partial expanded when skipPartiallyExpanded was enabled. Set skipPartiallyExpanded to false to use this function.");
        }
        Object animateTo$material3_release$default = animateTo$material3_release$default(this, SheetValue.PartiallyExpanded, this.showMotionSpec, suspendLambda);
        return animateTo$material3_release$default == CoroutineSingletons.COROUTINE_SUSPENDED ? animateTo$material3_release$default : Unit.INSTANCE;
    }
}
