package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MutexImpl$onSelectCancellationUnlockConstructor$1 extends Lambda implements Function3 {
    final /* synthetic */ MutexImpl this$0;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, final Object obj2, Object obj3) {
        final MutexImpl mutexImpl = this.this$0;
        return new Function1() { // from class: kotlinx.coroutines.sync.MutexImpl$onSelectCancellationUnlockConstructor$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj4) {
                MutexImpl.this.unlock(obj2);
                return Unit.INSTANCE;
            }
        };
    }
}
