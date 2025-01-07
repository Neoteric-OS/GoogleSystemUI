package kotlinx.coroutines.selects;

import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.channels.BufferedChannel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SelectClause1Impl {
    public final BufferedChannel clauseObject;
    public final Function3 onCancellationConstructor;
    public final Function3 processResFunc;
    public final Function3 regFunc;

    public SelectClause1Impl(BufferedChannel bufferedChannel, Function3 function3, Function3 function32, Function3 function33) {
        this.clauseObject = bufferedChannel;
        this.regFunc = function3;
        this.processResFunc = function32;
        this.onCancellationConstructor = function33;
    }
}
