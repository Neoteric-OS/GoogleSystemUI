package androidx.compose.material3.internal;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Ref$FloatRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AnchoredDraggableKt$animateTo$2 extends SuspendLambda implements Function4 {
    final /* synthetic */ AnchoredDraggableState $this_animateTo;
    final /* synthetic */ float $velocity;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnchoredDraggableKt$animateTo$2(AnchoredDraggableState anchoredDraggableState, float f, Continuation continuation) {
        super(4, continuation);
        this.$this_animateTo = anchoredDraggableState;
        this.$velocity = f;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        AnchoredDraggableKt$animateTo$2 anchoredDraggableKt$animateTo$2 = new AnchoredDraggableKt$animateTo$2(this.$this_animateTo, this.$velocity, (Continuation) obj4);
        anchoredDraggableKt$animateTo$2.L$0 = (AnchoredDragScope) obj;
        anchoredDraggableKt$animateTo$2.L$1 = (DraggableAnchors) obj2;
        anchoredDraggableKt$animateTo$2.L$2 = obj3;
        return anchoredDraggableKt$animateTo$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final AnchoredDragScope anchoredDragScope = (AnchoredDragScope) this.L$0;
            float positionOf = ((MapDraggableAnchors) ((DraggableAnchors) this.L$1)).positionOf(this.L$2);
            if (!Float.isNaN(positionOf)) {
                final Ref$FloatRef ref$FloatRef = new Ref$FloatRef();
                float offset = Float.isNaN(this.$this_animateTo.getOffset()) ? 0.0f : this.$this_animateTo.getOffset();
                ref$FloatRef.element = offset;
                float f = this.$velocity;
                AnimationSpec animationSpec = (AnimationSpec) this.$this_animateTo.animationSpec.invoke();
                Function2 function2 = new Function2() { // from class: androidx.compose.material3.internal.AnchoredDraggableKt$animateTo$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        float floatValue = ((Number) obj2).floatValue();
                        float floatValue2 = ((Number) obj3).floatValue();
                        AnchoredDraggableState anchoredDraggableState = ((AnchoredDraggableState$anchoredDragScope$1) AnchoredDragScope.this).this$0;
                        ((SnapshotMutableFloatStateImpl) anchoredDraggableState.offset$delegate).setFloatValue(floatValue);
                        ((SnapshotMutableFloatStateImpl) anchoredDraggableState.lastVelocity$delegate).setFloatValue(floatValue2);
                        ref$FloatRef.element = floatValue;
                        return Unit.INSTANCE;
                    }
                };
                this.L$0 = null;
                this.L$1 = null;
                this.label = 1;
                if (SuspendAnimationKt.animate(offset, positionOf, f, animationSpec, function2, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
