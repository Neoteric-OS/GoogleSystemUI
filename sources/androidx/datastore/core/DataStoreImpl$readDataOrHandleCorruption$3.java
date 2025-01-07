package androidx.datastore.core;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DataStoreImpl$readDataOrHandleCorruption$3 extends SuspendLambda implements Function1 {
    final /* synthetic */ Ref$ObjectRef $newData;
    final /* synthetic */ Ref$IntRef $version;
    Object L$0;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataStoreImpl$readDataOrHandleCorruption$3(Ref$ObjectRef ref$ObjectRef, DataStoreImpl dataStoreImpl, Ref$IntRef ref$IntRef, Continuation continuation) {
        super(1, continuation);
        this.$newData = ref$ObjectRef;
        this.this$0 = dataStoreImpl;
        this.$version = ref$IntRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new DataStoreImpl$readDataOrHandleCorruption$3(this.$newData, this.this$0, this.$version, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref$IntRef ref$IntRef;
        Ref$ObjectRef ref$ObjectRef;
        Ref$IntRef ref$IntRef2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
        } catch (CorruptionException unused) {
            Ref$IntRef ref$IntRef3 = this.$version;
            DataStoreImpl dataStoreImpl = this.this$0;
            Object obj2 = this.$newData.element;
            this.L$0 = ref$IntRef3;
            this.label = 3;
            Object writeData$datastore_core_release = dataStoreImpl.writeData$datastore_core_release(obj2, true, this);
            if (writeData$datastore_core_release == coroutineSingletons) {
                return coroutineSingletons;
            }
            obj = writeData$datastore_core_release;
            ref$IntRef = ref$IntRef3;
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ref$ObjectRef = this.$newData;
            DataStoreImpl dataStoreImpl2 = this.this$0;
            this.L$0 = ref$ObjectRef;
            this.label = 1;
            obj = dataStoreImpl2.readDataFromFileOrDefault(this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ref$IntRef2 = (Ref$IntRef) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    ref$IntRef2.element = ((Number) obj).intValue();
                    return Unit.INSTANCE;
                }
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ref$IntRef = (Ref$IntRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                ref$IntRef.element = ((Number) obj).intValue();
                return Unit.INSTANCE;
            }
            ref$ObjectRef = (Ref$ObjectRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        ref$ObjectRef.element = obj;
        ref$IntRef2 = this.$version;
        SingleProcessCoordinator coordinator = this.this$0.getCoordinator();
        this.L$0 = ref$IntRef2;
        this.label = 2;
        obj = coordinator.getVersion();
        if (obj == coroutineSingletons) {
            return coroutineSingletons;
        }
        ref$IntRef2.element = ((Number) obj).intValue();
        return Unit.INSTANCE;
    }
}
