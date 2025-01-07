package com.android.systemui.dump;

import java.util.Collection;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceBuilderIterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DumpHandler$findTargetInCollection$1 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ Collection $dumpables;
    final /* synthetic */ Collection $logBuffers;
    final /* synthetic */ Collection $tableBuffers;
    final /* synthetic */ String $target;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DumpHandler$findTargetInCollection$1(Collection collection, String str, Collection collection2, Collection collection3, Continuation continuation) {
        super(continuation);
        this.$dumpables = collection;
        this.$target = str;
        this.$logBuffers = collection2;
        this.$tableBuffers = collection3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DumpHandler$findTargetInCollection$1 dumpHandler$findTargetInCollection$1 = new DumpHandler$findTargetInCollection$1(this.$dumpables, this.$target, this.$logBuffers, this.$tableBuffers, continuation);
        dumpHandler$findTargetInCollection$1.L$0 = obj;
        return dumpHandler$findTargetInCollection$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DumpHandler$findTargetInCollection$1) create((SequenceBuilderIterator) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0061  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L2b
            if (r1 == r4) goto L23
            if (r1 == r3) goto L1b
            if (r1 != r2) goto L13
            kotlin.ResultKt.throwOnFailure(r7)
            goto L6a
        L13:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L1b:
            java.lang.Object r1 = r6.L$0
            kotlin.sequences.SequenceBuilderIterator r1 = (kotlin.sequences.SequenceBuilderIterator) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L57
        L23:
            java.lang.Object r1 = r6.L$0
            kotlin.sequences.SequenceBuilderIterator r1 = (kotlin.sequences.SequenceBuilderIterator) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L45
        L2b:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            kotlin.sequences.SequenceBuilderIterator r7 = (kotlin.sequences.SequenceBuilderIterator) r7
            java.util.Collection r1 = r6.$dumpables
            java.lang.String r5 = r6.$target
            com.android.systemui.dump.DumpsysEntry r1 = com.android.systemui.dump.DumpHandler.Companion.access$findBestTargetMatch(r1, r5)
            if (r1 == 0) goto L44
            r6.L$0 = r7
            r6.label = r4
            r7.yield(r1, r6)
            return r0
        L44:
            r1 = r7
        L45:
            java.util.Collection r7 = r6.$logBuffers
            java.lang.String r4 = r6.$target
            com.android.systemui.dump.DumpsysEntry r7 = com.android.systemui.dump.DumpHandler.Companion.access$findBestTargetMatch(r7, r4)
            if (r7 == 0) goto L57
            r6.L$0 = r1
            r6.label = r3
            r1.yield(r7, r6)
            return r0
        L57:
            java.util.Collection r7 = r6.$tableBuffers
            java.lang.String r3 = r6.$target
            com.android.systemui.dump.DumpsysEntry r7 = com.android.systemui.dump.DumpHandler.Companion.access$findBestTargetMatch(r7, r3)
            if (r7 == 0) goto L6a
            r3 = 0
            r6.L$0 = r3
            r6.label = r2
            r1.yield(r7, r6)
            return r0
        L6a:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.dump.DumpHandler$findTargetInCollection$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
