package androidx.room;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MultiInstanceInvalidationClient$invalidationCallback$1$onInvalidation$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ String[] $tables;
    int label;
    final /* synthetic */ MultiInstanceInvalidationClient this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiInstanceInvalidationClient$invalidationCallback$1$onInvalidation$1(MultiInstanceInvalidationClient multiInstanceInvalidationClient, String[] strArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = multiInstanceInvalidationClient;
        this.$tables = strArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MultiInstanceInvalidationClient$invalidationCallback$1$onInvalidation$1(this.this$0, this.$tables, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        MultiInstanceInvalidationClient$invalidationCallback$1$onInvalidation$1 multiInstanceInvalidationClient$invalidationCallback$1$onInvalidation$1 = (MultiInstanceInvalidationClient$invalidationCallback$1$onInvalidation$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        multiInstanceInvalidationClient$invalidationCallback$1$onInvalidation$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        InvalidationTracker invalidationTracker = this.this$0.invalidationTracker;
        String[] strArr = this.$tables;
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
        invalidationTracker.getClass();
        Set of = SetsKt.setOf(Arrays.copyOf(strArr2, strArr2.length));
        InvalidationTracker$notifyObserversByTableNames$1 invalidationTracker$notifyObserversByTableNames$1 = InvalidationTracker$notifyObserversByTableNames$1.INSTANCE;
        TriggerBasedInvalidationTracker triggerBasedInvalidationTracker = invalidationTracker.implementation;
        ReentrantLock reentrantLock = triggerBasedInvalidationTracker.observerMapLock;
        reentrantLock.lock();
        try {
            for (ObserverWrapper observerWrapper : triggerBasedInvalidationTracker.observerMap.values()) {
                if (((Boolean) invalidationTracker$notifyObserversByTableNames$1.invoke(observerWrapper.observer)).booleanValue()) {
                    observerWrapper.notifyByTableNames$room_runtime_release(of);
                }
            }
            reentrantLock.unlock();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }
}
