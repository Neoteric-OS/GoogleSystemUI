package com.android.compose.animation.scene;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MultiPointerDraggableNode$pointerTracker$3 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ CoroutineContext $currentContext;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MultiPointerDraggableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiPointerDraggableNode$pointerTracker$3(CoroutineContext coroutineContext, MultiPointerDraggableNode multiPointerDraggableNode, Continuation continuation) {
        super(continuation);
        this.$currentContext = coroutineContext;
        this.this$0 = multiPointerDraggableNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MultiPointerDraggableNode$pointerTracker$3 multiPointerDraggableNode$pointerTracker$3 = new MultiPointerDraggableNode$pointerTracker$3(this.$currentContext, this.this$0, continuation);
        multiPointerDraggableNode$pointerTracker$3.L$0 = obj;
        return multiPointerDraggableNode$pointerTracker$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MultiPointerDraggableNode$pointerTracker$3) create((AwaitPointerEventScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0053 A[LOOP:0: B:6:0x0051->B:7:0x0053, LOOP_END] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x003e -> B:5:0x0041). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 419
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.MultiPointerDraggableNode$pointerTracker$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
