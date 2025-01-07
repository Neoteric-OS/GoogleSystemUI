package com.airbnb.lottie.compose;

import androidx.compose.foundation.MutatorMutex;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import com.airbnb.lottie.LottieComposition;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LottieAnimatableImpl implements LottieAnimatable {
    public final MutableState clipSpec$delegate;
    public final MutableState composition$delegate;
    public final State endProgress$delegate;
    public final State frameSpeed$delegate;
    public final MutableState isPlaying$delegate;
    public final MutableState iteration$delegate;
    public final MutableState iterations$delegate;
    public final MutableState lastFrameNanos$delegate;
    public final MutatorMutex mutex;
    public final MutableState progress$delegate;
    public final MutableState progressRaw$delegate;
    public final MutableState reverseOnRepeat$delegate;
    public final MutableState speed$delegate;
    public final MutableState useCompositionFrameRate$delegate;

    public LottieAnimatableImpl() {
        Boolean bool = Boolean.FALSE;
        this.isPlaying$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.iteration$delegate = SnapshotStateKt.mutableStateOf$default(1);
        this.iterations$delegate = SnapshotStateKt.mutableStateOf$default(1);
        this.reverseOnRepeat$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.clipSpec$delegate = SnapshotStateKt.mutableStateOf$default(null);
        this.speed$delegate = SnapshotStateKt.mutableStateOf$default(Float.valueOf(1.0f));
        this.useCompositionFrameRate$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this.frameSpeed$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: com.airbnb.lottie.compose.LottieAnimatableImpl$frameSpeed$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Float.valueOf((((Boolean) ((SnapshotMutableStateImpl) LottieAnimatableImpl.this.reverseOnRepeat$delegate).getValue()).booleanValue() && LottieAnimatableImpl.this.getIteration() % 2 == 0) ? -((Number) ((SnapshotMutableStateImpl) LottieAnimatableImpl.this.speed$delegate).getValue()).floatValue() : ((Number) ((SnapshotMutableStateImpl) LottieAnimatableImpl.this.speed$delegate).getValue()).floatValue());
            }
        });
        this.composition$delegate = SnapshotStateKt.mutableStateOf$default(null);
        Float valueOf = Float.valueOf(0.0f);
        this.progressRaw$delegate = SnapshotStateKt.mutableStateOf$default(valueOf);
        this.progress$delegate = SnapshotStateKt.mutableStateOf$default(valueOf);
        this.lastFrameNanos$delegate = SnapshotStateKt.mutableStateOf$default(Long.MIN_VALUE);
        this.endProgress$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: com.airbnb.lottie.compose.LottieAnimatableImpl$endProgress$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                float f = 0.0f;
                if (((LottieComposition) ((SnapshotMutableStateImpl) LottieAnimatableImpl.this.composition$delegate).getValue()) != null) {
                    if (((Number) ((SnapshotMutableStateImpl) LottieAnimatableImpl.this.speed$delegate).getValue()).floatValue() < 0.0f) {
                        if (((SnapshotMutableStateImpl) LottieAnimatableImpl.this.clipSpec$delegate).getValue() != null) {
                            throw new ClassCastException();
                        }
                    } else {
                        if (((SnapshotMutableStateImpl) LottieAnimatableImpl.this.clipSpec$delegate).getValue() != null) {
                            throw new ClassCastException();
                        }
                        f = 1.0f;
                    }
                }
                return Float.valueOf(f);
            }
        });
        SnapshotStateKt.derivedStateOf(new Function0() { // from class: com.airbnb.lottie.compose.LottieAnimatableImpl$isAtEnd$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(LottieAnimatableImpl.this.getIteration() == ((Number) ((SnapshotMutableStateImpl) LottieAnimatableImpl.this.iterations$delegate).getValue()).intValue() && ((Number) ((SnapshotMutableStateImpl) LottieAnimatableImpl.this.progress$delegate).getValue()).floatValue() == ((Number) LottieAnimatableImpl.this.endProgress$delegate.getValue()).floatValue());
            }
        });
        this.mutex = new MutatorMutex();
    }

    public static final boolean access$onFrame(LottieAnimatableImpl lottieAnimatableImpl, int i, long j) {
        LottieComposition lottieComposition = (LottieComposition) ((SnapshotMutableStateImpl) lottieAnimatableImpl.composition$delegate).getValue();
        if (lottieComposition == null) {
            return true;
        }
        MutableState mutableState = lottieAnimatableImpl.lastFrameNanos$delegate;
        long longValue = ((Number) ((SnapshotMutableStateImpl) mutableState).getValue()).longValue() == Long.MIN_VALUE ? 0L : j - ((Number) ((SnapshotMutableStateImpl) mutableState).getValue()).longValue();
        ((SnapshotMutableStateImpl) mutableState).setValue(Long.valueOf(j));
        MutableState mutableState2 = lottieAnimatableImpl.clipSpec$delegate;
        if (((SnapshotMutableStateImpl) mutableState2).getValue() != null) {
            throw new ClassCastException();
        }
        if (((SnapshotMutableStateImpl) mutableState2).getValue() != null) {
            throw new ClassCastException();
        }
        float duration = (longValue / 1000000) / lottieComposition.getDuration();
        State state = lottieAnimatableImpl.frameSpeed$delegate;
        float floatValue = ((Number) state.getValue()).floatValue() * duration;
        float floatValue2 = ((Number) state.getValue()).floatValue();
        MutableState mutableState3 = lottieAnimatableImpl.progressRaw$delegate;
        float floatValue3 = floatValue2 < 0.0f ? 0.0f - (((Number) ((SnapshotMutableStateImpl) mutableState3).getValue()).floatValue() + floatValue) : (((Number) ((SnapshotMutableStateImpl) mutableState3).getValue()).floatValue() + floatValue) - 1.0f;
        if (floatValue3 < 0.0f) {
            lottieAnimatableImpl.updateProgress(RangesKt.coerceIn(((Number) ((SnapshotMutableStateImpl) mutableState3).getValue()).floatValue(), 0.0f, 1.0f) + floatValue);
            return true;
        }
        int i2 = (int) (floatValue3 / 1.0f);
        int i3 = i2 + 1;
        if (lottieAnimatableImpl.getIteration() + i3 > i) {
            lottieAnimatableImpl.updateProgress(((Number) lottieAnimatableImpl.endProgress$delegate.getValue()).floatValue());
            lottieAnimatableImpl.setIteration(i);
            return false;
        }
        lottieAnimatableImpl.setIteration(lottieAnimatableImpl.getIteration() + i3);
        float f = floatValue3 - (i2 * 1.0f);
        lottieAnimatableImpl.updateProgress(((Number) state.getValue()).floatValue() < 0.0f ? 1.0f - f : 0.0f + f);
        return true;
    }

    public final int getIteration() {
        return ((Number) ((SnapshotMutableStateImpl) this.iteration$delegate).getValue()).intValue();
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return Float.valueOf(((Number) ((SnapshotMutableStateImpl) this.progress$delegate).getValue()).floatValue());
    }

    public final void setIteration(int i) {
        ((SnapshotMutableStateImpl) this.iteration$delegate).setValue(Integer.valueOf(i));
    }

    public final void updateProgress(float f) {
        LottieComposition lottieComposition;
        ((SnapshotMutableStateImpl) this.progressRaw$delegate).setValue(Float.valueOf(f));
        if (((Boolean) ((SnapshotMutableStateImpl) this.useCompositionFrameRate$delegate).getValue()).booleanValue() && (lottieComposition = (LottieComposition) ((SnapshotMutableStateImpl) this.composition$delegate).getValue()) != null) {
            f -= f % (1 / lottieComposition.frameRate);
        }
        ((SnapshotMutableStateImpl) this.progress$delegate).setValue(Float.valueOf(f));
    }
}
