package androidx.compose.animation.core;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InfiniteTransition {
    public final MutableVector _animations = new MutableVector(new TransitionAnimationState[16]);
    public final MutableState refreshChildNeeded$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
    public long startTimeNanos = Long.MIN_VALUE;
    public final MutableState isRunning$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransitionAnimationState implements State {
        public TargetBasedAnimation animation;
        public Object initialValue;
        public boolean isFinished;
        public long playTimeNanosOffset;
        public boolean startOnTheNextFrame;
        public Object targetValue;
        public final /* synthetic */ InfiniteTransition this$0;
        public final MutableState value$delegate;

        public TransitionAnimationState(InfiniteTransition infiniteTransition, Object obj, Object obj2, InfiniteRepeatableSpec infiniteRepeatableSpec) {
            TwoWayConverter twoWayConverter = VectorConvertersKt.FloatToVector;
            this.this$0 = infiniteTransition;
            this.initialValue = obj;
            this.targetValue = obj2;
            this.value$delegate = SnapshotStateKt.mutableStateOf$default(obj);
            this.animation = new TargetBasedAnimation(infiniteRepeatableSpec, twoWayConverter, this.initialValue, this.targetValue, null);
        }

        @Override // androidx.compose.runtime.State
        public final Object getValue() {
            return ((SnapshotMutableStateImpl) this.value$delegate).getValue();
        }
    }

    public final void run$animation_core_release(final int i, Composer composer) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-318043801);
        if ((i & 6) == 0) {
            i2 = (composerImpl.changedInstance(this) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            Object rememberedValue = composerImpl.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = SnapshotStateKt.mutableStateOf$default(null);
                composerImpl.updateRememberedValue(rememberedValue);
            }
            MutableState mutableState = (MutableState) rememberedValue;
            if (((Boolean) ((SnapshotMutableStateImpl) this.isRunning$delegate).getValue()).booleanValue() || ((Boolean) ((SnapshotMutableStateImpl) this.refreshChildNeeded$delegate).getValue()).booleanValue()) {
                boolean changedInstance = composerImpl.changedInstance(this);
                Object rememberedValue2 = composerImpl.rememberedValue();
                if (changedInstance || rememberedValue2 == composer$Companion$Empty$1) {
                    rememberedValue2 = new InfiniteTransition$run$1$1(mutableState, this, null);
                    composerImpl.updateRememberedValue(rememberedValue2);
                }
                EffectsKt.LaunchedEffect(composerImpl, this, (Function2) rememberedValue2);
            }
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.animation.core.InfiniteTransition$run$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    InfiniteTransition.this.run$animation_core_release(RecomposeScopeImplKt.updateChangedFlags(i | 1), (Composer) obj);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
