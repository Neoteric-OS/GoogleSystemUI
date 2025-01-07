package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoOpOverscrollEffect implements OverscrollEffect {
    public static final NoOpOverscrollEffect INSTANCE = new NoOpOverscrollEffect();

    @Override // androidx.compose.foundation.OverscrollEffect
    /* renamed from: applyToFling-BMRW4eQ */
    public final Object mo18applyToFlingBMRW4eQ(long j, Function2 function2, Continuation continuation) {
        Object invoke = function2.invoke(new Velocity(j), continuation);
        return invoke == CoroutineSingletons.COROUTINE_SUSPENDED ? invoke : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    /* renamed from: applyToScroll-Rhakbz0 */
    public final long mo19applyToScrollRhakbz0(long j, int i, Function1 function1) {
        return ((Offset) function1.invoke(new Offset(j))).packedValue;
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public final Modifier getEffectModifier() {
        return Modifier.Companion.$$INSTANCE;
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public final boolean isInProgress() {
        return false;
    }
}
