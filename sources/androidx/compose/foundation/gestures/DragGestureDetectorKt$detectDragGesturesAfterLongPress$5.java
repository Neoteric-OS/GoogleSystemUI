package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DragGestureDetectorKt$detectDragGesturesAfterLongPress$5 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ Function2 $onDrag;
    final /* synthetic */ Function0 $onDragCancel;
    final /* synthetic */ Function0 $onDragEnd;
    final /* synthetic */ Function1 $onDragStart;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DragGestureDetectorKt$detectDragGesturesAfterLongPress$5(Continuation continuation, Function0 function0, Function0 function02, Function1 function1, Function2 function2) {
        super(continuation);
        this.$onDragStart = function1;
        this.$onDragEnd = function0;
        this.$onDragCancel = function02;
        this.$onDrag = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DragGestureDetectorKt$detectDragGesturesAfterLongPress$5 dragGestureDetectorKt$detectDragGesturesAfterLongPress$5 = new DragGestureDetectorKt$detectDragGesturesAfterLongPress$5(continuation, this.$onDragEnd, this.$onDragCancel, this.$onDragStart, this.$onDrag);
        dragGestureDetectorKt$detectDragGesturesAfterLongPress$5.L$0 = obj;
        return dragGestureDetectorKt$detectDragGesturesAfterLongPress$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DragGestureDetectorKt$detectDragGesturesAfterLongPress$5) create((AwaitPointerEventScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0082 A[Catch: CancellationException -> 0x0017, TryCatch #0 {CancellationException -> 0x0017, blocks: (B:8:0x0013, B:9:0x007a, B:11:0x0082, B:13:0x008f, B:15:0x009b, B:17:0x009e, B:20:0x00a1, B:24:0x00a7, B:28:0x0026, B:29:0x0055, B:31:0x0059, B:36:0x002e, B:37:0x0046, B:41:0x003a), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a7 A[Catch: CancellationException -> 0x0017, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x0017, blocks: (B:8:0x0013, B:9:0x007a, B:11:0x0082, B:13:0x008f, B:15:0x009b, B:17:0x009e, B:20:0x00a1, B:24:0x00a7, B:28:0x0026, B:29:0x0055, B:31:0x0059, B:36:0x002e, B:37:0x0046, B:41:0x003a), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0059 A[Catch: CancellationException -> 0x0017, TryCatch #0 {CancellationException -> 0x0017, blocks: (B:8:0x0013, B:9:0x007a, B:11:0x0082, B:13:0x008f, B:15:0x009b, B:17:0x009e, B:20:0x00a1, B:24:0x00a7, B:28:0x0026, B:29:0x0055, B:31:0x0059, B:36:0x002e, B:37:0x0046, B:41:0x003a), top: B:2:0x0007 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r7.label
            r2 = 1
            r3 = 2
            r4 = 3
            if (r1 == 0) goto L32
            if (r1 == r2) goto L2a
            if (r1 == r3) goto L22
            if (r1 != r4) goto L1a
            java.lang.Object r0 = r7.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.util.concurrent.CancellationException -> L17
            goto L7a
        L17:
            r8 = move-exception
            goto Laf
        L1a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L22:
            java.lang.Object r1 = r7.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.util.concurrent.CancellationException -> L17
            goto L55
        L2a:
            java.lang.Object r1 = r7.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.util.concurrent.CancellationException -> L17
            goto L46
        L32:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            r1 = r8
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
            r7.L$0 = r1     // Catch: java.util.concurrent.CancellationException -> L17
            r7.label = r2     // Catch: java.util.concurrent.CancellationException -> L17
            r8 = 0
            java.lang.Object r8 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r1, r8, r7, r3)     // Catch: java.util.concurrent.CancellationException -> L17
            if (r8 != r0) goto L46
            return r0
        L46:
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8     // Catch: java.util.concurrent.CancellationException -> L17
            long r5 = r8.id     // Catch: java.util.concurrent.CancellationException -> L17
            r7.L$0 = r1     // Catch: java.util.concurrent.CancellationException -> L17
            r7.label = r3     // Catch: java.util.concurrent.CancellationException -> L17
            java.lang.Object r8 = androidx.compose.foundation.gestures.DragGestureDetectorKt.m60awaitLongPressOrCancellationrnUCldI(r1, r5, r7)     // Catch: java.util.concurrent.CancellationException -> L17
            if (r8 != r0) goto L55
            return r0
        L55:
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8     // Catch: java.util.concurrent.CancellationException -> L17
            if (r8 == 0) goto Lac
            kotlin.jvm.functions.Function1 r2 = r7.$onDragStart     // Catch: java.util.concurrent.CancellationException -> L17
            long r5 = r8.position     // Catch: java.util.concurrent.CancellationException -> L17
            androidx.compose.ui.geometry.Offset r3 = new androidx.compose.ui.geometry.Offset     // Catch: java.util.concurrent.CancellationException -> L17
            r3.<init>(r5)     // Catch: java.util.concurrent.CancellationException -> L17
            r2.invoke(r3)     // Catch: java.util.concurrent.CancellationException -> L17
            long r2 = r8.id     // Catch: java.util.concurrent.CancellationException -> L17
            androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5$1 r8 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5$1     // Catch: java.util.concurrent.CancellationException -> L17
            kotlin.jvm.functions.Function2 r5 = r7.$onDrag     // Catch: java.util.concurrent.CancellationException -> L17
            r8.<init>()     // Catch: java.util.concurrent.CancellationException -> L17
            r7.L$0 = r1     // Catch: java.util.concurrent.CancellationException -> L17
            r7.label = r4     // Catch: java.util.concurrent.CancellationException -> L17
            java.lang.Object r8 = androidx.compose.foundation.gestures.DragGestureDetectorKt.m62dragjO51t88(r1, r2, r8, r7)     // Catch: java.util.concurrent.CancellationException -> L17
            if (r8 != r0) goto L79
            return r0
        L79:
            r0 = r1
        L7a:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.util.concurrent.CancellationException -> L17
            boolean r8 = r8.booleanValue()     // Catch: java.util.concurrent.CancellationException -> L17
            if (r8 == 0) goto La7
            androidx.compose.ui.input.pointer.PointerEvent r8 = r0.getCurrentEvent()     // Catch: java.util.concurrent.CancellationException -> L17
            java.util.List r8 = r8.changes     // Catch: java.util.concurrent.CancellationException -> L17
            int r0 = r8.size()     // Catch: java.util.concurrent.CancellationException -> L17
            r1 = 0
        L8d:
            if (r1 >= r0) goto La1
            java.lang.Object r2 = r8.get(r1)     // Catch: java.util.concurrent.CancellationException -> L17
            androidx.compose.ui.input.pointer.PointerInputChange r2 = (androidx.compose.ui.input.pointer.PointerInputChange) r2     // Catch: java.util.concurrent.CancellationException -> L17
            boolean r3 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUp(r2)     // Catch: java.util.concurrent.CancellationException -> L17
            if (r3 == 0) goto L9e
            r2.consume()     // Catch: java.util.concurrent.CancellationException -> L17
        L9e:
            int r1 = r1 + 1
            goto L8d
        La1:
            kotlin.jvm.functions.Function0 r8 = r7.$onDragEnd     // Catch: java.util.concurrent.CancellationException -> L17
            r8.invoke()     // Catch: java.util.concurrent.CancellationException -> L17
            goto Lac
        La7:
            kotlin.jvm.functions.Function0 r8 = r7.$onDragCancel     // Catch: java.util.concurrent.CancellationException -> L17
            r8.invoke()     // Catch: java.util.concurrent.CancellationException -> L17
        Lac:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        Laf:
            kotlin.jvm.functions.Function0 r7 = r7.$onDragCancel
            r7.invoke()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
