package androidx.compose.runtime;

import androidx.collection.MutableScatterSet;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class Recomposer$writeObserverOf$1 extends Lambda implements Function1 {
    final /* synthetic */ ControlledComposition $composition;
    final /* synthetic */ MutableScatterSet $modifiedValues;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Recomposer$writeObserverOf$1(MutableScatterSet mutableScatterSet, ControlledComposition controlledComposition) {
        super(1);
        this.$composition = controlledComposition;
        this.$modifiedValues = mutableScatterSet;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ((CompositionImpl) this.$composition).recordWriteOf(obj);
        MutableScatterSet mutableScatterSet = this.$modifiedValues;
        if (mutableScatterSet != null) {
            mutableScatterSet.add(obj);
        }
        return Unit.INSTANCE;
    }
}
