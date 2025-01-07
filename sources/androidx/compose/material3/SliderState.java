package androidx.compose.material3;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.ClosedFloatRange;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliderState implements DraggableState {
    public final SliderState$dragScope$1 dragScope;
    public final Function0 gestureEndAction;
    public final MutableState isDragging$delegate;
    public boolean isRtl;
    public Function1 onValueChange;
    public Function0 onValueChangeFinished;
    public final MutableFloatState pressOffset$delegate;
    public final MutableFloatState rawOffset$delegate;
    public final MutatorMutex scrollMutex;
    public final int steps;
    public final MutableFloatState thumbWidth$delegate;
    public final float[] tickFractions;
    public final MutableIntState totalWidth$delegate;
    public final MutableFloatState trackHeight$delegate;
    public final ClosedFloatingPointRange valueRange;
    public final MutableFloatState valueState$delegate;

    /* JADX WARN: Type inference failed for: r5v8, types: [androidx.compose.material3.SliderState$dragScope$1] */
    public SliderState(float f, int i, Function0 function0, ClosedFloatingPointRange closedFloatingPointRange) {
        float[] fArr;
        this.steps = i;
        this.onValueChangeFinished = function0;
        this.valueRange = closedFloatingPointRange;
        this.valueState$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
        float f2 = SliderKt.TrackHeight;
        if (i == 0) {
            fArr = new float[0];
        } else {
            int i2 = i + 2;
            float[] fArr2 = new float[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                fArr2[i3] = i3 / (i + 1);
            }
            fArr = fArr2;
        }
        this.tickFractions = fArr;
        this.totalWidth$delegate = SnapshotIntStateKt.mutableIntStateOf(0);
        this.trackHeight$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.thumbWidth$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.isDragging$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
        this.gestureEndAction = new SliderState$gestureEndAction$1(this);
        ClosedFloatRange closedFloatRange = (ClosedFloatRange) this.valueRange;
        float f3 = closedFloatRange._start;
        float f4 = closedFloatRange._endInclusive - f3;
        this.rawOffset$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(MathHelpersKt.lerp(0.0f, 0.0f, RangesKt.coerceIn(f4 == 0.0f ? 0.0f : (f - f3) / f4, 0.0f, 1.0f)));
        this.pressOffset$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.dragScope = new DragScope() { // from class: androidx.compose.material3.SliderState$dragScope$1
            @Override // androidx.compose.foundation.gestures.DragScope
            public final void dragBy(float f5) {
                SliderState.this.dispatchRawDelta(f5);
            }
        };
        this.scrollMutex = new MutatorMutex();
    }

    public final void dispatchRawDelta(float f) {
        float intValue = ((SnapshotMutableIntStateImpl) this.totalWidth$delegate).getIntValue();
        MutableFloatState mutableFloatState = this.thumbWidth$delegate;
        float f2 = 2;
        float max = Math.max(intValue - (((SnapshotMutableFloatStateImpl) mutableFloatState).getFloatValue() / f2), 0.0f);
        float min = Math.min(((SnapshotMutableFloatStateImpl) mutableFloatState).getFloatValue() / f2, max);
        MutableFloatState mutableFloatState2 = this.rawOffset$delegate;
        float floatValue = ((SnapshotMutableFloatStateImpl) mutableFloatState2).getFloatValue() + f;
        MutableFloatState mutableFloatState3 = this.pressOffset$delegate;
        ((SnapshotMutableFloatStateImpl) mutableFloatState2).setFloatValue(((SnapshotMutableFloatStateImpl) mutableFloatState3).getFloatValue() + floatValue);
        ((SnapshotMutableFloatStateImpl) mutableFloatState3).setFloatValue(0.0f);
        float access$snapValueToTick = SliderKt.access$snapValueToTick(((SnapshotMutableFloatStateImpl) mutableFloatState2).getFloatValue(), this.tickFractions, min, max);
        ClosedFloatRange closedFloatRange = (ClosedFloatRange) this.valueRange;
        float f3 = max - min;
        float lerp = MathHelpersKt.lerp(closedFloatRange._start, closedFloatRange._endInclusive, RangesKt.coerceIn(f3 == 0.0f ? 0.0f : (access$snapValueToTick - min) / f3, 0.0f, 1.0f));
        if (lerp == getValue()) {
            return;
        }
        Function1 function1 = this.onValueChange;
        if (function1 != null) {
            function1.invoke(Float.valueOf(lerp));
        } else {
            setValue(lerp);
        }
    }

    @Override // androidx.compose.foundation.gestures.DraggableState
    public final Object drag(Continuation continuation, Function2 function2) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(continuation, new SliderState$drag$2(this, MutatePriority.UserInput, function2, null));
        return coroutineScope == CoroutineSingletons.COROUTINE_SUSPENDED ? coroutineScope : Unit.INSTANCE;
    }

    public final float getCoercedValueAsFraction$material3_release() {
        ClosedFloatRange closedFloatRange = (ClosedFloatRange) this.valueRange;
        float f = closedFloatRange._start;
        float f2 = closedFloatRange._endInclusive;
        float coerceIn = RangesKt.coerceIn(getValue(), closedFloatRange._start, closedFloatRange._endInclusive);
        float f3 = SliderKt.TrackHeight;
        float f4 = f2 - f;
        return RangesKt.coerceIn(f4 == 0.0f ? 0.0f : (coerceIn - f) / f4, 0.0f, 1.0f);
    }

    public final float getValue() {
        return ((SnapshotMutableFloatStateImpl) this.valueState$delegate).getFloatValue();
    }

    public final void setValue(float f) {
        ClosedFloatingPointRange closedFloatingPointRange = this.valueRange;
        ((SnapshotMutableFloatStateImpl) this.valueState$delegate).setFloatValue(SliderKt.access$snapValueToTick(RangesKt.coerceIn(f, Float.valueOf(((ClosedFloatRange) closedFloatingPointRange)._start).floatValue(), Float.valueOf(((ClosedFloatRange) closedFloatingPointRange)._endInclusive).floatValue()), this.tickFractions, Float.valueOf(((ClosedFloatRange) closedFloatingPointRange)._start).floatValue(), Float.valueOf(((ClosedFloatRange) closedFloatingPointRange)._endInclusive).floatValue()));
    }
}
