package androidx.datastore.core;

import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DataStoreImpl$readDataAndUpdateCache$4 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $cachedVersion;
    Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataStoreImpl$readDataAndUpdateCache$4(DataStoreImpl dataStoreImpl, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
        this.$cachedVersion = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DataStoreImpl$readDataAndUpdateCache$4 dataStoreImpl$readDataAndUpdateCache$4 = new DataStoreImpl$readDataAndUpdateCache$4(this.this$0, this.$cachedVersion, continuation);
        dataStoreImpl$readDataAndUpdateCache$4.Z$0 = ((Boolean) obj).booleanValue();
        return dataStoreImpl$readDataAndUpdateCache$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((DataStoreImpl$readDataAndUpdateCache$4) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v6 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        Throwable th;
        boolean z;
        State state;
        boolean z2;
        boolean z3;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        boolean z4 = this.label;
        try {
        } catch (Throwable th2) {
            if (z4 != 0) {
                SingleProcessCoordinator coordinator = this.this$0.getCoordinator();
                this.L$0 = th2;
                this.Z$0 = z4;
                this.label = 2;
                Object version = coordinator.getVersion();
                if (version == coroutineSingletons) {
                    return coroutineSingletons;
                }
                z = z4 ? 1 : 0;
                obj = version;
                th = th2;
            } else {
                i = this.$cachedVersion;
                th = th2;
                z3 = z4;
            }
        }
        if (z4 == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z5 = this.Z$0;
            DataStoreImpl dataStoreImpl = this.this$0;
            this.Z$0 = z5;
            this.label = 1;
            obj = DataStoreImpl.access$readDataOrHandleCorruption(dataStoreImpl, z5, this);
            z4 = z5;
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (z4 != 1) {
                if (z4 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                z = this.Z$0;
                th = (Throwable) this.L$0;
                ResultKt.throwOnFailure(obj);
                i = ((Number) obj).intValue();
                z3 = z;
                state = new ReadException(th, i);
                z2 = z3;
                return new Pair(state, Boolean.valueOf(z2));
            }
            boolean z6 = this.Z$0;
            ResultKt.throwOnFailure(obj);
            z4 = z6;
        }
        state = (State) obj;
        z2 = z4;
        return new Pair(state, Boolean.valueOf(z2));
    }
}
