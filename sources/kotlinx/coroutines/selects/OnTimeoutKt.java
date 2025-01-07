package kotlinx.coroutines.selects;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.selects.SelectImplementation.ClauseData;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class OnTimeoutKt {
    public static final void onTimeout(SelectImplementation selectImplementation, long j, Function1 function1) {
        OnTimeout onTimeout = new OnTimeout(j);
        OnTimeout$selectClause$1 onTimeout$selectClause$1 = OnTimeout$selectClause$1.INSTANCE;
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(3, onTimeout$selectClause$1);
        selectImplementation.register(selectImplementation.new ClauseData(onTimeout, onTimeout$selectClause$1, SelectKt.DUMMY_PROCESS_RESULT_FUNCTION, SelectKt.PARAM_CLAUSE_0, function1, null), false);
    }
}
