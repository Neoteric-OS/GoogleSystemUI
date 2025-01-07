package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SearchBar_androidKt$SearchBar$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Animatable $animationProgress;
    final /* synthetic */ MutableState $currentBackEvent;
    final /* synthetic */ boolean $expanded;
    final /* synthetic */ MutableFloatState $finalBackProgress;
    final /* synthetic */ MutableState $firstBackEvent;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchBar_androidKt$SearchBar$1$1(Animatable animatable, boolean z, MutableFloatState mutableFloatState, MutableState mutableState, MutableState mutableState2, Continuation continuation) {
        super(2, continuation);
        this.$animationProgress = animatable;
        this.$expanded = z;
        this.$finalBackProgress = mutableFloatState;
        this.$firstBackEvent = mutableState;
        this.$currentBackEvent = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SearchBar_androidKt$SearchBar$1$1(this.$animationProgress, this.$expanded, this.$finalBackProgress, this.$firstBackEvent, this.$currentBackEvent, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SearchBar_androidKt$SearchBar$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TweenSpec tweenSpec = (((Number) this.$animationProgress.internalState.getValue()).floatValue() <= 0.0f || ((Number) this.$animationProgress.internalState.getValue()).floatValue() >= 1.0f) ? this.$expanded ? SearchBar_androidKt.AnimationEnterFloatSpec : SearchBar_androidKt.AnimationExitFloatSpec : SearchBar_androidKt.AnimationPredictiveBackExitFloatSpec;
            float f = this.$expanded ? 1.0f : 0.0f;
            if (((Number) this.$animationProgress.internalState.getValue()).floatValue() != f) {
                Animatable animatable = this.$animationProgress;
                Float f2 = new Float(f);
                this.label = 1;
                if (Animatable.animateTo$default(animatable, f2, tweenSpec, null, null, this, 12) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (!this.$expanded) {
            ((SnapshotMutableFloatStateImpl) this.$finalBackProgress).setFloatValue(Float.NaN);
            this.$firstBackEvent.setValue(null);
            this.$currentBackEvent.setValue(null);
        }
        return Unit.INSTANCE;
    }
}
