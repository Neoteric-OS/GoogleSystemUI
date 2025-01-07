package androidx.datastore.core;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DataStoreImpl$readDataOrHandleCorruption$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $preLockVersion;
    Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataStoreImpl$readDataOrHandleCorruption$2(DataStoreImpl dataStoreImpl, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
        this.$preLockVersion = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DataStoreImpl$readDataOrHandleCorruption$2 dataStoreImpl$readDataOrHandleCorruption$2 = new DataStoreImpl$readDataOrHandleCorruption$2(this.this$0, this.$preLockVersion, continuation);
        dataStoreImpl$readDataOrHandleCorruption$2.Z$0 = ((Boolean) obj).booleanValue();
        return dataStoreImpl$readDataOrHandleCorruption$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((DataStoreImpl$readDataOrHandleCorruption$2) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0058  */
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
            if (r1 == 0) goto L20
            if (r1 == r3) goto L1a
            if (r1 != r2) goto L12
            java.lang.Object r5 = r5.L$0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L48
        L12:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L1a:
            boolean r1 = r5.Z$0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L32
        L20:
            kotlin.ResultKt.throwOnFailure(r6)
            boolean r1 = r5.Z$0
            androidx.datastore.core.DataStoreImpl r6 = r5.this$0
            r5.Z$0 = r1
            r5.label = r3
            java.lang.Object r6 = r6.readDataFromFileOrDefault(r5)
            if (r6 != r0) goto L32
            return r0
        L32:
            if (r1 == 0) goto L4f
            androidx.datastore.core.DataStoreImpl r1 = r5.this$0
            androidx.datastore.core.SingleProcessCoordinator r1 = r1.getCoordinator()
            r5.L$0 = r6
            r5.label = r2
            java.lang.Object r5 = r1.getVersion()
            if (r5 != r0) goto L45
            return r0
        L45:
            r4 = r6
            r6 = r5
            r5 = r4
        L48:
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
            goto L54
        L4f:
            int r5 = r5.$preLockVersion
            r4 = r6
            r6 = r5
            r5 = r4
        L54:
            androidx.datastore.core.Data r0 = new androidx.datastore.core.Data
            if (r5 == 0) goto L5d
            int r1 = r5.hashCode()
            goto L5e
        L5d:
            r1 = 0
        L5e:
            r0.<init>(r1, r6, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
