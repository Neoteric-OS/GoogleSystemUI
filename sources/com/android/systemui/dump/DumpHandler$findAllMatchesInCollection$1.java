package com.android.systemui.dump;

import java.util.Collection;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceBuilderIterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DumpHandler$findAllMatchesInCollection$1 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ Collection $dumpables;
    final /* synthetic */ Collection $logBuffers;
    final /* synthetic */ Collection $tableBuffers;
    final /* synthetic */ List $targets;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DumpHandler$findAllMatchesInCollection$1(Collection collection, Collection collection2, Collection collection3, List list, Continuation continuation) {
        super(continuation);
        this.$dumpables = collection;
        this.$logBuffers = collection2;
        this.$tableBuffers = collection3;
        this.$targets = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DumpHandler$findAllMatchesInCollection$1 dumpHandler$findAllMatchesInCollection$1 = new DumpHandler$findAllMatchesInCollection$1(this.$dumpables, this.$logBuffers, this.$tableBuffers, this.$targets, continuation);
        dumpHandler$findAllMatchesInCollection$1.L$0 = obj;
        return dumpHandler$findAllMatchesInCollection$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DumpHandler$findAllMatchesInCollection$1) create((SequenceBuilderIterator) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c8 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r9.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L2d
            if (r1 == r4) goto L25
            if (r1 == r3) goto L1c
            if (r1 != r2) goto L14
            kotlin.ResultKt.throwOnFailure(r10)
            goto Lc9
        L14:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L1c:
            java.lang.Object r1 = r9.L$0
            kotlin.sequences.SequenceBuilderIterator r1 = (kotlin.sequences.SequenceBuilderIterator) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L97
        L25:
            java.lang.Object r1 = r9.L$0
            kotlin.sequences.SequenceBuilderIterator r1 = (kotlin.sequences.SequenceBuilderIterator) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L66
        L2d:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            kotlin.sequences.SequenceBuilderIterator r10 = (kotlin.sequences.SequenceBuilderIterator) r10
            java.util.Collection r1 = r9.$dumpables
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.List r5 = r9.$targets
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r1 = r1.iterator()
        L43:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L5a
            java.lang.Object r7 = r1.next()
            r8 = r7
            com.android.systemui.dump.DumpsysEntry$DumpableEntry r8 = (com.android.systemui.dump.DumpsysEntry.DumpableEntry) r8
            boolean r8 = com.android.systemui.dump.DumpHandler.Companion.access$matchesAny(r8, r5)
            if (r8 == 0) goto L43
            r6.add(r7)
            goto L43
        L5a:
            r9.L$0 = r10
            r9.label = r4
            java.lang.Object r1 = r10.yieldAll(r6, r9)
            if (r1 != r0) goto L65
            return r0
        L65:
            r1 = r10
        L66:
            java.util.Collection r10 = r9.$logBuffers
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.List r4 = r9.$targets
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r10 = r10.iterator()
        L75:
            boolean r6 = r10.hasNext()
            if (r6 == 0) goto L8c
            java.lang.Object r6 = r10.next()
            r7 = r6
            com.android.systemui.dump.DumpsysEntry$LogBufferEntry r7 = (com.android.systemui.dump.DumpsysEntry.LogBufferEntry) r7
            boolean r7 = com.android.systemui.dump.DumpHandler.Companion.access$matchesAny(r7, r4)
            if (r7 == 0) goto L75
            r5.add(r6)
            goto L75
        L8c:
            r9.L$0 = r1
            r9.label = r3
            java.lang.Object r10 = r1.yieldAll(r5, r9)
            if (r10 != r0) goto L97
            return r0
        L97:
            java.util.Collection r10 = r9.$tableBuffers
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.List r3 = r9.$targets
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r10 = r10.iterator()
        La6:
            boolean r5 = r10.hasNext()
            if (r5 == 0) goto Lbd
            java.lang.Object r5 = r10.next()
            r6 = r5
            com.android.systemui.dump.DumpsysEntry$TableLogBufferEntry r6 = (com.android.systemui.dump.DumpsysEntry.TableLogBufferEntry) r6
            boolean r6 = com.android.systemui.dump.DumpHandler.Companion.access$matchesAny(r6, r3)
            if (r6 == 0) goto La6
            r4.add(r5)
            goto La6
        Lbd:
            r10 = 0
            r9.L$0 = r10
            r9.label = r2
            java.lang.Object r9 = r1.yieldAll(r4, r9)
            if (r9 != r0) goto Lc9
            return r0
        Lc9:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.dump.DumpHandler$findAllMatchesInCollection$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
