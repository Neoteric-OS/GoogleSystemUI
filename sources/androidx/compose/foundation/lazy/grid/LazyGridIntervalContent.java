package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import androidx.compose.foundation.lazy.layout.MutableIntervalList;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyGridIntervalContent extends LazyLayoutIntervalContent implements LazyGridScope {
    public static final Function2 DefaultSpan = new Function2() { // from class: androidx.compose.foundation.lazy.grid.LazyGridIntervalContent$Companion$DefaultSpan$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((Number) obj2).intValue();
            return new GridItemSpan(LazyGridSpanKt.GridItemSpan(1));
        }
    };
    public boolean hasCustomSpans;
    public final LazyGridSpanLayoutProvider spanLayoutProvider = new LazyGridSpanLayoutProvider(this);
    public final MutableIntervalList intervals = new MutableIntervalList();

    public LazyGridIntervalContent(Function1 function1) {
        function1.invoke(this);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
    public final MutableIntervalList getIntervals$1() {
        return this.intervals;
    }

    public final void items(int i, Function1 function1, Function2 function2, Function1 function12, ComposableLambdaImpl composableLambdaImpl) {
        this.intervals.addInterval(i, new LazyGridInterval(function1, function2 == null ? DefaultSpan : function2, function12, composableLambdaImpl));
        if (function2 != null) {
            this.hasCustomSpans = true;
        }
    }
}
