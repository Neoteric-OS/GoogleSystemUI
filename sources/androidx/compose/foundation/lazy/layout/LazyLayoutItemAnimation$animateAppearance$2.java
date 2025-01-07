package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LazyLayoutItemAnimation$animateAppearance$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ GraphicsLayer $layer;
    final /* synthetic */ boolean $shouldResetValue;
    final /* synthetic */ FiniteAnimationSpec $spec;
    int label;
    final /* synthetic */ LazyLayoutItemAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyLayoutItemAnimation$animateAppearance$2(boolean z, LazyLayoutItemAnimation lazyLayoutItemAnimation, FiniteAnimationSpec finiteAnimationSpec, GraphicsLayer graphicsLayer, Continuation continuation) {
        super(2, continuation);
        this.$shouldResetValue = z;
        this.this$0 = lazyLayoutItemAnimation;
        this.$spec = finiteAnimationSpec;
        this.$layer = graphicsLayer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new LazyLayoutItemAnimation$animateAppearance$2(this.$shouldResetValue, this.this$0, this.$spec, this.$layer, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((LazyLayoutItemAnimation$animateAppearance$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$shouldResetValue) {
                    Animatable animatable = this.this$0.visibilityAnimation;
                    Float f = new Float(0.0f);
                    this.label = 1;
                    if (animatable.snapTo(f, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    LazyLayoutItemAnimation lazyLayoutItemAnimation = this.this$0;
                    int i2 = LazyLayoutItemAnimation.$r8$clinit;
                    lazyLayoutItemAnimation.setAppearanceAnimationInProgress(false);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            Animatable animatable2 = this.this$0.visibilityAnimation;
            Float f2 = new Float(1.0f);
            FiniteAnimationSpec finiteAnimationSpec = this.$spec;
            final GraphicsLayer graphicsLayer = this.$layer;
            final LazyLayoutItemAnimation lazyLayoutItemAnimation2 = this.this$0;
            Function1 function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateAppearance$2.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    GraphicsLayer.this.setAlpha(((Number) ((Animatable) obj2).internalState.getValue()).floatValue());
                    lazyLayoutItemAnimation2.onLayerPropertyChanged.invoke();
                    return Unit.INSTANCE;
                }
            };
            this.label = 2;
            if (Animatable.animateTo$default(animatable2, f2, finiteAnimationSpec, null, function1, this, 4) == coroutineSingletons) {
                return coroutineSingletons;
            }
            LazyLayoutItemAnimation lazyLayoutItemAnimation3 = this.this$0;
            int i22 = LazyLayoutItemAnimation.$r8$clinit;
            lazyLayoutItemAnimation3.setAppearanceAnimationInProgress(false);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation4 = this.this$0;
            int i3 = LazyLayoutItemAnimation.$r8$clinit;
            lazyLayoutItemAnimation4.setAppearanceAnimationInProgress(false);
            throw th;
        }
    }
}
