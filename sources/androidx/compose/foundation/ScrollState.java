package androidx.compose.foundation;

import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScrollState implements ScrollableState {
    public static final SaverKt$Saver$1 Saver;
    public float accumulator;
    public final MutableIntState value$delegate;
    public final MutableIntState viewportSize$delegate = SnapshotIntStateKt.mutableIntStateOf(0);
    public final MutableInteractionSource internalInteractionSource = InteractionSourceKt.MutableInteractionSource();
    public final MutableIntState _maxValueState = SnapshotIntStateKt.mutableIntStateOf(Integer.MAX_VALUE);
    public final ScrollableState scrollableState = ScrollableStateKt.ScrollableState(new Function1() { // from class: androidx.compose.foundation.ScrollState$scrollableState$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            float floatValue = ((Number) obj).floatValue();
            float value = ScrollState.this.getValue() + floatValue + ScrollState.this.accumulator;
            float coerceIn = RangesKt.coerceIn(value, 0.0f, ((SnapshotMutableIntStateImpl) r1._maxValueState).getIntValue());
            boolean z = value == coerceIn;
            float value2 = coerceIn - ScrollState.this.getValue();
            int round = Math.round(value2);
            ScrollState scrollState = ScrollState.this;
            ((SnapshotMutableIntStateImpl) scrollState.value$delegate).setIntValue(scrollState.getValue() + round);
            ScrollState.this.accumulator = value2 - round;
            if (!z) {
                floatValue = value2;
            }
            return Float.valueOf(floatValue);
        }
    });
    public final State canScrollForward$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.ScrollState$canScrollForward$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(ScrollState.this.getValue() < ((SnapshotMutableIntStateImpl) ScrollState.this._maxValueState).getIntValue());
        }
    });
    public final State canScrollBackward$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.ScrollState$canScrollBackward$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(ScrollState.this.getValue() > 0);
        }
    });

    static {
        ScrollState$Companion$Saver$1 scrollState$Companion$Saver$1 = new Function2() { // from class: androidx.compose.foundation.ScrollState$Companion$Saver$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((ScrollState) obj2).getValue());
            }
        };
        ScrollState$Companion$Saver$2 scrollState$Companion$Saver$2 = new Function1() { // from class: androidx.compose.foundation.ScrollState$Companion$Saver$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new ScrollState(((Number) obj).intValue());
            }
        };
        SaverKt$Saver$1 saverKt$Saver$1 = SaverKt.AutoSaver;
        Saver = new SaverKt$Saver$1(scrollState$Companion$Saver$1, scrollState$Companion$Saver$2);
    }

    public ScrollState(int i) {
        this.value$delegate = SnapshotIntStateKt.mutableIntStateOf(i);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final float dispatchRawDelta(float f) {
        return this.scrollableState.dispatchRawDelta(f);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollBackward() {
        return ((Boolean) this.canScrollBackward$delegate.getValue()).booleanValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollForward() {
        return ((Boolean) this.canScrollForward$delegate.getValue()).booleanValue();
    }

    public final int getValue() {
        return ((SnapshotMutableIntStateImpl) this.value$delegate).getIntValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final Object scroll(MutatePriority mutatePriority, Function2 function2, ContinuationImpl continuationImpl) {
        Object scroll = this.scrollableState.scroll(mutatePriority, function2, continuationImpl);
        return scroll == CoroutineSingletons.COROUTINE_SUSPENDED ? scroll : Unit.INSTANCE;
    }
}
