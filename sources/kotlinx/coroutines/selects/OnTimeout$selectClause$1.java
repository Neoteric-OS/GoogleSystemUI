package kotlinx.coroutines.selects;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.DelayKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class OnTimeout$selectClause$1 extends FunctionReferenceImpl implements Function3 {
    public static final OnTimeout$selectClause$1 INSTANCE = new OnTimeout$selectClause$1();

    public OnTimeout$selectClause$1() {
        super(3, OnTimeout.class, "register", "register(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        final OnTimeout onTimeout = (OnTimeout) obj;
        final SelectInstance selectInstance = (SelectInstance) obj2;
        long j = onTimeout.timeMillis;
        Unit unit = Unit.INSTANCE;
        if (j <= 0) {
            ((SelectImplementation) selectInstance).internalResult = unit;
        } else {
            Runnable runnable = new Runnable() { // from class: kotlinx.coroutines.selects.OnTimeout$register$$inlined$Runnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((SelectImplementation) SelectInstance.this).trySelectInternal(onTimeout, Unit.INSTANCE);
                }
            };
            SelectImplementation selectImplementation = (SelectImplementation) selectInstance;
            CoroutineContext coroutineContext = selectImplementation.context;
            selectImplementation.disposableHandleOrSegment = DelayKt.getDelay(coroutineContext).invokeOnTimeout(j, runnable, coroutineContext);
        }
        return unit;
    }
}
