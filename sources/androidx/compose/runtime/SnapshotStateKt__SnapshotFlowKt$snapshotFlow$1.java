package androidx.compose.runtime;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function0 $block;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1(Function0 function0, Continuation continuation) {
        super(2, continuation);
        this.$block = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1 snapshotStateKt__SnapshotFlowKt$snapshotFlow$1 = new SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1(this.$block, continuation);
        snapshotStateKt__SnapshotFlowKt$snapshotFlow$1.L$0 = obj;
        return snapshotStateKt__SnapshotFlowKt$snapshotFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00de A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00ea A[Catch: all -> 0x0059, TryCatch #3 {all -> 0x0059, blocks: (B:15:0x00e6, B:17:0x00ea, B:20:0x00f3, B:22:0x0108, B:24:0x0114, B:26:0x011e, B:29:0x0133, B:38:0x0147, B:43:0x0150, B:46:0x015a, B:51:0x0161, B:57:0x0179, B:59:0x0182, B:71:0x01a9, B:72:0x01ac, B:87:0x0051, B:53:0x016e, B:56:0x0176, B:67:0x01a4, B:68:0x01a7, B:55:0x0172), top: B:86:0x0051, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01b4 A[LOOP:0: B:16:0x00e8->B:48:0x01b4, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x015f A[EDGE_INSN: B:49:0x015f->B:50:0x015f BREAK  A[LOOP:0: B:16:0x00e8->B:48:0x01b4], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0161 A[Catch: all -> 0x0059, TRY_LEAVE, TryCatch #3 {all -> 0x0059, blocks: (B:15:0x00e6, B:17:0x00ea, B:20:0x00f3, B:22:0x0108, B:24:0x0114, B:26:0x011e, B:29:0x0133, B:38:0x0147, B:43:0x0150, B:46:0x015a, B:51:0x0161, B:57:0x0179, B:59:0x0182, B:71:0x01a9, B:72:0x01ac, B:87:0x0051, B:53:0x016e, B:56:0x0176, B:67:0x01a4, B:68:0x01a7, B:55:0x0172), top: B:86:0x0051, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0159  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r22) {
        /*
            Method dump skipped, instructions count: 458
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
