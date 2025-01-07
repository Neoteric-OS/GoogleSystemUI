package com.android.systemui.communal.ui.compose;

import androidx.compose.foundation.lazy.grid.LazyGridState;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DragAndDropTargetStateKt$rememberDragAndDropTargetState$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ LazyGridState $gridState;
    final /* synthetic */ DragAndDropTargetState $state;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DragAndDropTargetStateKt$rememberDragAndDropTargetState$1(DragAndDropTargetState dragAndDropTargetState, LazyGridState lazyGridState, Continuation continuation) {
        super(2, continuation);
        this.$state = dragAndDropTargetState;
        this.$gridState = lazyGridState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DragAndDropTargetStateKt$rememberDragAndDropTargetState$1(this.$state, this.$gridState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DragAndDropTargetStateKt$rememberDragAndDropTargetState$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x005a -> B:6:0x0033). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r5.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L24
            if (r1 == r3) goto L1c
            if (r1 != r2) goto L14
            java.lang.Object r1 = r5.L$0
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = (kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L33
        L14:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L1c:
            java.lang.Object r1 = r5.L$0
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = (kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L3e
        L24:
            kotlin.ResultKt.throwOnFailure(r6)
            com.android.systemui.communal.ui.compose.DragAndDropTargetState r6 = r5.$state
            kotlinx.coroutines.channels.BufferedChannel r6 = r6.scrollChannel
            r6.getClass()
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r1 = new kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator
            r1.<init>()
        L33:
            r5.L$0 = r1
            r5.label = r3
            java.lang.Object r6 = r1.hasNext(r5)
            if (r6 != r0) goto L3e
            return r0
        L3e:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L5d
            java.lang.Object r6 = r1.next()
            java.lang.Number r6 = (java.lang.Number) r6
            float r6 = r6.floatValue()
            androidx.compose.foundation.lazy.grid.LazyGridState r4 = r5.$gridState
            r5.L$0 = r1
            r5.label = r2
            java.lang.Object r6 = androidx.compose.foundation.gestures.ScrollExtensionsKt.scrollBy(r4, r6, r5)
            if (r6 != r0) goto L33
            return r0
        L5d:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.ui.compose.DragAndDropTargetStateKt$rememberDragAndDropTargetState$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
