package androidx.datastore.core;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$IntRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DataStoreImpl$writeData$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Object $newData;
    final /* synthetic */ Ref$IntRef $newVersion;
    final /* synthetic */ boolean $updateCache;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataStoreImpl$writeData$2(Ref$IntRef ref$IntRef, DataStoreImpl dataStoreImpl, Object obj, boolean z, Continuation continuation) {
        super(2, continuation);
        this.$newVersion = ref$IntRef;
        this.this$0 = dataStoreImpl;
        this.$newData = obj;
        this.$updateCache = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DataStoreImpl$writeData$2 dataStoreImpl$writeData$2 = new DataStoreImpl$writeData$2(this.$newVersion, this.this$0, this.$newData, this.$updateCache, continuation);
        dataStoreImpl$writeData$2.L$0 = obj;
        return dataStoreImpl$writeData$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DataStoreImpl$writeData$2) create((WriteScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0067  */
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
            if (r1 == r3) goto L18
            if (r1 != r2) goto L10
            kotlin.ResultKt.throwOnFailure(r6)
            goto L63
        L10:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L18:
            java.lang.Object r1 = r5.L$1
            kotlin.jvm.internal.Ref$IntRef r1 = (kotlin.jvm.internal.Ref$IntRef) r1
            java.lang.Object r3 = r5.L$0
            androidx.datastore.core.WriteScope r3 = (androidx.datastore.core.WriteScope) r3
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4b
        L24:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.Object r6 = r5.L$0
            androidx.datastore.core.WriteScope r6 = (androidx.datastore.core.WriteScope) r6
            kotlin.jvm.internal.Ref$IntRef r1 = r5.$newVersion
            androidx.datastore.core.DataStoreImpl r4 = r5.this$0
            androidx.datastore.core.SingleProcessCoordinator r4 = r4.getCoordinator()
            r5.L$0 = r6
            r5.L$1 = r1
            r5.label = r3
            androidx.datastore.core.AtomicInt r3 = r4.version
            java.util.concurrent.atomic.AtomicInteger r3 = r3.delegate
            int r3 = r3.incrementAndGet()
            java.lang.Integer r4 = new java.lang.Integer
            r4.<init>(r3)
            if (r4 != r0) goto L49
            return r0
        L49:
            r3 = r6
            r6 = r4
        L4b:
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
            r1.element = r6
            java.lang.Object r6 = r5.$newData
            r1 = 0
            r5.L$0 = r1
            r5.L$1 = r1
            r5.label = r2
            java.lang.Object r6 = r3.writeData(r6, r5)
            if (r6 != r0) goto L63
            return r0
        L63:
            boolean r6 = r5.$updateCache
            if (r6 == 0) goto L81
            androidx.datastore.core.DataStoreImpl r6 = r5.this$0
            androidx.datastore.core.DataStoreInMemoryCache r6 = r6.inMemoryCache
            androidx.datastore.core.Data r0 = new androidx.datastore.core.Data
            java.lang.Object r1 = r5.$newData
            if (r1 == 0) goto L76
            int r2 = r1.hashCode()
            goto L77
        L76:
            r2 = 0
        L77:
            kotlin.jvm.internal.Ref$IntRef r5 = r5.$newVersion
            int r5 = r5.element
            r0.<init>(r2, r5, r1)
            r6.tryUpdate(r0)
        L81:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl$writeData$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
