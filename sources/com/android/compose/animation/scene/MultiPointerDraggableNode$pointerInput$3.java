package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollNode;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.VelocityKt;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.JobKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MultiPointerDraggableNode$pointerInput$3 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ CoroutineContext $currentContext;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MultiPointerDraggableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiPointerDraggableNode$pointerInput$3(CoroutineContext coroutineContext, MultiPointerDraggableNode multiPointerDraggableNode, Continuation continuation) {
        super(continuation);
        this.$currentContext = coroutineContext;
        this.this$0 = multiPointerDraggableNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MultiPointerDraggableNode$pointerInput$3 multiPointerDraggableNode$pointerInput$3 = new MultiPointerDraggableNode$pointerInput$3(this.$currentContext, this.this$0, continuation);
        multiPointerDraggableNode$pointerInput$3.L$0 = obj;
        return multiPointerDraggableNode$pointerInput$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MultiPointerDraggableNode$pointerInput$3) create((AwaitPointerEventScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x005a -> B:7:0x0023). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AwaitPointerEventScope awaitPointerEventScope;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (CancellationException e) {
                if (!JobKt.isActive(this.$currentContext)) {
                    throw e;
                }
            }
        }
        while (JobKt.isActive(this.$currentContext)) {
            final MultiPointerDraggableNode multiPointerDraggableNode = this.this$0;
            Orientation orientation = multiPointerDraggableNode.orientation;
            Function1 function1 = multiPointerDraggableNode.startDragImmediately;
            Function3 function3 = new Function3() { // from class: com.android.compose.animation.scene.MultiPointerDraggableNode$pointerInput$3.1
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    long j = ((Offset) obj2).packedValue;
                    float floatValue = ((Number) obj3).floatValue();
                    int intValue = ((Number) obj4).intValue();
                    return (DragController) ((SwipeToSceneNode$multiPointerDraggableNode$3) MultiPointerDraggableNode.this.onDragStarted).invoke(new Offset(j), Float.valueOf(floatValue), Integer.valueOf(intValue));
                }
            };
            Function2 function2 = new Function2() { // from class: com.android.compose.animation.scene.MultiPointerDraggableNode$pointerInput$3.2
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    DragController dragController = (DragController) obj2;
                    float floatValue = ((Number) obj3).floatValue();
                    MultiPointerDraggableNode multiPointerDraggableNode2 = MultiPointerDraggableNode.this;
                    NestedScrollDispatcher nestedScrollDispatcher = multiPointerDraggableNode2.dispatcher;
                    long mo736toOffsettuRUvjQ = multiPointerDraggableNode2.converter.mo736toOffsettuRUvjQ(floatValue);
                    NestedScrollNode nestedScrollNode = nestedScrollDispatcher.nestedScrollNode;
                    NestedScrollNode nestedScrollNode2 = null;
                    if (nestedScrollNode != null && nestedScrollNode.isAttached) {
                        nestedScrollNode2 = (NestedScrollNode) TraversableNodeKt.findNearestAncestor(nestedScrollNode);
                    }
                    float mo735toFloatk4lQ0M = floatValue - multiPointerDraggableNode2.converter.mo735toFloatk4lQ0M(nestedScrollNode2 != null ? nestedScrollNode2.mo139onPreScrollOzD1aCk(mo736toOffsettuRUvjQ, 1) : 0L);
                    float onDrag = dragController.onDrag(mo735toFloatk4lQ0M);
                    multiPointerDraggableNode2.converter.mo735toFloatk4lQ0M(multiPointerDraggableNode2.dispatcher.m452dispatchPostScrollDzOQY0M(1, multiPointerDraggableNode2.converter.mo736toOffsettuRUvjQ(onDrag), multiPointerDraggableNode2.converter.mo736toOffsettuRUvjQ(mo735toFloatk4lQ0M - onDrag)));
                    return Unit.INSTANCE;
                }
            };
            Function1 function12 = new Function1() { // from class: com.android.compose.animation.scene.MultiPointerDraggableNode$pointerInput$3.3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    final DragController dragController = (DragController) obj2;
                    MultiPointerDraggableNode multiPointerDraggableNode2 = MultiPointerDraggableNode.this;
                    float maximumFlingVelocity = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(multiPointerDraggableNode2, CompositionLocalsKt.LocalViewConfiguration)).getMaximumFlingVelocity();
                    MultiPointerDraggableNode multiPointerDraggableNode3 = MultiPointerDraggableNode.this;
                    BuildersKt.launch$default(multiPointerDraggableNode2.dispatcher.getCoroutineScope(), null, null, new MultiPointerDraggableNode$startFlingGesture$1(multiPointerDraggableNode2, multiPointerDraggableNode2.converter.mo734toFloatTH1AsA0(multiPointerDraggableNode3.velocityTracker.m470calculateVelocityAH228Gc(VelocityKt.Velocity(maximumFlingVelocity, maximumFlingVelocity))), new Function1() { // from class: com.android.compose.animation.scene.MultiPointerDraggableNode.pointerInput.3.3.2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return Float.valueOf(DragController.this.onStop(((Number) obj3).floatValue(), true));
                        }
                    }, null), 3);
                    return Unit.INSTANCE;
                }
            };
            Function1 function13 = new Function1() { // from class: com.android.compose.animation.scene.MultiPointerDraggableNode$pointerInput$3.4
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    final DragController dragController = (DragController) obj2;
                    MultiPointerDraggableNode multiPointerDraggableNode2 = MultiPointerDraggableNode.this;
                    BuildersKt.launch$default(multiPointerDraggableNode2.dispatcher.getCoroutineScope(), null, null, new MultiPointerDraggableNode$startFlingGesture$1(multiPointerDraggableNode2, 0.0f, new Function1() { // from class: com.android.compose.animation.scene.MultiPointerDraggableNode.pointerInput.3.4.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return Float.valueOf(DragController.this.onStop(((Number) obj3).floatValue(), true));
                        }
                    }, null), 3);
                    return Unit.INSTANCE;
                }
            };
            SwipeDetector swipeDetector = multiPointerDraggableNode.swipeDetector;
            this.L$0 = awaitPointerEventScope;
            this.label = 1;
            if (MultiPointerDraggableNode.access$detectDragGestures(multiPointerDraggableNode, awaitPointerEventScope, orientation, function1, function3, function2, function12, function13, swipeDetector, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        }
        return Unit.INSTANCE;
    }
}
