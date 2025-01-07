package kotlinx.coroutines.selects;

import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SelectKt {
    public static final Function3 DUMMY_PROCESS_RESULT_FUNCTION = new Function3() { // from class: kotlinx.coroutines.selects.SelectKt$DUMMY_PROCESS_RESULT_FUNCTION$1
        @Override // kotlin.jvm.functions.Function3
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return null;
        }
    };
    public static final Symbol STATE_REG = new Symbol("STATE_REG");
    public static final Symbol STATE_COMPLETED = new Symbol("STATE_COMPLETED");
    public static final Symbol STATE_CANCELLED = new Symbol("STATE_CANCELLED");
    public static final Symbol NO_RESULT = new Symbol("NO_RESULT");
    public static final Symbol PARAM_CLAUSE_0 = new Symbol("PARAM_CLAUSE_0");
}
