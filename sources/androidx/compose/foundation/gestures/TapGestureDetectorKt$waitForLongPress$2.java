package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TapGestureDetectorKt$waitForLongPress$2 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ PointerEventPass $pass;
    final /* synthetic */ Ref$ObjectRef $result;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TapGestureDetectorKt$waitForLongPress$2(PointerEventPass pointerEventPass, Ref$ObjectRef ref$ObjectRef, Continuation continuation) {
        super(continuation);
        this.$pass = pointerEventPass;
        this.$result = ref$ObjectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        TapGestureDetectorKt$waitForLongPress$2 tapGestureDetectorKt$waitForLongPress$2 = new TapGestureDetectorKt$waitForLongPress$2(this.$pass, this.$result, continuation);
        tapGestureDetectorKt$waitForLongPress$2.L$0 = obj;
        return tapGestureDetectorKt$waitForLongPress$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((TapGestureDetectorKt$waitForLongPress$2) create((AwaitPointerEventScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x00b4, code lost:
    
        r14.$result.element = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005a, code lost:
    
        if (androidx.compose.foundation.gestures.TapGestureDetector_androidKt.isDeepPress(r15) == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005c, code lost:
    
        r14.$result.element = androidx.compose.foundation.gestures.LongPressResult.Success.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0064, code lost:
    
        r15 = r15.changes;
        r6 = r15.size();
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006b, code lost:
    
        if (r7 >= r6) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006d, code lost:
    
        r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r15.get(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0077, code lost:
    
        if (r8.isConsumed() != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0085, code lost:
    
        if (androidx.compose.ui.input.pointer.PointerEventKt.m460isOutOfBoundsjwHxaWs(r8, r1.mo456getSizeYbymL2g(), r1.mo455getExtendedTouchPaddingNHjbRc()) == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0088, code lost:
    
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x008b, code lost:
    
        r14.$result.element = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0090, code lost:
    
        r15 = androidx.compose.ui.input.pointer.PointerEventPass.Final;
        r14.L$0 = r1;
        r14.label = 2;
        r15 = r1.awaitPointerEvent(r15, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x009a, code lost:
    
        if (r15 != r0) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009c, code lost:
    
        return r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c2 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x009a -> B:6:0x009d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r14.label
            androidx.compose.foundation.gestures.LongPressResult$Canceled r2 = androidx.compose.foundation.gestures.LongPressResult.Canceled.INSTANCE
            r3 = 1
            r4 = 2
            r5 = 0
            if (r1 == 0) goto L28
            if (r1 == r3) goto L20
            if (r1 != r4) goto L18
            java.lang.Object r1 = r14.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
            kotlin.ResultKt.throwOnFailure(r15)
            goto L9d
        L18:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L20:
            java.lang.Object r1 = r14.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
            kotlin.ResultKt.throwOnFailure(r15)
            goto L3f
        L28:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r15 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r15
        L2f:
            androidx.compose.ui.input.pointer.PointerEventPass r1 = r14.$pass
            r14.L$0 = r15
            r14.label = r3
            java.lang.Object r1 = r15.awaitPointerEvent(r1, r14)
            if (r1 != r0) goto L3c
            return r0
        L3c:
            r13 = r1
            r1 = r15
            r15 = r13
        L3f:
            androidx.compose.ui.input.pointer.PointerEvent r15 = (androidx.compose.ui.input.pointer.PointerEvent) r15
            java.util.List r6 = r15.changes
            int r7 = r6.size()
            r8 = r5
        L48:
            if (r8 >= r7) goto Lc2
            java.lang.Object r9 = r6.get(r8)
            androidx.compose.ui.input.pointer.PointerInputChange r9 = (androidx.compose.ui.input.pointer.PointerInputChange) r9
            boolean r9 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUp(r9)
            if (r9 != 0) goto Lbf
            boolean r6 = androidx.compose.foundation.gestures.TapGestureDetector_androidKt.isDeepPress(r15)
            if (r6 == 0) goto L64
            kotlin.jvm.internal.Ref$ObjectRef r14 = r14.$result
            androidx.compose.foundation.gestures.LongPressResult$Success r15 = androidx.compose.foundation.gestures.LongPressResult.Success.INSTANCE
            r14.element = r15
            goto Ld3
        L64:
            java.util.List r15 = r15.changes
            int r6 = r15.size()
            r7 = r5
        L6b:
            if (r7 >= r6) goto L90
            java.lang.Object r8 = r15.get(r7)
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
            boolean r9 = r8.isConsumed()
            if (r9 != 0) goto L8b
            long r9 = r1.mo456getSizeYbymL2g()
            long r11 = r1.mo455getExtendedTouchPaddingNHjbRc()
            boolean r8 = androidx.compose.ui.input.pointer.PointerEventKt.m460isOutOfBoundsjwHxaWs(r8, r9, r11)
            if (r8 == 0) goto L88
            goto L8b
        L88:
            int r7 = r7 + 1
            goto L6b
        L8b:
            kotlin.jvm.internal.Ref$ObjectRef r14 = r14.$result
            r14.element = r2
            goto Ld3
        L90:
            androidx.compose.ui.input.pointer.PointerEventPass r15 = androidx.compose.ui.input.pointer.PointerEventPass.Final
            r14.L$0 = r1
            r14.label = r4
            java.lang.Object r15 = r1.awaitPointerEvent(r15, r14)
            if (r15 != r0) goto L9d
            return r0
        L9d:
            androidx.compose.ui.input.pointer.PointerEvent r15 = (androidx.compose.ui.input.pointer.PointerEvent) r15
            java.util.List r15 = r15.changes
            int r6 = r15.size()
            r7 = r5
        La6:
            if (r7 >= r6) goto Lbc
            java.lang.Object r8 = r15.get(r7)
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
            boolean r8 = r8.isConsumed()
            if (r8 == 0) goto Lb9
            kotlin.jvm.internal.Ref$ObjectRef r14 = r14.$result
            r14.element = r2
            goto Ld3
        Lb9:
            int r7 = r7 + 1
            goto La6
        Lbc:
            r15 = r1
            goto L2f
        Lbf:
            int r8 = r8 + 1
            goto L48
        Lc2:
            kotlin.jvm.internal.Ref$ObjectRef r14 = r14.$result
            androidx.compose.foundation.gestures.LongPressResult$Released r0 = new androidx.compose.foundation.gestures.LongPressResult$Released
            java.util.List r15 = r15.changes
            java.lang.Object r15 = r15.get(r5)
            androidx.compose.ui.input.pointer.PointerInputChange r15 = (androidx.compose.ui.input.pointer.PointerInputChange) r15
            r0.<init>(r15)
            r14.element = r0
        Ld3:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForLongPress$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
