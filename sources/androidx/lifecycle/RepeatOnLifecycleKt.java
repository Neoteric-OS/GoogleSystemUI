package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RepeatOnLifecycleKt {
    public static final Object repeatOnLifecycle(Lifecycle lifecycle, Lifecycle.State state, Function2 function2, Continuation continuation) {
        Object coroutineScope;
        if (state == Lifecycle.State.INITIALIZED) {
            throw new IllegalArgumentException("repeatOnLifecycle cannot start work with the INITIALIZED lifecycle state.");
        }
        Lifecycle.State state2 = ((LifecycleRegistry) lifecycle).state;
        Lifecycle.State state3 = Lifecycle.State.DESTROYED;
        Unit unit = Unit.INSTANCE;
        return (state2 != state3 && (coroutineScope = CoroutineScopeKt.coroutineScope(continuation, new RepeatOnLifecycleKt$repeatOnLifecycle$3(lifecycle, state, function2, null))) == CoroutineSingletons.COROUTINE_SUSPENDED) ? coroutineScope : unit;
    }

    public static final Object repeatOnLifecycle(LifecycleOwner lifecycleOwner, Lifecycle.State state, Function2 function2, Continuation continuation) {
        Object repeatOnLifecycle = repeatOnLifecycle(lifecycleOwner.getLifecycle(), state, function2, continuation);
        return repeatOnLifecycle == CoroutineSingletons.COROUTINE_SUSPENDED ? repeatOnLifecycle : Unit.INSTANCE;
    }
}
