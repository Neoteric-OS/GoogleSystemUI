package kotlin;

import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class LazyKt__LazyJVMKt {
    public static Lazy lazy(Function0 function0) {
        return new SynchronizedLazyImpl(function0);
    }

    public static Lazy lazy(LazyThreadSafetyMode lazyThreadSafetyMode, Function0 function0) {
        int ordinal = lazyThreadSafetyMode.ordinal();
        if (ordinal == 0) {
            return new SynchronizedLazyImpl(function0);
        }
        if (ordinal == 1) {
            return new SafePublicationLazyImpl(function0);
        }
        if (ordinal == 2) {
            return new UnsafeLazyImpl(function0);
        }
        throw new NoWhenBranchMatchedException();
    }
}
