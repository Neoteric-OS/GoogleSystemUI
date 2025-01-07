package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GeneratorSequence implements Sequence {
    public final Lambda getInitialValue;
    public final Function1 getNextValue;

    /* JADX WARN: Multi-variable type inference failed */
    public GeneratorSequence(Function0 function0, Function1 function1) {
        this.getInitialValue = (Lambda) function0;
        this.getNextValue = function1;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        return new GeneratorSequence$iterator$1(this);
    }
}
