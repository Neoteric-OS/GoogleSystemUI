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
final class DragGestureDetectorKt$detectHorizontalDragGestures$5 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ Function0 $onDragCancel;
    final /* synthetic */ Function0 $onDragEnd;
    final /* synthetic */ Function1 $onDragStart;
    final /* synthetic */ Function2 $onHorizontalDrag;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DragGestureDetectorKt$detectHorizontalDragGestures$5(Continuation continuation, Function0 function0, Function0 function02, Function1 function1, Function2 function2) {
        super(continuation);
        this.$onDragStart = function1;
        this.$onHorizontalDrag = function2;
        this.$onDragEnd = function0;
        this.$onDragCancel = function02;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DragGestureDetectorKt$detectHorizontalDragGestures$5 dragGestureDetectorKt$detectHorizontalDragGestures$5 = new DragGestureDetectorKt$detectHorizontalDragGestures$5(continuation, this.$onDragEnd, this.$onDragCancel, this.$onDragStart, this.$onHorizontalDrag);
        dragGestureDetectorKt$detectHorizontalDragGestures$5.L$0 = obj;
        return dragGestureDetectorKt$detectHorizontalDragGestures$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DragGestureDetectorKt$detectHorizontalDragGestures$5) create((AwaitPointerEventScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00a1  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r13.label
            r2 = 0
            r3 = 2
            r4 = 3
            r5 = 1
            if (r1 == 0) goto L31
            if (r1 == r5) goto L29
            if (r1 == r3) goto L1d
            if (r1 != r4) goto L15
            kotlin.ResultKt.throwOnFailure(r14)
            goto L99
        L15:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L1d:
            java.lang.Object r1 = r13.L$1
            kotlin.jvm.internal.Ref$FloatRef r1 = (kotlin.jvm.internal.Ref$FloatRef) r1
            java.lang.Object r3 = r13.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r3 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r3
            kotlin.ResultKt.throwOnFailure(r14)
            goto L67
        L29:
            java.lang.Object r1 = r13.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
            kotlin.ResultKt.throwOnFailure(r14)
            goto L46
        L31:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.Object r14 = r13.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r14 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r14
            r13.L$0 = r14
            r13.label = r5
            java.lang.Object r1 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r14, r2, r13, r3)
            if (r1 != r0) goto L43
            return r0
        L43:
            r12 = r1
            r1 = r14
            r14 = r12
        L46:
            androidx.compose.ui.input.pointer.PointerInputChange r14 = (androidx.compose.ui.input.pointer.PointerInputChange) r14
            kotlin.jvm.internal.Ref$FloatRef r11 = new kotlin.jvm.internal.Ref$FloatRef
            r11.<init>()
            long r6 = r14.id
            androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$drag$1 r9 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$drag$1
            r9.<init>()
            r13.L$0 = r1
            r13.L$1 = r11
            r13.label = r3
            int r8 = r14.type
            r5 = r1
            r10 = r13
            java.lang.Object r14 = androidx.compose.foundation.gestures.DragGestureDetectorKt.m58awaitHorizontalPointerSlopOrCancellationgDDlDlE(r5, r6, r8, r9, r10)
            if (r14 != r0) goto L65
            return r0
        L65:
            r3 = r1
            r1 = r11
        L67:
            androidx.compose.ui.input.pointer.PointerInputChange r14 = (androidx.compose.ui.input.pointer.PointerInputChange) r14
            if (r14 == 0) goto Lac
            kotlin.jvm.functions.Function1 r5 = r13.$onDragStart
            androidx.compose.ui.geometry.Offset r6 = new androidx.compose.ui.geometry.Offset
            long r7 = r14.position
            r6.<init>(r7)
            r5.invoke(r6)
            kotlin.jvm.functions.Function2 r5 = r13.$onHorizontalDrag
            float r1 = r1.element
            java.lang.Float r6 = new java.lang.Float
            r6.<init>(r1)
            r5.invoke(r14, r6)
            androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$1 r1 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$1
            kotlin.jvm.functions.Function2 r5 = r13.$onHorizontalDrag
            r1.<init>()
            r13.L$0 = r2
            r13.L$1 = r2
            r13.label = r4
            long r4 = r14.id
            java.lang.Object r14 = androidx.compose.foundation.gestures.DragGestureDetectorKt.m63horizontalDragjO51t88(r3, r4, r1, r13)
            if (r14 != r0) goto L99
            return r0
        L99:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto La7
            kotlin.jvm.functions.Function0 r13 = r13.$onDragEnd
            r13.invoke()
            goto Lac
        La7:
            kotlin.jvm.functions.Function0 r13 = r13.$onDragCancel
            r13.invoke()
        Lac:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
