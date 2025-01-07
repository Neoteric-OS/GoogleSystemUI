package com.android.compose.animation.scene;

import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.unit.Velocity;
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
final class MultiPointerDraggableNode$startFlingGesture$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ float $initialVelocity;
    final /* synthetic */ Function1 $onFling;
    float F$0;
    float F$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ MultiPointerDraggableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiPointerDraggableNode$startFlingGesture$1(MultiPointerDraggableNode multiPointerDraggableNode, float f, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = multiPointerDraggableNode;
        this.$initialVelocity = f;
        this.$onFling = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MultiPointerDraggableNode$startFlingGesture$1(this.this$0, this.$initialVelocity, this.$onFling, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MultiPointerDraggableNode$startFlingGesture$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MultiPointerDraggableNode multiPointerDraggableNode;
        float f;
        Object m453dispatchPreFlingQWom1Mo;
        Function1 function1;
        MultiPointerDraggableNode multiPointerDraggableNode2;
        MultiPointerDraggableNode multiPointerDraggableNode3;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            multiPointerDraggableNode = this.this$0;
            f = this.$initialVelocity;
            Function1 function12 = this.$onFling;
            NestedScrollDispatcher nestedScrollDispatcher = multiPointerDraggableNode.dispatcher;
            long mo737toVelocityadjELrA = multiPointerDraggableNode.converter.mo737toVelocityadjELrA(f);
            this.L$0 = multiPointerDraggableNode;
            this.L$1 = function12;
            this.L$2 = multiPointerDraggableNode;
            this.F$0 = f;
            this.label = 1;
            m453dispatchPreFlingQWom1Mo = nestedScrollDispatcher.m453dispatchPreFlingQWom1Mo(mo737toVelocityadjELrA, this);
            if (m453dispatchPreFlingQWom1Mo == coroutineSingletons) {
                return coroutineSingletons;
            }
            function1 = function12;
            multiPointerDraggableNode2 = multiPointerDraggableNode;
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                multiPointerDraggableNode3 = (MultiPointerDraggableNode) this.L$0;
                ResultKt.throwOnFailure(obj);
                multiPointerDraggableNode3.converter.mo734toFloatTH1AsA0(((Velocity) obj).packedValue);
                return Unit.INSTANCE;
            }
            f = this.F$0;
            MultiPointerDraggableNode multiPointerDraggableNode4 = (MultiPointerDraggableNode) this.L$2;
            Function1 function13 = (Function1) this.L$1;
            MultiPointerDraggableNode multiPointerDraggableNode5 = (MultiPointerDraggableNode) this.L$0;
            ResultKt.throwOnFailure(obj);
            m453dispatchPreFlingQWom1Mo = obj;
            multiPointerDraggableNode = multiPointerDraggableNode5;
            function1 = function13;
            multiPointerDraggableNode2 = multiPointerDraggableNode4;
        }
        float mo734toFloatTH1AsA0 = multiPointerDraggableNode2.converter.mo734toFloatTH1AsA0(((Velocity) m453dispatchPreFlingQWom1Mo).packedValue);
        float f2 = f - mo734toFloatTH1AsA0;
        float floatValue = ((Number) function1.invoke(new Float(f2))).floatValue();
        NestedScrollDispatcher nestedScrollDispatcher2 = multiPointerDraggableNode.dispatcher;
        long mo737toVelocityadjELrA2 = multiPointerDraggableNode.converter.mo737toVelocityadjELrA(floatValue);
        long mo737toVelocityadjELrA3 = multiPointerDraggableNode.converter.mo737toVelocityadjELrA(f2 - floatValue);
        this.L$0 = multiPointerDraggableNode;
        this.L$1 = null;
        this.L$2 = null;
        this.F$0 = mo734toFloatTH1AsA0;
        this.F$1 = floatValue;
        this.label = 2;
        Object m451dispatchPostFlingRZ2iAVY = nestedScrollDispatcher2.m451dispatchPostFlingRZ2iAVY(mo737toVelocityadjELrA2, mo737toVelocityadjELrA3, this);
        if (m451dispatchPostFlingRZ2iAVY == coroutineSingletons) {
            return coroutineSingletons;
        }
        MultiPointerDraggableNode multiPointerDraggableNode6 = multiPointerDraggableNode;
        obj = m451dispatchPostFlingRZ2iAVY;
        multiPointerDraggableNode3 = multiPointerDraggableNode6;
        multiPointerDraggableNode3.converter.mo734toFloatTH1AsA0(((Velocity) obj).packedValue);
        return Unit.INSTANCE;
    }
}
