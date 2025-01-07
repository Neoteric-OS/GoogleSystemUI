package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface OverscrollEffect {
    /* renamed from: applyToFling-BMRW4eQ */
    Object mo18applyToFlingBMRW4eQ(long j, Function2 function2, Continuation continuation);

    /* renamed from: applyToScroll-Rhakbz0 */
    long mo19applyToScrollRhakbz0(long j, int i, Function1 function1);

    Modifier getEffectModifier();

    boolean isInProgress();
}
